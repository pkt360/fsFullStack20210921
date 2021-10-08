import { Directive, EventEmitter, HostBinding, HostListener, Input, Output } from '@angular/core';

@Directive({ selector: `[myWinConfirm]` })
export class WindowConfirmDirective {
  @Output('myWinConfirm') winConfirm: EventEmitter<any> = new EventEmitter();
  @Input('myWinConfirmMessage') winConfirmMessage = '¿Seguro?';
  @HostBinding('class.pressed') isPressed: boolean = false;

  @HostListener('click', ['$event'])
  confirmFirst() {
    if (window.confirm(this.winConfirmMessage)) {
      this.winConfirm.emit(null);
    }
  }
  @HostListener('mousedown') hasPressed() { this.isPressed = true; }
  @HostListener('mouseup') hasReleased() { this.isPressed = false; }
}

@Directive({ selector: '[show]' })
export class ShowDirective {
  @HostBinding('hidden') hidden: boolean = false;
  @Input('show') set show(value: boolean) { this.hidden = !value; }
}

export const DIRECTIVAS_ATRIBUTO = [ WindowConfirmDirective, ShowDirective, ]
