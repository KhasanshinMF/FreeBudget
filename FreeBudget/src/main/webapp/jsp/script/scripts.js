function toggleCategoryForm() {
    const form = document.getElementById('addCategoryForm');
    form.style.display = (form.style.display === 'none' || form.style.display === '') ? 'block' : 'none';
}

function toggleTransactionForm() {
    const form = document.getElementById('addTransactionForm');
    form.style.display = (form.style.display === 'none' || form.style.display === '') ? 'block' : 'none';
}