/*=========================
*  	table click button update
*========================*/
function editInformation( updateInfo ){
 $("#mainOwnerIDUpdate").val( updateInfo.mainOwnerID );
 $("#mainOwnerNameUpdate").val( updateInfo.mainOwnerName );
 $("#addressUpdate").val( updateInfo.address );
} // end click button update
/*=========================
*  Modal Button Save Change
*========================*/
function updateDataInformation(){

var mainOwnerID = $("#mainOwnerIDUpdate").val();
var mainOwnerName = $("#mainOwnerNameUpdate").val();
var address = $("#addressUpdate").val();
if ( mainOwnerID == "" ) {
            Swal.fire(
                      'Required!',
                      'Main Owner ID Should not be empty',
                      'error'
                    )
} else if ( mainOwnerName == "") {
                    Swal.fire(
                      'Required!',
                      'Main Owner Name Should not be empty',
                      'error'
                    )
}
Swal.fire({
  title: "Are you sure?",
  text: "Do you want to update ?",
  icon: "warning",
  showCancelButton: true,
  confirmButtonColor: "#3085d6",
  cancelButtonColor: "#d33",
  confirmButtonText: "Yes, Update it!"
}).then((result) => {
  if (result.isConfirmed) {

        var requestData = {
                                "body":{
                                         "mainOwnerID"      : mainOwnerID,
                                         "mainOwnerName"    : mainOwnerName,
                                         "address"	        : address
                                       }
                            };
          $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/api/v1/main-owner/update",
                data: JSON.stringify(requestData),
                dataType: 'json',
                cache: false,
                timeout: 600000,
                success: function (data) {
                   var header = data.header;
                   if ( "Y" == header.successYN ) {
                      Swal.fire({
                          title: "Updated!",
                          text: "Your information has been updated.",
                          icon: "success"
                       });
                      // reload page
                      setTimeout("window.location.reload()",1000);
                   } else {
                       Swal.fire(
                                  'Fail',
                                  'Cannot update new data',
                                  'error'
                        )
                   }
                },
                error: function (e) {
                    Swal.fire(
                              'Fail',
                              'Cannot add new data',
                              'error'
                    )

                }
            });

  }
});

} // end Modal button Save Change

/*=========================
*  	Register Function
*========================*/
function registerDataInformation(){
var mainOwnerName = $("#mainOwnerName").val();
var address = $("#address").val();
if ( mainOwnerName == "" ) {
                Swal.fire(
                      'Required!',
                      'Main Owner Name Should not be empty',
                      'error'
                    );
} else {
    Swal.fire({
      title: "Are you sure?",
      text: "Do you want to register ?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, Register it!"
    }).then((result) => {
      if (result.isConfirmed) {

            var requestData = {
                                    "body":{
                                             "mainOwnerName"    : mainOwnerName,
                                             "address"	        : address

                                           }
                                };
              $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/api/v1/main-owner/register",
                    data: JSON.stringify(requestData),
                    dataType: 'json',
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                       var header = data.header;
                       if ( "Y" == header.successYN ) {
                          Swal.fire({
                              title: "Registered!",
                              text: "Your information has been registered.",
                              icon: "success"
                           });
                          // reload page
                          setTimeout("window.location.reload()",1000);
                       } else {
                           Swal.fire(
                                      'Fail',
                                      'Cannot register new data',
                                      'error'
                            )
                       }
                    },
                    error: function (e) {
                        Swal.fire(
                                  'Fail',
                                  'Cannot add new data',
                                  'error'
                        )

                    }
                });

      }
    }); // end sweet alert
}
} // end function register
/*=========================
*  	Main Function
*========================*/
$(document).ready(function(){
editInformation();
updateDataInformation();
registerDataInformation();
});