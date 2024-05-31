import {saveSupplier, updateSupplier, deleteSupplier, getAllSuppliers} from "../api/SupplierAPI.js";
import {SupplierModel} from "../model/SupplierModel.js";

let allSuppliers = [];
let localSuppliers = [];
let internationalSuppliers = [];

//handle popup
$("body").click(function (event) {
    if (!$(event.target).closest('.sup-popup').length && !$(event.target).closest('#btn-add-sup-popup').length && !$(event.target).closest(".btn-sup-update").length) {
        $(".sup-popup").css("display", "none");
    } else {
        $(".sup-popup").css("display", "block");
    }
});

//when document ready should be done
$(document).ready(async function () {
    // Prevent form submission on pressing Enter key
    $('.emp-save-form').on('keypress', function (e) {
        if (e.which === 13) {
            e.preventDefault();
        }
    });
    allSuppliers = await getAllSuppliers();
    loadAllSuppliers(allSuppliers);
    await filteringSuppliers();
    eachCustomerCountForLabel()
    $(":input").attr('autocomplete', 'off');
});

// each customer counts for label
const eachCustomerCountForLabel = () => {
    $(".suppliers-lbl").eq(0).text(internationalSuppliers.length);
    $(".suppliers-lbl").eq(1).text(localSuppliers.length);
}

// supplier edit icon fire
$(document).on('click', '.btn-sup-update', function () {
    selectedEditSupplierCode = $(this).closest("tr").find(".supplierCode").text();

    $("#sup-popup-header").text("UPDATE SUPPLIER");
    $("#sup-save-update-btn").text("Update Supplier");
    $("#supplierName").val($(this).closest("tr").find(".supplierName").text());
    $("#sup-category").val($(this).closest("tr").find(".supplierCategory").text().toLowerCase());
    $("#sup-email").val($(this).closest("tr").find(".supplierEmail").text());
    $("#sup-addressLine1").val($(this).closest("tr").find(".supplierAddress1").text());
    $("#sup-addressLine2").val($(this).closest("tr").find(".supplierAddress2").text());
    $("#sup-addressLine3").val($(this).closest("tr").find(".supplierAddress3").text());
    $("#sup-addressLine4").val($(this).closest("tr").find(".supplierAddress4").text());
    $("#sup-addressLine5").val($(this).closest("tr").find(".supplierAddress5").text());
    $("#sup-addressLine6").val($(this).closest("tr").find(".supplierAddress6").text());
    $("#sup-contactNo1").val($(this).closest("tr").find(".supplierContact1").text());
    $("#sup-contactNo2").val($(this).closest("tr").find(".supplierContact2").text());

    $(".sup-popup").css("display", "block");
});

//clear inputs
const clearInputs = () => {
    $("#supplierName").val('');
    $("#sup-category").val('LOCAL');
    $("#sup-email").val('');
    $("#sup-addressLine1").val('');
    $("#sup-addressLine2").val('');
    $("#sup-addressLine3").val('');
    $("#sup-addressLine4").val('');
    $("#sup-addressLine5").val('');
    $("#sup-addressLine6").val('');
    $("#sup-contactNo1").val('');
    $("#sup-contactNo2").val('');
}

// making card and loading data
function loadingData(supplier) {
    return $("#sup-table-body").append(`<tr>
           <td class="sup-name-id">
               <span class="supplierName">${supplier.supplierName}</span><br>
                <span class="supplierCode">${supplier.supplierCode}</span>
                </td>
                <td class="supplierCategory">${supplier.category}</td>
                <td><span class="supplierAddress1">${supplier.address1}</span><span class="supplierAddress2"> ${supplier.address2}</span><span class="supplierAddress3"> ${supplier.address3}</span><span class="supplierAddress4"> ${supplier.address4}</span><span class="supplierAddress5"> ${supplier.address5}</span><span class="supplierAddress6"> ${supplier.address6}</span></td>
                <td class="supplierContact1">${supplier.contact1}</td>
                <td class="supplierContact2">${supplier.contact2}</td>
                <td class="supplierEmail">${supplier.email}</td>
                <td class="sup-action-td">
                    <button class="btn-sup-remove"><i class="fa-solid fa-trash-can fa-lg" style="color: #f50529;"></i></button>
                    <button class="btn-sup-update"><i class="fa-solid fa-pen-to-square fa-lg"></i></button>
                </td>
            </tr>`)
}

