
# Running the project

## API Endpoints

### Recommend Psychologists

```bash
curl -X POST http://localhost:8080/psychologists/recommend \
  -H "Content-Type: application/json" \
  -d '{"patientId": "patient-123"}'
```
