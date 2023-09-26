import React, {useState} from 'react';
import {Button, Form} from "semantic-ui-react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function StudentCreate() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');

    let navigate = useNavigate();

    const postData = () => {
        axios
            .post(`http://127.0.0.1:8080/api/students`, {
                "name": name, "email": email
            })
            .then(() => navigate('/students'))
            .catch((error) => {
                console.log(error.response.data)
            });
    }

    return (
        <Form className="create-form">
            <Form.Field>
                <label>Name</label>
                <input type="text" name="name" placeholder="Name"
                       onChange={(e) => setName(e.target.value)}
                />
            </Form.Field>
            <Form.Field>
                <label>Email</label>
                <input type="text" name="email" placeholder="Email"
                       onChange={(e) => setEmail(e.target.value)}
                />
            </Form.Field>
            <Button className="ui button" type="submit"
                    onClick={postData}
            >
                Add New
            </Button>
        </Form>
    )

}
