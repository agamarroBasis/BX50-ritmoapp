const PAYMENTS = [
  { id: 1, student: 'Sofia Rodríguez', initials: 'SR', color: 'green',  membership: 'Premium', amount: 120, date: '01/03/2025', status: 'paid',    method: 'Tarjeta' },
  { id: 2, student: 'Andrés García',   initials: 'AG', color: 'coral',  membership: 'Premium', amount: 120, date: '02/03/2025', status: 'paid',    method: 'Transferencia' },
  { id: 3, student: 'Maria Garcia',    initials: 'MG', color: 'purple', membership: 'Premium', amount: 120, date: '03/03/2025', status: 'paid',    method: 'Efectivo' },
  { id: 4, student: 'Diego Herrera',   initials: 'DH', color: 'blue',   membership: 'Premium', amount: 120, date: '05/03/2025', status: 'paid',    method: 'Tarjeta' },
  { id: 5, student: 'Juan Pérez',      initials: 'JP', color: 'blue',   membership: 'Básica',  amount: 70,  date: '08/03/2025', status: 'paid',    method: 'Efectivo' },
  { id: 6, student: 'Luis Fernández',  initials: 'LF', color: 'teal',   membership: 'Premium', amount: 120, date: '10/03/2025', status: 'pending', method: '—' },
  { id: 7, student: 'Valentina Cruz',  initials: 'VC', color: 'pink',   membership: 'Prueba',  amount: 40,  date: '12/03/2025', status: 'pending', method: '—' },
  { id: 8, student: 'Carlos López',    initials: 'CL', color: 'amber',  membership: 'Prueba',  amount: 40,  date: '02/02/2025', status: 'overdue', method: '—' },
];

const MONTHS_DATA = [
  { month: 'Oct', revenue: 6200, expenses: 2800 },
  { month: 'Nov', revenue: 6800, expenses: 2600 },
  { month: 'Dic', revenue: 7500, expenses: 3100 },
  { month: 'Ene', revenue: 7200, expenses: 2900 },
  { month: 'Feb', revenue: 7900, expenses: 2700 },
  { month: 'Mar', revenue: 8430, expenses: 2850 },
];

const MAX_VAL = Math.max(...MONTHS_DATA.map((m) => m.revenue));

const STATUS_LABEL = { paid: 'Pagado', pending: 'Pendiente', overdue: 'Vencido' };
const STATUS_BADGE  = { paid: 'badge-paid', pending: 'badge-pending', overdue: 'badge-overdue' };

