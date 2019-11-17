const express = require('express');
const path = require('path');
//const multer = require('multer');

const router = express.Router();

//router.use(multer().single(''));

router.post('/uploadProduct', (req,res,next)=>{
    console.log('In the uploadProduct route ')
    const image = req.file;
    console.log(image);
});


module.exports = router;