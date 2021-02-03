const express = require('express');
const app = express();
const port = 8080;
const { db, mongoose } = require('./databse');
const { tblCategory, tblProduct } = require('./model');

app.set("view engine", "ejs");
app.set("views", "./views");

app.use(express.urlencoded({ extended: true }));

app.get("/", (req, res) => {
    tblCategory.find((err, data) => {
        //console.log(data);
        res.render("index", { data: data });
    })
});

app.post("/", (req, res) => {
    //console.log(req.body);
    var tmp = new tblCategory(req.body);
    // var tmp = new tblCategory({
    //     CategoryName: req.body.CategoryName
    // });
    tmp.save((err, data) => {
        if (!err) {
            console.log("Inserted : ", data);
        }
    })
    res.redirect("/");
})

app.get("/delete", (req, res) => {
    tblCategory.findByIdAndRemove(req.query.Cid, (err, data) => {
        console.log("Record Deleted..", data);
        res.redirect("/");
    })
})

app.get("/updateRedirect", (req, res) => {
    tblCategory.findById(req.query.Cid, (err, data) => {
        res.render("update", { data: data });
    })
});

app.post("/update", (req, res) => {
    tblCategory.findByIdAndUpdate(req.query.Cid,req.body,(err,data)=>{
        console.log("Record Updated..", data);
        res.redirect("/");
    });
});

app.listen(port, () => {
    console.log("Server is runnig on port : ", port);
})
