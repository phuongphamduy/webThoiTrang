
if(!location.href.includes('/main')) {
    document.querySelector(".menu-list").style.display = 'none';
    $(document).ready(() => {
        $('.header-menu').hover(() => {
             document.querySelector(".menu-list").style.display = 'block';
        }, () => {
            document.querySelector(".menu-list").style.display = 'none';
        })

        $('.menu-list').hover(() => {
            document.querySelector(".menu-list").style.display = 'block';
       }, () => {
           document.querySelector(".menu-list").style.display = 'none';
       })
    })
}

var listA = document.querySelectorAll('.pagination li a');
var isActive = false;

listA.forEach(a => {
    if(a.href.toString() === location.href.toString()) {
        a.parentElement.classList.add("active");
        isActive = true;
    }
})

if(!isActive) {
    listA.item(0).parentElement.classList.add("active");
}

var listOptions = document.querySelectorAll("#numberP option");

listOptions.forEach(option => {
    const attrSelected = document.createAttribute("selected");
    if(location.href.includes(option.getAttribute("value"))) {
        option.setAttributeNode(attrSelected);
    }
})





