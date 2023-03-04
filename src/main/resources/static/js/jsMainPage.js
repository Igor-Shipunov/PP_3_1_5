const UsersTable = document.getElementById("mainTable");

async function showAllUsers() {
    fetch("api/admin")
        .then((res) => res.json())
        .then(
            (users) => {
                if (users.length > 0) {
                    let temp = "";

                    users.forEach((user) => {
                        temp += `<tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.username}</td>
                        <td>${user.roles.map(role => role.name.substring(5) + " " )}</td> 
                        <td>
                             <button class="btn btn-info" type="button"
                             data-bs-toggle="modal" data-bs-target="#modalEdit"
                             onclick="editModal(${user.id})">Edit</button></td>
                             <td>
                             <button class="btn btn-danger" type="button"
                             data-bs-toggle="modal" data-bs-target="#modalDelete"
                             onclick="deleteModal(${user.id})">Delete</button></td>
                        </tr>`
                    })
                    UsersTable.innerHTML = temp;
                }
            }
            )
}
showAllUsers()