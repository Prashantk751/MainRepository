//const products=[];
const fs = require('fs');
const path = require('path');

const p = path.join(
    path.dirname(process.mainModule.filename),
    'data',
    'product.json'
    );

const getProductsFromFile = cb => {
    
    fs.readFile(p, (err, fileContent) => {
        if(err)
        {
           cb([]);
        }
        else
        {
            cb(JSON.parse(fileContent));
        }
    });
};

module.exports = class Product{
    constructor(title, imageUrl, description, price)
    {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
    }

    save(){
        //products.push(this)
       getProductsFromFile(products => {
        products.push(this);
        fs.writeFile(p, JSON.stringify(products),(err) => {
            console.log(err);
        });

       });    
    }

    //static keyword make sure that i can call this method on the class itself not through object
   static fetchAll(cb){
        //return products;
        getProductsFromFile(cb);
    }
}