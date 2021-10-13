import { isIBAN, IBANValidator } from './IBANValidator.directive';

fdescribe('IBANValidator', () => {
  it('should create an instance', () => {
    const directive = new IBANValidator();
    expect(directive).toBeTruthy();
  });

  describe('Pruebas de la funcion isIBAN copiada de Internet ', () => {
    [ 'ES91 2100 0418 4502 0005 1332',
      'ES9121000418450200051332',
      'DE89370400440532013000',
      'PT50000201231234567890154',
      'FR1420041010050500013M02606',
      'GT82TRAJ01020000001210029690',
    ].forEach(caso => {
      it(`IBAN OK: ${caso}`, () => expect(isIBAN(caso)).toBeTrue());
    });
    ['ES91 2100 0418 4502 0005 1331', 'ES912100041845020005133', '9121000418450200051332', 'INGB0002445588', ''].forEach(caso => {
      it(`IBAN KO: ${caso}`, () => expect(isIBAN(caso)).toBeFalse());
    });
  });

});


// export default function isIBAN(str: string) {
//   return hasValidIbanFormat(str) && hasValidIbanChecksum(str);
// }
