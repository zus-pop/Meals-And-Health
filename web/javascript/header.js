const headerContainer = document.getElementById('header-container');
const userSection = document.getElementById('user-section');
const userNavBar = document.getElementById('user-nav-bar');
const searchBarScrolling = document.getElementById('search-bar-scrolling');
const arrowDown = document.getElementById('fa-angle-down');

document.onscroll = () => {
//     console.log(window.scrollY);
    const view = window.scrollY;
    if (view > 400)
        headerContainer.classList.add('scrolling');
    else
        headerContainer.classList.remove('scrolling');

    if (searchBarScrolling) {
        if (view > 600) {
            searchBarScrolling.classList.add('active');
        } else
            searchBarScrolling.classList.remove('active');
    }
};

if (userSection) {
    userSection.onclick = _ => {
        userNavBar.classList.toggle('active');
        arrowDown.classList.toggle('up');
    };
}
