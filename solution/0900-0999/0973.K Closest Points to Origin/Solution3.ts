function kClosest(points: number[][], k: number): number[][] {
    if (k === points.length) return points;

    const distance = points.map(([x, y]) => x ** 2 + y ** 2);
    let indexes = points.map((_, i) => i);
    let [l, r] = [0, Math.max(...distance)];
    const res: number[] = [];

    while (k) {
        const mid = (l + r) >> 1;

        const closer: number[] = [];
        const farther: number[] = [];
        for (const i of indexes) {
            if (distance[i] <= mid) closer.push(i);
            else farther.push(i);
        }

        if (closer.length > k) {
            r = mid;
            indexes = closer;
        } else {
            l = mid;
            k -= closer.length;
            res.push(...closer);
            indexes = farther;
        }
    }

    return res.map(i => points[i]);
}
