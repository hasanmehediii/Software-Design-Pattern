from contextlib import asynccontextmanager
from fastapi import FastAPI
from database import Database

@asynccontextmanager
async def lifespan(app: FastAPI):
    Database()
    print("🚀 App started, DB connected")
    
    yield

    Database._client.close()
    print("🛑 App stopped, DB connection closed")

app = FastAPI(lifespan=lifespan)