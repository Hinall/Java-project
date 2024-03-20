$(document).ready(function () {
    var JSON_DATA = {
        user_name: "admin@gmail.com",
        password: "Admin@123"
    };
    let token;
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/digitaltwin/weblogin",
        contentType: "application/json",
        async: false,
        data: JSON.stringify(JSON_DATA),
        success: function (res) {
            token = res.data[0].accessToken;
            console.log(token);
            localStorage.setItem("token", token);
        }
    });

    var grid_input = {
        ward_no: "",
        omplaint_date_from: "",
        complaint_date_to: ""
    };
    let table_data;
    let table = $('#table_id');

    $.ajax({
        url: "http://localhost:8090/digitaltwin/dashboard/get_retms_dashboard_grid_data",
        type: 'post',
        async: false,
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', localStorage.getItem("token"));
        },
        data: JSON.stringify(grid_input),
        success: function (res) {
            console.log(JSON.parse(res));
            parsed_data = JSON.parse(res);
            table_data = parsed_data.data; // No need to parse JSON, it's already an object
            console.log(table_data);
            setDatatable(table_data)
        }
    });
    function setDatatable(data) {
        // table.clear().draw();
        $('#table_id').DataTable().clear().destroy();
        $('#table_id').DataTable({
            data: data,
            orderCellsTop: true,
            fixedHeader: true,
            dom: 'Bfrtp',
            columns: [
                { data: 'ward_no', title: 'Ward Id' },
                { data: 'compaint_created', title: 'Complaint Created' },
                { data: 'reference_number', title: 'Reference Number' },
                { data: 'complaint_latlong', title: 'Complaint LatLong' },
                { data: 'complaint_image_url', title: 'Complaint Image url', orderable: false },
                { data: 'complaint_number', title: 'Complaint Number', orderable: false },
                { data: 'notice_issued', title: 'Notice Issued', orderable: false },
                { data: 'speaking_order_issued', title: 'Speaking Order Issued' },
                { data: 'demolition_done', title: 'Demotion Done', orderable: false },
                { data: 'noticed_closed', title: 'Map', orderable: false },
                { data: 'complaint_created_date', title: 'Complaint Created Date', orderable: false },
                { data: 'created_on', title: 'Created On', orderable: false },
                { data: 'notice_issued_date', title: 'Notice Issued Date', orderable: false },
                { data: 'speaking_order_issued_date', title: 'Speaking Order Issued Date', orderable: false },
                { data: 'demolition_done_date', title: 'Demolition Done Date', orderable: false },
                { data: 'demolition_image_url', title: 'Demolition Image Url', orderable: false }
            ],
            columnDefs: [
                { targets: '_all', className: 'text-center' } // Apply text-center class to all columns
            ]
        });

        // Clone the table header for filtering
        $('#table_id thead tr').clone(true).addClass('filters').appendTo('#table_id thead');


        // Add filtering functionality to the cloned header
        $('#table_id thead .filters th').each(function () {
            var title = $(this).text();
            $(this).html('<input type="text" placeholder="' + title + '" />');

            $('input', this).on('keyup change', function () {
                if (table.column($(this).parent().index()).search() !== this.value) {
                    table
                        .column($(this).parent().index())
                        .search(this.value)
                        .draw();
                }
            });
        });

    };


    // Fetch state name for dropdown
    $.ajax({
        url: "http://localhost:8090/digitaltwin/get_state_name",
        type: 'GET',
        async: false,
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', localStorage.getItem("token"));
        },
        success: function (res) {
            console.log(JSON.parse(res));
            parsed_data = JSON.parse(res);
            let all_state_name = parsed_data.data; // No need to parse JSON, it's already an object
            console.log(all_state_name);
            all_state_name.forEach((item) => {

                $("#state_name_dropdown").append(`<li><a class="dropdown-item" href="#">${item}</a></li>`);
            });
        },
    });
    let selected_stateName;
    const all_state_name = [];
    $(document).on('click', '#state_name_dropdown  a', function () {
        selected_stateName = $(this).text();
        console.log(selected_stateName);
        $("#navbarScrollingDropdown_statename").text('state:' + selected);
        findWArdIdByStateName(selected);

        all_state_name.forEach((item) => {

            $("#state_name_dropdown").append(`<li><a class="dropdown-item" href="#">${item}</a></li>`);
        });
    });
    let selected_wardId;
    $(document).on('click', '#wardId_dropdown  a', function () {
        selected_wardId = $(this).text();
        console.log(selected_wardId);
        $("#navbarScrollingDropdown_wardId").text('ward-id:' + selected);

        $.ajax({
            url: "http://localhost:8090/digitaltwin/grid_data_by_ward/" + selected,
            type: 'POST',
            async: false,
            contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', localStorage.getItem("token"));
            },
            success: function (res) {
                console.log(JSON.parse(res));
                parsed_data = JSON.parse(res);
                let gridDataByWardId = parsed_data.data; // No need to parse JSON, it's already an object
                console.log(gridDataByWardId);
                $(".filters").hide();
                setDatatable(gridDataByWardId);
            },
        });

    });

    function findWArdIdByStateName(state_name) {
        $("#wardId_dropdown").empty();
        $.ajax({
            url: "http://localhost:8090/digitaltwin/get_ward_id/" + state_name,
            type: 'POST',
            async: false,
            contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', localStorage.getItem("token"));
            },
            success: function (res) {
                console.log(JSON.parse(res));
                parsed_data = JSON.parse(res);
                let state_wise_id = parsed_data.data; // No need to parse JSON, it's already an object
                console.log(state_wise_id);
                state_wise_id.forEach((item) => {

                    $("#wardId_dropdown").append(`<li><a class="dropdown-item" href="#">${item}</a></li>`);
                });
            },
        });
    }

    Cesium.Ion.defaultAccessToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI4MWQ1YTZhYS1jYzZjLTQ2ODEtYWRjMS1mYzU3MDEwNzQwYjEiLCJpZCI6MTk5MzE4LCJpYXQiOjE3MDk0NzE3MzZ9.5Rlne10Z-QN4x6BnVpL7BF2Jqy8nnDUvH_Q8ZNyDXck';
    const viewer = new Cesium.Viewer('cesiumContainer', {
        terrain: Cesium.Terrain.fromWorldTerrain(),
    });

    // Fly the camera to San Francisco at the given longitude, latitude, and height.
    viewer.camera.flyTo({
        destination: Cesium.Cartesian3.fromDegrees(72.8777, 19.0760, 10000),
        orientation: {
            heading: Cesium.Math.toRadians(0.0),
            pitch: Cesium.Math.toRadians(-90.0),
        }
    });
