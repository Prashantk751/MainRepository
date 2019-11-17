const express = require('express');
const path = require('path');
const fs = require('fs');
//const multer = require('multer');

const router = express.Router();

//router.use(multer().single(''));

router.post('/download', (req,res,next)=>{
    console.log('In the download route ')
    const value = req.body.random;
    console.log("value of the imput text is: ",value);

    const invoicePath = path.join('data/myfile.json');
    const arr = [{"id":1, "name":"zx","age":24},{"id":2,"name":"mn","age":25},{"id":3,"name":"rt","age":26},{"id":4,"name":"pi","age":27},{"id":5,"name":"kl","age":28}];
    let writedata = JSON.stringify(arr);
    fs.writeFile(invoicePath, JSON.stringify(arr, null, 2), function (err) {
        if (err) throw err;
        console.log('Data Saved!');
      });
    
    fs.readFile(invoicePath, (err, data)=>{
        if(err)
        {
            return next(err);
        }
        res.setHeader('Content-Type','text/plain');
        //res.setHeader('Content-Type','application//pdf');
        res.setHeader('Content-Disposition','attachment; filename="'+invoicePath+'"');
        res.send(data);
    });


    // file_data = f_in.read() 
    // file_data = file_data.replace("}{", "},{") 
    // file_data = "[" + file_data + "]"
    // data = json.loads(file_data)
});


module.exports = router;