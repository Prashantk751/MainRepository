const express = require('express');
const path = require('path');
//const rootDir = require('../util/path');
const router = express.Router();
const adminController = require('../controllers/admin');

router.get('/admin/add-product', adminController.getAddProduct);

router.get('/admin/products', adminController.getProducts);

router.post('/admin/add-product', adminController.postAddProduct);

module.exports = router;
//exports.routes = router;