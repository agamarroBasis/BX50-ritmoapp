import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';

function Login() {
  const [role, setRole] = useState('instructor');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    setLoading(true);
    setTimeout(() => {
      if (role === 'admin') {
        navigate('/admin/dashboard');
      } else {
        navigate('/dashboard');
      }
    }, 700);
  };

  return (
    <div className="login-page">
      {/* ── Left panel: Brand ── */}
      <div className="login-brand">
        <div className="brand-content">
          <div className="brand-logo-wrap">
            <span className="brand-logo-text">R</span>
          </div>
          <h1 className="brand-name">RITMO</h1>
          <p className="brand-tagline">Academia de Baile</p>
          <p className="brand-sub">Donde cada movimiento cuenta una historia.</p>

          <div className="brand-stats">
            <div className="brand-stat">
              <span className="stat-num">120+</span>
              <span className="stat-lbl">Alumnos</span>
            </div>
            <div className="brand-stat">
              <span className="stat-num">8</span>
              <span className="stat-lbl">Instructores</span>
            </div>
            <div className="brand-stat">
              <span className="stat-num">15</span>
              <span className="stat-lbl">Clases/sem</span>
            </div>
          </div>
        </div>

        <div className="brand-deco">
          <div className="deco-circle c1" />
          <div className="deco-circle c2" />
          <div className="deco-circle c3" />
        </div>
      </div>

      {/* ── Right panel: Form ── */}
      <div className="login-form-panel">
        <div className="login-card">
          <div className="login-card-header">
            <h2>Bienvenido de vuelta</h2>
            <p>Ingresa tus credenciales para continuar</p>
          </div>

          {/* Role selector */}
          <div className="role-tabs">
            <button
              className={`role-tab ${role === 'instructor' ? 'active' : ''}`}
              onClick={() => setRole('instructor')}
              type="button"
            >
              Instructor
            </button>
            <button
              className={`role-tab ${role === 'admin' ? 'active' : ''}`}
              onClick={() => setRole('admin')}
              type="button"
            >
              Administrador
            </button>
          </div>

          <form onSubmit={handleLogin} className="login-form">
            <div className="form-field">
              <label htmlFor="email">Correo electrónico</label>
              <input
                id="email"
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder={role === 'admin' ? 'admin@ritmo.com' : 'instructor@ritmo.com'}
                required
              />
            </div>

            <div className="form-field">
              <div className="field-label-row">
                <label htmlFor="password">Contraseña</label>
                <button type="button" className="forgot-link">¿Olvidaste tu contraseña?</button>
              </div>
              <input
                id="password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="••••••••"
                required
              />
            </div>

            <button type="submit" className={`submit-btn ${role} ${loading ? 'loading' : ''}`} disabled={loading}>
              {loading ? (
                <span className="spinner" />
              ) : (
                `Iniciar Sesión como ${role === 'admin' ? 'Administrador' : 'Instructor'}`
              )}
            </button>
          </form>

          <p className="login-hint">
            {role === 'instructor'
              ? 'Portal exclusivo para instructores certificados de Ritmo.'
              : 'Acceso restringido al equipo administrativo de Ritmo.'}
          </p>
        </div>
      </div>
    </div>
  );
}

export default Login;
