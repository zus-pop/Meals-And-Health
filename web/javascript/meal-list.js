const buyButtons = document.querySelectorAll('.buy-btn');
const cartNoti = document.querySelector('#cart-noti');

buyButtons.forEach(buyButton => {
    buyButton.addEventListener('click', _ => {
        $.ajax({
            type: 'GET',
            url: 'main?route=customer-add-to-cart',
            data: {
                mealId: buyButton.value
            },
            success: ({ result, size }) => {
                if (result) {
                    cartNoti.classList.add('has-item');

                    if (size > 0 && size < 10) {
                        cartNoti.innerHTML = size;
                    } else {
                        cartNoti.innerHTML = '9+';
                    }
                    window.alert('Add to cart successfully!');
                } else {
                    window.alert('You need to login first to use this feature!');
            }
            }
        });
    });
});