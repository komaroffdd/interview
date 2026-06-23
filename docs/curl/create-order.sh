# Базовый заказ с одним товаром
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 1,
    "items": [
      {
        "productName": "iPhone 15",
        "quantity": 1,
        "price": 999.99
      }
    ]
  }'