function AdminFinance() {
  // Full-month totals (all 98 members; the table shows a sample subset)
  const totalRevenue = 8430;
  const totalPending = 280;
  const expenses     = 2850;
  const net          = totalRevenue - expenses;

  return (
    <div>
      <div className="admin-page-header">
        <div>
          <h1 className="admin-page-title">Finanzas</h1>
          <p className="admin-page-sub">Resumen financiero — Marzo 2025</p>
        </div>
        <button className="btn btn-primary">📥 Exportar Reporte</button>
      </div>

      {/* KPI cards */}
      <div className="kpi-grid" style={{ marginBottom: 24 }}>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Ingresos (Mar)</span><div className="kpi-icon green">💰</div></div>
          <div className="kpi-value">${totalRevenue.toLocaleString()}</div>
          <div className="kpi-delta up">↑ +12% vs Feb</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Gastos (Mar)</span><div className="kpi-icon amber">📤</div></div>
          <div className="kpi-value">${expenses.toLocaleString()}</div>
          <div className="kpi-delta down">↑ +5% vs Feb</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Neto</span><div className="kpi-icon purple">📊</div></div>
          <div className="kpi-value">${net.toLocaleString()}</div>
          <div className="kpi-delta up">↑ Ganancia</div>
        </div>
        <div className="kpi-card">
          <div className="kpi-top"><span className="kpi-label">Pendiente</span><div className="kpi-icon blue">⏳</div></div>
          <div className="kpi-value">${totalPending.toLocaleString()}</div>
          <div className="kpi-delta down">↓ Por cobrar</div>
        </div>
      </div>

      <div className="admin-grid-2">
        {/* Bar chart (CSS) */}
        <div className="a-card">
          <div className="a-card-header">
            <span className="a-card-title">Ingresos vs Gastos (6 meses)</span>
          </div>
          <div className="a-card-body">
            <div style={{ display: 'flex', alignItems: 'flex-end', gap: 8, height: 180, paddingBottom: 0, position: 'relative', background: 'var(--admin-bg)', borderRadius: 10, padding: '16px 12px 0' }}>
              {MONTHS_DATA.map((m) => {
                const revH = Math.round((m.revenue / MAX_VAL) * 130);
                const expH = Math.round((m.expenses / MAX_VAL) * 130);
                return (
                  <div key={m.month} style={{ flex: 1, display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'flex-end', gap: 0, height: '100%' }}>
                    <div style={{ display: 'flex', alignItems: 'flex-end', gap: 2, width: '100%', justifyContent: 'center' }}>
                      <div style={{ width: 10, height: revH, background: 'var(--admin-accent)', borderRadius: '3px 3px 0 0' }} title={`$${m.revenue}`} />
                      <div style={{ width: 10, height: expH, background: '#EF4444', borderRadius: '3px 3px 0 0', opacity: 0.75 }} title={`$${m.expenses}`} />
                    </div>
                    <span style={{ fontSize: 10, color: 'var(--admin-muted)', fontWeight: 700, paddingTop: 6, paddingBottom: 8 }}>{m.month}</span>
                  </div>
                );
              })}
            </div>
            <div style={{ display: 'flex', gap: 16, justifyContent: 'center', marginTop: 8 }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: 6, fontSize: 12, color: 'var(--admin-muted)' }}>
                <span style={{ width: 10, height: 10, borderRadius: 2, background: 'var(--admin-accent)', display: 'inline-block' }} />
                Ingresos
              </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: 6, fontSize: 12, color: 'var(--admin-muted)' }}>
                <span style={{ width: 10, height: 10, borderRadius: 2, background: 'var(--admin-red)', opacity: 0.7, display: 'inline-block' }} />
                Gastos
              </div>
            </div>
          </div>
        </div>

        {/* Membership distribution */}
        <div className="a-card">
          <div className="a-card-header">
            <span className="a-card-title">Distribución de Membresías</span>
          </div>
          <div className="a-card-body" style={{ display: 'flex', flexDirection: 'column', gap: 16 }}>
            {[
              { label: 'Premium',     count: 48, pct: 49, color: '#6366F1', badge: 'badge-premium' },
              { label: 'Básica',      count: 36, pct: 37, color: '#3B82F6', badge: 'badge-basic' },
              { label: 'En Prueba',   count: 14, pct: 14, color: '#F59E0B', badge: 'badge-trial' },
            ].map((m) => (
              <div key={m.label}>
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: 6 }}>
                  <div style={{ display: 'flex', alignItems: 'center', gap: 8 }}>
                    <span className={`badge ${m.badge}`}>{m.label}</span>
                  </div>
                  <div style={{ fontSize: 13, fontWeight: 700, color: 'var(--admin-text)' }}>
                    {m.count} <span style={{ color: 'var(--admin-muted)', fontWeight: 500 }}>({m.pct}%)</span>
                  </div>
                </div>
                <div style={{ height: 8, background: 'var(--admin-border)', borderRadius: 4, overflow: 'hidden' }}>
                  <div style={{ width: `${m.pct}%`, height: '100%', background: m.color, borderRadius: 4 }} />
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Payments table */}
      <div className="a-card" style={{ marginTop: 20 }}>
        <div className="a-card-header">
          <span className="a-card-title">Pagos de Marzo 2025</span>
          <button className="btn btn-ghost btn-sm">Exportar</button>
        </div>
        <div className="a-card-body" style={{ padding: 0 }}>
          <div style={{ overflowX: 'auto' }}>
            <table className="a-table">
              <thead>
                <tr>
                  <th>Alumno</th>
                  <th>Membresía</th>
                  <th>Monto</th>
                  <th>Fecha</th>
                  <th>Método</th>
                  <th>Estado</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {PAYMENTS.map((p) => (
                  <tr key={p.id}>
                    <td>
                      <div className="t-user">
                        <div className={`t-avatar ${p.color}`}>{p.initials}</div>
                        <span className="t-name">{p.student}</span>
                      </div>
                    </td>
                    <td>{p.membership}</td>
                    <td style={{ fontWeight: 700, fontSize: 15 }}>
                      ${p.amount}
                    </td>
                    <td style={{ color: 'var(--admin-muted)', fontSize: 13 }}>{p.date}</td>
                    <td style={{ fontSize: 13 }}>{p.method}</td>
                    <td>
                      <span className={`badge ${STATUS_BADGE[p.status]}`}>
                        {STATUS_LABEL[p.status]}
                      </span>
                    </td>
                    <td>
                      {p.status !== 'paid' ? (
                        <button className="btn btn-primary btn-sm">Marcar Pagado</button>
                      ) : (
                        <button className="btn btn-ghost btn-sm">Recibo</button>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminFinance;
