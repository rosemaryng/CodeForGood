const User = require('./models/User')

function initialise(app, users) {

  // Health check
  app.get('/_health', (req, res) => res.send('OK'))

  // Get all users
  app.get('/users', (req, res) => {
    return res.json({ users })
  })

  // On register new user
  app.post('/user', (req, res) => {
    const user = req.body
    console.log(user)
    const parsedUser = User.fromJSON(user)
    if (!parsedUser)
      return res.status(400).json({ message: 'Bad details'})
    
    console.log(parsedUser)
    return res.send('OK')
  })
  
}

module.exports = {
  initialise
}