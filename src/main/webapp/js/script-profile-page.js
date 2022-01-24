/*
This script is used in editprofile page 
@author Samu Uunonen
*/

const btnDelete = document.getElementById('btn-delete');
const btnNo = document.getElementById('btn-no');
const confirmDelete = document.getElementById('confirm-delete-profile');



btnNo.addEventListener('click', () => {
    hideDeleteConfirm();
    btnDelete.disabled = false;
});

btnDelete.addEventListener('click', () => {
    showDeleteConfirm();
    btnDelete.disabled = true;
});


function hideDeleteConfirm() {
    confirmDelete.classList.add('hide');
}
function showDeleteConfirm() {
    confirmDelete.classList.remove('hide');
}



