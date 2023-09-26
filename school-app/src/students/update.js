import React, { useState, useEffect } from 'react';
import {Button, Form} from "semantic-ui-react";
import axios from 'axios';
import {useNavigate} from "react-router-dom";

export default function StudentUpdate() {
    const [id, setId] = useState('');
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');

    let navigate = useNavigate();

    useEffect(() => {
        setId(localStorage.getItem('studentId'))
        setName(localStorage.getItem('studentName'));
        setEmail(localStorage.getItem('studentEmail'));
    }, []);

    const updateData = () => {
        axios
            .put(`http://127.0.0.1:8080/api/students/` + id, {
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
                       value={name}
                       onChange={(e) => setName(e.target.value)}
                />
            </Form.Field>
            <Form.Field>
                <label>Email</label>
                <input type="text" name="email" placeholder="Email"
                       value={email}
                       onChange={(e) => setEmail(e.target.value)}
                />
            </Form.Field>
            <Button className="ui button" type="submit"
                    onClick={updateData}
            >
                Update Student
            </Button>
        </Form>
    )

}
