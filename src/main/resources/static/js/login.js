// Call the dataTables jQuery plugin
$(document).ready(function() {
  //On ready

});

async function login() {

    let datos = {};

    datos.email = document.querySelector('#txtInputEmail').value;
    datos.password = document.querySelector('#txtInputPassword').value;



    const Request = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    const response = await Request.text();

    if(response != "FAIL"){
        localStorage.token = response;
        localStorage.email = datos.email;
        window.location.href = 'user.html'
    } else {
        alert("Las credenciales son incorrectas");
    }

}
