param (
    [Parameter(Mandatory=$true)]
    [string]$Version
)

$ErrorActionPreference = "Stop"

Write-Host "=== Build Maven ==="

./mvnw clean package

Write-Host "=== Build Docker $Version ==="

docker build `
    -t "poc-kubernetes:$Version" `
    .

Write-Host "=== Tag ==="

docker tag `
    "poc-kubernetes:$Version" `
    "localhost:5000/poc-kubernetes:$Version"

Write-Host "=== Push ==="

docker push "localhost:5000/poc-kubernetes:$Version"

Write-Host "=== Helm deployment DEV ==="

helm upgrade --install poc-dev `
    ./helm/poc-kubernetes `
    -n poc-helm-dev `
    -f ./helm/poc-kubernetes/values-dev.yaml `
    --set app.image.tag="$Version"

Write-Host "=== Waiting for rollout ==="

kubectl rollout status `
    deployment/poc-kubernetes `
    -n poc-helm-dev

Write-Host "=== DONE ==="