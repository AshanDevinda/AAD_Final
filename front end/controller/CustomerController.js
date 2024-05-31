import {CustomerModel} from "../model/CustomerModel.js";
import {UpdateCustomerModel} from "../model/UpdateCustomerModel.js";
import {
    saveCustomer, getAllCustomers, updateCustomer, deleteCustomer, becomeLoyaltyCustomer, addPointAndMarkRecentPurchaseDateAndLevelForCustomer
} from "../api/CustomerAPI.js";

let allCustomers = [];
let normalCustomers = [];
let loyaltyCustomers = [];

//when document ready should be done
$(document).ready(async function () {
    // Prevent form submission on pressing Enter key
    $('#cus-save-form').on('keypress', function (e) {
        if (e.which === 13) {
            e.preventDefault();
        }
    });
    loadAllCustomers(await getAllCustomers());
    await filteringCustomers();
    eachCustomerCountForLabel();
    $(":input").attr('autocomplete', 'off');
});

//popup handle
$("body").click(function (event) {
    if (!$(event.target).closest('.cus-popup').length && !$(event.target).closest('#btn-add-customer-popup').length && !$(event.target).closest(".action-td button:nth-child(2)").length) {
        $(".cus-popup").css("display", "none");
    } else {
        $(".cus-popup").css("display", "block");
    }
});

//clear inputs
const clearInputs = () => {
    $("#customerName").val(''), $("#cus-gender").val('Male'), $("#cus-dob").val(''), $("#cus-addressLine1").val(''), $("#cus-addressLine2").val(''), $("#cus-addressLine3").val(''), $("#cus-addressLine4").val(''), $("#cus-addressLine5").val(''), $("#cus-contactNo").val(''), $("#customerEmail").val('')
}

//customer edit icon fire
$(document).on('click', '.action-td>button:last-child', function () {
    selectedEditCustomerCode = $(this).closest("tr").find(".customerCode").text();
    console.log(selectedEditCustomerCode);
    $("#cus-popup-header").text("UPDATE CUSTOMER");
    $("#cus-save-update-btn").text("Update Customer")
    $("#customerName").val($(this).closest("tr").find(".customerName").text()), $("#customerEmail").val($(this).closest("tr").find(".email").text()), $("#cus-dob").val($(this).closest("tr").find(".dob").text()), $("#cus-addressLine1").val($(this).closest("tr").find(".address1").text()), $("#cus-addressLine2").val($(this).closest("tr").find(".address2").text()), $("#cus-addressLine3").val($(this).closest("tr").find(".address3").text()), $("#cus-addressLine4").val($(this).closest("tr").find(".address4").text()), $("#cus-addressLine5").val($(this).closest("tr").find(".address5").text()), $("#cus-contactNo").val($(this).closest("tr").find(".contact").text())
    $(".cus-popup").css("display", "block")
});

//filtering customers
const filteringCustomers = async () => {
    allCustomers = await getAllCustomers();
    allCustomers.map((customer) => {
        customer.joinDateAsALoyaltyCustomer != null ? normalCustomers.push(customer) : loyaltyCustomers.push(customer);
    });
}

// each customer counts for label
const eachCustomerCountForLabel = () => {
    $(".cus-count-label").eq(0).text(allCustomers.length);
    $(".cus-count-label").eq(1).text(loyaltyCustomers.length);
    $(".cus-count-label").eq(2).text(normalCustomers.length);
}

// making table and loading data
function loadingData(customer) {
    return $("#customer-detail-table tbody").append(`<tr>
                <td class="cus-name-id">
                    <span class="customerName">${customer.customerName}</span><br>
                    <span class="customerCode">${customer.customerCode}</span>
                </td>
                <td class="gender" style="font-size: 12px">${customer.gender}</td>
                <td class="joinDateAsALoyaltyCustomer">${customer.joinDateAsALoyaltyCustomer === null ? ` <button class="btn-join-loyalty"><i class="fa-solid fa-handshake fa-xl" style="color: #F6995C;"></i></button>` : customer.joinDateAsALoyaltyCustomer}</td>
                <td class="dob">${customer.dob}</td>
                <td><span class="address1">${customer.address1}</span><span class="address2"> ${customer.address2}</span><span class="address3"> ${customer.address3}</span><span class="address4"> ${customer.address4}</span><span class="address5"> ${customer.address5}</span></td>
                <td class="email">${customer.email}</td>
                <td class="contact">${customer.contact}</td>
                <td class="recentPurchaseDateTime">${customer.recentPurchaseDate === null ? "not yet" : moment.utc(customer.recentPurchaseDate).local().format('YYYY-MM-DD HH:mm:ss')}</td>
                <td class="emp-level-lbl">
                    <label>${customer.level.toLowerCase()}</label>
                </td>
                <td class="totalPoints">${customer.totalPoints}</td>
                <td class="action-td">
                    <button><i class="fa-solid fa-trash-can fa-lg" style="color: #f50529;"></i></button>
                    <button><i class="fa-solid fa-pen-to-square fa-lg"></i></button>
                </td>
            </tr>`)
}


