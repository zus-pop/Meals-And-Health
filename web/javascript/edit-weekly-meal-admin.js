const addButtons = document.querySelectorAll('.add-meal');
const removeButtons = document.querySelectorAll('.remove');

removeButtons.forEach(button => {
    if (button.parentNode.parentNode.childElementCount < 3) {
        button.classList.add('disabled');
    }
});

addButtons.forEach(button => {
    button.addEventListener('click', event => {
        const parent = button.parentNode;
        console.log(parent.childElementCount);
        if (parent.childElementCount < 3) {
            parent.querySelectorAll('.remove')
                .forEach(button => {
                    button.classList.remove('disabled');
                });
        }
        const clone = event.target.previousElementSibling.cloneNode(true);
        clone.querySelector('.remove')
            .addEventListener('click', event => {
                const removeChild = event.target.parentNode;
                const parent = removeChild.parentNode;
                removeChild.remove();
                if (parent.childElementCount < 3) {
                    parent.querySelectorAll('.remove')
                        .forEach(button => {
                            button.classList.add('disabled');
                        });
                }
                else {
                    parent.querySelectorAll('.remove')
                        .forEach(button => {
                            button.classList.remove('disabled');
                        });

                }
            });
        button.before(clone);
    });
});


removeButtons.forEach(button => {
    button.addEventListener('click', event => {
        const removeChild = event.target.parentNode;
        const parent = removeChild.parentNode;
        removeChild.remove();
        if (parent.childElementCount < 3) {
            parent.querySelectorAll('.remove')
                .forEach(button => {
                    button.classList.add('disabled');
                });
        }
        else {
            parent.querySelectorAll('.remove')
                .forEach(button => {
                    button.classList.remove('disabled');
                });

        }
    });
});