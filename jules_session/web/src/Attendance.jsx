import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Attendance.css';

const initialStudents = [
  { id: 1, name: 'Maria Garcia', isPresent: null },
  { id: 2, name: 'Juan Perez', isPresent: null },
  { id: 3, name: 'Sofia Rodriguez', isPresent: null },
  { id: 4, name: 'Carlos Lopez', isPresent: null },
  { id: 5, name: 'Ana Martinez', isPresent: null },
  { id: 6, name: 'Luis Fernandez', isPresent: null },
];

function Attendance() {
  const [students, setStudents] = useState(initialStudents);
  const navigate = useNavigate();

  const handleStatusChange = (id, status) => {
    setStudents((prev) =>
      prev.map((student) =>
        student.id === id ? { ...student, isPresent: status } : student
      )
    );
  };

  const handleSave = () => {
    // In a real app, send data to backend
    navigate('/dashboard');
  };

  return (
    <div className="attendance-container">
      <header className="attendance-header">
        <button className="back-button" onClick={() => navigate(-1)}>
          &larr; Atrás
        </button>
        <h1>Asistencia: Salsa Cubana</h1>
      </header>

      <ul className="student-list">
        {students.map((student) => (
          <li key={student.id} className="student-row">
            <div className="student-info">
              <div className="avatar">{student.name.charAt(0)}</div>
              <span className="name">{student.name}</span>
            </div>
            <div className="status-toggles">
              <button
                className={`toggle-btn present ${student.isPresent === true ? 'active' : ''}`}
                onClick={() => handleStatusChange(student.id, true)}
              >
                ✓
              </button>
              <button
                className={`toggle-btn absent ${student.isPresent === false ? 'active' : ''}`}
                onClick={() => handleStatusChange(student.id, false)}
              >
                ✕
              </button>
            </div>
          </li>
        ))}
      </ul>

      <div className="action-footer">
        <button className="primary-button" onClick={handleSave}>
          Guardar Asistencia
        </button>
      </div>
    </div>
  );
}

export default Attendance;
