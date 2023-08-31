function* cycleGenerator(arr: number[], startIndex: number): Generator<number, void, number> {
    let i = startIndex;
    let x = yield arr[i];
    while (true) {
        i = (i + x + 10000 * arr.length) % arr.length;
        x = yield arr[i];
    }
}
/**
 *  const gen = cycleGenerator([1,2,3,4,5], 0);
 *  gen.next().value  // 1
 *  gen.next(1).value // 2
 *  gen.next(2).value // 4
 *  gen.next(6).value // 5
 */
