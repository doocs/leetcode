function firstDayBeenInAllRooms(nextVisit: number[]): number {
    const n = nextVisit.length;
    const mod = 1e9 + 7;
    const f: number[] = new Array<number>(n).fill(0);
    for (let i = 1; i < n; ++i) {
        f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1 + mod) % mod;
    }
    return f[n - 1];
}
