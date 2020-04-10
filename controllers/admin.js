const Product = require('../models/product');

exports.getAddProduct = (req,res,next)=>{
    console.log("In the add product path");
    //res.send('<form action="/product" method="post"><input type="text" name="title"><button type="submit">Add Product</button></form>');
        //res.sendFile(path.join(__dirname, '../views/add-product.html'));
    //res.sendFile(path.join(rootDir, '../views/add-product.html'));
    res.render('admin/edit-product',{
        pageTitle: 'Add Product',
        path: '/admin/add-product',
        editing: false
});
}

exports.postAddProduct = (req,res,next)=>{
    console.log(req.body);
    const title = req.body.title;
    const imageUrl = req.body.imageUrl;
    const description = req.body.description;
    const price = req.body.price;

    const product = new Product(null, title, imageUrl, description, price);
    product.save();
    res.redirect('/');
};

exports.getEditProduct = (req,res,next)=>{
    const editMode = req.query.edit;

    if(!editMode){
        return res.redirect('/');
    }

    const prodId = req.params.productId;
    Product.findById(prodId, product => {

        if(!product){
            return res.redirect('/');
        }else
        {
            res.render('admin/edit-product',{
            pageTitle: 'Add Product',
            path: '/admin/edit-product',
            editing:editMode,
            product: product
            });        
        }
    });
    
}

exports.postEditProduct = (req, res, next) =>{
    console.log(req.body);
    const prodId = req.body.productId;
    console.log(prodId);
    const updatedTitle = req.body.title;
    const updatedPrice = req.body.price;
    const updatedImageUrl = req.body.imageUrl;
    const updatedDesc = req.body.description;

    const updatedProduct = new Product(prodId, updatedTitle, updatedImageUrl, updatedDesc, updatedPrice);
    updatedProduct.save();
    res.redirect('/admin/products');

};

exports.postDeleteProduct = (req, res, next) =>{
    const prodId = req.body.productId;
    Product.deleteById(prodId);
};

exports.getProducts = (req,res,next) => {
    Product.fetchAll( products =>{
        res.render('admin/products',{
            prods: products,
            pageTitle:'Admin products',
            path:'/admin/products',
            hasProducts: products.length > 0,
            activeShop: true,
            productCSS: true
        });   
       });
}
