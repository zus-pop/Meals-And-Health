const getSiblings = element => {
    const siblings = [];
    let sibling = element.parentNode.firstChild;

    while (sibling) {
        if (sibling !== element && sibling.nodeType === 1) {
            siblings.push(sibling);
        }
        sibling = sibling.nextSibling;
    }
    return siblings;
};

const types = document.querySelectorAll('.type');
const days = document.querySelectorAll('.day');
const mealsSection = document.querySelectorAll('.meals-section');

days.forEach(day => {
    day.addEventListener('click', _ => {
        const mealDay = document.querySelector(`#${day.innerHTML.toLowerCase()}`);
        mealDay.classList.add('active');
        getSiblings(mealDay).forEach(sibling => sibling.classList.remove('active'));

        day.classList.add('active');
        getSiblings(day).forEach(sibling => sibling.classList.remove('active'));
    });
});

types.forEach(type => {
    type.addEventListener('click', _ => {
        type.classList.add('active');
        const siblings = getSiblings(type);
        siblings.forEach(sibling => sibling.classList.remove('active'));

        mealsSection.forEach(section => {
            if (section.classList.contains(type.innerHTML))
                section.classList.add('active');
            else
                section.classList.remove('active');
        });
    });
});