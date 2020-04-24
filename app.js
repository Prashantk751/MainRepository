//const http = require('http');

const express = require('express');
const adminRoutes = require('./routes/admin');
const shopRoutes = require('./routes/shop');
const uploadRoutes = require('./routes/uploadProduct');
const downloadRoutes = require('./routes/download');
const path = require('path');
const bodyParser = require('body-parser');
const multer = require('multer');
const expresshbs = require('express-handlebars');
const app = express();
const errorController = require('./controllers/error');
const handlebars = require('handlebars');

const db = require('./util/database');

const fileStorage = multer.diskStorage({
    destination: (req, file, cb) => {
        cb(null, 'images');
    },
    filename: (req, file, cb) => {
        cb(null, file.originalname);
    }
    
});

// db.execute('select * from products')
// .then(result=>{
//     console.log(result);
// })
// .catch((err)=>{
//     console.log(err);
// });

const fileFilter = (req, file, cb) => {
    if(file.mimetype === 'images/png' || file.mimetype === 'images/jpeg' || file.mimetype === 'images/jpg')
    {
        cb(null, true);
    }else
    {
        cb(null, false);
    }
}


app.engine('handlebars',expresshbs({layoutsDir: 'views/layouts/', defaultLayout: 'main'}));
app.set('view engine', 'handlebars');//specifyying the default work engine
app.set('views','views')//specifying the path to the folder which will contain all template files

//using body-parser module to extract data from the incoming request
app.use(bodyParser.urlencoded({extended:false}));

//app.use(multer({dest: 'images'}).single('myfile'));
app.use(multer({storage: fileStorage}).single('myfile'));
app.use(express.static(path.join(__dirname,'public')));
app.use(adminRoutes);
app.use(downloadRoutes);
app.use('/upload',(req,res,next)=>{
    res.render('upload');
})

handlebars.registerHelper('ifCond', function(v1, v2, options) {
    if(v1 === v2) {
      return options.fn(this);
    }
    return options.inverse(this);
  });

app.use(uploadRoutes);
//if this root path is at the top you can never reach the other routes because it will get you to the root path
//that is why its a good practice to use it at the last
app.use(shopRoutes);

app.use(errorController.get404);

//const server = http.createServer(app);
//server.listen(3000);
app.listen(3000);