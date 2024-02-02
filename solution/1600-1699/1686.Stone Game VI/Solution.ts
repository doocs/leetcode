function stoneGameVI(aliceValues: number[], bobValues: number[]): number {
    const n = aliceValues.length;
    const vals: number[][] = [];
    for (let i = 0; i < n; ++i) {
        vals.push([aliceValues[i] + bobValues[i], i]);
    }
    vals.sort((a, b) => b[0] - a[0]);
    let [a, b] = [0, 0];
    for (let k = 0; k < n; ++k) {
        const i = vals[k][1];
        if (k % 2 == 0) {
            a += aliceValues[i];
        } else {
            b += bobValues[i];
        }
    }
    if (a === b) {
        return 0;
    }
    return a > b ? 1 : -1;
}
