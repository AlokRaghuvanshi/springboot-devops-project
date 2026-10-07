#!/bin/bash
set -euo pipefail

echo "Starting deployment..."

export MYSQL_ROOT_PASSWORD=$(
  aws secretsmanager get-secret-value \
    --secret-id devops/mysql/root-password \
    --region ap-south-1 \
    --query SecretString \
    --output text |
  python3 -c 'import sys,json; print(json.load(sys.stdin)["MYSQL_ROOT_PASSWORD"])'
)

docker compose -f compose.deploy.yml pull

docker compose -f compose.deploy.yml up -d

unset MYSQL_ROOT_PASSWORD

echo "Deployment completed."