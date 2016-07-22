/**
 * Created by icondor on 16/07/16.
 */

/**
 * Created by condor on 03/04/15.
 */

function listFeed(feeds) { // feeds means all rows in the db
    var myFeedHTML = document.getElementById('myFeedHTML')
    var listHtml = ''; // a temporary to store the content

    for (var i = 0; i < feeds.length; i++) {
        var feed = feeds[i]; // one row in db
        var oneHTMLRow =
            '<h2>' +
            feed.content+'   '+
            feed.insertDate+
            '</h2>';
        listHtml += oneHTMLRow;
    }
    myFeedHTML.innerHTML = listHtml;
}

function loadFeed() {
    $.ajax({
        url: 'myfeed'
    }).done(function (response) {
        listFeed(response.myFeed);
    });
}


function addTweet() {
    var toDoText = document.getElementById('tweet').value;
    $.ajax({
        url: 'addtweeter?textToTweet='+toDoText
    }).done(function (response) {
        location.href = "index.html";
    });
}
function sendValues(){
    xmlHttp = new XMLHttpRequest();
    var url ="adduser"+"?user="+document.getElementById("user").value+"&pass="+document.getElementById("pass").value+
        "&adress="+document.getElementById("adress").value
    xmlHttp.open("GET",url,true);
    xmlHttp.send();

    document.getElementById('user').value='';
    document.getElementById('pass').value='';
    document.getElementById('adress').value='';
}
function addUser() {
    var toDoText = document.getElementById('usr').value;
    $.ajax({
        url: 'userN?userName='+toDoText
    }).done(function (response) {
        location.href = "index.html";
    });
}

function listUsrFeed(feeds) { // feeds means all rows in the db
    var myFeedHTML = document.getElementById('userFeed')
    var listHtml = ''; // a temporary to store the content

    for (var i = 0; i < feeds.length; i++) {
        var feed = feeds[i]; // one row in db
        var oneHTMLRow =
            '<h2>' +
            feed.content+'   '+
            feed.insertDate+
            '</h2>';
        listHtml += oneHTMLRow;
    }
    myFeedHTML.innerHTML = listHtml;
}

function loadUsrFeed() {
    $.ajax({
        url: 'usrFeed'
    }).done(function (response) {
        listUsrFeed(response.myFeed);
    });
}




