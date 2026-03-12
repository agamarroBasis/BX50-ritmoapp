import { Outlet, NavLink, useNavigate } from 'react-router-dom';
import './Admin.css';

const NAV_ITEMS = [
  {
    to: '/admin/dashboard',
    label: 'Dashboard',
    badge: null,
    icon: (
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" width="16" height="16">
        <rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/>
        <rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/>
      </svg>
    ),
  },
  {
    to: '/admin/calendar',
    label: 'Calendario',
    badge: null,
    icon: (
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" width="16" height="16">
        <rect x="3" y="4" width="18" height="18" rx="2"/>
        <line x1="16" y1="2" x2="16" y2="6"/>
        <line x1="8" y1="2" x2="8" y2="6"/>
        <line x1="3" y1="10" x2="21" y2="10"/>
        <circle cx="8" cy="15" r="1" fill="currentColor"/>
        <circle cx="12" cy="15" r="1" fill="currentColor"/>
        <circle cx="16" cy="15" r="1" fill="currentColor"/>
      </svg>
    ),
  },
  {
    to: '/admin/students',
    label: 'Alumnos',
    badge: '3',
    icon: (
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" width="16" height="16">
        <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
        <circle cx="9" cy="7" r="4"/>
        <path d="M23 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.75"/>
      </svg>
    ),
  },
  {
    to: '/admin/instructors',
    label: 'Instructores',
    badge: null,
    icon: (
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" width="16" height="16">
        <path d="M22 10v6M2 10l10-5 10 5-10 5z"/>
        <path d="M6 12v5c3 3 9 3 12 0v-5"/>
      </svg>
    ),
  },
  {
    to: '/admin/classes',
    label: 'Clases',
    badge: null,
    icon: (
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" width="16" height="16">
        <rect x="3" y="4" width="18" height="18" rx="2"/>
        <line x1="16" y1="2" x2="16" y2="6"/><line x1="8" y1="2" x2="8" y2="6"/>
        <line x1="3" y1="10" x2="21" y2="10"/>
      </svg>
    ),
  },
  {
    to: '/admin/finance',
    label: 'Finanzas',
    badge: null,
    icon: (
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" width="16" height="16">
        <line x1="12" y1="1" x2="12" y2="23"/>
        <path d="M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"/>
      </svg>
    ),
  },
];

function AdminLayout() {
  const navigate = useNavigate();
  const now = new Date();
  const dateStr = now.toLocaleDateString('es-ES', {
    weekday: 'short', day: 'numeric', month: 'short', year: 'numeric'
  });

  return (
    <div className="admin-app">
      {/* ── Sidebar ── */}
      <aside className="admin-sidebar">
        {/* Brand */}
        <div className="sidebar-brand">
          <div className="sidebar-brand-logo">
            <div className="sidebar-brand-icon">R</div>
            <span className="sidebar-brand-name">RITMO</span>
          </div>
          <span className="sidebar-brand-sub">Panel de Administración</span>
        </div>

        {/* Nav */}
        <nav className="sidebar-nav">
          <span className="sidebar-nav-label">Menú Principal</span>

          {NAV_ITEMS.map((item) => (
            <NavLink
              key={item.to}
              to={item.to}
              className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
            >
              <span className="nav-link-icon">{item.icon}</span>
              <span className="nav-link-text">{item.label}</span>
              {item.badge && <span className="nav-link-badge">{item.badge}</span>}
            </NavLink>
          ))}
        </nav>

        {/* Footer */}
        <div className="sidebar-footer">
          <div className="sidebar-user">
            <div className="sidebar-user-avatar">AG</div>
            <div className="sidebar-user-info">
              <span className="sidebar-user-name">Andrés García</span>
              <span className="sidebar-user-role">Administrador</span>
            </div>
            <button
              className="sidebar-logout"
              title="Cerrar sesión"
              onClick={() => navigate('/login')}
            >
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" width="16" height="16">
                <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/>
                <polyline points="16 17 21 12 16 7"/>
                <line x1="21" y1="12" x2="9" y2="12"/>
              </svg>
            </button>
          </div>
        </div>
      </aside>

      {/* ── Main area ── */}
      <div className="admin-main">
        {/* Top bar */}
        <header className="admin-topbar">
          <div className="topbar-left">
            <span className="topbar-page-title">Academia Ritmo</span>
            <span className="topbar-breadcrumb">Portal de Administración</span>
          </div>
          <div className="topbar-right">
            <span className="topbar-date">{dateStr}</span>
            <button className="topbar-notif" title="Notificaciones">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" width="18" height="18">
                <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/>
                <path d="M13.73 21a2 2 0 0 1-3.46 0"/>
              </svg>
              <span className="notif-dot" />
            </button>
          </div>
        </header>

        {/* Page content */}
        <main className="admin-content">
          <Outlet />
        </main>
      </div>
    </div>
  );
}

export default AdminLayout;
