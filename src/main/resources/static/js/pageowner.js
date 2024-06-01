/*=========================
 *  	table click button update
 *========================*/
function editInformation( updateInfo ){
     $("#pageOwnerIDUpdate").val( updateInfo.pageOwnerID );
     $("#pageOwnerNameUpdate").val( updateInfo.pageOwnerName );
     $("#phoneUpdate").val( updateInfo.phone );
     $("#addressUpdate").val( updateInfo.address );
} // end click button update
/*=========================
 *  Modal Button Save Change
 *========================*/
function updateDataInformation(){

   var pageOwnerID = $("#pageOwnerIDUpdate").val();
   var phone = $("#phoneUpdate").val();
   var pageOwnerName = $("#pageOwnerNameUpdate").val();
   var address = $("#addressUpdate").val();
   if ( pageOwnerID == "" ) {
                Swal.fire(
                          'Required!',
                          'Page Owner ID Should not be empty',
                          'error'
                        )
   } else if ( pageOwnerName == "") {
                        Swal.fire(
                          'Required!',
                          'Page Owner Name Should not be empty',
                          'error'
                        )
   } else {
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
                                                "pageOwnerID"       : pageOwnerID,
                                                "phone"		        : phone,
                                                "pageOwnerName" 	: pageOwnerName,
                                                "address"	        : address
                                              }
                                   };
                 $.ajax({
                       type: "POST",
                       contentType: "application/json",
                       url: "/api/v1/page-owner/update",
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

   }


} // end Modal button Save Change

/*=========================
 *  	Register Function
 *========================*/
function registerDataInformation(){
   var pageOwnerName = $("#pageOwnerName").val();
   var phone = $("#phone").val();
   var address = $("#address").val();
    if ( pageOwnerName == "" ) {
                Swal.fire(
                          'Required!',
                          'Page Owner Name Should not be empty',
                          'error'
                        )
   }  else {
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
                                                     "pageOwnerName"    : pageOwnerName,
                                                     "phone"	        : phone,
                                                     "address"	        : address

                                                   }
                                        };
                      $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/api/v1/page-owner/register",
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

/*=========================
 *  	Main Function
 *========================*/
$(document).ready(function(){
   editInformation();
   updateDataInformation();
   registerDataInformation();
});