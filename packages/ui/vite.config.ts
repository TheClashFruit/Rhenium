import { defineConfig } from 'vite';
import { resolve } from 'path';

import react from '@vitejs/plugin-react';
import dts from 'vite-plugin-dts';
import { libInjectCss } from 'vite-plugin-lib-inject-css';

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    react(),
    libInjectCss(),
    dts({
      include: ['src'],
      tsconfigPath: './tsconfig.app.json'
    })
  ],
  base: 'rhenium://html/',
  build: {
    copyPublicDir: false,
    lib: {
      entry: resolve(__dirname, 'src/index.ts'),
      formats: ['es']
    },
    rollupOptions: {
      output: {
        entryFileNames: '[name].js'
      }
    }
  }
});
