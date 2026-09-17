kubectl port-forward `
  -n monitoring `
  service/monitoring-kube-prometheus-prometheus `
  9090:9090