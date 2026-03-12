const KPIS = [
  { label: 'Total Alumnos',        value: '124',    delta: '+5 este mes',     dir: 'up',   icon: '👥', cls: 'purple' },
  { label: 'Membresías Activas',   value: '98',     delta: '+3 esta semana',  dir: 'up',   icon: '✅', cls: 'green'  },
  { label: 'Ingresos (Marzo)',     value: '$8,430', delta: '+12% vs febrero', dir: 'up',   icon: '💰', cls: 'amber'  },
  { label: 'Clases Hoy',           value: '6',      delta: '2 en curso',      dir: null,   icon: '📅', cls: 'blue'   },
];

const ACTIVITY = [
  { color: 'green',  text: 'Maria Garcia renovó su membresía Premium.',         time: 'hace 10 min' },
  { color: 'blue',   text: 'Nueva clase de Bachata agregada para el viernes.',   time: 'hace 25 min' },
  { color: 'amber',  text: 'Pago pendiente de Luis Fernández ($120).',           time: 'hace 1h' },
  { color: 'purple', text: 'Instructor Carlos López actualizó su horario.',      time: 'hace 2h' },
  { color: 'red',    text: 'Ana Martínez canceló su membresía básica.',          time: 'hace 3h' },
  { color: 'green',  text: 'Diego Herrera completó 10 clases este mes.',         time: 'ayer' },
];

const UPCOMING = [
  { time: '14:00', name: 'Merengue Básico',    instructor: 'Laura V.',   students: 8,  max: 15, color: '#10B981' },
  { time: '16:00', name: 'Salsa On2',          instructor: 'Andrés G.',  students: 12, max: 15, color: '#FF4D2E' },
  { time: '18:00', name: 'Salsa Cubana',       instructor: 'Andrés G.',  students: 15, max: 15, color: '#F5A623' },
  { time: '19:30', name: 'Bachata Sensual',    instructor: 'Laura V.',   students: 10, max: 12, color: '#6366F1' },
];

function AdminDashboard() {
  return (
    <div>
      {/* Header */}
      <div className="admin-page-header">
        <div>
          <h1 className="admin-page-title">Dashboard</h1>
          <p className="admin-page-sub">Resumen general de la academia</p>
        </div>
        <button className="btn btn-primary">+ Reporte Mensual</button>
      </div>

      {/* KPIs */}
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
            {!k.dir && <div style={{ fontSize: 13, color: 'var(--admin-muted)', fontWeight: 500 }}>{k.delta}</div>}
          </div>
        ))}
      </div>

      {/* Two-column grid */}
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
                  <span className={`activity-dot ${a.color}`} />
                  <span className="activity-text">{a.text}</span>
                  <span className="activity-time">{a.time}</span>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* Upcoming classes */}
        <div className="a-card">
          <div className="a-card-header">
            <span className="a-card-title">Clases de Hoy</span>
            <button className="a-card-action">Ver horario</button>
          </div>
          <div className="a-card-body">
            <div style={{ display: 'flex', flexDirection: 'column', gap: 10 }}>
              {UPCOMING.map((cls, i) => {
                const pct = Math.round((cls.students / cls.max) * 100);
                return (
                  <div key={i} style={{
                    background: 'var(--admin-bg)',
                    borderRadius: 10,
                    padding: '12px 14px',
                    display: 'flex',
                    alignItems: 'center',
                    gap: 12
                  }}>
                    <div style={{
                      width: 4,
                      alignSelf: 'stretch',
                      borderRadius: 4,
                      background: cls.color,
                      flexShrink: 0
                    }} />
                    <div style={{ flex: 1, minWidth: 0 }}>
                      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: 4 }}>
                        <span style={{ fontWeight: 700, fontSize: 14, color: 'var(--admin-text)' }}>{cls.name}</span>
                        <span style={{ fontSize: 13, color: 'var(--admin-muted)', fontWeight: 600 }}>{cls.time}</span>
                      </div>
                      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: 6 }}>
                        <span style={{ fontSize: 12, color: 'var(--admin-muted)' }}>{cls.instructor}</span>
                        <span style={{ fontSize: 12, color: 'var(--admin-muted)', fontWeight: 500 }}>
                          {cls.students}/{cls.max} alumnos
                        </span>
                      </div>
                      <div style={{ height: 4, background: 'var(--admin-border)', borderRadius: 2, overflow: 'hidden' }}>
                        <div style={{ width: `${pct}%`, height: '100%', background: cls.color, borderRadius: 2 }} />
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
