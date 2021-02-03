const express = require('express');
const app = express();
const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/DemoDb_1', { useNewUrlParser: true, useUnifiedTopology: true });
const port = 80;

app.set("view engine", "ejs");
app.set("views", "./views");

app.use(express.urlencoded({ extended: false }));

const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function () {
    console.log("Connected.");
});

const tblCategorySchema = new mongoose.Schema({
    CategoryName: String
});

const tblProductSchema = new mongoose.Schema({
    ProductName: String,
    CategoryId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'tblCategory'
    }
});

const tblCategory = mongoose.model('tblCategory', tblCategorySchema);
const tblProduct = mongoose.model('tblProduct', tblProductSchema);

app.get("/", (req, res) => {
    tblCategory.find((err, data) => {
        if (!err) {
            res.render("index", { cats: data });
        }
    })
    // var username ="user";
    // var password ="user";
    // var categoryid = "5ff07194687a0c174472c845";
    // var tmpLog = new tblLogin({
    //     UserName:username,
    //     Password:password,
    //     CategoryId:categoryid
    // })
    // tmpLog.save((err,data)=>{
    //     if(!err)
    //     {
    //         console.log(data);
    //     }
    // })
});

app.post("/insertCat", (req, res) => {
    const tmp = new tblCategory({
        CategoryName: req.body.categoryname
    });
    tmp.save((err, data) => {
        if (!err) {
            console.log(data);
        }
    });
    res.redirect("/");
})

app.get("/delCat", (req, res) => {
    var id = req.query.id;
    tblCategory.findByIdAndDelete(id, (err, data) => {
        if (!err) {
            console.log("Cat deleted.", data);
            res.redirect("/");
        }
    })
})

app.get("/editCat", (req, res) => {
    var id = req.query.id;
    tblCategory.findById(id, (err, data) => {
        if (!err) {
            //console.log(data);
            res.render("edit", { data: data });
        }

    })
})

app.post("/updateCat", (req, res) => {
    var id = req.query.id;
    tblCategory.findByIdAndUpdate(id, { CategoryName: req.body.categoryname }, (err, data) => {
        if (!err) {
            console.log("Updated.", data);
            res.redirect("/");
        }
    })
})

//Product

app.get("/product", (req, res) => {
    tblCategory.find((err, data) => {
        tblProduct.find((err, pro) => {
            res.render("product", { cats: data, pros: pro });
        })
    })
})

app.post("/addProduct", (req, res) => {
    var tmp = new tblProduct({
        ProductName: req.body.ProductName,
        CategoryId: req.body.CategoryId
    })
    tmp.save((err, data) => {
        if (!err)
            console.log(data);

        res.redirect("/product");
    })
})

app.listen(port, () => {
    console.log("Server is running on : ", port);
})