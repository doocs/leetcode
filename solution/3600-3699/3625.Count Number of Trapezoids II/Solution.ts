function countTrapezoids(points: number[][]): number {
    const n = points.length;

    const cnt1: Map<number, Map<number, number>> = new Map();
    const cnt2: Map<number, Map<number, number>> = new Map();

    for (let i = 0; i < n; i++) {
        const [x1, y1] = points[i];
        for (let j = 0; j < i; j++) {
            const [x2, y2] = points[j];
            const [dx, dy] = [x2 - x1, y2 - y1];

            const k = dx === 0 ? 1e9 : dy / dx;
            const b = dx === 0 ? x1 : (y1 * dx - x1 * dy) / dx;

            if (!cnt1.has(k)) {
                cnt1.set(k, new Map());
            }
            const mapB = cnt1.get(k)!;
            mapB.set(b, (mapB.get(b) || 0) + 1);

            const p = (x1 + x2 + 2000) * 4000 + (y1 + y2 + 2000);

            if (!cnt2.has(p)) {
                cnt2.set(p, new Map());
            }
            const mapK = cnt2.get(p)!;
            mapK.set(k, (mapK.get(k) || 0) + 1);
        }
    }

    let ans = 0;
    for (const e of cnt1.values()) {
        let s = 0;
        for (const t of e.values()) {
            ans += s * t;
            s += t;
        }
    }
    for (const e of cnt2.values()) {
        let s = 0;
        for (const t of e.values()) {
            ans -= s * t;
            s += t;
        }
    }

    return ans;
}
