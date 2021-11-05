import { Inject, Injectable, InjectionToken, Optional } from '@angular/core';

export const ERROR_LEVEL = new InjectionToken<string>('ERROR_LEVEL');

@Injectable()
export class LoggerService {
  private nivel = 99;

  constructor(@Optional() @Inject(ERROR_LEVEL) nivel?: number) {
    if (nivel != null)
      this.nivel = nivel;
  }

  public error(msg: string): void {
    if (this.nivel > 0)
      console.error(msg);
  }

  public warn(msg: string): void {
    if (this.nivel > 1)
      console.warn(msg);
  }

  public info(msg: string): void {
    if (this.nivel > 2)
      if (console.info)
        console.info(msg);
      else
        console.log(msg);
  }

  public log(msg: string): void {
    if (this.nivel > 3)
      console.log(msg);
  }
}

@Injectable()
export class LoggerHTTPService {
  private nivel = 99;

  constructor(@Optional() @Inject(ERROR_LEVEL) nivel?: number) {
    if (nivel != null)
      this.nivel = nivel;
  }

  public error(msg: string): void {
    if (this.nivel > 0)
      console.error('HTTP: ' + msg);
  }

  public warn(msg: string): void {
    if (this.nivel > 1)
      console.warn('HTTP: ' + msg);
  }

  public info(msg: string): void {
    if (this.nivel > 2)
      if (console.info)
        console.info('HTTP: ' + msg);
      else
        console.log(msg);
  }

  public log(msg: string): void {
    if (this.nivel > 3)
      console.log('HTTP: ' + msg);
  }
}
