const KPIS = [
  { label: 'Total Alumnos',      value: '124',    delta: '+5 este mes',     dir: 'up',  icon: '👥', cls: 'purple' },
  { label: 'Membresías Activas', value: '98',     delta: '+3 esta semana',  dir: 'up',  icon: '✅', cls: 'green'  },
  { label: 'Ingresos (Marzo)',   value: '$8,430', delta: '+12% vs febrero', dir: 'up',  icon: '💰', cls: 'amber'  },
  { label: 'Clases Hoy',         value: '6',      delta: '2 en curso',      dir: null,  icon: '📅', cls: 'blue'   },
];

const ACTIVITY = [
  { icon: '🎉', color: 'green',  text: 'Maria Garcia renovó su membresía Premium.',        time: 'hace 10 min' },
  { icon: '📅', color: 'blue',   text: 'Nueva clase de Bachata agregada para el viernes.',  time: 'hace 25 min' },
  { icon: '⚠️', color: 'amber',  text: 'Pago pendiente de Luis Fernández ($120).',          time: 'hace 1h'     },
  { icon: '🧑‍🏫', color: 'purple', text: 'Instructor Carlos actualizó su horario.',           time: 'hace 2h'     },
  { icon: '❌', color: 'red',    text: 'Ana Martínez canceló su membresía básica.',         time: 'hace 3h'     },
  { icon: '🏅', color: 'green',  text: 'Diego Herrera completó 10 clases este mes.',        time: 'ayer'        },
];

const UPCOMING = [
  { time: '14:00', name: 'Merengue Básico',  instructor: 'Laura V.',  students: 8,  max: 15, color: '#10B981' },
  { time: '16:00', name: 'Salsa On2',        instructor: 'Andrés G.', students: 12, max: 15, color: '#FF4D2E' },
  { time: '18:00', name: 'Salsa Cubana',     instructor: 'Andrés G.', students: 15, max: 15, color: '#F5A623' },
  { time: '19:30', name: 'Bachata Sensual',  instructor: 'Laura V.',  students: 10, max: 12, color: '#6366F1' },
];

function AdminDashboard() {
  const now = new Date();
  const dateStr = now.toLocaleDateString('es-ES', {
    weekday: 'long', day: 'numeric', month: 'long', year: 'numeric'
  });

  return (
    <div>
      {/* ── Page header ── */}
      <div className="admin-page-header">
        <div>
          <h1 className="admin-page-title">Dashboard</h1>
          <p className="admin-page-sub">Resumen general de la academia</p>
        </div>
        <button className="btn btn-primary">+ Reporte Mensual</button>
      </div>

      {/* ── Welcome banner ── */}
      <div className="dash-welcome">
        <div className="dash-welcome-left">
          <div className="dash-welcome-greeting">Bienvenido de vuelta,</div>
          <div className="dash-welcome-name">Andrés García 👋</div>
          <div className="dash-welcome-sub">{dateStr} · 3 tareas pendientes hoy</div>
        </div>
        <div className="dash-welcome-stats">
          <div className="dash-welcome-stat">
            <span className="dash-welcome-stat-value">6</span>
            <span className="dash-welcome-stat-label">Clases hoy</span>
          </div>
          <div className="dash-welcome-divider" />
          <div className="dash-welcome-stat">
            <span className="dash-welcome-stat-value">$8,430</span>
            <span className="dash-welcome-stat-label">Ingresos marzo</span>
          </div>
          <div className="dash-welcome-divider" />
          <div className="dash-welcome-stat">
            <span className="dash-welcome-stat-value">92%</span>
            <span className="dash-welcome-stat-label">Asistencia</span>
          </div>
        </div>
      </div>

      {/* ── KPI Cards ── */}
      <div className="kpi-grid">
        {KPIS.map((k) => (
          <div key={k.label} className="kpi-card">
            <div className="kpi-top">
              <span className="kpi-label">{k.label}</span>
              <div className={`kpi-icon ${k.cls}`}>{k.icon}</div>
            </div>
            <div className="kpi-value">{k.value}</div>
            {k.dir && (
              <div className={`kpi-delta ${k.dir}`}>
                {k.dir === 'up' ? '↑' : '↓'} {k.delta}
              </div>
            )}
            {!k.dir && (
              <div style={{ fontSize: 13, color: 'var(--ak-muted)', fontWeight: 500 }}>
                {k.delta}
              </div>
            )}
          </div>
        ))}
      </div>

      {/* ── Two-column content ── */}
      <div className="admin-grid-2">

        {/* Activity feed */}
        <div className="a-card">
          <div className="a-card-header">
            <span className="a-card-title">Actividad Reciente</span>
            <button className="a-card-action">Ver todo</button>
          </div>
          <div className="a-card-body">
            <div className="activity-list">
              {ACTIVITY.map((a, i) => (
                <div key={i} className="activity-item">
                  <div className={`activity-icon ${a.color}`}>{a.icon}</div>
                  <span className="activity-text">{a.text}</span>
                  <span className="activity-time">{a.time}</span>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* Today's classes */}
        <div className="a-card">
          <div className="a-card-header">
            <span className="a-card-title">Clases de Hoy</span>
            <button className="a-card-action">Ver calendario</button>
          </div>
          <div className="a-card-body">
            <div style={{ display: 'flex', flexDirection: 'column', gap: 10 }}>
              {UPCOMING.map((cls, i) => {
                const pct    = Math.round((cls.students / cls.max) * 100);
                const isFull = cls.students >= cls.max;
                return (
                  <div key={i} className="today-class-row">
                    <div className="today-class-bar" style={{ background: cls.color }} />
                    <div className="today-class-body">
                      <div className="today-class-top">
                        <span className="today-class-name">{cls.name}</span>
                        <span className="today-class-time">{cls.time}</span>
                      </div>
                      <div className="today-class-meta">
                        <span>{cls.instructor}</span>
                        <span style={{ fontWeight: 600, color: isFull ? 'var(--ak-red)' : 'var(--ak-muted)' }}>
                          {cls.students}/{cls.max} alumnos
                        </span>
                      </div>
                      <div className="today-class-progress">
                        <div style={{
                          width: `${pct}%`,
                          background: isFull ? 'var(--ak-red)' : cls.color
                        }} />
                      </div>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
