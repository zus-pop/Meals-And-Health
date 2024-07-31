const observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.classList.add('observed');
            return;
        }

        entry.target.classList.remove('observed');
    });
});

const cards = document.querySelectorAll('.card');

cards.forEach(card => observer.observe(card));