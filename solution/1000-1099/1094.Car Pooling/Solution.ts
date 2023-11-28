function carPooling(trips: number[][], capacity: number): boolean {
    const mx = Math.max(...trips.map(([, , t]) => t));
    const d = Array(mx + 1).fill(0);
    for (const [x, f, t] of trips) {
        d[f] += x;
        d[t] -= x;
    }
    let s = 0;
    for (const x of d) {
        s += x;
        if (s > capacity) {
            return false;
        }
    }
    return true;
}
