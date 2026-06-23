# Отменить заказ через DELETE
curl -X DELETE http://localhost:8080/api/orders/3 \
  -H "Content-Type: application/json"

# Проверить что заказ отменился
curl -X GET http://localhost:8080/api/orders/3 | jq '.status'
