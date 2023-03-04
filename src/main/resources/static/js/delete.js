function deleteModal(id) {
    fetch('api/user/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json()
            .then(user => {
                document.getElementById('idDel').value = user.id;
                document.getElementById('firstNameDel').value = user.name;
                document.getElementById('lastNameDel').value = user.lastName;
                document.getElementById('ageDel').value = user.age;
                document.getElementById('emailDel').value = user.username;
            })
    })
}

async function deleteUser() {
    await fetch('api/delete/' + document.getElementById('idDel').value, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(document.getElementById('idDel').value)
    })
        .then(() => {
            document.getElementById("nav-admin-tab").click();
            showAllUsers();
            document.getElementById("closeDeleteModal").click();})
}