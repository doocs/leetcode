function minSessions(tasks: number[], sessionTime: number): number {
    const n = tasks.length;
    const ok: boolean[] = new Array(1 << n).fill(false);
    for (let i = 1; i < 1 << n; ++i) {
        let t = 0;
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 1) {
                t += tasks[j];
            }
        }
        ok[i] = t <= sessionTime;
    }

    const f: number[] = new Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (let i = 1; i < 1 << n; ++i) {
        for (let j = i; j > 0; j = (j - 1) & i) {
            if (ok[j]) {
                f[i] = Math.min(f[i], f[i ^ j] + 1);
            }
        }
    }
    return f[(1 << n) - 1];
}
