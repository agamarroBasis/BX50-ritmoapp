import { useNavigate } from 'react-router-dom';
import './Dashboard.css';

const TODAY_CLASSES = [
  {
    id: 1,
    time: '16:00 – 17:30',
    name: 'Salsa On2',
    level: 'Principiante',
    room: 'Sala A',
    students: 12,
    color: '#FF4D2E',
  },
  {
    id: 2,
    time: '18:00 – 19:30',
    name: 'Salsa Cubana',
    level: 'Intermedio',
    room: 'Sala A',
    students: 15,
    color: '#F5A623',
  },
  {
    id: 3,
    time: '19:30 – 21:00',
    name: 'Bachata Sensual',
    level: 'Avanzado',
    room: 'Sala B',
    students: 10,
    color: '#8B5CF6',
  },
];

const WEEK_DAYS = ['L', 'M', 'X', 'J', 'V', 'S'];
const WEEK_CLASSES = [2, 1, 3, 1, 2, 1]; // classes per day

function Dashboard() {
  const navigate = useNavigate();
  const now = new Date();
  const dateStr = now.toLocaleDateString('es-ES', { weekday: 'long', day: 'numeric', month: 'long' });
  const dateFormatted = dateStr.charAt(0).toUpperCase() + dateStr.slice(1);

  return (
    <div className="inst-page">
      {/* ── Top Bar ── */}
      <header className="inst-header">
        <div>
          <p className="inst-date">{dateFormatted}</p>
          <h1 className="inst-greeting">Hola, Andrés 👋</h1>
        </div>
        <div className="inst-avatar">A</div>
      </header>

      {/* ── Stats Row ── */}
      <div className="inst-stats">
        <div className="inst-stat-card">
          <span className="stat-icon">🎯</span>
          <div>
            <p className="stat-value">3</p>
            <p className="stat-label">Clases hoy</p>
          </div>
        </div>
        <div className="inst-stat-card">
          <span className="stat-icon">👥</span>
          <div>
            <p className="stat-value">37</p>
            <p className="stat-label">Alumnos hoy</p>
          </div>
        </div>
        <div className="inst-stat-card">
          <span className="stat-icon">📅</span>
          <div>
            <p className="stat-value">10</p>
            <p className="stat-label">Esta semana</p>
          </div>
        </div>
      </div>

      {/* ── Today's Schedule ── */}
      <section className="inst-section">
        <div className="section-header">
          <h2 className="section-title">Clases de Hoy</h2>
          <span className="section-badge">{TODAY_CLASSES.length}</span>
        </div>

        <div className="class-list">
          {TODAY_CLASSES.map((cls) => (
            <div key={cls.id} className="class-card" style={{ '--card-accent': cls.color }}>
              <div className="class-card-accent" />
              <div className="class-card-body">
                <div className="class-card-top">
                  <div>
                    <span className="class-time">{cls.time}</span>
                    <h3 className="class-name">{cls.name}</h3>
                    <div className="class-meta">
                      <span className="class-tag">{cls.level}</span>
                      <span className="class-tag">{cls.room}</span>
                    </div>
                  </div>
                  <div className="class-students">
                    <span className="students-num">{cls.students}</span>
                    <span className="students-lbl">alumnos</span>
                  </div>
                </div>
                <button
                  className="attend-btn"
                  onClick={() => navigate('/attendance')}
                  style={{ '--btn-color': cls.color }}
                >
                  Registrar Asistencia →
                </button>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* ── Weekly Overview ── */}
      <section className="inst-section">
        <h2 className="section-title">Esta Semana</h2>
        <div className="week-bar">
          {WEEK_DAYS.map((day, i) => (
            <div key={day} className={`week-day ${i === 1 ? 'today' : ''}`}>
              <span className="week-day-name">{day}</span>
              <div className="week-day-dots">
                {Array.from({ length: WEEK_CLASSES[i] }).map((_, j) => (
                  <span key={j} className="day-dot" />
                ))}
              </div>
              <span className="week-day-count">{WEEK_CLASSES[i]}</span>
            </div>
          ))}
        </div>
      </section>

      {/* ── Bottom Nav ── */}
      <nav className="inst-nav">
        <button className="nav-item active">
          <span className="nav-icon">🏠</span>
          <span>Inicio</span>
        </button>
        <button className="nav-item">
          <span className="nav-icon">📋</span>
          <span>Horario</span>
        </button>
        <button className="nav-item">
          <span className="nav-icon">👤</span>
          <span>Perfil</span>
        </button>
      </nav>
    </div>
  );
}

export default Dashboard;
