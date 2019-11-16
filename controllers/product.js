const Product = require('../models/product');
exports.getAddProduct = (req,res,next)=>{
    console.log("In the add product path");
    //res.send('<form action="/product" method="post"><input type="text" name="title"><button type="submit">Add Product</button></form>');
        //res.sendFile(path.join(__dirname, '../views/add-product.html'));
    //res.sendFile(path.join(rootDir, '../views/add-product.html'));
    res.render('add-product',{pageTitle: 'Add Product'});
}

exports.postAddProduct = (req,res,next)=>{
    console.log(req.body);
    const product = new Product(req.body.title);
    product.save();
    res.redirect('/');
}

exports.getProducts = (req,res,next)=>{
   // const products = Product.fetchAll();
   Product.fetchAll( products =>{
    res.render('shop',{
        prods: products,
        pageTitle:'shop',
        path:'/',
        hasProducts: products.length > 0,
        activeShop: true,
        productCSS: true
    });   
   });
    console.log("In the root path");
    //res.send("<h1>Hello from Express Js</h1>");
        //res.sendFile(path.join(__dirname, "../views/shop.html"));
        //console.log("products data is",adminData.products);
    //res.sendFile(path.join(rootDir, "../views/shop.html"));
    //res.render('shop', {prods: products, pageTitle:'shop'});
}