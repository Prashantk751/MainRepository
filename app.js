//const http = require('http');

const express = require('express');
const adminRoutes = require('./routes/admin');
const shopRoutes = require('./routes/shop');

const path = require('path');
const bodyParser = require('body-parser');
const expresshbs = require('express-handlebars');
const app = express();
const errorController = require('./controllers/error');
app.engine('handlebars',expresshbs({layoutsDir: 'views/layouts/', defaultLayout: 'main'}));
app.set('view engine', 'handlebars');//specifyying the default work engine
app.set('views','views')//specifying the path to the folder which will contain all template files
//using body-parser module to extract data from the incoming request
app.use(bodyParser.urlencoded());

app.use(adminRoutes);

//if this root path is at the top you can never reach the other routes because it will get you to the root path
//that is why its a good practice to use it at the last
app.use(shopRoutes);

app.use(errorController.get404);

//const server = http.createServer(app);
//server.listen(3000);
app.listen(3000);