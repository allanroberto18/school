import './App.css';
import CourseCreate from "./course/create";
import ReadCourses from "./course/read";
import {BrowserRouter, Link, Route, Routes} from 'react-router-dom'
import CourseUpdate from "./course/update";
import ReadStudents from "./students/read";
import StudentCreate from "./students/create";
import StudentUpdate from "./students/update";
import AddCourseToStudent from "./students/add-course";
import ReadCoursesFromStudents from "./students/read-course";

function App() {
    return (
        <BrowserRouter>
            <div className="main">
                <h2 className="main-header">School App</h2>
                <div className={'ui menu'}>
                    <Link className='item' to={'/'}>Home</Link>
                    <Link className='item' to={'/students'}>Students</Link>
                    <Link className='item' to={'/courses'}>Courses</Link>
                </div>
                <div>
                    <Routes>
                        <Route path='/courses' element={<ReadCourses/>}/>
                        <Route path="/courses/create" element={<CourseCreate/>}/>
                        <Route path="/courses/update" element={<CourseUpdate/>}/>
                        <Route path='/students' element={<ReadStudents/>}/>
                        <Route path="/students/create" element={<StudentCreate/>}/>
                        <Route path="/students/update" element={<StudentUpdate/>}/>
                        <Route path="/students/addCourse" element={<AddCourseToStudent/>}/>
                        <Route path="/students/myCourses" element={<ReadCoursesFromStudents/>}/>
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
    );
}

export default App;
