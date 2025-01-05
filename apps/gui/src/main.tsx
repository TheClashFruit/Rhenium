import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';

import './index.css';

import { Button } from '@rhenium/ui';

function App() {
  return (
    <>
      <Button />
    </>
  )
}

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
