export const saveEmployee = async (newEmployee) => {
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/employee/save`, {
            method: 'POST', body: JSON.stringify(newEmployee), headers: {
                'Content-Type': 'application/json',
            },
        });
        if (response.status === 203) {
            //allert
        }
    } catch (error) {
        console.log(error.body)
        //allert
    }
};
export const getAllEmployee = async () => {
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/employee/getAllEmployee`);
        const allEmployees = await response.json();
        return allEmployees;
    } catch (error) {
        // console.log(error.body)
        //allert
    }
}
export const updateEmployee = async (updateEmployee, employeeCode) => {
    console.log(updateEmployee)
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/employee/updateById/${employeeCode}`, {
            method: 'PATCH', body: JSON.stringify(updateEmployee), headers: {
                'Content-type': 'application/json',
            },
        })
        if (response.status === 200) {
            console.log("updated")
        }

    } catch (error) {
        console.log(error.body)
        //alert
    }
}
export const deleteEmployee = async (employeeCode) => {
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/employee/removeById/${employeeCode}`, {
            method: 'DELETE',
        });
        if (response.status === 204) {
            console.log("deleted :" + employeeCode)
        }
    } catch (error) {
        console.log(error.body)
        //alert
    }
}
