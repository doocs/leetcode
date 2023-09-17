function countPairs(coordinates: number[][], k: number): number {
    const cnt: Map<number, number> = new Map();
    const f = (x: number, y: number): number => x * 1000000 + y;
    let ans = 0;
    for (const [x2, y2] of coordinates) {
        for (let a = 0; a <= k; ++a) {
            const b = k - a;
            const [x1, y1] = [a ^ x2, b ^ y2];
            ans += cnt.get(f(x1, y1)) ?? 0;
        }
        cnt.set(f(x2, y2), (cnt.get(f(x2, y2)) ?? 0) + 1);
    }
    return ans;
}
