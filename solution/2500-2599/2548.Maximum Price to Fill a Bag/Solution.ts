function maxPrice(items: number[][], capacity: number): number {
    items.sort((a, b) => a[1] * b[0] - a[0] * b[1]);
    let ans = 0;
    for (const [p, w] of items) {
        const v = Math.min(w, capacity);
        ans += (v / w) * p;
        capacity -= v;
    }
    return capacity ? -1 : ans;
}
