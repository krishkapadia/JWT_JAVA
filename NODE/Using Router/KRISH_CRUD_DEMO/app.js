const express = require('express');
const app = express();
const port = 8080;
const router = require("./router");

app.set("view engine", "ejs");
app.set("views", "./views");

app.use(express.urlencoded({ extended: true }));
app.use(router);

app.listen(port, () => {
    console.log("Server is runnig on port : ", port);
})
