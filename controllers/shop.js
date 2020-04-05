const Product = require('../models/product');

exports.getProducts = (req,res,next)=>{
    // const products = Product.fetchAll();
    Product.fetchAll( products =>{
     res.render('shop/product-list',{
         prods: products,
         pageTitle:'All Products',
         path:'/products',
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
 };

 exports.getIndex = (req,res,next)=>{
    // const products = Product.fetchAll();
    Product.fetchAll( products =>{
     res.render('shop/index',{
         prods: products,
         pageTitle:'shop',
         path:'/',
         hasProducts: products.length > 0,
         activeShop: true,
         productCSS: true
     });   
    });
   
 };

 exports.getCart = (req,res,next) =>{
     res.render('shop/cart', {
         path:'/cart',
         pageTitle: 'Your Cart'
     });
 };

 exports.getCheckout = (req,res,next) =>{
    res.render('shop/checkout', {
        path:'/checkout',
        pageTitle: 'Checkout'
    });
};

exports.getOrders = (req,res,next) =>{
    res.render('shop/orders', {
        path:'/orders',
        pageTitle: 'Your Orders'
    });
};
