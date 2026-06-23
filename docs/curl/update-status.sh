# Подтвердить заказ
curl -X PUT "http://localhost:8080/api/orders/1/status?status=CONFIRMED" \
  -H "Content-Type: application/json"

# Отправить заказ
curl -X PUT "http://localhost:8080/api/orders/1/status?status=SHIPPED" \
  -H "Content-Type: application/json"

# Доставить заказ
curl -X PUT "http://localhost:8080/api/orders/1/status?status=DELIVERED" \
  -H "Content-Type: application/json"

# Отменить заказ
curl -X PUT "http://localhost:8080/api/orders/2/status?status=CANCELLED" \
  -H "Content-Type: application/json"
