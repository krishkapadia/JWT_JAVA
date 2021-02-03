const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/DemoDb_1', { useNewUrlParser: true, useUnifiedTopology: true });
const db = mongoose.connection;

db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function () {
    console.log("Databse Connected..");
});

module.exports = {db,mongoose};