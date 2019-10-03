var file;
function dragAndDrop(event) {
    event.preventDefault();
    event.stopPropagation();
    var fileName = URL.createObjectURL(event.dataTransfer.files[0]);
    var $preview = $(".image-drop");
    if($("div.image-drop img").length > 0 ) {
        $("div.image-drop > img").remove();
    } else{
        $("div.image-drop > p").remove();
    }
    var previewImg = document.createElement("img");
    previewImg.setAttribute("class", "img-circle img-thumbnail image-upload uploaded-image");
    previewImg.setAttribute("src", fileName);
    $preview.append(previewImg);
    file = event.dataTransfer.files[0];
    dragLeave(event);
};
function dragEnter(event) {
    event.preventDefault();
    event.stopPropagation();
    var $div = $("div.image-drop");
    $div.removeClass("border-dotted");
    $div.addClass("border-dotted-big");
    return false;
};
function dragLeave(event) {
    event.preventDefault();
    event.stopPropagation();
    var $div = $("div.image-drop");
    $div.removeClass("border-dotted-big");
    $div.addClass("border-dotted");
    return false;
};
$("button.btn-left").click(function (event) {
    event.preventDefault();
    var formData = new FormData();
    console.log(file);
    formData.append("imagefile", file);
    var token = $("meta[name='_csrf']").attr("content");
    $.ajax({
        url: '/user/uploadImage',
        type: 'POST',
        enctype: 'multipart/form-data',
        headers: { "X-CSRF-TOKEN": token },
        data: formData,
        processData: false,
        contentType: false,
        error: function () {
            alert("Please, try to use image smaller size");
        }
    }).then(function () {
        window.location.reload();
    })
});
$(function() {
    $.datepicker.regional['ru'] = {
        closeText: 'Закрыть',
        prevText: 'Пред',
        nextText: 'След',
        currentText: 'Сегодня',
        monthNames: ['Январь','Февраль','Март','Апрель','Май','Июнь',
            'Июль','Август','Сентябрь','Октябрь','Ноябрь','Декабрь'],
        monthNamesShort: ['Янв','Фев','Мар','Апр','Май','Июн',
            'Июл','Авг','Сен','Окт','Ноя','Дек'],
        dayNames: ['воскресенье','понедельник','вторник','среда','четверг','пятница','суббота'],
        dayNamesShort: ['вск','пнд','втр','срд','чтв','птн','сбт'],
        dayNamesMin: ['Вс','Пн','Вт','Ср','Чт','Пт','Сб'],
        weekHeader: 'Нед',
        dateFormat: 'dd.mm.yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: '',};
    $.datepicker.setDefaults($.datepicker.regional['ru']);
    var options = {
        changeMonth: true,
        changeYear: true,
        highlightWeek: true,
        yearRange: "-100:+0"
    };
    $( "#datepicker" ).datepicker(options);
});