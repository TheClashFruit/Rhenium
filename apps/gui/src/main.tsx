import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';

import './index.css';

import { Button } from '@rhenium/ui';
import { Minecraft } from '@rhenium/bridge';

function App() {
  Minecraft.helloWorld();

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
