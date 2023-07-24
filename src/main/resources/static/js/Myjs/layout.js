
if(location.href.includes('nav')) {
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



