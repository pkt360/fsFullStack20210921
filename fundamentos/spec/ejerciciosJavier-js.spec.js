fdescribe('Verificacion de los ejercicios de JavaScript', () => {
    describe('Ejercicio 1: Crear una función que devuelva un numero aleatorio', () => {
        [[1, 100], [-10, -1] /*, [0, 10] */].forEach(caso => {
            it(`Valor entre ${caso[0]} mas ${caso[1]}`, () => {
                let rslt = aleatorio(caso[0], caso[1])
                expect(rslt).toBeGreaterThanOrEqual(caso[0])
                expect(rslt).toBeLessThanOrEqual(caso[1])
                expect(Number.isInteger(rslt)).toBeTrue()
            })
        });

        it('Excepcion por falta de argumentos', () => {
            expect(() => aleatorio()).toThrow()
        });

        [
            { inicio: 1, fin: 'a', msg: 'Falta el valor final' },
            { inicio: 'b', fin: 0, msg: 'Falta el valor inicial' },
            { inicio: true, fin: 'cadena', msg: 'Falta el valor inicial' },
            { inicio: 10, fin: 1, msg: 'El valor final debe ser mayor que el valor inicial' },
        ].forEach(caso => {
            it(`Excepcion por argumentos erroneos: ${caso.inicio} ${caso.fin} -> ${caso.msg}`, () => {
                expect(() => aleatorio(caso.inicio, caso.fin)).toThrowError(Error, caso.msg);
            })
        });

        describe('Ejemplos de espias', () => {
            beforeAll(() => {
                spyOn(Math, 'random').and.callThrough()
            })

            it('Espia', () => {
                let rslt = aleatorio(1, 100)
                expect(Math.random).toHaveBeenCalled()
                expect(rslt).toBeGreaterThanOrEqual(1)
                expect(rslt).toBeLessThanOrEqual(100)
                expect(Number.isInteger(rslt)).toBeTrue()
            })
        })

        describe('Ejemplos de dobles de pruebas', () => {
            it('Doble de pruebas', () => {
                spyOn(Math, 'random').and.returnValues(0.83435, 1, 0, 0.54673843)
                expect(aleatorio(1, 100)).toBe(83)
                expect(aleatorio(1, 100)).toBe(100)
                expect(aleatorio(1, 100)).toBe(1)
                expect(aleatorio(10, 20)).toBe(15)
            })
        })
    })

    describe('Ejercicio 2: Adivina el Número', () => {
        let juego = null;
        const NUM_INTENTOS = 10;

        beforeAll(() => {
            spyOn(Math, 'random').and.returnValue(0.83435)
        });

        beforeEach(() => {
            juego = new JuegoConClase(NUM_INTENTOS, 100);
        });

        it('Mi número es mayor.', () => {
            juego.PruebaCon(82)
            expect(juego.mensaje).toBe('Mi número es mayor.')
            expect(juego.intentos).toBe(1)
            expect(juego.encontrado).toBeFalse()
        });

        it('Mi número es menor.', () => {
            juego.PruebaCon(84)
            juego.PruebaCon(84)
            expect(juego.mensaje).toBe('Mi número es menor.')
            expect(juego.intentos).toBe(2)
            expect(juego.encontrado).toBeFalse()
        });

        it('Intentos', () => {
            for(let i = 1; i < NUM_INTENTOS; i++) {
                juego.PruebaCon(1)
                expect(juego.intentos).toBe(i)
            }
            juego.PruebaCon(1)
            expect(juego.mensaje).toBe('Upsss! Se acabaron los intentos, el número era el 83')
            expect(juego.intentos).toBe(NUM_INTENTOS)
            expect(juego.encontrado).toBeFalse()
        });

        it('Bieeen!!! Acertaste.', () => {
            juego.PruebaCon(83)
            expect(juego.mensaje).toBe('Bieeen!!! Acertaste.')
            expect(juego.encontrado).toBeTrue()
        });

        it('Excedido el numero de intentos', () => {
            for(let i = 0; i < NUM_INTENTOS; i++) {
                juego.PruebaCon(1)
            }
            expect(() => juego.PruebaCon(1)).toThrow()
            expect(juego.intentos).toBe(NUM_INTENTOS)
            expect(juego.encontrado).toBeFalse()
        });

        xit('No es un número', () => {
            juego.PruebaCon('otra cosa')
            expect(juego.intentos).toBe(0)
            expect(juego.encontrado).toBeFalse()
        });

    })

    describe('Ejercicio 3: Crear una función que devuelva un array con el numero de elementos indicado, inicializados al valor suministrado.', () => {
        [
            { elemetos: 10, valor: '' },
            { elemetos: 5, valor: 0 },
            { elemetos: 0, valor: true },
        ].forEach(caso => {
            it(`${caso.elemetos} elementos con valor ${caso.valor}`, () => {
                let rslt = dameArray(caso.elemetos, caso.valor)
                expect(rslt.length).toBe(caso.elemetos)
                for (let i = 0; i < caso.elemetos; i++)
                    expect(rslt[i]).toBe(caso.valor)
            })
        });

        it('Solo número de elementos', () => {
            const numElementos = 7;
            let rslt = dameArray(numElementos)
            expect(rslt.length).toBe(numElementos)
            for (let i = 0; i < numElementos; i++)
                expect(rslt[i]).toBe("")

        });

        it('Con argumentos variables', () => {
            const numElementos = 7;
            let rslt = dameArray(numElementos, true, 1, 2)
            expect(rslt.length).toBe(numElementos)
            expect(rslt[0]).toBe(1)
            expect(rslt[1]).toBe(2)
            for (let i = 2; i < numElementos; i++)
                expect(rslt[i]).toBe(true)
        });
    })

    describe('Ejercicio 4: Crear una función que devuelva un determinado número de números primos.', () => {
        it('Números primos del 1 al 100', () => {
            const primos = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97];
            const numElementos = primos.length;
            let rslt = damePrimos(numElementos)
            expect(rslt.length).toBe(numElementos)
            for (let i = 0; i < numElementos; i++)
                expect(rslt[i]).toBe(primos[i])
        });

        xit('Ver en consola', () => {
            let primos = getPrimos(100)
            for (let p of primos) {
                console.log(p)
                if (p > 100) break;
            }
            console.log('------------')
            for (let p of primos) {
                console.log(p)
            }
        })
    })

    describe('Ejercicio 6: Crear una función que valide un NIF.', () => {
        ['12345678z', '12345678Z', '1234S'].forEach(caso => {
            it(`NIF valido: ${caso}`, () => {
                expect(esNIF(caso)).toBeTrue()
            })
        });

        ['12345678', 'Z', '1234J', null, undefined, ''].forEach(caso => {
            it(`NIF invalido: ${caso}`, () => {
                expect(esNIF(caso)).toBeFalse()
            })
        });
    })

    describe('Ejercicio 7: Palíndromos.', () => {
        ['ana', 'reconocer', 'La ruta nos aporto otro paso natural', 'SOMOS O NO SOMOS',
            'Dábale arroz a la zorra el abad', 'áéíóúüuuoiea', 'a¿¡+-*!?\'"a',
            'No subas, abusón', '¿Será lodo o dólares?'].forEach(caso => {
                it(`Palíndromo valido: ${caso}`, () => {
                    expect(esPalindromo(caso)).toBeTrue()
                })
            });

        [1, 'no lo es', '', null, undefined, '    '].forEach(caso => {
            it(`Palíndromo invalido: ${caso}`, () => {
                expect(esPalindromo(caso)).toBeFalse()
            })
        });
    })
})