from motor.motor_asyncio import AsyncIOMotorClient # type: ignore
from dotenv import load_dotenv
import os

load_dotenv()

class Database:
    _instance = None
    _client = None
    _db = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(Database, cls).__new__(cls)
            cls._client = AsyncIOMotorClient(os.getenv("MONGO_URI"))
            cls._db = cls._client[os.getenv("DB_NAME")]
            print("✅ New DB instance created")
        else:
            print("♻️  Returning existing DB instance")
        return cls._instance

    def get_db(self):
        return self._db


def get_database():
    return Database().get_db()