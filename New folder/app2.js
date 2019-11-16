const http = require('http');

// const server = http.createServer(function(req,res){
//     console.log('Hello');
// });

const server = http.createServer((req,res) => {
    /*
    Receiving the Request
    */
    
    //console.log(req);
    //console.log(req.url,req.method,req.headers);

    //process.exit();  this is used to exit from the current process and it stop accepting any new request.

    /*
    Sending the Response
    */


    /*
    taking input from user and sending the input data
    */
  const fs = require('fs');
    const url = req.url;
    const method = req.method;
    
    if(url === '/')
    {
        res.write('<html>');
        res.write('<head><title>Form Input</title></head>');
        res.write('<body>');
        res.write('<form action="/message" method="POST">');
        res.write('Your Input');
        res.write('<input type="text" name="msg"><button type="submit">Send</button>');
        res.write('</form>');
        res.write('</body>')
        res.write('<html>');
        return res.end();
    }

    if(url === '/message' && method === "POST")
    {
        const body = [];
        req.on('data', (chunk)=>{
            console.log(chunk);
            body.push(chunk);
        });

        req.on('end', ()=>{
            const parsedBody = Buffer.concat(body).toString();
            console.log(parsedBody);
            const message = parsedBody.split('=')[1];
            console.log(message);
            //fs.writeFileSync('message.txt', message);
            fs.writeFile('message.txt', message, err =>{
                res.statusCode = 302;
                res.setHeader('Location', '/');
                return res.end();
            });
        });

        
    }

    res.setHeader('content-type','text/html');
    res.write('<html>');
    res.write('<head><title>Node Js Server</title></head>');
    res.write('<body><h1>Hello From Node Js </h1></body>');
    res.write('<html>');
    res.end();
});

server.listen(3000);