import { useNavigate } from 'react-router-dom';
import './Dashboard.css';

function Dashboard() {
  const navigate = useNavigate();

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <h1>Buenos Días, Andrés</h1>
        <p className="date">Lunes, 10 Marzo</p>
      </header>

      <div className="dashboard-content">
        <section className="next-class-card">
          <div className="card-header">
            <span className="label">SIGUIENTE CLASE</span>
          </div>
          <div className="card-body">
            <h2>Salsa Cubana</h2>
            <p className="details">Intermedio • 18:00 - 19:30</p>
            <div className="footer-details">
              <span>Sala A</span>
              <span>15 Alumnos</span>
            </div>
          </div>
        </section>

        <section className="action-section">
          <button 
            className="primary-button" 
            onClick={() => navigate('/attendance')}
          >
            Registrar Asistencia
          </button>
        </section>
      </div>
    </div>
  );
}

export default Dashboard;
