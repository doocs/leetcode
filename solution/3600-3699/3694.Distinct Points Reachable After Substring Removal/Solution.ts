function distinctPoints(s: string, k: number): number {
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    const g = new Array(n + 1).fill(0);
    let x = 0,
        y = 0;
    for (let i = 1; i <= n; ++i) {
        const c = s[i - 1];
        if (c === 'U') ++y;
        else if (c === 'D') --y;
        else if (c === 'L') --x;
        else ++x;
        f[i] = x;
        g[i] = y;
    }
    const st = new Set<number>();
    for (let i = k; i <= n; ++i) {
        const a = f[n] - (f[i] - f[i - k]);
        const b = g[n] - (g[i] - g[i - k]);
        st.add(a * n + b);
    }
    return st.size;
}
