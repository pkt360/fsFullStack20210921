import { Directive, ElementRef, forwardRef, Input } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, ValidationErrors, Validator, ValidatorFn } from '@angular/forms';
import { convertValue } from './utils.class';

export function UppercaseValidation(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    if (!control.value) { return null; }
    return control.value === control.value.toUpperCase() ? null : { uppercase: 'Tiene que estar en mayusculas' }
  };
}

@Directive({
  selector: '[uppercase]',
  providers: [{ provide: NG_VALIDATORS, useExisting: UppercaseValidator, multi: true }]
})
export class UppercaseValidator implements Validator {
  validate(control: AbstractControl): ValidationErrors | null {
    return UppercaseValidation()(control);
  }
}

export function NIFValidation(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    if (!control.value) { return null; }
    const err = { nif: { invalidFormat: true, invalidChar: true, message: 'No es un NIF valido' } };
    if (/^\d{1,8}\w$/.test(control.value)) {
      const letterValue = control.value.substr(control.value.length - 1);
      const numberValue = control.value.substr(0, control.value.length - 1);
      err.nif.invalidFormat = false;
      return letterValue.toUpperCase() === 'TRWAGMYFPDXBNJZSQVHLCKE'.charAt(numberValue % 23) ? null : err;
    } else { return err; }
  };
}
@Directive({
  selector: '[nif][formControlName],[nif][formControl],[nif][ngModel]',
  providers: [{ provide: NG_VALIDATORS, useExisting: NIFValidator, multi: true }]
})
export class NIFValidator implements Validator {
  validate(control: AbstractControl): ValidationErrors | null {
    return NIFValidation()(control);
  }
}

@Directive({
  selector: '[type][formControlName],[type][formControl],[type][ngModel]',
  providers: [
    { provide: NG_VALIDATORS, useExisting: TypeValidator, multi: true }
  ]
})
export class TypeValidator implements Validator {
  constructor(private elem: ElementRef) { }
  validate(control: AbstractControl): ValidationErrors | null {
    const valor = control.value;
    if (valor) {
      const dom = this.elem.nativeElement;
      if (dom.validity) { // dom.checkValidity();
        return dom.validity.typeMismatch ? { 'type': dom.validationMessage } : null;
      }
    }
    return null;
  }
}

export function ExcludeValidation(start: any, end: any): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    if (!control.value) { return null; }
    if(!start) throw new Error("Falta el valor de inicio del rango")
    if(!end) throw new Error("Falta el valor de finalización del rango")
    return control.value < (start) || (control.value) > (end) ? null : { exclude: `Tiene que ser menor que ${start} o mayor que ${end}` }
  };
}

@Directive({
  selector: '[exclude-start][formControlName],[exclude-start][formControl],[exclude-start][ngModel],[formControlName],[exclude-end][formControl],[exclude-end][ngModel]',
  providers: [{ provide: NG_VALIDATORS, useExisting: ExcludeValidator, multi: true }]
})
export class ExcludeValidator implements Validator {
  @Input('exclude-start') start: any;
  @Input('exclude-end') end: any;
  validate(control: AbstractControl): ValidationErrors | null {
    return ExcludeValidation(this.start, this.end)(control);
  }
}

export function NotBlankValidation(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
     return control.value?.trim() ? null : { notblank: 'No puede estar en blanco' }
  };
}

@Directive({
  selector: '[notblank]',
  providers: [{ provide: NG_VALIDATORS, useExisting: NotBlankValidator, multi: true }]
})
export class NotBlankValidator implements Validator {
  validate(control: AbstractControl): ValidationErrors | null {
    return NotBlankValidation()(control);
  }
}


export const MIS_VALIDADORES = [UppercaseValidator, NIFValidator, TypeValidator, ExcludeValidator, NotBlankValidator ]
