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
    nameTd.classList.add("name");
    tr.appendChild(nameTd);

    const lastNameTd = document.createElement("td");
    lastNameTd.textContent = user.lastName;
    lastNameTd.classList.add("lastName");
    tr.appendChild(lastNameTd);

    const ageTd = document.createElement("td");
    ageTd.textContent = user.age;
    ageTd.classList.add("age");
    tr.appendChild(ageTd);

    const usernameTd = document.createElement("td");
    usernameTd.textContent = user.username;
    usernameTd.classList.add("username");
    tr.appendChild(usernameTd);

    const rolesTd = document.createElement("td");
    rolesTd.textContent = user.roles.map(role => " " + role.name.substring(5));
    rolesTd.classList.add("roles");
    tr.appendChild(rolesTd);

    const editTd = document.createElement("td");
    const editBtn = document.createElement("button");
    editBtn.textContent = "Edit";
    editBtn.classList.add("editBtn");
    editBtn.addEventListener("click", () => {
        openEditModal(user.id);
    });
    editTd.appendChild(editBtn);
    tr.appendChild(editTd);

    const deleteTd = document.createElement("td");
    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Delete";
    deleteBtn.classList.add("deleteBtn");
    deleteBtn.addEventListener("click", () => {
        openDeleteModal(user.id);
    });
    deleteTd.appendChild(deleteBtn);
    tr.appendChild(deleteTd);

    return tr;
}

function loadUsers() {
    fetch("http://localhost:8080/api/admin")
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(users => {
            // Clear the table body
            usersTableBody.innerHTML = "";

            users.forEach(user => {
                const tr = createUserRow(user);
                usersTableBody.appendChild(tr);
            });
        })
        .catch(error => console.error("Error loading users:", error));
}
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

loadUsers();
getCurrentUser();

