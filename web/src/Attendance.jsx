import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Attendance.css';

const initialStudents = [
  { id: 1, name: 'Maria Garcia',    initials: 'MG', level: 'Intermedio', isPresent: null },
  { id: 2, name: 'Juan Pérez',      initials: 'JP', level: 'Intermedio', isPresent: null },
  { id: 3, name: 'Sofia Rodríguez', initials: 'SR', level: 'Avanzado',   isPresent: null },
  { id: 4, name: 'Carlos López',    initials: 'CL', level: 'Principiante', isPresent: null },
  { id: 5, name: 'Ana Martínez',    initials: 'AM', level: 'Intermedio', isPresent: null },
  { id: 6, name: 'Luis Fernández',  initials: 'LF', level: 'Avanzado',   isPresent: null },
  { id: 7, name: 'Valentina Cruz',  initials: 'VC', level: 'Principiante', isPresent: null },
  { id: 8, name: 'Diego Herrera',   initials: 'DH', level: 'Intermedio', isPresent: null },
];

const LEVEL_COLOR = {
  Principiante: '#10B981',
  Intermedio:   '#F5A623',
  Avanzado:     '#6366F1',
};

function Attendance() {
  const [students, setStudents] = useState(initialStudents);
  const [saved, setSaved] = useState(false);
  const navigate = useNavigate();

  const mark = (id, status) =>
    setStudents((prev) =>
      prev.map((s) => (s.id === id ? { ...s, isPresent: status } : s))
    );

  const marked   = students.filter((s) => s.isPresent !== null).length;
  const present  = students.filter((s) => s.isPresent === true).length;
  const absent   = students.filter((s) => s.isPresent === false).length;
  const progress = Math.round((marked / students.length) * 100);

  const handleSave = () => {
    setSaved(true);
    setTimeout(() => navigate('/dashboard'), 1000);
  };

  return (
    <div className="att-page">
      {/* ── Header ── */}
      <header className="att-header">
        <button className="att-back" onClick={() => navigate(-1)} aria-label="Volver">
          ←
        </button>
        <div className="att-header-info">
          <h1 className="att-title">Salsa Cubana</h1>
          <p className="att-subtitle">Intermedio · 18:00 – 19:30 · Sala A</p>
        </div>
      </header>

      {/* ── Progress bar ── */}
      <div className="att-progress-wrap">
        <div className="att-progress-bar">
          <div className="att-progress-fill" style={{ width: `${progress}%` }} />
        </div>
        <div className="att-progress-stats">
          <span>{marked} / {students.length} marcados</span>
          <span className="att-prog-right">
            <span className="pill-present">{present} presentes</span>
            <span className="pill-absent">{absent} ausentes</span>
          </span>
        </div>
      </div>

      {/* ── Student list ── */}
      <ul className="att-list">
        {students.map((s) => (
          <li key={s.id} className={`att-row ${s.isPresent === true ? 'row-present' : s.isPresent === false ? 'row-absent' : ''}`}>
            <div className="att-student">
              <div className="att-avatar" style={{ '--av-color': LEVEL_COLOR[s.level] }}>
                {s.initials}
              </div>
              <div className="att-info">
                <span className="att-name">{s.name}</span>
                <span className="att-level" style={{ color: LEVEL_COLOR[s.level] }}>{s.level}</span>
              </div>
            </div>

            <div className="att-toggles">
              <button
                className={`toggle present ${s.isPresent === true ? 'active' : ''}`}
                onClick={() => mark(s.id, true)}
                aria-label="Presente"
              >
                ✓
              </button>
              <button
                className={`toggle absent ${s.isPresent === false ? 'active' : ''}`}
                onClick={() => mark(s.id, false)}
                aria-label="Ausente"
              >
                ✕
              </button>
            </div>
          </li>
        ))}
      </ul>

      {/* ── Footer ── */}
      <div className="att-footer">
        <div className="att-footer-inner">
          <div className="att-footer-info">
            <span className="footer-stat">
              <span style={{ color: '#10B981', fontWeight: 700 }}>{present}</span> presentes
            </span>
            <span className="footer-sep">·</span>
            <span className="footer-stat">
              <span style={{ color: '#EF4444', fontWeight: 700 }}>{absent}</span> ausentes
            </span>
            <span className="footer-sep">·</span>
            <span className="footer-stat">
              <span style={{ fontWeight: 700 }}>{students.length - marked}</span> sin marcar
            </span>
          </div>
          <button
            className={`att-save-btn ${saved ? 'saved' : ''}`}
            onClick={handleSave}
            disabled={saved}
          >
            {saved ? '✓ Guardado' : 'Guardar Asistencia'}
          </button>
        </div>
      </div>
    </div>
  );
}

export default Attendance;
