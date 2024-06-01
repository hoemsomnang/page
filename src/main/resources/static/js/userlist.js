/*=========================
 *  	table click button update
 *========================*/
function editInformation( updateInfo ){
     $("#userIDUpdate").val( updateInfo.userID );
     $("#fullNameUpdate").val( updateInfo.fullName );
     $("#rolesUpdate").val( updateInfo.roles );
     $("#passwordUpdate").val( "");
     $("#confirmPasswordUpdate").val( "");
     $("#mainOwnerIDUpdate").val( updateInfo.mainOwnerID);
     var roles = $("#rolesUpdate").val();
     if ( roles == "ADMIN") {
          $('#mainOwnerIDUpdate').prop('disabled', false);
     } else if( roles == "USER" ){
          $('#mainOwnerIDUpdate').prop('disabled', true);
     }

} // end click button update
/*=========================
 *  Modal Button Save Change
 *========================*/
function updateDataInformation(){

   var userID = $("#userIDUpdate").val();
   var fullName = $("#fullNameUpdate").val();
   var password = $("#passwordUpdate").val();
   var confirmPassword= $("#confirmPasswordUpdate").val();
   var roles= $("#rolesUpdate").val();
    var mainOwnerID = $("#mainOwnerIDUpdate").val();
      if ( roles == "USER" ) {
           mainOwnerID = 0;
      }
   if ( fullName == "" ) {
                Swal.fire(
                          'Required!',
                          'User full name not be empty',
                          'error'
                        )
   }  else  if (  password != "" && confirmPassword == "" ) {
                          Swal.fire(
                           'Required!',
                           'Please input confirm password',
                           'error'
                         )
     } else if ( password == "" && confirmPassword != "" ) {
                         Swal.fire(
                           'Required!',
                           'Please input password',
                           'error'
                         )
     } else if ( password != confirmPassword ) {
       Swal.fire(
                                   'Required!',
                                   'Password not match',
                                   'error'
                                 )
     }  else {
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
                                                "userID"      : userID,
                                                "fullName"	  : fullName,
                                                "roles" 	  : roles,
                                                "password"	  : password,
                                                "mainOwnerID" : mainOwnerID
                                              }
                                   };
                 $.ajax({
                       type: "POST",
                       contentType: "application/json",
                      url: "/api/v1/user/update",
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
   var userID = $("#userID").val();
   var fullName = $("#fullName").val();
   var password = $("#password").val();
   var confirmPassword = $("#confirmPassword").val();
   var roles = $("#roles").val();
   var mainOwnerID = $("#mainOwnerID").val();
   if ( roles == "USER" ) {
        mainOwnerID = 0;
   }
    if ( userID == "" ) {
                Swal.fire(
                          'Required!',
                          'UserID Should not be empty',
                          'error'
                        )
   } else if ( fullName == "" ) {
              Swal.fire(
                      'Required!',
                      'Full Name not be empty',
                      'error'
               )
   } else if ( password != confirmPassword ){
              Swal.fire(
                      'Required!',
                      'Please check your password again',
                      'error'
               )
   }else {
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
                                                     "userID"      : userID,
                                                     "fullName"	   : fullName,
                                                     "password"	   : password,
                                                     "roles"       : roles,
                                                     "mainOwnerID" : mainOwnerID

                                                   }
                                        };
                      $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/api/v1/user/register",
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
                                              header.resultMessage,
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

function userRoleOnChange(){
   var roles = $("#roles").val();
   if ( roles == "ADMIN") {
    $('#mainOwnerID').prop('disabled', false);
   } else if( roles == "USER" ){
    $('#mainOwnerID').prop('disabled', true);
   }
}


function registerModel(){
     $("#userID").val("");
     $("#password").val("");
     $("#confirmPassword").val("");
     $('#mainOwnerID').prop('disabled', true);
}

function userRoleUpdateOnChange(){
   var roles = $("#rolesUpdate").val();
   if ( roles == "ADMIN") {
    $('#mainOwnerIDUpdate').prop('disabled', false);
   } else if( roles == "USER" ){
    $('#mainOwnerIDUpdate').prop('disabled', true);
   }
}

/*=========================
 *  	Main Function
 *========================*/
$(document).ready(function(){
   editInformation();
   updateDataInformation();
   registerDataInformation();
   registerModel();
   userRoleOnChange();
   userRoleUpdateOnChange();
});