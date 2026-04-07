const LIMIT = 1e9;

const GOOD: number[] = (() => {
    const cnt = new Map<number, number>();
    const cubes: number[] = Array.from({ length: 1001 }, (_, i) => i * i * i);

    for (let a = 1; a <= 1000; a++) {
        for (let b = a; b <= 1000; b++) {
            const x = cubes[a] + cubes[b];
            if (x > LIMIT) break;
            cnt.set(x, (cnt.get(x) ?? 0) + 1);
        }
    }

    const res: number[] = [];
    for (const [x, v] of cnt.entries()) {
        if (v > 1) res.push(x);
    }

    res.sort((a, b) => a - b);
    return res;
})();

function findGoodIntegers(n: number): number[] {
    const idx = _.sortedLastIndex(GOOD, n);
    return GOOD.slice(0, idx);
}
