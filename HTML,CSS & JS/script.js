document.getElementById('add-item-btn').addEventListener('click', function() {
    const itemName = document.getElementById('item-name').value;
    const itemQuantity = document.getElementById('item-quantity').value;
    const itemPrice = document.getElementById('item-price').value;

    if (itemName && itemQuantity && itemPrice) {
        addItemToInventory(itemName, itemQuantity, itemPrice);
        document.getElementById('item-name').value = '';
        document.getElementById('item-quantity').value = '';
        document.getElementById('item-price').value = '';
        updateTotalCount();
        updateSubtotalPrice();
    } else {
        alert('Please enter item name, quantity, and price.');
    }
});

function addItemToInventory(name, quantity, price) {
    const inventoryList = document.getElementById('inventory-list');
    const listItem = document.createElement('li');
    listItem.innerHTML = `${name} - Quantity: ${quantity}, Price: ₹${price} <button onclick="editItem(this)">Edit</button> <button onclick="removeItem(this)">Remove</button>`;
    inventoryList.appendChild(listItem);
}

function removeItem(button) {
    const listItem = button.parentElement;
    listItem.remove();
    updateTotalCount();
    updateSubtotalPrice();
}

function editItem(button) {
    const listItem = button.parentElement;
    const [name, details] = listItem.textContent.split(' - Quantity: ');
    const [quantity, price] = details.split(', Price: $');

    document.getElementById('item-name').value = name.trim();
    document.getElementById('item-quantity').value = quantity.trim();
    document.getElementById('item-price').value = price.trim().split(' ')[0];

    listItem.remove();
    updateTotalCount();
    updateSubtotalPrice();
}

function searchItem() {
    const searchValue = document.getElementById('search-item').value.toLowerCase();
    const items = document.getElementById('inventory-list').getElementsByTagName('li');

    Array.from(items).forEach(item => {
        const itemName = item.textContent.toLowerCase();
        if (itemName.includes(searchValue)) {
            item.style.display = '';
        } else {
            item.style.display = 'none';
        }
    });
}

function updateTotalCount() {
    const totalCount = document.getElementById('inventory-list').getElementsByTagName('li').length;
    document.getElementById('total-count').textContent = totalCount;
}

function updateSubtotalPrice() {
    const items = document.getElementById('inventory-list').getElementsByTagName('li');
    let subtotal = 0;

    Array.from(items).forEach(item => {
        const details = item.textContent.split(', Price: ₹')[1];
        const price = parseFloat(details.split(' ')[0].trim());
        subtotal += price;
    });

    document.getElementById('subtotal-price').textContent = subtotal.toFixed(2);
}
