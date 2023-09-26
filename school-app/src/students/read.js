import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, Table} from 'semantic-ui-react'
import {Link} from 'react-router-dom';

export default function ReadStudents() {

    const [APIData, setAPIData] = useState([]);

    useEffect(() => {
        getData()
    }, [])

    const setData = (data) => {
        let {id, name, email} = data;
        localStorage.setItem('studentId', id);
        localStorage.setItem('studentName', name);
        localStorage.setItem('studentEmail', email);
    }

    const onDelete = (id) => {
        axios
            .delete(`http://127.0.0.1:8080/api/students/` + id)
            .then(() => getData())
            .catch((error) => {
                console.log(error.response.data)
            })
    }

    const getData = () => {
        axios
            .get(`http://127.0.0.1:8080/api/students`)
            .then((response) => {
                setAPIData(response.data);
            });
    }

    return (
        <div>
            <div>
                <Link to={'/students/create'}>
                    <Button>Create student</Button>
                </Link>
            </div>
            <Table>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Name</Table.HeaderCell>
                        <Table.HeaderCell>Email</Table.HeaderCell>
                        <Table.HeaderCell>Option</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {APIData.map((data) => {
                        return (
                            <Table.Row>
                                <Table.Cell>{data.name}</Table.Cell>
                                <Table.Cell>{data.email}</Table.Cell>

                                <Table.Cell>
                                    <Link to='/students/addCourse'>
                                        <Button onClick={() => setData(data)}>Add Course</Button>
                                    </Link>
                                    <Link to='/students/myCourses'>
                                        <Button onClick={() => setData(data)}>My Courses</Button>
                                    </Link>
                                    <Link to='/students/update'>
                                        <Button onClick={() => setData(data)}>Update</Button>
                                    </Link>
                                    <Button onClick={() => onDelete(data.id)}>Delete</Button>
                                </Table.Cell>
                            </Table.Row>
                        )
                    })}
                </Table.Body>
            </Table>
        </div>
    );
};