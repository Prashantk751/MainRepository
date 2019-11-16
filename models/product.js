//const products=[];
const fs = require('fs');
const path = require('path');

module.exports = class Product{
    constructor(t)
    {
        this.title = t;
    }

    save(){
        //products.push(this)
        const p = path.join(
            path.dirname(process.mainModule.filename),
            'data',
            'product.json'
            );

        fs.readFile(p, (err, fileContent) => {
            let products = [];
            if(!err)
            {
                products = JSON.parse(fileContent);
            }
            products.push(this);
            fs.writeFile(p, JSON.stringify(products),(err) => {
                console.log(err);
            });

        });    
    }

    //static keyword make sure that i can call this method on the class itself not through object
   static fetchAll(cb){
        //return products;
        const p = path.join(
            path.dirname(process.mainModule.filename),
            'data',
            'product.json'
            );

        fs.readFile(p, (err,fileContent)=>{
            if(err){
                //return [];
                cb([]);
            }
            //return JSON.parse(fileContent);
            cb(JSON.parse(fileContent));
        });
    }
}