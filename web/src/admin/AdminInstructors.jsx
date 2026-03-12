const INSTRUCTORS = [
  {
    id: 1,
    name: 'Andrés García',
    email: 'andres@ritmo.com',
    initials: 'AG',
    color: 'coral',
    specialties: ['Salsa Cubana', 'Salsa On2'],
    classes: 10,
    students: 48,
    rating: 4.9,
    status: 'active',
    joined: '2022-01',
  },
  {
    id: 2,
    name: 'Laura Vargas',
    email: 'laura@ritmo.com',
    initials: 'LV',
    color: 'purple',
    specialties: ['Bachata', 'Merengue'],
    classes: 8,
    students: 35,
    rating: 4.8,
    status: 'active',
    joined: '2022-04',
  },
  {
    id: 3,
    name: 'Carlos Medina',
    email: 'carlos@ritmo.com',
    initials: 'CM',
    color: 'blue',
    specialties: ['Cumbia', 'Vallenato'],
    classes: 6,
    students: 22,
    rating: 4.7,
    status: 'active',
    joined: '2023-02',
  },
  {
    id: 4,
    name: 'Patricia Ríos',
    email: 'patricia@ritmo.com',
    initials: 'PR',
    color: 'green',
    specialties: ['Tango', 'Vals'],
    classes: 5,
    students: 18,
    rating: 4.9,
    status: 'active',
    joined: '2023-06',
  },
  {
    id: 5,
    name: 'Miguel Torres',
    email: 'miguel@ritmo.com',
    initials: 'MT',
    color: 'amber',
    specialties: ['Hip Hop', 'Urban'],
    classes: 4,
    students: 20,
    rating: 4.6,
    status: 'active',
    joined: '2024-01',
  },
  {
    id: 6,
    name: 'Diana López',
    email: 'diana@ritmo.com',
    initials: 'DL',
    color: 'pink',
    specialties: ['Zumba', 'Aeróbicos'],
    classes: 7,
    students: 40,
    rating: 4.8,
    status: 'inactive',
    joined: '2023-03',
  },
];

function AdminInstructors() {
  const active = INSTRUCTORS.filter((i) => i.status === 'active').length;

  return (
    <div>
      <div className="admin-page-header">
        <div>
          <h1 className="admin-page-title">Instructores</h1>
          <p className="admin-page-sub">{INSTRUCTORS.length} instructores · {active} activos</p>
        </div>
        <button className="btn btn-primary">+ Nuevo Instructor</button>
      </div>

      {/* Summary */}
      <div className="kpi-grid" style={{ marginBottom: 24 }}>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Total Instructores</span><div className="kpi-icon purple">🎓</div></div>
          <div className="kpi-value">{INSTRUCTORS.length}</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Activos</span><div className="kpi-icon green">✅</div></div>
          <div className="kpi-value">{active}</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Total Clases</span><div className="kpi-icon amber">📅</div></div>
          <div className="kpi-value">{INSTRUCTORS.reduce((a, i) => a + i.classes, 0)}</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Alumnos Totales</span><div className="kpi-icon blue">👥</div></div>
          <div className="kpi-value">{INSTRUCTORS.reduce((a, i) => a + i.students, 0)}</div>
        </div>
      </div>

      {/* Instructor cards grid */}
      <div className="instructor-grid">
        {INSTRUCTORS.map((inst) => (
          <div key={inst.id} className="instructor-card">
            <div style={{ width: '100%', display: 'flex', justifyContent: 'flex-end' }}>
              <span className={`badge ${inst.status === 'active' ? 'badge-active' : 'badge-inactive'}`}>
                {inst.status === 'active' ? 'Activo' : 'Inactivo'}
              </span>
            </div>

            <div className={`instructor-avatar t-avatar ${inst.color}`} style={{ width: 60, height: 60, fontSize: 20 }}>
              {inst.initials}
            </div>

            <div>
              <div className="instructor-name">{inst.name}</div>
              <div className="instructor-specialty">{inst.email}</div>
            </div>

            <div className="instructor-tags">
              {inst.specialties.map((s) => (
                <span key={s} className="tag">{s}</span>
              ))}
            </div>

            <div style={{ display: 'flex', gap: 20, justifyContent: 'center', width: '100%' }}>
              <div style={{ textAlign: 'center' }}>
                <div style={{ fontSize: 18, fontWeight: 800, color: 'var(--admin-text)' }}>{inst.classes}</div>
                <div style={{ fontSize: 11, color: 'var(--admin-muted)', fontWeight: 600 }}>Clases</div>
              </div>
              <div style={{ textAlign: 'center' }}>
                <div style={{ fontSize: 18, fontWeight: 800, color: 'var(--admin-text)' }}>{inst.students}</div>
                <div style={{ fontSize: 11, color: 'var(--admin-muted)', fontWeight: 600 }}>Alumnos</div>
              </div>
              <div style={{ textAlign: 'center' }}>
                <div style={{ fontSize: 18, fontWeight: 800, color: 'var(--admin-text)' }}>⭐{inst.rating}</div>
                <div style={{ fontSize: 11, color: 'var(--admin-muted)', fontWeight: 600 }}>Rating</div>
              </div>
            </div>

            <div style={{ display: 'flex', gap: 8, width: '100%' }}>
              <button className="btn btn-ghost btn-sm" style={{ flex: 1 }}>Ver Perfil</button>
              <button className="btn btn-ghost btn-sm" style={{ flex: 1 }}>Editar</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AdminInstructors;
