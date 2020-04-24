const mysql = require('mysql2');

const pool = mysql.createPool({
    host:'localhost',
    user:'root',
    database:'node-complete',
    password:'9716690060'
});


/*
Ppstgres Connection
*/
//var pgp = require('pg-promise')(/* options */)
/*
var db = pgp('postgres://username:password@host:port/database')

db.one('SELECT $1 AS value', 123)
  .then(function (data) {
    console.log('DATA:', data.value)
  })
  .catch(function (error) {
    console.log('ERROR:', error)
  })


*/ 

module.exports = pool.promise();