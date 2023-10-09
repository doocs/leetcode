function maxAliveYear(birth: number[], death: number[]): number {
    const base = 1900;
    const d: number[] = Array(102).fill(0);
    for (let i = 0; i < birth.length; ++i) {
        const [a, b] = [birth[i] - base, death[i] - base];
        ++d[a];
        --d[b + 1];
    }
    let [s, mx] = [0, 0];
    let ans = 0;
    for (let i = 0; i < d.length; ++i) {
        s += d[i];
        if (mx < s) {
            mx = s;
            ans = base + i;
        }
    }
    return ans;
}
