// Get the modal elements
const deleteModal = document.getElementById("deleteModal");
const editModal = document.getElementById("editModal");

const cancelDeleteBtn = document.getElementById("cancelDeleteBtn");
const confirmDeleteBtn = document.getElementById("confirmDeleteBtn");
const closeDeleteBtn = document.getElementById("CloseDeleteBtn");

const cancelEditBtn = document.getElementById("cancelEditBtn");
const saveChangesBtn = document.getElementById("saveChangesBtn");
const closeEditBtn = document.getElementById("CloseEditBtn");

const nameField = document.getElementById("nameToEdit");
const lastNameField = document.getElementById("lastNameToEdit");
const ageField = document.getElementById("ageToEdit");
const usernameField = document.getElementById("usernameToEdit");
const passwordField = document.getElementById("passwordToEdit");
const userIdField = document.getElementById("userIdToEdit");

const nameFieldToDelete = document.getElementById("nameToDelete");
const lastNameFieldToDelete = document.getElementById("lastNameToDelete");
const ageFieldToDelete = document.getElementById("ageToDelete");
const usernameFieldToDelete = document.getElementById("usernameToDelete");
const userIdFieldToDelete = document.getElementById("userIdToDelete");

function openDeleteModal(userId) {
    const rowToChange = document.querySelector(`tr[data-user-id="${userId}"]`);
    userIdFieldToDelete.value = userId;
    nameFieldToDelete.value = rowToChange.querySelector('.name').textContent;
    lastNameFieldToDelete.value = rowToChange.querySelector(".lastName").textContent;
    ageFieldToDelete.value = rowToChange.querySelector(".age").textContent;
    usernameFieldToDelete.value = rowToChange.querySelector(".username").textContent;;
    deleteModal.style.display = "block";
    cancelDeleteBtn.addEventListener("click", () => closeModals());
    closeDeleteBtn.addEventListener("click", () => closeModals());
    confirmDeleteBtn.addEventListener("click", () => deleteUser(userId));
}

function openEditModal(userId) {
    const rowToChange = document.querySelector(`tr[data-user-id="${userId}"]`);
    userIdField.value = userId;
    nameField.value = rowToChange.querySelector('.name').textContent;
    lastNameField.value = rowToChange.querySelector(".lastName").textContent;
    ageField.value = rowToChange.querySelector(".age").textContent;
    usernameField.value = rowToChange.querySelector(".username").textContent;
    editModal.style.display = "block";
    cancelEditBtn.addEventListener("click", () => closeModals());
    closeEditBtn.addEventListener("click", () => closeModals());
    saveChangesBtn.addEventListener("click", () => editUser(userId, nameField.value, lastNameField.value,
                                                                    ageField.value, usernameField.value, passwordField.value));
}

function closeModals() {
    deleteModal.style.display = "none";
    editModal.style.display = "none";
}


function deleteUser(userId) {
    fetch(`http://localhost:8080/api/delete/${userId}`, {
        method: "DELETE",
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            const rowToDelete = document.querySelector(`tr[data-user-id="${userId}"]`);
            rowToDelete.remove();
            closeModals();
        })
        .catch(error => console.error("Error deleting user:", error));
}


function editUser(userId, name, lastName, age, username, password) {
    const selectedOptions = document.querySelectorAll('#userRolesToEdit option:checked');
    const selectedValues = Array.from(selectedOptions).map(option => option.value);
    console.log(selectedValues)
    const userData = {
        id: userId,
        name: name,
        lastName: lastName,
        age: age,
        username: username,
        password: password,
        roles: selectedValues
    };

    console.log(userData);
    fetch(`http://localhost:8080/api/update/${userId}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            const rowToEdit = document.querySelector(`tr[data-user-id="${userId}"]`);
            rowToEdit.querySelector('.name').textContent = name;
            rowToEdit.querySelector(".lastName").textContent = lastName;
            rowToEdit.querySelector(".age").textContent = age;
            rowToEdit.querySelector(".username").textContent = username;
            rowToEdit.querySelector(".roles").textContent = selectedValues.map(role => " " + role.substring(5));
            closeModals();
        })
        .catch(error => console.error("Error editing user:", error));
}
