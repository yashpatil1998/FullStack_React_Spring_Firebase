import React, { Component } from 'react'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.css';

class PostNewEmployee extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: '',
            name: '',
            age: ''
        }
    }

    changeHandler = (event) => {
        this.setState({ [event.target.name]: event.target.value })
    }

    submitHandler = (event) => {

        event.preventDefault()
        console.log(this.state)
        axios.post('http://localhost:8080/createEmployee', this.state)
            // axios.post('https://jsonplaceholder.typicode.com/posts', this.state)
            .then(response => {
                console.log(response)
                alert('Form has been submitted')
            })
            .catch(error => {
                console.log(error)
                alert(`Error while submitting form \n Error :::${error}`)
            })
    }


    render() {
        return (
            <div>
                Enter new employee details here
                <div>
                    <form onSubmit={this.submitHandler}>
                        <div className="form-group">
                            <label>Employee ID </label>
                            <input type='text' name='id' value={this.state.id} onChange={this.changeHandler} className="form-control" />
                        </div>
                        <div className="form-group">
                            <label>Employee name </label>
                            <input type='text' name='name' value={this.state.name} onChange={this.changeHandler} className="form-control" />
                        </div>
                        <div className="form-group">
                            <label>Age </label>
                            <input type='text' name='age' value={this.state.age} onChange={this.changeHandler} className="form-control" />
                        </div>
                        <button type='submit'>Create New Employee</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default PostNewEmployee
