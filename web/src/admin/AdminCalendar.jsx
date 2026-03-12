import { useState } from 'react';

const MONTHS = [
  'Enero','Febrero','Marzo','Abril','Mayo','Junio',
  'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'
];
const DAY_HEADERS = ['Lun','Mar','Mié','Jue','Vie','Sáb','Dom'];
const DAY_SHORT   = ['Dom','Lun','Mar','Mié','Jue','Vie','Sáb'];

// Weekly recurring schedule (dayOfWeek: 0=Sun, 1=Mon, …, 6=Sat)
const WEEKLY_CLASSES = [
  { dayOfWeek: 1, id: 1, name: 'Salsa Cubana',    time: '18:00', end: '19:30', instructor: 'Andrés García',  room: 'Sala A', color: '#FF4D2E', level: 'Intermedio',   enrolled: 15, max: 15 },
  { dayOfWeek: 1, id: 2, name: 'Salsa On2',        time: '16:00', end: '17:30', instructor: 'Andrés García',  room: 'Sala A', color: '#F5A623', level: 'Principiante', enrolled: 12, max: 15 },
  { dayOfWeek: 2, id: 3, name: 'Bachata Sensual',  time: '19:30', end: '21:00', instructor: 'Laura Vargas',   room: 'Sala B', color: '#6366F1', level: 'Avanzado',     enrolled: 10, max: 12 },
  { dayOfWeek: 3, id: 4, name: 'Merengue Básico',  time: '14:00', end: '15:30', instructor: 'Laura Vargas',   room: 'Sala A', color: '#10B981', level: 'Principiante', enrolled: 8,  max: 15 },
  { dayOfWeek: 3, id: 5, name: 'Tango Argentino',  time: '17:00', end: '19:00', instructor: 'Patricia Ríos', room: 'Sala C', color: '#1E40AF', level: 'Avanzado',     enrolled: 6,  max: 10 },
  { dayOfWeek: 4, id: 6, name: 'Salsa On2',        time: '19:30', end: '21:00', instructor: 'Andrés García',  room: 'Sala B', color: '#FF4D2E', level: 'Avanzado',     enrolled: 9,  max: 12 },
  { dayOfWeek: 5, id: 7, name: 'Cumbia',            time: '11:00', end: '12:30', instructor: 'Carlos Medina', room: 'Sala B', color: '#EC4899', level: 'Principiante', enrolled: 9,  max: 12 },
  { dayOfWeek: 6, id: 8, name: 'Hip Hop',           time: '10:00', end: '11:30', instructor: 'Miguel Torres', room: 'Sala A', color: '#3B82F6', level: 'Intermedio',   enrolled: 12, max: 12 },
  { dayOfWeek: 6, id: 9, name: 'Zumba',             time: '09:00', end: '10:00', instructor: 'Diana López',   room: 'Sala C', color: '#14B8A6', level: 'Todos',         enrolled: 20, max: 25 },
];

// Build 6-row × 7-col grid for a given month (Monday-first)
function getMonthWeeks(year, month) {
  const firstDow   = new Date(year, month, 1).getDay();          // 0=Sun
  const startOffset = firstDow === 0 ? 6 : firstDow - 1;        // shift to Mon-first
  const daysInMonth = new Date(year, month + 1, 0).getDate();
  const weeks = [];
  let day = 1 - startOffset;
  while (day <= daysInMonth) {
    const week = [];
    for (let d = 0; d < 7; d++) {
      week.push(day > 0 && day <= daysInMonth ? day : 0);
      day++;
    }
    weeks.push(week);
  }
  return weeks;
}

function getClassesForDay(year, month, day) {
  if (!day) return [];
  const dow = new Date(year, month, day).getDay();
  return WEEKLY_CLASSES
    .filter(c => c.dayOfWeek === dow)
    .sort((a, b) => a.time.localeCompare(b.time));
}

// For demo, "today" = March 12 2026
const DEMO_TODAY = { year: 2026, month: 2, day: 12 };

