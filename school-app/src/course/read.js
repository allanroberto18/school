import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, Table} from 'semantic-ui-react'
import {Link} from 'react-router-dom';

export default function ReadCourses() {

    const [APIData, setAPIData] = useState([]);

    useEffect(() => {
        getData()
    }, [])

    const setData = (data) => {
        let {id, name, status} = data;
        localStorage.setItem('courseId', id);
        localStorage.setItem('courseName', name);
        localStorage.setItem('courseStatus', status);
    }

    const onDelete = (id) => {
        axios
            .delete(`http://127.0.0.1:8080/api/courses/` + id)
            .then(() => getData())
            .catch((error) => {
                console.log(error.response.data)
            })
    }

    const getData = () => {
        axios.get(`http://127.0.0.1:8080/api/courses`)
            .then((response) => {
                setAPIData(response.data);
            });
    }

    return (
        <div>
            <div>
                <Link to={'/courses/create'}>
                    <Button>Create course</Button>
                </Link>
            </div>
            <Table>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Course</Table.HeaderCell>
                        <Table.HeaderCell>Status</Table.HeaderCell>
                        <Table.HeaderCell>Option</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {APIData.map((data) => {
                        return (
                            <Table.Row>
                                <Table.Cell>{data.name}</Table.Cell>
                                <Table.Cell>{data.status === 'ENABLED' ? 'Enabled' : 'Disabled'}</Table.Cell>

                                <Table.Cell>
                                    <Link to='/courses/update'>
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