var provider;
var layer;
$('#checkbox1').change(function () {
    if ($(this).is(':checked')) {
        provider = new Cesium.WebMapServiceImageryProvider({
            url: 'https://re-gis.mcgm.gov.in/erdas-apollo/vector/MCGM?',
            layers: 'ward_boundary',
            parameters: {
                format: 'image/png',
                transparent: true
            }
        });
        layer=viewer.imageryLayers.addImageryProvider(provider); 
        alert('Imagery layer added');
    } else {
            viewer.imageryLayers.remove(layer); 
            alert('Imagery layer removed');
    }
});
$('#checkbox2').change(function () {
    if ($(this).is(':checked')) {

        
        provider = new Cesium.WebMapServiceImageryProvider({
            url: 'https://re-gis.mcgm.gov.in/erdas-apollo/vector/MCGM?',
            layers: '',
            parameters: {
                format: 'image/png',
                transparent: true
            }
        });
        viewer.imageryLayers.addImageryProvider(provider); 
  
    } else {
            viewer.imageryLayers.remove(provider , false); 
    }
});
   
    // async function cesium3d(URL) {
    //     var building = await Cesium.Cesium3DTileset.fromUrl();

    //     viewer.scene.primitives.add(building);
    //     building.show = true;
    // }



//     //    }
//     let URL="";
//     $(document).on('click', '#findUrl', function (state_name, ward_id) {
//         var data = {
//             "stateName": "",
//             "wardId": ""
//         }
//         $.ajax({
//             url: "http://localhost:8090/digitaltwin/get_url_from_stateId_wardId/",
//             type: 'POST',
//             async: false,
//             contentType: 'application/json',
//             data: data,
//             beforeSend: function (xhr) {
//                 xhr.setRequestHeader('Authorization', localStorage.getItem("token"));
//             },
//             success: function (res) {
//                 console.log(res);
//                 // URL = res;
//                 URL="https://re-gis.mcgm.gov.in:9443/data/Building_D/tileset.json";
//                 cesium3d(URL);;
//             },
//         });

//     });




});
