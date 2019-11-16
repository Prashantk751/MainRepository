const express = require('express');
const path = require('path');
//const rootDir = require('../util/path');
const router = express.Router();
const prod = require('../controllers/product');

router.get('/add-product', prod.getAddProduct);

router.post('/add-product', prod.postAddProduct);

module.exports = router;
//exports.routes = router;