//load all customers to table
const loadAllCustomers = (selectedCustomerList) => {
    $("#customer-detail-table tbody").empty();
    selectedCustomerList.map((customer) => {
        loadingData(customer)
    })
}

//save and update customer
let selectedEditCustomerCode;
$("#cus-save-update-btn").on("click", async function (event) {
    event.preventDefault();
    if ($("#cus-save-update-btn").text() === "Save Customer") {
        await saveCustomer(new CustomerModel($("#customerName").val(), $("#cus-gender").val().toUpperCase(), "2022-05-16", "NEW", 0, $("#cus-dob").val(), $("#cus-addressLine1").val(), $("#cus-addressLine2").val(), $("#cus-addressLine3").val(), $("#cus-addressLine4").val(), $("#cus-addressLine5").val(), $("#cus-contactNo").val(), $("#customerEmail").val()));
    } else {
        let updatedCustomer = new UpdateCustomerModel($("#customerName").val(), $("#cus-gender").val().toUpperCase(), $("#cus-dob").val(), $("#cus-addressLine1").val(), $("#cus-addressLine2").val(), $("#cus-addressLine3").val(), $("#cus-addressLine4").val(), $("#cus-addressLine5").val(), $("#cus-contactNo").val(), $("#customerEmail").val());
        await updateCustomer(updatedCustomer, selectedEditCustomerCode);
    }
    clearInputs();
    loadAllCustomers(await getAllCustomers());
    await filteringCustomers();
    eachCustomerCountForLabel();
});

//remove customer
$(document).on('click', '.action-td>button:first-child', async function () {
    let selectedCustomer = $(this).closest("tr").find(".customerCode").text();
    console.log(selectedCustomer);
    await deleteCustomer(selectedCustomer);
    loadAllCustomers(await getAllCustomers());
    await filteringCustomers();
    eachCustomerCountForLabel();
})

//be loyalty customer
$(document).on('click', '.btn-join-loyalty', async function () {
    let result = await becomeLoyaltyCustomer($(this).closest("tr").find(".customerCode").text(), new Date().toISOString().split('T')[0]);
    if (result === 200) {
        $(this).closest("tr").find(".joinDateAsALoyaltyCustomer").text(new Date().toISOString().split('T')[0]);
        await filteringCustomers();
        eachCustomerCountForLabel();
    }
})

//loading filtered customers when click radio buttons
$('input[name="customerOption"]').change(async function () {
    await filteredCustomersForTable($(this).val());
});
const filteredCustomersForTable = async (ratioButtonValue) => {
    await loadAllCustomers(ratioButtonValue === "normal" ? normalCustomers : ratioButtonValue === "loyalty" ? loyaltyCustomers : allCustomers);
}

//search customer
$("#customer-search-input").on('input', async function () {
    $("#customer-detail-table tbody").empty();
    let allCustomers = await getAllCustomers();
    allCustomers.map((customer) => {
        if (customer.customerCode.toLowerCase().startsWith($("#customer-search-input").val().toLowerCase()) || customer.customerName.toLowerCase().startsWith($("#customer-search-input").val().toLowerCase()) || customer.email.toLowerCase().startsWith($("#customer-search-input").val().toLowerCase()) || customer.contact.toLowerCase().startsWith($("#customer-search-input").val().toLowerCase())) {
            loadingData(customer)
        }
    })
})

const addPointAndMarkRecentPurchaseDateAndForCustomer = async () => {
    await addPointAndMarkRecentPurchaseDateAndLevelForCustomer("Cf5db8fb6", 1, moment().format('YYYY-MM-DD HH:mm:ss'))
}


