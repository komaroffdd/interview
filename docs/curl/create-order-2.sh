curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 1,
    "items": [
      {
        "productName": "MacBook Pro",
        "quantity": 1,
        "price": 1999.99
      },
      {
        "productName": "AirPods Pro",
        "quantity": 2,
        "price": 249.99
      },
      {
        "productName": "USB-C Cable",
        "quantity": 3,
        "price": 19.99
      }
    ]
  }'
