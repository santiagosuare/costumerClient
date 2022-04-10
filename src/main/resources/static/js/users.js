// Call the dataTables jQuery plugin
$(document).ready(function() {
  uploadUsers();
  $('#users').DataTable();

  updateUserMail();
});

function updateUserMail(){
    document.getElementById('txtemail-user').outerHTML = localStorage.email;
}

async function uploadUsers() {
    const Request = await fetch('api/users', {
        method: 'GET',
        headers: getHeaders()
    });

    const users = await Request.json();


    let listHtml = '';
    for(let user of users) {

        let buttonDelete = '<a href="#" onClick="deleteUser('+ user.id +')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        let phone = user.phone == null ? "-" : user.phone;

        let userHtml = '<tr><td>'+ user.id
                        +'</td><td>' +user.name
                        +'</td><td>' +user.lastName
                        + '</td><td>'+user.email
                        +'</td><td>'+ phone
                        +'</td><td>' + buttonDelete + '</td></tr>';

        listHtml += userHtml;

    }

    document.querySelector('#users tbody').outerHTML = listHtml;

}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    }
}

async function deleteUser (id) {

    if(!confirm('Desea eliminar este usuario?')){
        return;
    }

    const Request = await fetch('api/users/' + id , {
            method: 'DELETE',
            headers: getHeaders()
        });

        location.reload();
}