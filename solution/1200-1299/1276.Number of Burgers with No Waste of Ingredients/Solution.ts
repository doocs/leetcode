function numOfBurgers(tomatoSlices: number, cheeseSlices: number): number[] {
    const k = 4 * cheeseSlices - tomatoSlices;
    const y = k >> 1;
    const x = cheeseSlices - y;
    return k % 2 || y < 0 || x < 0 ? [] : [x, y];
}
