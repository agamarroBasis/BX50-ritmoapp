import { Outlet, NavLink, useNavigate } from 'react-router-dom';
import './Admin.css';

const NAV_ITEMS = [
  { to: '/admin/dashboard',    icon: '📊', label: 'Dashboard',    badge: null },
  { to: '/admin/students',     icon: '👥', label: 'Alumnos',      badge: '3'  },
  { to: '/admin/instructors',  icon: '🎓', label: 'Instructores', badge: null },
  { to: '/admin/classes',      icon: '📅', label: 'Clases',       badge: null },
  { to: '/admin/finance',      icon: '💰', label: 'Finanzas',     badge: null },
];

function AdminLayout() {
  const navigate = useNavigate();
  const now = new Date();
  const dateStr = now.toLocaleDateString('es-ES', { weekday: 'short', day: 'numeric', month: 'short', year: 'numeric' });

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
            <div className="sidebar-user-avatar">A</div>
            <div className="sidebar-user-info">
              <span className="sidebar-user-name">Andrés García</span>
              <span className="sidebar-user-role">Administrador</span>
            </div>
            <button
              className="sidebar-logout"
              title="Cerrar sesión"
              onClick={() => navigate('/login')}
            >
              ⬡
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
            <span className="topbar-breadcrumb">Admin Portal</span>
          </div>
          <div className="topbar-right">
            <span className="topbar-date">{dateStr}</span>
            <button className="topbar-notif">
              🔔
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
