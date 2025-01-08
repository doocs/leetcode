function meetRequirement(n: number, lights: number[][], requirement: number[]): number {
    const d: number[] = Array(n + 1).fill(0);
    for (const [p, r] of lights) {
        const [i, j] = [Math.max(0, p - r), Math.min(n - 1, p + r)];
        ++d[i];
        --d[j + 1];
    }
    let [ans, s] = [0, 0];
    for (let i = 0; i < n; ++i) {
        s += d[i];
        if (s >= requirement[i]) {
            ++ans;
        }
    }
    return ans;
}
