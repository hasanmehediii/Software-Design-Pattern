### Run these commands in your terminal to see the Singleton pattern in action:

```bash
python -m venv venv
source venv/bin/activate  # On Windows, use `venv\Scripts\activate
pip install -r requirements.txt
uvicorn main:app --reload
```
# In another terminal, make multiple requests to the /items endpoint
1. curl http://localhost:8000/items
2. curl http://localhost:8000/items
3. curl http://localhost:8000/items

### Test Singleton Behavior
```
python -c "
from database import Database

db1 = Database()
db2 = Database()

print('Same instance?', db1 is db2)
print('Same ID?', id(db1) == id(db2))
"
``` 

### You should see the following output in your server logs, confirming that only one database instance is created:

```✅ New DB instance created
♻️  Returning existing DB instance
Same instance? True
Same ID? True
```