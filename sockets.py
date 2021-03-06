import asyncio
import websockets
import json


async def hello():
    uri = "ws://localhost:3000"
    async with websockets.connect(uri) as websocket:
        #name = input("What's your name? ")

        await websocket.send(json.dumps({'event':'connected'}))
        #print(f"> {name}")

        greeting = await websocket.recv()
        print(f"< {greeting}")

asyncio.get_event_loop().run_until_complete(hello())