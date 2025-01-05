import { defineConfig } from 'vite';

import react from '@vitejs/plugin-react';

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  base: 'rhenium://html/',
  build: {
    outDir: '../mod/src/main/resources/assets/rhenium/html',
    emptyOutDir: true,

    rollupOptions: {
      external: ['react', 'react-dom'],
    }
  }
});
