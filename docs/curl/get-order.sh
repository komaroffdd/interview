# Получить конкретный заказ
curl -X GET http://localhost:8080/api/orders/$1 | jq '.'
