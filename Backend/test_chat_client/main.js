console.log('Initialised!')

// Connect
const socketConnection = io.connect('http://localhost:8080')

let socketId = null
socketConnection.on('connect', () => {
  socketId = socketConnection.id
})

// DOM elements
const chatWindow = document.getElementById('window')
const nameBox = document.getElementById('nameBox')
const nameBtn = document.getElementById('nameBtn')
const messageBox = document.getElementById('message')
const sendBtn = document.getElementById('sendBtn')

nameBox.addEventListener('keyup', event => {
  if (event.keyCode === 13) {
    event.preventDefault()
    nameBtn.click()
  }
})

messageBox.addEventListener('keyup', event => {
  if (event.keyCode === 13) {
    event.preventDefault()
    sendBtn.click()
  }
})

nameBtn.addEventListener('click', event => {
  event.preventDefault()
  const newName = nameBox.value
  socketConnection.emit('updateName', {
    name: newName
  })
})

sendBtn.addEventListener('click', event => {
  event.preventDefault()
  const messageToSend = messageBox.value
  console.log(`Sending ${messageToSend}`)

  socketConnection.emit('sendMsg', {
    to: 'FooBar',
    message: messageToSend
  })

  chatWindow.appendChild(createSent(messageToSend))
  messageBox.value = ''
})

socketConnection.on('response', ({ from, message }) => {
  chatWindow.appendChild(createReceived(from, message))
})

function createSent(message) {
  const div = document.createElement('div')
  div.classList.add('bubble')
  div.classList.add('self')
  div.innerHTML = message
  return div
}

function createReceived(from, message) {
  const div = document.createElement('div')
  div.classList.add('bubble')
  div.classList.add('other')
  div.innerHTML = `<strong>${from}</strong><br />${message}`
  return div
}