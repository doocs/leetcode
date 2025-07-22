function maxSumDistinctTriplet(x: number[], y: number[]): number {
    const n = x.length;
    const arr: [number, number][] = [];
    for (let i = 0; i < n; i++) {
        arr.push([x[i], y[i]]);
    }
    arr.sort((a, b) => b[1] - a[1]);
    const vis = new Set<number>();
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const [a, b] = arr[i];
        if (!vis.has(a)) {
            vis.add(a);
            ans += b;
            if (vis.size === 3) {
                return ans;
            }
        }
    }
    return -1;
}
