function initialise(io, users) {
  const mainRoom = io
    .on('connection', socket => {
      console.log('Socket connected', socket.id) 
      
      users[socket.id] = {}

      // update name
      socket.on('updateName', ({ name }) => {
        users[socket.id].name = name
      })

      socket.on('sendMsg', ({ to, message}) => {
        console.log(`From ${socket.id} received message ${message}`)

        for (let [userId, _] of Object.entries(users)) {
          if (userId === socket.id)
            continue

          console.log(`Sending to ${userId}`)
          io.to(userId).emit('response', {
            from: 'TODO name',
            message: message
          })
        }
      })

      socket.on('disconnect', () => {
        console.log('Socket disconnected')
        delete users[socket.id]
      })
    })
}

module.exports = {
  initialise 
}