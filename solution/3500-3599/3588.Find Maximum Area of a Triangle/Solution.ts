function maxArea(coords: number[][]): number {
    function calc(): number {
        let [mn, mx] = [Infinity, 0];
        const f = new Map<number, number>();
        const g = new Map<number, number>();

        for (const [x, y] of coords) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            if (f.has(x)) {
                f.set(x, Math.min(f.get(x)!, y));
                g.set(x, Math.max(g.get(x)!, y));
            } else {
                f.set(x, y);
                g.set(x, y);
            }
        }

        let ans = 0;
        for (const [x, y] of f) {
            const d = g.get(x)! - y;
            ans = Math.max(ans, d * Math.max(mx - x, x - mn));
        }
        return ans;
    }

    let ans = calc();
    for (const c of coords) {
        [c[0], c[1]] = [c[1], c[0]];
    }
    ans = Math.max(ans, calc());
    return ans > 0 ? ans : -1;
}
