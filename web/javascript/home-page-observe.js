const mealCard = document.querySelectorAll('.meal-card');
const weeklyCard = document.querySelectorAll('.weekly-card');
mealCard.forEach(meal => meal.classList.remove('observed'));
weeklyCard.forEach(plan => plan.classList.remove('observed'));

const observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.classList.add('observed');
            return;
        }

        entry.target.classList.remove('observed');
    });
});

mealCard.forEach(meal => observer.observe(meal));
weeklyCard.forEach(plan => observer.observe(plan));
