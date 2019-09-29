var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + host + webCtx + "/push";

var chatClient = null;

function connect() {
    chatClient = new WebSocket(endPointURL);
    chatClient.onmessage = function (event) {
        $.notify({
            message: event.data
        },{
            type: 'info'
        });
    };
}

function disconnect() {
    chatClient.close();
}

function sendMessage() {
    var messageBody = $(".message-body").val().trim();
    if(messageBody) {
        var companionUserId = $(".companion-user").val().trim();
        var jsonObj = {"companion": companionUserId, "message": messageBody};
        chatClient.send(JSON.stringify(jsonObj));
    }
}

$(document).ready(function () {
    connect();
    $('.submit-message').on("click", function () {
        sendMessage();
    });
});
