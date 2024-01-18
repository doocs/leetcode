function digArtifacts(n: number, artifacts: number[][], dig: number[][]): number {
    const s: Set<number> = new Set();
    for (const [x, y] of dig) {
        s.add(x * n + y);
    }
    let ans = 0;
    const check = (a: number[]): number => {
        const [x1, y1, x2, y2] = a;
        for (let x = x1; x <= x2; ++x) {
            for (let y = y1; y <= y2; ++y) {
                if (!s.has(x * n + y)) {
                    return 0;
                }
            }
        }
        return 1;
    };
    for (const a of artifacts) {
        ans += check(a);
    }
    return ans;
}
