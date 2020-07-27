import React, { Component } from 'react'
import axios from 'axios'

import 'bootstrap/dist/css/bootstrap.css';

class GetDetails extends Component {

    constructor(props) {
        super(props)

        this.state = {
            employees: []
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/getEmployeeDetails')
            .then(response => {
                console.log(response)
                this.setState({ employees: response.data })
            })
            .catch(error => {
                console.log(error)
            })
    }


    render() {
        const sList = this.state.employees.map(employee => <div className="list-group-item" key={employee.id}>ID : {employee.id} Name : {employee.name} Age : {employee.age}</div>)
        return (
            <div>
                <h5>All Employee Details are visible here</h5>
                <div className="list-group">{sList}</div>
                <br />
            </div>
        )
    }
}

export default GetDetails