export default function AdminCalendar() {
  const [year, setYear]           = useState(2026);
  const [month, setMonth]         = useState(2); // March
  const [selectedDay, setSelectedDay] = useState(12);

  const weeks          = getMonthWeeks(year, month);
  const selectedClasses = selectedDay ? getClassesForDay(year, month, selectedDay) : [];

  const totalClasses = weeks.reduce((sum, week) =>
    sum + week.reduce((s, d) => s + (d ? getClassesForDay(year, month, d).length : 0), 0), 0);

  function prevMonth() {
    if (month === 0) { setYear(y => y - 1); setMonth(11); }
    else setMonth(m => m - 1);
    setSelectedDay(null);
  }
  function nextMonth() {
    if (month === 11) { setYear(y => y + 1); setMonth(0); }
    else setMonth(m => m + 1);
    setSelectedDay(null);
  }

  const calStatsData = [
    { label: 'Clases / semana',      value: WEEKLY_CLASSES.length, color: '#6366F1', borderColor: '#6366F1' },
    { label: `Total en ${MONTHS[month]}`, value: totalClasses,     color: '#FF4D2E', borderColor: '#FF4D2E' },
    { label: 'Instructores activos', value: 6,                     color: '#10B981', borderColor: '#10B981' },
    { label: 'Salas en uso',         value: 3,                     color: '#F5A623', borderColor: '#F5A623' },
  ];

  return (
    <div>
      {/* ── Page header ── */}
      <div className="admin-page-header">
        <div>
          <h1 className="admin-page-title">Calendario de Clases</h1>
          <p className="admin-page-sub">Vista mensual · {totalClasses} clases en {MONTHS[month]} {year}</p>
        </div>
        <div style={{ display: 'flex', gap: 8 }}>
          <button className="btn btn-ghost">Exportar</button>
          <button className="btn btn-primary">+ Nueva Clase</button>
        </div>
      </div>

      {/* ── Quick-stat strip ── */}
      <div className="cal-stats-row">
        {calStatsData.map(s => (
          <div key={s.label} className="cal-stat" style={{ borderLeftColor: s.borderColor }}>
            <span className="cal-stat-value" style={{ color: s.color }}>{s.value}</span>
            <span className="cal-stat-label">{s.label}</span>
          </div>
        ))}
      </div>

      {/* ── Calendar + Detail panel ── */}
      <div className="cal-layout">

        {/* Calendar card */}
        <div className="a-card cal-card">
          {/* Month navigation */}
          <div className="a-card-header cal-header">
            <button className="cal-nav-btn" onClick={prevMonth} title="Mes anterior">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" width="15" height="15">
                <polyline points="15 18 9 12 15 6"/>
              </svg>
            </button>
            <span className="cal-month-title">{MONTHS[month]} {year}</span>
            <button className="cal-nav-btn" onClick={nextMonth} title="Mes siguiente">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" width="15" height="15">
                <polyline points="9 18 15 12 9 6"/>
              </svg>
            </button>
          </div>

          <div style={{ padding: '12px 16px 16px' }}>
            {/* Day-of-week headers */}
            <div className="cal-grid-header">
              {DAY_HEADERS.map(h => (
                <span key={h} className="cal-day-header">{h}</span>
              ))}
            </div>

            {/* Grid */}
            <div className="cal-grid">
              {weeks.map((week, wi) =>
                week.map((day, di) => {
                  const classes    = day ? getClassesForDay(year, month, day) : [];
                  const isToday    = day === DEMO_TODAY.day && month === DEMO_TODAY.month && year === DEMO_TODAY.year;
                  const isSelected = day === selectedDay;
                  return (
                    <div
                      key={`${wi}-${di}`}
                      className={[
                        'cal-day-cell',
                        !day         ? 'cal-day-empty'    : '',
                        isSelected   ? 'cal-day-selected' : '',
                        isToday && !isSelected ? 'cal-day-today' : '',
                      ].join(' ')}
                      onClick={() => day && setSelectedDay(day)}
                    >
                      {day > 0 && (
                        <>
                          <span className={`cal-day-num ${isToday ? 'cal-day-num-today' : ''} ${isSelected ? 'cal-day-num-selected' : ''}`}>
                            {day}
                          </span>
                          <div className="cal-pills">
                            {classes.slice(0, 2).map(c => (
                              <div key={c.id} className="cal-pill" style={{ background: c.color }}>
                                {c.name}
                              </div>
                            ))}
                            {classes.length > 2 && (
                              <div className="cal-pill cal-pill-more">
                                +{classes.length - 2} más
                              </div>
                            )}
                          </div>
                        </>
                      )}
                    </div>
                  );
                })
              )}
            </div>

            {/* Legend */}
            <div className="cal-legend">
              {WEEKLY_CLASSES.map(c => (
                <div key={c.id} className="cal-legend-item">
                  <span className="cal-legend-dot" style={{ background: c.color }} />
                  <span className="cal-legend-name">{c.name}</span>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* ── Detail panel ── */}
        <div className="cal-detail-panel">
          {!selectedDay ? (
            <div className="cal-detail-empty">
              <div className="cal-detail-empty-icon">📅</div>
              <p>Selecciona un día en el calendario para ver las clases programadas</p>
            </div>
          ) : (
            <>
              <div className="cal-detail-header">
                <div>
                  <span className="cal-detail-date">
                    {DAY_SHORT[new Date(year, month, selectedDay).getDay()]}, {selectedDay} de {MONTHS[month]}
                  </span>
                  <span className="cal-detail-year">{year}</span>
                </div>
                <span className="cal-detail-count">
                  {selectedClasses.length} clase{selectedClasses.length !== 1 ? 's' : ''}
                </span>
              </div>

              {selectedClasses.length === 0 ? (
                <div className="cal-detail-no-classes">
                  <span style={{ fontSize: 32 }}>🎉</span>
                  <p>Sin clases este día</p>
                  <span style={{ fontSize: 12, color: 'var(--ak-label)' }}>Día libre</span>
                </div>
              ) : (
                <div className="cal-detail-list">
                  {selectedClasses.map(c => {
                    const pct = Math.round((c.enrolled / c.max) * 100);
                    const isFull = c.enrolled >= c.max;
                    return (
                      <div key={c.id} className="cal-detail-item">
                        <div className="cal-detail-item-bar" style={{ background: c.color }} />
                        <div className="cal-detail-item-body">
                          <div className="cal-detail-item-name">{c.name}</div>
                          <div className="cal-detail-item-meta">
                            {c.time} – {c.end} &nbsp;·&nbsp; {c.room}
                          </div>
                          <div className="cal-detail-item-instructor">{c.instructor}</div>
                          <div className="cal-detail-item-footer">
                            <span
                              className="badge"
                              style={{ background: `${c.color}18`, color: c.color, fontSize: 10.5 }}
                            >
                              {c.level}
                            </span>
                            <span className={`badge ${isFull ? 'badge-full' : 'badge-open'}`} style={{ fontSize: 10.5 }}>
                              {isFull ? 'Llena' : 'Con cupo'}
                            </span>
                          </div>
                          <div style={{ marginTop: 8 }}>
                            <div style={{ display: 'flex', justifyContent: 'space-between', fontSize: 11, color: 'var(--ak-muted)', marginBottom: 4 }}>
                              <span>{c.enrolled}/{c.max} alumnos</span>
                              <span style={{ fontWeight: 700, color: isFull ? 'var(--ak-red)' : 'var(--ak-text)' }}>{pct}%</span>
                            </div>
                            <div className="cal-detail-progress-bar">
                              <div style={{
                                width: `${pct}%`,
                                background: isFull ? 'var(--ak-red)' : c.color
                              }} />
                            </div>
                          </div>
                        </div>
                      </div>
                    );
                  })}
                </div>
              )}
            </>
          )}
        </div>
      </div>
    </div>
  );
}
