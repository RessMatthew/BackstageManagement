$(document).ready(function() {

    $(".accounts").click(function (){

        var username = this.id;
        var id = username.substring(0,username.length-5);
        console.log(id);
        location.href="/account/manageAccount?username="+id;

    })



})