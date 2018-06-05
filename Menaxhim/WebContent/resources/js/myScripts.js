var editPassword = function() {
	$('#editPassword').modal('toggle');
}

var editModal = function(name, surname, email, id) {
	$('.modalEmri').val(name);
	$('.modalMbiemri').val(surname);
	$('.modalEmail').val(email);
	$('#editUser\\:userId').val(id);
	$('#editModal').modal('toggle');
}
var  editFshiTaskModal = function(){
	$('#editFshiTask').modal('toggle');
}

var editTaskModal = function(name, description, startDate, endDate, id,
		gjendjeId) {
	$('.modalName').val(name);
	$('.modalDescription').val(description);
	$('.modalStartDate').val(startDate);
	$('.modalEndDate').val(endDate);
	$('#editTask\\:taskId').val(id);
	$('.modalGjendjeId').val(gjendjeId);
	$('#editTask\\:gjendjeId').val(gjendjeId);
	$('#editTaskModal').modal('toggle');
}

$('.btn-success').removeClass('ui-button');
$('.btn-success').removeClass('ui-widget');
$('.btn-success').removeClass('ui-state-default');
$('.btn-success').removeClass('ui-corner-all');
$('.btn-success').removeClass('ui-button-text-only');
$('.btn-primary').removeClass('ui-button');
$('.btn-primary').removeClass('ui-widget');
$('.btn-primary').removeClass('ui-state-default');
$('.btn-primary').removeClass('ui-corner-all');
$('.btn-primary').removeClass('ui-button-text-only');
$('.btn-default').removeClass('ui-button');
$('.btn-default').removeClass('ui-widget');
$('.btn-default').removeClass('ui-state-default');
$('.btn-default').removeClass('ui-corner-all');
$('.btn-default').removeClass('ui-button-text-only');

$('#myDatepicker').datetimepicker({
	format : 'MM/DD/YYYY HH:mm:ss'
});
$('#myDatepicker2').datetimepicker({
	format : 'MM/DD/YYYY HH:mm:ss'
});
$('#myDatepicker3').datetimepicker({
	format : 'MM/DD/YYYY HH:mm:ss'
});
$('#myDatepicker4').datetimepicker({
	format : 'MM/DD/YYYY HH:mm:ss'
});

$('.reset').on('click', function() {
	location.reload();
});
$('.date-picker').datetimepicker();
