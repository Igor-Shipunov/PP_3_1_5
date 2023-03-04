const addUserForm = document.getElementById("add-user-form")

addUserForm.addEventListener('submit', (e)=>{
    e.preventDefault();

    let name = document.getElementById('name').value;
    let lastName = document.getElementById('lastName').value;
    let age = document.getElementById('age').value;
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;
    let roles = Array.from(document.getElementById("newUserRoles").selectedOptions).map(role => "ROLE_" + role.value);
    console.log(roles);

    /*let userTestRole = {      //For testing
        name : 'ROLE_ADMIN'
    };
    let userTestRoleArr = Array.of(userTestRole);*/

    fetch('api/new', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            name: name,
            lastName: lastName,
            age: age,
            username: email,
            password: password,
            roles: roles,
            //roles: userTestRoleArr  //For testing
        })
    })
        .then(user => {
            const usersArr = [];
            usersArr.push(user);
            /*showAllUsers(usersArr);*/
        })
        .then(() => {
            document.getElementById("nav-admin-tab").click();})
})
