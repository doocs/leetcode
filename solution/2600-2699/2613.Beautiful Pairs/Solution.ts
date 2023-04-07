function beautifulPair(nums1: number[], nums2: number[]): number[] {
    const pl: Map<number, number[]> = new Map();
    const n = nums1.length;
    for (let i = 0; i < n; ++i) {
        const z = f(nums1[i], nums2[i]);
        if (!pl.has(z)) {
            pl.set(z, []);
        }
        pl.get(z)!.push(i);
    }
    const points: number[][] = [];
    for (let i = 0; i < n; ++i) {
        const z = f(nums1[i], nums2[i]);
        if (pl.get(z)!.length > 1) {
            return [i, pl.get(z)![1]];
        }
        points.push([nums1[i], nums2[i], i]);
    }
    points.sort((a, b) => a[0] - b[0]);

    const dfs = (l: number, r: number): number[] => {
        if (l >= r) {
            return [1 << 30, -1, -1];
        }
        const m = (l + r) >> 1;
        const x = points[m][0];
        let t1 = dfs(l, m);
        let t2 = dfs(m + 1, r);
        if (
            t1[0] > t2[0] ||
            (t1[0] == t2[0] &&
                (t1[1] > t2[1] || (t1[1] == t2[1] && t1[2] > t2[2])))
        ) {
            t1 = t2;
        }
        const t: number[][] = [];
        for (let i = l; i <= r; ++i) {
            if (Math.abs(points[i][0] - x) <= t1[0]) {
                t.push(points[i]);
            }
        }
        t.sort((a, b) => a[1] - b[1]);
        for (let i = 0; i < t.length; ++i) {
            for (let j = i + 1; j < t.length; ++j) {
                if (t[j][1] - t[i][1] > t1[0]) {
                    break;
                }
                const pi = Math.min(t[i][2], t[j][2]);
                const pj = Math.max(t[i][2], t[j][2]);
                const d = dist(t[i][0], t[i][1], t[j][0], t[j][1]);
                if (
                    d < t1[0] ||
                    (d == t1[0] && (pi < t1[1] || (pi == t1[1] && pj < t1[2])))
                ) {
                    t1 = [d, pi, pj];
                }
            }
        }
        return t1;
    };
    return dfs(0, n - 1).slice(1);
}

function dist(x1: number, y1: number, x2: number, y2: number): number {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
}

function f(x: number, y: number): number {
    return x * 100000 + y;
}
