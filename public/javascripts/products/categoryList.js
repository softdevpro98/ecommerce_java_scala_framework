var colDefs = [
  { "visible": false, "searchable": false, "targets": 0 }
];

$(function() {
  $('#categoriesTable').DataTable({
    'ajax': {
      'url': "/app/datacollection/listingData",
      'dataSrc': ""
    },
    'lengthMenu': [[10, 25, 50, -1], [10, 25, 50, "All"]],
    "columnDefs": colDefs
  });
})
