import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, Table} from 'semantic-ui-react'
import {Link} from 'react-router-dom';

export default function ReadCoursesFromStudents() {

    const [studentId, setStudentId] = useState('');
    const [courseId, setSetCourseId] = useState('');
    const [studentName, setStudentName] = useState('');
    const [courses, setCourses] = useState({
            studentId: '',
            courses: []
        }
    );

    useEffect(() => {
        const studentId = localStorage.getItem('studentId');
        const studentName = localStorage.getItem('studentName');

        setStudentId(studentId);
        setStudentName(studentName);

        getData(studentId)
    }, []);

    const setData = () => {
        setStudentId(studentId)
        setSetCourseId('');
    }

    const getData = (studentId) => {
        const url = `http://127.0.0.1:8080/api/courses/registration/` + studentId;
        console.log(url);
        axios
            .get(url)
            .then((response) => {
                setCourses(response.data);
            });
    }

    const onDelete = (id) => {
        let url = `http://127.0.0.1:8080/api/courses/registration/` + studentId + "/" + id;
        console.log(url);
        axios
            .delete(url)
            .then(() => getData(studentId))
            .catch((error) => {
                console.log(error.response.data)
            })
    }

    return (
        <div>
            <Link to='/students/addCourse'>
                <Button onClick={() => setData()}>Add Course</Button>
            </Link>
            <Table>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Student Id</Table.HeaderCell>
                        <Table.HeaderCell>Student Name</Table.HeaderCell>
                        <Table.HeaderCell>Course</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    <Table.Row>
                        <Table.Cell>{studentId}</Table.Cell>
                        <Table.Cell>{studentName}</Table.Cell>
                        <Table.Cell>
                            <Table>
                                {courses.courses.map((data) => {
                                    return (
                                        <Table.Row>
                                            <Table.Cell>{data.id}</Table.Cell>
                                            <Table.Cell>{data.name}</Table.Cell>

                                            <Table.Cell>
                                                <Button onClick={() => onDelete(data.id)}>Delete</Button>
                                            </Table.Cell>
                                        </Table.Row>
                                    )
                                })}
                            </Table>
                        </Table.Cell>
                    </Table.Row>
                </Table.Body>
            </Table>
        </div>
    );
};