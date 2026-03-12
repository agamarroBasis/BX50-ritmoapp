import { useState } from 'react';

const CLASSES = [
  { id: 1, name: 'Salsa Cubana',   level: 'Intermedio',   day: 'Lunes',    time: '18:00 – 19:30', instructor: 'Andrés García',  room: 'Sala A', enrolled: 15, max: 15, status: 'full',   color: '#FF4D2E' },
  { id: 2, name: 'Salsa On2',      level: 'Principiante', day: 'Lunes',    time: '16:00 – 17:30', instructor: 'Andrés García',  room: 'Sala A', enrolled: 12, max: 15, status: 'open',   color: '#F5A623' },
  { id: 3, name: 'Bachata Sensual',level: 'Avanzado',     day: 'Martes',   time: '19:30 – 21:00', instructor: 'Laura Vargas',   room: 'Sala B', enrolled: 10, max: 12, status: 'open',   color: '#6366F1' },
  { id: 4, name: 'Merengue Básico',level: 'Principiante', day: 'Miércoles',time: '14:00 – 15:30', instructor: 'Laura Vargas',   room: 'Sala A', enrolled: 8,  max: 15, status: 'open',   color: '#10B981' },
  { id: 5, name: 'Tango Argentino',level: 'Avanzado',     day: 'Jueves',   time: '17:00 – 19:00', instructor: 'Patricia Ríos', room: 'Sala C', enrolled: 6,  max: 10, status: 'open',   color: '#1E293B' },
  { id: 6, name: 'Cumbia',         level: 'Principiante', day: 'Viernes',  time: '11:00 – 12:30', instructor: 'Carlos Medina',  room: 'Sala B', enrolled: 9,  max: 12, status: 'open',   color: '#EC4899' },
  { id: 7, name: 'Hip Hop',        level: 'Intermedio',   day: 'Sábado',   time: '10:00 – 11:30', instructor: 'Miguel Torres',  room: 'Sala A', enrolled: 12, max: 12, status: 'full',   color: '#3B82F6' },
  { id: 8, name: 'Zumba',          level: 'Todos',        day: 'Sábado',   time: '09:00 – 10:00', instructor: 'Diana López',    room: 'Sala C', enrolled: 20, max: 25, status: 'open',   color: '#14B8A6' },
];

const DAYS = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];

function AdminClasses() {
  const [filterDay, setFilterDay] = useState('');
  const [search, setSearch] = useState('');

  const filtered = CLASSES.filter((c) => {
    const matchDay    = filterDay ? c.day === filterDay : true;
    const matchSearch = c.name.toLowerCase().includes(search.toLowerCase()) ||
                        c.instructor.toLowerCase().includes(search.toLowerCase());
    return matchDay && matchSearch;
  });

  return (
    <div>
      <div className="admin-page-header">
        <div>
          <h1 className="admin-page-title">Clases</h1>
          <p className="admin-page-sub">{CLASSES.length} clases en el horario semanal</p>
        </div>
        <button className="btn btn-primary">+ Crear Clase</button>
      </div>

      {/* KPI row */}
      <div className="kpi-grid" style={{ marginBottom: 24 }}>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Total Clases</span><div className="kpi-icon purple">📅</div></div>
          <div className="kpi-value">{CLASSES.length}</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Con Cupo</span><div className="kpi-icon green">✅</div></div>
          <div className="kpi-value">{CLASSES.filter(c => c.status === 'open').length}</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Llenas</span><div className="kpi-icon amber">🔴</div></div>
          <div className="kpi-value">{CLASSES.filter(c => c.status === 'full').length}</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Salas en Uso</span><div className="kpi-icon blue">🏠</div></div>
          <div className="kpi-value">3</div>
        </div>
      </div>

      {/* Day filter pills */}
      <div style={{ display: 'flex', gap: 6, marginBottom: 16, flexWrap: 'wrap' }}>
        <button
          className="btn btn-sm"
          style={{
            background: !filterDay ? 'var(--admin-accent)' : 'var(--admin-card)',
            color: !filterDay ? '#fff' : 'var(--admin-muted)',
            border: `1px solid ${!filterDay ? 'var(--admin-accent)' : 'var(--admin-border)'}`,
          }}
          onClick={() => setFilterDay('')}
        >
          Todos
        </button>
        {DAYS.map((day) => (
          <button
            key={day}
            className="btn btn-sm"
            style={{
              background: filterDay === day ? 'var(--admin-accent)' : 'var(--admin-card)',
              color: filterDay === day ? '#fff' : 'var(--admin-muted)',
              border: `1px solid ${filterDay === day ? 'var(--admin-accent)' : 'var(--admin-border)'}`,
            }}
            onClick={() => setFilterDay(filterDay === day ? '' : day)}
          >
            {day}
          </button>
        ))}
      </div>

      <div className="a-card">
        <div className="a-card-header">
          <span className="a-card-title">Horario de Clases</span>
          <div className="search-wrap" style={{ maxWidth: 260 }}>
            <span className="search-icon">🔍</span>
            <input
              className="search-input"
              type="text"
              placeholder="Buscar clase o instructor..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </div>
        </div>
        <div className="a-card-body" style={{ padding: 0 }}>
          <div style={{ overflowX: 'auto' }}>
            <table className="a-table">
              <thead>
                <tr>
                  <th>Clase</th>
                  <th>Día</th>
                  <th>Horario</th>
                  <th>Instructor</th>
                  <th>Sala</th>
                  <th>Alumnos</th>
                  <th>Estado</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {filtered.map((c) => {
                  const pct = Math.round((c.enrolled / c.max) * 100);
                  return (
                    <tr key={c.id}>
                      <td>
                        <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
                          <div style={{ width: 4, height: 36, borderRadius: 2, background: c.color, flexShrink: 0 }} />
                          <div>
                            <div className="t-name">{c.name}</div>
                            <div className="t-email">{c.level}</div>
                          </div>
                        </div>
                      </td>
                      <td style={{ fontWeight: 600, color: 'var(--admin-muted)', fontSize: 13 }}>{c.day}</td>
                      <td style={{ fontWeight: 600, fontSize: 13 }}>{c.time}</td>
                      <td style={{ fontSize: 13 }}>{c.instructor}</td>
                      <td>
                        <span style={{
                          background: 'var(--admin-bg)',
                          padding: '3px 8px',
                          borderRadius: 6,
                          fontSize: 12,
                          fontWeight: 600,
                          color: 'var(--admin-muted)',
                        }}>{c.room}</span>
                      </td>
                      <td>
                        <div style={{ minWidth: 80 }}>
                          <div style={{ display: 'flex', justifyContent: 'space-between', fontSize: 12, color: 'var(--admin-muted)', marginBottom: 4 }}>
                            <span>{c.enrolled}/{c.max}</span>
                            <span>{pct}%</span>
                          </div>
                          <div style={{ height: 4, background: 'var(--admin-border)', borderRadius: 2, overflow: 'hidden' }}>
                            <div style={{ width: `${pct}%`, height: '100%', background: c.color, borderRadius: 2 }} />
                          </div>
                        </div>
                      </td>
                      <td>
                        <span className={`badge ${c.status === 'full' ? 'badge-full' : 'badge-open'}`}>
                          {c.status === 'full' ? 'Llena' : 'Con cupo'}
                        </span>
                      </td>
                      <td>
                        <div style={{ display: 'flex', gap: 6 }}>
                          <button className="btn btn-ghost btn-sm">Editar</button>
                          <button className="btn btn-danger btn-sm">—</button>
                        </div>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminClasses;
