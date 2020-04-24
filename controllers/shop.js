const Product = require('../models/product');
const Cart = require('../models/cart');

exports.getProducts2 = (req,res,next)=>{
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

exports.getProducts = (req,res,next)=>{
    Product.fetchAll()
    .then(([rows, fieldData])=>{
        res.render('shop/product-list',{
            prods: rows,
            pageTitle:'All Products',
            path:'/products',
            hasProducts: rows.length > 0,
            activeShop: true,
            productCSS: true
        });   
      
    })
    .catch( err=> console.log(err));
    
}

//  exports.getIndex = (req,res,next)=>{
//     Product.fetchAll( products =>{
//      res.render('shop/index',{
//          prods: products,
//          pageTitle:'shop',
//          path:'/',
//          hasProducts: products.length > 0,
//          activeShop: true,
//          productCSS: true
//      });   
//     });
   
//  };


exports.getIndex = (req,res,next)=>{
    Product.fetchAll()
    .then(([rows, fieldData])=>{
        res.render('shop/index',{
            prods: rows,
            pageTitle:'shop',
            path:'/',
            hasProducts: rows.length > 0,
            activeShop: true,
            productCSS: true
        });   
      
    })
    .catch( err=> console.log(err));
    
   
 };

//  exports.getProductDetail = (req,res,next) => {
//     const prodId = req.params.productId;
//     //console.log("Product id is:",prodId);
    
//     Product.findById(prodId, product =>{
//         console.log(product);
//         res.render('shop/product-detail', {
//             product: product,
//             pageTitle:'Product Detail',
//             path:'/products'
//         })
//     })
//     //res.redirect('/');
//  };


exports.getProductDetail = (req,res,next) => {
    const prodId = req.params.productId;
    //console.log("Product id is:",prodId);
    
    Product.findById(prodId)
    .then(([product])=>{
        console.log(product);
        res.render('shop/product-detail', {
            product: product[0],
            pageTitle:'Product Detail',
            path:'/products'
        });
    })
    .catch(err =>{
        console.log(err);
    });
    //res.redirect('/');
 };

 exports.getCart = (req,res,next) =>{
     Cart.getCart(cart => {
        Product.fetchAll(products=>{
            const cartProducts =[];
            for(product of products){
                const cartProductData = cart.products.find(prod => prod.id === product.id);
                if(cartProductData){
                    cartProducts.push({productData: product, qty:cartProductData.qty});
                }
            }
            res.render('shop/cart', {
                path:'/cart',
                pageTitle: 'Your Cart',
                products: cartProducts
            });
        });
     });
     
 };

 exports.postCart = (req, res, next) => {
    const prodId = req.body.productId;
    console.log(prodId);
    Product.findById(prodId, product => {
        Cart.addProduct(prodId, product.price);
    }); 

    res.redirect('/cart');
 };


exports.deleteCartProduct = (req, res, next) =>{
    const prodId = req.body.productId;
    Product.findById(prodId, product =>{
        Cart.deleteProduct(prodId, product.price);
        res.redirect('/cart');
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
