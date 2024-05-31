export const saveCustomer = async (newCustomer) => {
    console.log(newCustomer)
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/customer/save`, {
            method: 'POST', body: JSON.stringify(newCustomer), headers: {
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
export const getAllCustomers = async () => {
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/customer/getAllCustomers`);
        const allCustomers = await response.json();
        // Convert dates to local time
        allCustomers.forEach(customer => {
            customer.timestamp = moment.utc(customer.recentPurchaseDate).local().format('YYYY-MM-DD HH:mm:ss');
        });
        return allCustomers
    } catch (error) {
        // console.log(error.body)
        //allert
    }
}

export const updateCustomer = async (updatedCustomer, customerCode) => {
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/customer/updateById/${customerCode}`, {
            method: 'PATCH', body: JSON.stringify(updatedCustomer), headers: {
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

export const becomeLoyaltyCustomer = async (customerCode, joinDate) => {
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/customer/loyalty?customerCode=${customerCode}&joinLoyaltyDate=${joinDate}`, {
            method: 'PATCH', headers: {
                'Content-type': 'application/json',
            },
        })
        if (response.status === 200) {
            return 200;
        }

    } catch (error) {
        return 400
        //alert
    }
}

export const deleteCustomer = async (customerCode) => {
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/customer/removeById/${customerCode}`, {
            method: 'DELETE',
        });
        if (response.status === 204) {
            console.log("deleted :" + customerCode)
        }
    } catch (error) {
        console.log(error.body)
        //alert
    }
}

export const addPointAndMarkRecentPurchaseDateAndLevelForCustomer = async (customerCode, newPoints, recentPurchaseDate) => {
    console.log(recentPurchaseDate)
    try {
        const response = await fetch(`http://localhost:8080/helloShoe/api/customer/purchase?customerCode=${customerCode}&newPoints=${newPoints}&recentPurchaseDate=${recentPurchaseDate}`, {
            method: 'PATCH', headers: {
                'Content-type': 'application/json',
            },
        })
        if (response.status === 200) {
            console.log("updated")
        }

    } catch (error) {
        return "error"
        //alert
    }
}