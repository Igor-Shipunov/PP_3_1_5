const currentUsername = document.getElementById("usernameOfCurrentUser");
const currentRoles = document.getElementById("rolesOfCurrentUser");

const usersTableBody = document.querySelector("#usersTable tbody");


function createUserRow(user) {
    const tr = document.createElement("tr");
    tr.setAttribute("data-user-id", user.id);

    const idTd = document.createElement("td");
    idTd.textContent = user.id;
    tr.appendChild(idTd);

    const nameTd = document.createElement("td");
    nameTd.textContent = user.name;
    tr.appendChild(nameTd);

    const lastNameTd = document.createElement("td");
    lastNameTd.textContent = user.lastName;
    tr.appendChild(lastNameTd);

    const ageTd = document.createElement("td");
    ageTd.textContent = user.age;
    tr.appendChild(ageTd);

    const usernameTd = document.createElement("td");
    usernameTd.textContent = user.username;
    tr.appendChild(usernameTd);

    const rolesTd = document.createElement("td");
    rolesTd.textContent = user.roles.map(role => " " + role.name.substring(5));
    tr.appendChild(rolesTd);

    return tr;
}

function getCurrentUser() {
    fetch("http://localhost:8080/api/currentUser")
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(currentUser => {
            showCurrentUserData(currentUser)
            usersTableBody.innerHTML = "";
            const tr = createUserRow(currentUser);
            usersTableBody.appendChild(tr);
        })
}

function showCurrentUserData(currentUser) {
    currentUsername.innerText = currentUser.username;
    currentRoles.innerText = currentUser.roles.map(role => " " + role.name.substring(5));
}

getCurrentUser();