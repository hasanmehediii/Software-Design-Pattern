# routes/items.py
from fastapi import APIRouter
from database import get_database

router = APIRouter()

@router.get("/items")
async def get_items():
    db = get_database()
    items = await db["items"].find().to_list(100)
    return items