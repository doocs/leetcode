function kClosest(points: number[][], k: number): number[][] {
    const dist = points.map(([x, y]) => x * x + y * y);
    let [l, r] = [0, Math.max(...dist)];
    while (l < r) {
        const mid = (l + r) >> 1;
        let cnt = 0;
        for (const d of dist) {
            if (d <= mid) {
                ++cnt;
            }
        }
        if (cnt >= k) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return points.filter((_, i) => dist[i] <= l);
}
