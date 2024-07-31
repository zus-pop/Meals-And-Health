const headerSearchBar = document.getElementById('header-search-bar');
const mainSearchBar = document.getElementById('main-search-bar');


mainSearchBar.oninput = event => {
    headerSearchBar.value = event.target.value;
};

headerSearchBar.oninput = event => {
    mainSearchBar.value = event.target.value;
};