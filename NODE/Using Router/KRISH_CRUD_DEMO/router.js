var express = require('express');
var router = express.Router();
const { db, mongoose } = require('./databse');
const { tblCategory, tblProduct } = require('./model');

router.get("/", (req, res) => {
    tblCategory.find((err, data) => {
        //console.log(data);
        res.render("index", { data: data });
    })
});

router.post("/", (req, res) => {
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

router.get("/delete", (req, res) => {
    tblCategory.findByIdAndRemove(req.query.Cid, (err, data) => {
        console.log("Record Deleted..", data);
        res.redirect("/");
    })
})

router.get("/updateRedirect", (req, res) => {
    tblCategory.findById(req.query.Cid, (err, data) => {
        res.render("update", { data: data });
    })
});

router.post("/update", (req, res) => {
    tblCategory.findByIdAndUpdate(req.query.Cid, req.body, (err, data) => {
        console.log("Record Updated..", data);
        res.redirect("/");
    });
});

module.exports = router;