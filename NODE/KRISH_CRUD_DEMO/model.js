const {db,mongoose} = require('./databse');

const tblCategorySchema = new mongoose.Schema({
    CategoryName: String
});

const tblProductSchema = new mongoose.Schema({
    ProductName: {type : String},
    CategoryId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'tblCategory'
    }
});

const tblCategory = mongoose.model('tblCategory', tblCategorySchema);
const tblProduct = mongoose.model('tblProduct', tblProductSchema);

module.exports = { tblCategory,tblProduct};