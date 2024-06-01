/*=========================
 *  	table click button update
 *========================*/
function editInformation( updateInfo ){
     $("#updateOfficeID").val( updateInfo.OfficeID );
     $("#updateOfficeName").val( updateInfo.OfficeName );
     $("#updateofficeOwnerName").val( updateInfo.officeOwnerName );
     $("#updateaddress").val( updateInfo.address );
} // end click button update
/*=========================
 *  Modal Button Save Change
 *========================*/
function updateDataInformation(){

   var updateOfficeID = $("#updateOfficeID").val();
   var updateOfficeName = $("#updateOfficeName").val();
   var updateofficeOwnerName = $("#updateofficeOwnerName").val();
   var updateaddress = $("#updateaddress").val();
   if ( updateOfficeID == "" ) {
                Swal.fire(
                          'Required!',
                          'Office ID Should not be empty',
                          'error'
                        )
   } else if ( updateOfficeName == "") {
                        Swal.fire(
                          'Required!',
                          'Office Name Should not be empty',
                          'error'
                        )
   } else if ( updateofficeOwnerName == "") {
                        Swal.fire(
                          'Required!',
                          'Office Owner Name Should not be empty',
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
                                             "OfficeID"         : updateOfficeID,
                                             "OfficeName"		: updateOfficeName,
                                             "officeOwnerName"	: updateofficeOwnerName,
                                             "address"	        : updateaddress
                                           }
                                };
              $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/api/v1/Office/update",
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
   var OfficeName = $("#OfficeName").val();
   var officeOwnerName = $("#officeOwnerName").val();
   var address = $("#address").val();
    if ( OfficeName == "" ) {
                Swal.fire(
                          'Required!',
                          'Office Name Should not be empty',
                          'error'
                        )
   } else if ( officeOwnerName == "") {
                        Swal.fire(
                          'Required!',
                          'Office Owner Name Should not be empty',
                          'error'
                        )
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
                                                     "OfficeName"       : OfficeName,
                                                     "officeOwnerName"	: officeOwnerName,
                                                     "address"	        : address

                                                   }
                                        };
                      $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/api/v1/Office/register",
                            url: "/api/v1/Office/register",
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
           });
   }

} // end function register
function menuOpen(){

};
/*=========================
 *  	Main Function
 *========================*/
$(document).ready(function(){
   editInformation();
   updateDataInformation();
   registerDataInformation();
   menuOpen();
});