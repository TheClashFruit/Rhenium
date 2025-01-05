interface CefQueryOptions {
  request: string;
  persistent?: boolean;

  onSuccess?: (response: string) => void;
  onFailure?: (errcode: number, errmsg: string) => void;
}

interface CefWindow extends Window {
  javaQuery: (options: CefQueryOptions) => number;
  javaQueryCancel: (id: number) => void;
}

export const cefWindow = window as CefWindow & typeof globalThis;