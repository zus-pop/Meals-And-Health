/* global fetch */

const citySelect = document.getElementById('city');
const districtSelect = document.getElementById('district');
const wardSelect = document.getElementById('ward');

function resetList(list, content) {
    list.replaceChildren();
    list.innerHTML += `<option disabled selected value=''>${content}</option>`;
}

(function fetchCities() {
    fetch('https://vapi.vnappmob.com/api/province/')
            .then(response => response.json())
            .then(data => data.results)
            .then(cities => {
                console.log(cities);
                cities.forEach(city => {
                    citySelect.innerHTML +=
                            `<option id=${city.province_id}>
                        ${city.province_name}                            
                    </option>`;
                });
            })
            .catch(err => console.log(err));
})();

const fetchDistricts = province_id => {
    fetch(`https://vapi.vnappmob.com/api/province/district/${province_id}`)
            .then(response => response.json())
            .then(data => data.results)
            .then(districts => {
                // console.log(districts.length);
                if (districtSelect.hasChildNodes) {
                    resetList(districtSelect, 'Select District');
                }
                districts.forEach(district => {
                    districtSelect.innerHTML +=
                            `<option id=${district.district_id}>
                        ${district.district_name}
                    </option>`;
                });
            })
            .catch(err => console.log(err));
};

const fetchWards = district_id => {
    fetch(`https://vapi.vnappmob.com/api/province/ward/${district_id}`)
            .then(response => response.json())
            .then(data => data.results)
            .then(wards => {
                // console.log(wards.length);
                if (wardSelect.hasChildNodes) {
                    const content = wards.length ? 'Select Ward' : 'Not have any ward';
                    resetList(wardSelect, content);
                }
                wards.forEach(ward => {
                    wardSelect.innerHTML +=
                            `<option id=${ward.ward_id}>
                        ${ward.ward_name}
                    </option>`;
                });
            })
            .then(_ => {
                if (wardSelect.classList.contains('cart')) {
                    const length = wardSelect.options.length;
                    if (length === 1) {
                        wardSelect.required = false;
                    } else {
                        wardSelect.required = true;
                    }
                }
            })
            .catch(err => console.log(err));
};

citySelect.onchange = event => {
    if (event.target.value) {
        const cities = event.target.options;
        const selectedCity = cities[cities.selectedIndex];
        console.log(selectedCity.value);
        districtSelect.disabled = false;
        wardSelect.disabled = true;
        resetList(wardSelect, 'Select Ward');
        fetchDistricts(selectedCity.id);
    } else
        districtSelect.disabled = true;
};

districtSelect.onchange = event => {
    if (event.target.value) {
        const wards = event.target.options;
        const selectedWard = wards[wards.selectedIndex];
        console.log(selectedWard.value);
        wardSelect.disabled = false;
        fetchWards(selectedWard.id);
    } else
        wardSelect.disabled = true;
};

wardSelect.onchange = event => console.log(event.target.value);

