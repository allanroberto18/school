import React, {useState} from 'react';
import {Button, Form} from "semantic-ui-react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function CourseCreate() {
    const [name, setName] = useState('');
    const [status, setStatus] = useState('');
    let navigate = useNavigate();

    const postData = () => {
        axios
            .post(`http://127.0.0.1:8080/api/courses`, {
                "name": name, "status": status
            })
            .then(() => navigate('/courses'))
            .catch((error) => {
                console.log(error.response.data)
            });
    }

    return (
        <Form className="create-form">
            <Form.Field>
                <label>Name</label>
                <input type="text" name="name" placeholder="Course name"
                       onChange={(e) => setName(e.target.value)}
                />
            </Form.Field>
            <Form.Field>
                <label>Status</label>
                <select className="ui fluid search dropdown" name="status"
                        onChange={(e) => setStatus(e.target.value)}
                >
                    <option value="">Select an option</option>
                    <option value="ENABLED">Enabled</option>
                    <option value="DISABLED">Disabled</option>
                </select>
            </Form.Field>
            <Button className="ui button" type="submit"
                    onClick={postData}
            >
                Add New
            </Button>
        </Form>
    )

}
