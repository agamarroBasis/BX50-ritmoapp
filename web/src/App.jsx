import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './Login';
import Dashboard from './Dashboard';
import Attendance from './Attendance';
import AdminLayout from './admin/AdminLayout';
import AdminDashboard from './admin/AdminDashboard';
import AdminCalendar from './admin/AdminCalendar';
import AdminStudents from './admin/AdminStudents';
import AdminInstructors from './admin/AdminInstructors';
import AdminClasses from './admin/AdminClasses';
import AdminFinance from './admin/AdminFinance';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />} />

        {/* Instructor Portal */}
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/attendance" element={<Attendance />} />

        {/* Admin Portal */}
        <Route path="/admin" element={<AdminLayout />}>
          <Route index element={<Navigate to="/admin/dashboard" replace />} />
          <Route path="dashboard"   element={<AdminDashboard />} />
          <Route path="calendar"    element={<AdminCalendar />} />
          <Route path="students"    element={<AdminStudents />} />
          <Route path="instructors" element={<AdminInstructors />} />
          <Route path="classes"     element={<AdminClasses />} />
          <Route path="finance"     element={<AdminFinance />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
