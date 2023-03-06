const addUserForm = document.getElementById("add-user-form")
const currentUsername = document.getElementById("usernameOfCurrentUser");
const currentRoles = document.getElementById("rolesOfCurrentUser");

addUserForm.addEventListener('submit', ()=>{
    const name = document.getElementById('name').value;
    const lastName = document.getElementById('lastName').value;
    const age = document.getElementById('age').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const roles = Array.from(document.getElementById("newUserRoles").selectedOptions).map(role => "ROLE_" + role.value);
    //console.log(roles);

    fetch(' http://localhost:8080/api/new', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            name: name,
            lastName: lastName,
            age: age,
            username: username,
            password: password,
            roles: roles,
        })
    })
})

function getCurrentUser() {
    fetch("http://localhost:8080/api/currentUser")
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(currentUser => showCurrentUserData(currentUser))
}

function showCurrentUserData(currentUser) {
    currentUsername.innerText = currentUser.username;
    currentRoles.innerText = currentUser.roles.map(role => " " + role.name.substring(5));
}

getCurrentUser();
