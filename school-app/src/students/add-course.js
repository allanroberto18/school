import React, { useState, useEffect } from 'react';
import {Button, Form} from "semantic-ui-react";
import axios from 'axios';
import {useNavigate} from "react-router-dom";

export default function AddCourseToStudent() {
    const [studentId, setStudentId] = useState('');
    const [courseId, setCourseId] = useState('');
    const [courses, setCourses] = useState([]);

    let navigate = useNavigate();

    useEffect(() => {
        setStudentId(localStorage.getItem('studentId'));
        setCourseId(localStorage.getItem('courseId'));
        getData()
    }, []);

    const postData = () => {
        axios
            .post(`http://127.0.0.1:8080/api/courses/registration/` + studentId, {
                "courseId": courseId
            })
            .then(() => navigate('/students'))
            .catch((error) => {
                let message = error.response.data.message;
                if (error.response.data.message === undefined) {
                    message = error.response.data.detail;
                }

                alert(message);

                console.log(message);
            });
    }

    const getData = () => {
        axios
            .get(`http://127.0.0.1:8080/api/courses/available`)
            .then((response) => {
                setCourses(response.data);
            });
    }

    return (
        <Form className="create-form">
            <Form.Field>
                <label>Courses</label>
                <select name="courseId" onChange={(e) => setCourseId(e.target.value)}>
                    <option value=''>Select a course</option>
                    {courses.map(({ id, name }, index) => <option value={id} >{name}</option>)}
                </select>
            </Form.Field>
            <Button className="ui button" type="submit" onClick={postData}>
                Add Course
            </Button>
        </Form>
    )

}
