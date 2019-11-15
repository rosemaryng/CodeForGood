class User {

  constructor(name, age, cancerBefore, interests, relateToCancer) {
    this.name = name
    this.age = age
    this.cancerBefore = cancerBefore
    this.interests = interests
    this.relateToCancer = relateToCancer
  }

  setSocketId(socketId) {
    this.socketId = socketId
  }

}

function fromJSON(user) {
  console.log(user)
  const { name, age, cancerBefore, interests, relateToCancer } = user
  if (!name || !age || !cancerBefore || !interests || !relateToCancer)
    return false;

  return new User(name, age, cancerBefore, interests, relateToCancer)
}

module.exports = {
  User, fromJSON
}