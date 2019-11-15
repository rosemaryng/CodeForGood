// Application setup 
const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const server = require('http').Server(app)
const io = require('socket.io')(server)

// Middleware
app.use(bodyParser.json())

// Locals
const WebSockets = require('./src/WebSockets')
const ApiGateway = require('./src/ApiGateway')

// Constants
const API_PORT = 3000
const WS_PORT = 8080

// Global state
const users = {}

// Initialisations
WebSockets.initialise(io, users)
ApiGateway.initialise(app, users)

server.listen(WS_PORT, () => console.log(`WebSockets listening on port ${WS_PORT}`))
app.listen(API_PORT, () => console.log(`APIs listening on port ${API_PORT}`))