//filtering suppliers
const filteringSuppliers = async () => {
    localSuppliers.length = 0;
    internationalSuppliers.length = 0;
    allSuppliers.map((supplier) => {
        supplier.category === "LOCAL" ? localSuppliers.push(supplier) : internationalSuppliers.push(supplier);
    });
}

//loading filtered suppliers when click radio buttons
$('input[name="sup-option"]').change(async function () {
    await filteredSuppliersForContainer($(this).val());
});

const filteredSuppliersForContainer = async (ratioButtonValue) => {
    await loadAllSuppliers(ratioButtonValue === "local" ? localSuppliers : ratioButtonValue === "international" ? internationalSuppliers : allSuppliers);
}

//search customer
$(".sup-search-input").on('input', async function () {
    $("#sup-table-body").empty();
    allSuppliers.map((supplier) => {
        if (supplier.supplierCode.toLowerCase().startsWith($(".sup-search-input").val().toLowerCase()) || supplier.supplierName.toLowerCase().startsWith($(".sup-search-input").val().toLowerCase()) || supplier.email.toLowerCase().startsWith($(".sup-search-input").val().toLowerCase()) ||  supplier.contact1.toLowerCase().startsWith($(".sup-search-input").val().toLowerCase()) ||  supplier.contact2.toLowerCase().startsWith($(".sup-search-input").val().toLowerCase())) {
            loadingData(supplier)
        }
    })
})

//save and update employee
let selectedEditSupplierCode;
$("#sup-save-update-btn").on("click", async function (event) {
    event.preventDefault();
    if ($("#sup-save-update-btn").text() === "Save Supplier") {
        await saveSupplier(new SupplierModel(
            null,
            $("#supplierName").val(),
            $("#sup-category").val().toUpperCase(),
            $("#sup-addressLine1").val(),
            $("#sup-addressLine2").val(),
            $("#sup-addressLine3").val(),
            $("#sup-addressLine4").val(),
            $("#sup-addressLine5").val(),
            $("#sup-addressLine6").val(),
            $("#sup-contactNo1").val(),
            $("#sup-contactNo2").val(),
            $("#sup-email").val()))
    } else {
        let updatedSupplier = new SupplierModel(
            null,
            $("#supplierName").val(),
            $("#sup-category").val().toUpperCase(),
            $("#sup-addressLine1").val(),
            $("#sup-addressLine2").val(),
            $("#sup-addressLine3").val(),
            $("#sup-addressLine4").val(),
            $("#sup-addressLine5").val(),
            $("#sup-addressLine6").val(),
            $("#sup-contactNo1").val(),
            $("#sup-contactNo2").val(),
            $("#sup-email").val()
        );
        await updateSupplier(updatedSupplier, selectedEditSupplierCode);
    }
    clearInputs();
    allSuppliers = await getAllSuppliers();
    loadAllSuppliers(allSuppliers);
    $(".sup-popup").css("display", "none")
    await filteringSuppliers();
    eachCustomerCountForLabel();
});

// load all suppliers to container
const loadAllSuppliers = (selectedSuppliers) => {
    $("#sup-table-body").empty();
    selectedSuppliers.map((supplier) => {
        loadingData(supplier)
    })
}

//remove customer
$(document).on('click', '.btn-sup-remove', async function () {
    let selectedSupplier = $(this).closest("tr").find(".supplierCode").text();
    console.log(selectedSupplier);
    await deleteSupplier(selectedSupplier);
    allSuppliers = await  getAllSuppliers();
    loadAllSuppliers(allSuppliers);
    await filteringSuppliers();
    eachCustomerCountForLabel();
})
