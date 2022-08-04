function eliminateMaximum(dist: number[], speed: number[]): number {
    const n = dist.length;
    const times = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        times[i] = Math.floor((dist[i] - 1) / speed[i]);
    }
    times.sort((a, b) => a - b);
    for (let i = 0; i < n; ++i) {
        if (times[i] < i) {
            return i;
        }
    }
    return n;
}
