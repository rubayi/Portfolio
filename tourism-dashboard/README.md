# 📊 Tourism Dashboard

A real-time analytics dashboard for tourism business operations. Built with React and TypeScript for monitoring bookings, revenue, and inventory.

![React](https://img.shields.io/badge/React-18.2-blue)
![TypeScript](https://img.shields.io/badge/TypeScript-5.0-blue)
![Tailwind](https://img.shields.io/badge/Tailwind-3.4-06B6D4)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 🛠️ Tech Stack

| Category | Technology |
|----------|------------|
| **Framework** | React 18 with Hooks |
| **Language** | TypeScript 5.0 |
| **Styling** | Tailwind CSS 3.4 |
| **Charts** | Chart.js + react-chartjs-2 |
| **State Management** | React Query (TanStack Query) |
| **Routing** | React Router v6 |
| **HTTP Client** | Axios |
| **Build Tool** | Vite |
| **Testing** | Vitest + React Testing Library |

---

## 📁 Project Structure

```
src/
├── components/
│   ├── common/          # Reusable UI components
│   │   ├── Button.tsx
│   │   ├── Card.tsx
│   │   ├── Modal.tsx
│   │   └── Table.tsx
│   ├── charts/          # Chart components
│   │   ├── RevenueChart.tsx
│   │   ├── BookingTrends.tsx
│   │   └── PieChart.tsx
│   ├── dashboard/       # Dashboard-specific components
│   │   ├── StatCard.tsx
│   │   ├── RecentBookings.tsx
│   │   └── InventoryStatus.tsx
│   └── layout/          # Layout components
│       ├── Header.tsx
│       ├── Sidebar.tsx
│       └── MainLayout.tsx
├── hooks/               # Custom React hooks
│   ├── useBookings.ts
│   ├── useRevenue.ts
│   └── useInventory.ts
├── pages/               # Page components
│   ├── Dashboard.tsx
│   ├── Bookings.tsx
│   ├── Inventory.tsx
│   └── Reports.tsx
├── services/            # API service layer
│   ├── api.ts
│   ├── bookingService.ts
│   └── revenueService.ts
├── types/               # TypeScript type definitions
│   ├── booking.ts
│   ├── revenue.ts
│   └── inventory.ts
├── utils/               # Utility functions
│   ├── formatters.ts
│   ├── dateUtils.ts
│   └── exportUtils.ts
└── App.tsx
```

---

## 🚀 Getting Started

### Prerequisites
- Node.js 18+
- npm or yarn

### Installation

```bash
# Clone the repository
git clone https://github.com/rubayi/Portfolio.git
cd tourism-dashboard

# Install dependencies
npm install

# Start development server
npm run dev
```

The app will be available at `http://localhost:5173`

### Environment Variables

Create a `.env` file in the root directory:

```env
VITE_API_BASE_URL=http://localhost:8080/api
VITE_APP_NAME=Tourism Dashboard
```

---

## 📱 Key Components

### Dashboard Overview
```tsx
// Main dashboard with key metrics
<Dashboard>
  <StatCard title="Today's Revenue" value="$12,450" trend="+12%" />
  <StatCard title="Active Bookings" value="234" trend="+5%" />
  <StatCard title="Available Tours" value="45" />
  <RevenueChart data={revenueData} period="monthly" />
  <RecentBookings bookings={latestBookings} />
</Dashboard>
```

### Custom Hooks
```tsx
// Fetch and manage booking data
const { bookings, isLoading, error, refetch } = useBookings({
  status: 'confirmed',
  dateRange: { start: '2024-01-01', end: '2024-12-31' }
});
```

---

## 📊 Chart Examples

### Revenue Trend Chart
- Line chart showing daily/weekly/monthly revenue
- Comparison with previous period
- Interactive tooltips with detailed breakdown

### Booking Distribution
- Pie chart showing booking sources (website, phone, partner)
- Bar chart for tour category popularity
- Heatmap for peak booking times

---

## 🎨 Theming

The dashboard supports full theming via Tailwind CSS:

```tsx
// Toggle dark mode
const { theme, toggleTheme } = useTheme();

// Theme-aware components
<Card className="bg-white dark:bg-gray-800">
  <Text className="text-gray-900 dark:text-gray-100">
    Content here
  </Text>
</Card>
```

---

## 🧪 Testing

```bash
# Run unit tests
npm run test

# Run tests with coverage
npm run test:coverage

# Run e2e tests
npm run test:e2e
```

---

## 📦 Build & Deployment

```bash
# Build for production
npm run build

# Preview production build
npm run preview

# Deploy to Vercel (example)
vercel deploy --prod
```

---

## 🔌 API Integration

The dashboard connects to a REST API backend. Example endpoints:

```typescript
// services/api.ts
const API = {
  bookings: {
    getAll: () => axios.get('/bookings'),
    getById: (id: string) => axios.get(`/bookings/${id}`),
    create: (data: BookingInput) => axios.post('/bookings', data),
  },
  revenue: {
    getSummary: (period: string) => axios.get(`/revenue/summary?period=${period}`),
    getDaily: (date: string) => axios.get(`/revenue/daily?date=${date}`),
  },
  inventory: {
    getTours: () => axios.get('/inventory/tours'),
    updateAvailability: (id: string, data: AvailabilityUpdate) => 
      axios.patch(`/inventory/tours/${id}`, data),
  }
};
```

---

## 📈 Performance Optimizations

- **Code Splitting** - Lazy loading for route-based chunking
- **Memoization** - React.memo and useMemo for expensive computations
- **Virtual Scrolling** - Efficient rendering for large data tables
- **Query Caching** - React Query for intelligent data caching

---

## 🗺️ Roadmap

- [x] Core dashboard layout
- [x] Revenue analytics charts
- [x] Booking management table
- [ ] Real-time WebSocket updates
- [ ] Multi-language support (English, Korean, Japanese)
- [ ] Mobile responsive redesign
- [ ] PDF report generation

---

## 👤 Author

**Misook Lee**
- Full Stack Developer with 15+ years experience
- Specialized in React, Vue.js, and enterprise dashboards
- [LinkedIn](https://linkedin.com/in/misookyi) | [Email](mailto:rubayi@gmail.com)

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- [Chart.js](https://www.chartjs.org/) for beautiful charts
- [Tailwind CSS](https://tailwindcss.com/) for utility-first styling
- [Heroicons](https://heroicons.com/) for icons
