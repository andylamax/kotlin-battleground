module.exports = {
    sayHello: function(user) {
        var str = "Hello " + user.name;
        console.log(str);
        console.log(user)
        return str
    }
}