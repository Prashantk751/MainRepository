const express = require('express');
const path = require('path');

const router = express.Router();

const prod = require('../controllers/products');
router.get('/', prod.getProducts);


module.exports = router;