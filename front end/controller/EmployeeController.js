import {saveEmployee, updateEmployee, deleteEmployee, getAllEmployee} from "../api/EmployeeAPI.js";
import {EmployeeModel} from "../model/EmployeeModel.js";

let allEmployees = [];
let admins = [];
let users = [];

//handle popup
$("body").click(function (event) {
    if (!$(event.target).closest('.emp-popup').length && !$(event.target).closest('#btn-add-emp-popup').length && !$(event.target).closest(".btn-update-employee").length) {
        $(".emp-popup").css("display", "none");
    } else {
        $(".emp-popup").css("display", "block");
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
    allEmployees = await getAllEmployee();
    loadAllEmployees(allEmployees);
    await filteringEmployees();
    $(":input").attr('autocomplete', 'off');
});

// employee edit icon fire
$(document).on('click', '.btn-update-employee', function () {
    selectedEditEmployeeCode = $(this).closest(".emp-card").find(".emp-code").text();

    $("#emp-popup-header").text("UPDATE EMPLOYEE");
    $("#emp-save-update-btn").text("Update Employee");
    $("#employeeName").val($(this).closest(".emp-card").find(".emp-name").text());
    let gender = $(this).closest(".emp-card").find(".emp-gender").text();
    $("#gender").val(gender.charAt(0).toUpperCase() + gender.slice(1));
    $("#status").val($(this).closest(".emp-card").find(".emp-status").text());
    $("#designation").val($(this).closest(".emp-card").find(".emp-designation").text());
    $("#accessRole").val($(this).closest(".emp-card").find(".emp-access-role").text());
    $("#dob").val($(this).closest(".emp-card").find(".emp-dob").text());
    $("#dateOfJoin").val($(this).closest(".emp-card").find(".emp-join-date").text());
    $("#addressLine1").val($(this).closest(".emp-card").find(".emp-address1").text());
    $("#addressLine2").val($(this).closest(".emp-card").find(".emp-address2").text());
    $("#addressLine3").val($(this).closest(".emp-card").find(".emp-address3").text());
    $("#addressLine4").val($(this).closest(".emp-card").find(".emp-address4").text());
    $("#addressLine5").val($(this).closest(".emp-card").find(".emp-address5").text());
    $("#contactNo").val($(this).closest(".emp-card").find(".emp-contact").text());
    $("#email").val($(this).closest(".emp-card").find(".emp-email").text());
    $("#emergencyContactPerson").val($(this).closest(".emp-card").find(".emp-guardian-name").text());
    $("#emergencyContact").val($(this).closest(".emp-card").find(".emp-guardian-contact").text());

    $(".emp-popup").css("display", "block");
});

//clear inputs
const clearInputs = () => {
    $("#employeeName").val(''), $("#gender").val("MALE"), $("#status").val("MARRIED"), $("#designation").val(''), $("#accessRole").val(''), $("dob").val(''), $("#dateOfJoin").val(''), $("#addressLine1").val(''), $("#addressLine2").val(''), $("#addressLine3").val(''), $("#addressLine4").val(''), $("#addressLine5").val(''), $("#contactNo").val(''), $("#email").val(''), $("#emergencyContactPerson").val(''), $("#emergencyContact").val('');
}

// making card and loading data
function loadingData(employee) {
    return $(".emp-card-container").append(`
        <div class="emp-card">
            <div>
                <div>
                    <i class="fa-solid fa-circle fa-2xs" style="color: #B197FC;"></i>
                    <span class="emp-access-role">${employee.accessRole}</span>
                </div>
            </div>
            <div>
                <img src="assets/user.jpg" width="100px" height="100px" alt="pro-pic">
                <span class="emp-name">${employee.employeeName}</span>
                <span class="emp-code">${employee.employeeCode}</span>
            </div>
            <div class="emp-detail-txt">
                <span>Status</span>
                <span class="emp-status">${employee.status}</span>
            </div>
            <div class="emp-detail-txt">
                <span>Designation</span>
                <span class="emp-designation">${employee.designation}</span>
            </div>
            <div class="emp-detail-txt">
                <span>DOB</span>
                <span class="emp-dob">${employee.dob}</span>
            </div>
            <div class="emp-detail-txt">
                <span>Date Of Join</span>
                <span class="emp-join-date">${employee.dateOfJoin}</span>
            </div>
            <div class="emp-detail-txt">
                <span>Gender</span>
                <span class="emp-gender">${employee.gender.toLowerCase()}</span>
            </div>
            <div class="emp-detail-txt">
                <span>Address</span>
                <span>
                     <span class="emp-address1">${employee.address1}</span>
                     <span class="emp-address2">${employee.address2}</span>
                     <span class="emp-address3">${employee.address3}</span>
                     <span class="emp-address4">${employee.address4}</span>
                </span>
            </div>
            <div class="emp-detail-txt">
                <span>Postal</span>
                <span class="emp-address5">${employee.address5}</span>
            </div>
            <div class="emp-detail-txt">
                <span>Email</span>
                <span class="emp-email">${employee.email}</span>
            </div>
             <div class="emp-detail-txt">
                <span>contact</span>
                <span class="emp-contact">${employee.contact}</span>
            </div>
            <div class="emp-detail-txt">
                <span>EMG Person</span>
                <span class="emp-guardian-name">${employee.guardianName}</span>
            </div>
            <div class="emp-detail-txt">
                <span>EMG Number</span>
                <span class="emp-guardian-contact">${employee.guardianContact}</span>
            </div>
            <div>
                <button id="btn-remove-employee"><i class="fa-regular fa-trash-can fa-lg" style="color: #f20713;"></i></button>
                <button class="btn-update-employee"><i class="fa-regular fa-pen-to-square fa-lg"></i></i></button>
            </div>
        </div>`)
}

//filtering customers
const filteringEmployees = async () => {
    users.length = 0;
    admins.length = 0;
    allEmployees.map((employee) => {
        employee.accessRole === "ADMIN" ? admins.push(employee) : users.push(employee);
    });
}

//loading filtered employees when click radio buttons
$('input[name="employeeOption"]').change(async function () {
    await filteredEmployeesForContainer($(this).val());
});

const filteredEmployeesForContainer = async (ratioButtonValue) => {
    await loadAllEmployees(ratioButtonValue === "user" ? users : ratioButtonValue === "admin" ? admins : allEmployees);
}

//search customer
$("#employee-search-input").on('input', async function () {
    $(".emp-card-container").empty();
    allEmployees.map((employee) => {
        if (employee.employeeCode.toLowerCase().startsWith($("#employee-search-input").val().toLowerCase()) || employee.employeeName.toLowerCase().startsWith($("#employee-search-input").val().toLowerCase()) || employee.email.toLowerCase().startsWith($("#employee-search-input").val().toLowerCase()) || employee.contact.toLowerCase().startsWith($("#employee-search-input").val().toLowerCase())) {
            loadingData(employee)
        }
    })
})

//save and update employee
let selectedEditEmployeeCode;
$("#emp-save-update-btn").on("click", async function (event) {
    event.preventDefault();
    if ($("#emp-save-update-btn").text() === "Save Employee") {
        await saveEmployee(new EmployeeModel(null, $("#employeeName").val(), null, $("#gender").val().toUpperCase(), $("#status").val(), $("#designation").val(), $("#accessRole").val(), $("#dob").val(), $("#dateOfJoin").val(), $("#addressLine1").val(), $("#addressLine2").val(), $("#addressLine3").val(), $("#addressLine4").val(), $("#addressLine5").val(), $("#contactNo").val(), $("#email").val(), $("#emergencyContactPerson").val(), $("#emergencyContact").val()));
    } else {
        let updatedEmployee = new EmployeeModel(null, $("#employeeName").val(), null, $("#gender").val().toUpperCase(), $("#status").val(), $("#designation").val(), $("#accessRole").val(), $("#dob").val(), $("#dateOfJoin").val(), $("#addressLine1").val(), $("#addressLine2").val(), $("#addressLine3").val(), $("#addressLine4").val(), $("#addressLine5").val(), $("#contactNo").val(), $("#email").val(), $("#emergencyContactPerson").val(), $("#emergencyContact").val());
        await updateEmployee(updatedEmployee, selectedEditEmployeeCode);
    }
    clearInputs();
    allEmployees.length = 0;
    allEmployees = await getAllEmployee();
    loadAllEmployees(allEmployees);
    $(".emp-popup").css("display", "none")
    await filteringEmployees();
});

// load all employees to container
const loadAllEmployees = (selectedEmployees) => {
    $(".emp-card-container").empty();
    selectedEmployees.map((employee) => {
        loadingData(employee)
    })
}

//remove customer
$(document).on('click', '#btn-remove-employee', async function () {
    let selectedEmployee = $(this).closest(".emp-card").find(".emp-code").text();
    console.log(selectedEmployee);
    await deleteEmployee(selectedEmployee);
    loadAllEmployees(await getAllEmployee());
    // await filteringCustomers();
    // eachCustomerCountForLabel();
})
