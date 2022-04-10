// Call the dataTables jQuery plugin
$(document).ready(function() {
  //On ready

});

async function registerUser() {

    let datos = {};

    datos.name = document.querySelector('#txtName').value;
    datos.lastname = document.querySelector('#txtLastName').value;
    datos.email = document.querySelector('#txtEmail').value;
    datos.password = document.querySelector('#txtPassword').value;

    let repetPassword = document.querySelector('#txtRepeatPassword').value;

    if (repetPassword != datos.password){
        alert('La password son diferentes');
        return;
    }

    const Request = await fetch('api/users', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert("La cuenta fue creada exitosamente!");
    window.location.href = 'login.html'

}

