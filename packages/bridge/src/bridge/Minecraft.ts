import { cefWindow } from '../util/window';

export class Minecraft {
  static async helloWorld(): Promise<string> {
    return new Promise((resolve, reject) => {
      cefWindow.javaQuery({
        request: 'helloWorld',
        onSuccess: (response) => {
          resolve(response);
        },
        onFailure: (errcode, errmsg) => {
          reject(new Error(`Error code: ${errcode}, message: ${errmsg}`));
        }
      });
    });
  }
}