
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

listA.forEach(a => {
    if(a.href.toString() === location.href.toString()) {
        a.parentElement.classList.add("active");
    }
})





