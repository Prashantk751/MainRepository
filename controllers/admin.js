const Product = require('../models/product');

exports.getAddProduct = (req,res,next)=>{
    console.log("In the add product path");
    //res.send('<form action="/product" method="post"><input type="text" name="title"><button type="submit">Add Product</button></form>');
        //res.sendFile(path.join(__dirname, '../views/add-product.html'));
    //res.sendFile(path.join(rootDir, '../views/add-product.html'));
    res.render('admin/add-product',{
        pageTitle: 'Add Product',
        path: '/admin/add-product'
});
}

exports.postAddProduct = (req,res,next)=>{
    console.log(req.body);
    const title = req.body.title;
    const imageUrl = req.body.imageUrl;
    const description = req.body.description;
    const price = req.body.price;

    const product = new Product(title, imageUrl, description, price);
    product.save();
    res.redirect('/');
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
