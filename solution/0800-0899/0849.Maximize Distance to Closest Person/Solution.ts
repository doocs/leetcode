function maxDistToClosest(seats: number[]): number {
    let first = -1,
        last = -1;
    let d = 0,
        n = seats.length;
    for (let i = 0; i < n; ++i) {
        if (seats[i] === 1) {
            if (last !== -1) {
                d = Math.max(d, i - last);
            }
            if (first === -1) {
                first = i;
            }
            last = i;
        }
    }
    return Math.max(first, n - last - 1, d >> 1);
}
