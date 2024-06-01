/*=========================
 * Percent On change
 *========================*/
function percentOnChange ( changeMode ) {
   if ( 'office' == changeMode ) {
        var officePercent = $("#officePercent").val();
        if ( officePercent == "" || officePercent == 0 ) {
             $("#officePercent").val(0);
             $("#ownerPercent").val(0);
        } else if ( officePercent > 100 ) {
              $("#officePercent").val(0);
              $("#ownerPercent").val(0);
              Swal.fire(
                                      'Required!',
                                      'Office percent cannot bigger than 100',
                                      'error'
                                    )
        } else {
            var newOwnerPercent = 100 - officePercent;
            $("#ownerPercent").val(newOwnerPercent);
        }
   } else if ( 'owner' == changeMode ) {
       var ownerPercent = $("#ownerPercent").val();
       if ( ownerPercent == "" || ownerPercent == 0 ) {
            $("#officePercent").val(0);
            $("#ownerPercent").val(0);
       } else if ( ownerPercent > 100 ) {
             $("#officePercent").val(0);
             $("#ownerPercent").val(0);
             Swal.fire(
                                     'Required!',
                                     'Owner percent cannot bigger than 100',
                                     'error'
                                   )
       } else {
         var newOfficePercent = 100 - ownerPercent;
         $("#officePercent").val(newOfficePercent);
       }
   } if ( 'officeUpdate' == changeMode ) {
         var officePercentUpdate = $("#officePercentUpdate").val();
         if ( officePercentUpdate == "" || officePercentUpdate == 0 ) {
              $("#officePercentUpdate").val(0);
              $("#ownerPercentUpdate").val(0);
         } else if ( officePercentUpdate > 100 ) {
                $("#officePercentUpdate").val(0);
                $("#ownerPercentUpdate").val(0);
               Swal.fire(
                                       'Required!',
                                       'Office percent cannot bigger than 100',
                                       'error'
                                     )
         } else {
             var newOwnerPercentUpdate = 100 - officePercentUpdate;
             $("#ownerPercentUpdate").val(newOwnerPercentUpdate);
         }
    } else if ( 'ownerUpdate' == changeMode ) {
        var ownerPercentUpdate = $("#ownerPercentUpdate").val();
        if ( ownerPercentUpdate == "" || ownerPercentUpdate == 0 ) {
             $("#officePercentUpdate").val(0);
             $("#ownerPercentUpdate").val(0);
        } else if ( ownerPercentUpdate > 100 ) {
              $("#officePercentUpdate").val(0);
              $("#ownerPercentUpdate").val(0);
              Swal.fire(
                                      'Required!',
                                      'Owner percent cannot bigger than 100',
                                      'error'
                                    )
        } else {
          var newOfficePercentUpdate = 100 - ownerPercentUpdate;
          $("#officePercentUpdate").val(newOfficePercentUpdate);
        }
    }
   //
}

/*=========================
 * table click button update
 *========================*/
function editInformation( updateInfo ){
     $("#pageIDUpdate").val( updateInfo.pageID );
     $("#pageNameUpdate").val( updateInfo.pageName );
     $("#pageOwnerIDUpdate").val( updateInfo.pageOwnerID );
    // $("#pageOwnerName").val( updateInfo.pageOwnerName );
     $("#mainOwnerIDUpdate").val( updateInfo.mainOwnerID );
    // $("#mainOwnerName").val( updateInfo.mainOwnerName );
     $("#pageEarningUpdate").val( updateInfo.pageEarn );
     $("#ownerAmountUpdate").val( updateInfo.ownerAmount );
     $("#officeAmountUpdate").val( updateInfo.officeAmount );
     $("#ownerPercentUpdate").val( updateInfo.ownerPercent );
     $("#officePercentUpdate").val( updateInfo.officePercent );
     $("#pageMonthIDUpdate").val( updateInfo.monthID );
     $("#pageStatusUpdate").val( updateInfo.pageStatus );
     $("#pageLinkUpdate").val( updateInfo.pageLink );
     $("#monthID").val( updateInfo.monthID );
     $("#years").val( updateInfo.years );
} // end click button update
/*=========================
 *  Modal Button Save Change
 *========================*/
function updateDataInformation(){

   var pageIDUpdate = $("#pageIDUpdate").val();
   var mainOwnerIDUpdate = $("#mainOwnerIDUpdate").val();
   var pageMonthIDUpdate = $("#pageMonthIDUpdate").val();
   var pageEarningUpdate = $("#pageEarningUpdate").val();
   var pageStatusUpdate = $("#pageStatusUpdate").val();
   var pageOwnerIDUpdate = $("#pageOwnerIDUpdate").val();
   var pageNameUpdate = $("#pageNameUpdate").val();
   var officePercentUpdate = $("#officePercentUpdate").val();
   var ownerPercentUpdate = $("#ownerPercentUpdate").val();
   var pageLink = $("#pageLinkUpdate").val();
   var monthID = $("#monthID").val();
   var years = $("#years").val();
   if ( pageNameUpdate == "" ) {
                Swal.fire(
                          'Required!',
                          'Page Name Should not be empty',
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
                                                  "pageID"        : pageIDUpdate,
                                                  "mainOwnerID"   : mainOwnerIDUpdate,
                                                  "pageMonthID"   : pageMonthIDUpdate,
                                                  "pageEarn"      : pageEarningUpdate,
                                                  "pageStatus"    : pageStatusUpdate,
                                                  "pageOwnerID"   : pageOwnerIDUpdate,
                                                  "pageName"      : pageNameUpdate,
                                                  "officePercent" : officePercentUpdate,
                                                  "ownerPercent"  : ownerPercentUpdate,
                                                  "pageLink"      : pageLink,
                                                  "monthID"       : monthID,
                                                  "years"         : years
                                              }
                                   };
                 $.ajax({
                       type: "POST",
                       contentType: "application/json",
                       url: "/api/v1/page-list/update",
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
   var pageID = $("#pageID").val();
   var mainOwnerID = $("#mainOwnerID").val();
   var pageMonthID = $("#pageMonthID").val();
   var pageEarn = $("#pageEarning").val();
   var pageStatus = $("#pageStatus").val();
   var pageOwnerID = $("#pageOwnerID").val();
   var pageName = $("#pageName").val();
   var officePercent = $("#officePercent").val();
   var ownerPercent = $("#ownerPercent").val();
   var pageLink = $("#pageLink").val();
    if ( pageID == "" ) {
                Swal.fire(
                          'Required!',
                          'Page ID Should not be empty',
                          'error'
                        )
   } else if ( pageName == ""){
                Swal.fire(
                          'Required!',
                          'Page name not be empty',
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
                                                    "pageID"        : pageID,
                                                    "mainOwnerID"   : mainOwnerID,
                                                    "pageMonthID"   : pageMonthID,
                                                    "pageEarn"      : pageEarn,
                                                    "pageStatus"    : pageStatus,
                                                    "pageOwnerID"   : pageOwnerID,
                                                    "pageName"      : pageName,
                                                    "officePercent" : officePercent,
                                                    "ownerPercent"  : ownerPercent,
                                                    "pageLink"      : pageLink

                                                   }
                                        };
                      $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/api/v1/page-list/register",
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

/*=========================
 *  	Main Function
 *========================*/
$(document).ready(function(){
   editInformation();
   updateDataInformation();
   registerDataInformation();
   percentOnChange();

});