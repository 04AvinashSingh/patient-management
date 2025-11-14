#!/bin/bash
export MSYS_NO_PATHCONV=1
export MSYS2_ARG_CONV_EXCL="*"
set -e

aws --endpoint-url=http://localhost:4566 cloudformation delete-stack \
    --stack-name patient-management

aws --endpoint-url=http://localhost:4566 cloudformation deploy \
    --stack-name patient-management \
    --template-file "./cdk.out/localstack.template.json"

aws --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers \
    --query "LoadBalancers[0].DNSName" --output text
