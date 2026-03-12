import { useState } from 'react';

const STUDENTS = [
  { id: 1, name: 'Maria Garcia',    email: 'maria@email.com',    level: 'Intermedio',   membership: 'premium', status: 'active',   since: '2024-01', color: 'purple', initials: 'MG' },
  { id: 2, name: 'Juan Pérez',      email: 'juan@email.com',     level: 'Principiante', membership: 'basic',   status: 'active',   since: '2024-08', color: 'blue',   initials: 'JP' },
  { id: 3, name: 'Sofia Rodríguez', email: 'sofia@email.com',    level: 'Avanzado',     membership: 'premium', status: 'active',   since: '2023-06', color: 'green',  initials: 'SR' },
  { id: 4, name: 'Carlos López',    email: 'carlos@email.com',   level: 'Principiante', membership: 'trial',   status: 'active',   since: '2025-03', color: 'amber',  initials: 'CL' },
  { id: 5, name: 'Ana Martínez',    email: 'ana@email.com',      level: 'Intermedio',   membership: 'basic',   status: 'inactive', since: '2024-05', color: 'coral',  initials: 'AM' },
  { id: 6, name: 'Luis Fernández',  email: 'luis@email.com',     level: 'Avanzado',     membership: 'premium', status: 'active',   since: '2023-11', color: 'teal',   initials: 'LF' },
  { id: 7, name: 'Valentina Cruz',  email: 'vale@email.com',     level: 'Principiante', membership: 'trial',   status: 'active',   since: '2025-02', color: 'pink',   initials: 'VC' },
  { id: 8, name: 'Diego Herrera',   email: 'diego@email.com',    level: 'Intermedio',   membership: 'premium', status: 'active',   since: '2024-03', color: 'blue',   initials: 'DH' },
];

const MEMBERSHIP_LABEL = { premium: 'Premium', basic: 'Básica', trial: 'Prueba' };
const MEMBERSHIP_BADGE = { premium: 'badge badge-premium', basic: 'badge badge-basic', trial: 'badge badge-trial' };
const STATUS_BADGE = { active: 'badge badge-active', inactive: 'badge badge-inactive' };
const STATUS_LABEL = { active: 'Activo', inactive: 'Inactivo' };

function AdminStudents() {
  const [search, setSearch] = useState('');
  const [filterLevel, setFilterLevel] = useState('');
  const [filterStatus, setFilterStatus] = useState('');

  const filtered = STUDENTS.filter((s) => {
    const matchSearch = s.name.toLowerCase().includes(search.toLowerCase()) ||
                        s.email.toLowerCase().includes(search.toLowerCase());
    const matchLevel  = filterLevel  ? s.level === filterLevel  : true;
    const matchStatus = filterStatus ? s.status === filterStatus : true;
    return matchSearch && matchLevel && matchStatus;
  });

  return (
    <div>
      <div className="admin-page-header">
        <div>
          <h1 className="admin-page-title">Alumnos</h1>
          <p className="admin-page-sub">{STUDENTS.length} alumnos registrados</p>
        </div>
        <button className="btn btn-primary">+ Nuevo Alumno</button>
      </div>

      {/* Summary cards */}
      <div className="kpi-grid" style={{ marginBottom: 20 }}>
        <div className="kpi-card">
          <div className="kpi-top">
            <span className="kpi-label">Total Alumnos</span>
            <div className="kpi-icon purple">👥</div>
          </div>
          <div className="kpi-value">124</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top">
            <span className="kpi-label">Activos</span>
            <div className="kpi-icon green">✅</div>
          </div>
          <div className="kpi-value">98</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top">
            <span className="kpi-label">En Prueba</span>
            <div className="kpi-icon amber">⏳</div>
          </div>
          <div className="kpi-value">14</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top">
            <span className="kpi-label">Inactivos</span>
            <div className="kpi-icon blue">⏸</div>
          </div>
          <div className="kpi-value">12</div>
        </div>
      </div>

      {/* Table */}
      <div className="a-card">
        <div className="a-card-header">
          <span className="a-card-title">Listado de Alumnos</span>
          <button className="btn btn-ghost btn-sm">Exportar CSV</button>
        </div>
        <div className="a-card-body" style={{ paddingBottom: 0 }}>
          {/* Filters */}
          <div className="filter-bar">
            <div className="search-wrap">
              <span className="search-icon">🔍</span>
              <input
                className="search-input"
                type="text"
                placeholder="Buscar por nombre o correo..."
                value={search}
                onChange={(e) => setSearch(e.target.value)}
              />
            </div>
            <select
              className="filter-select"
              value={filterLevel}
              onChange={(e) => setFilterLevel(e.target.value)}
            >
              <option value="">Todos los niveles</option>
              <option>Principiante</option>
              <option>Intermedio</option>
              <option>Avanzado</option>
            </select>
            <select
              className="filter-select"
              value={filterStatus}
              onChange={(e) => setFilterStatus(e.target.value)}
            >
              <option value="">Todos los estados</option>
              <option value="active">Activos</option>
              <option value="inactive">Inactivos</option>
            </select>
          </div>

          {/* Table */}
          <div style={{ overflowX: 'auto' }}>
            <table className="a-table">
              <thead>
                <tr>
                  <th>Alumno</th>
                  <th>Nivel</th>
                  <th>Membresía</th>
                  <th>Estado</th>
                  <th>Desde</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {filtered.map((s) => (
                  <tr key={s.id}>
                    <td>
                      <div className="t-user">
                        <div className={`t-avatar ${s.color}`}>{s.initials}</div>
                        <div>
                          <div className="t-name">{s.name}</div>
                          <div className="t-email">{s.email}</div>
                        </div>
                      </div>
                    </td>
                    <td>{s.level}</td>
                    <td><span className={MEMBERSHIP_BADGE[s.membership]}>{MEMBERSHIP_LABEL[s.membership]}</span></td>
                    <td><span className={STATUS_BADGE[s.status]}>{STATUS_LABEL[s.status]}</span></td>
                    <td style={{ color: 'var(--admin-muted)', fontSize: 13 }}>{s.since}</td>
                    <td>
                      <div style={{ display: 'flex', gap: 6 }}>
                        <button className="btn btn-ghost btn-sm">Ver</button>
                        <button className="btn btn-ghost btn-sm">Editar</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          <div style={{ padding: '14px 0', display: 'flex', justifyContent: 'space-between', alignItems: 'center', borderTop: '1px solid var(--admin-border)', marginTop: 0 }}>
            <span style={{ fontSize: 13, color: 'var(--admin-muted)' }}>
              Mostrando {filtered.length} de {STUDENTS.length} alumnos
            </span>
            <div style={{ display: 'flex', gap: 6 }}>
              <button className="btn btn-ghost btn-sm">← Anterior</button>
              <button className="btn btn-ghost btn-sm" style={{ background: 'var(--admin-accent)', color: '#fff' }}>1</button>
              <button className="btn btn-ghost btn-sm">2</button>
              <button className="btn btn-ghost btn-sm">Siguiente →</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminStudents;
