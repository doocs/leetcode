function minimumRemoval(beans: number[]): number {
    beans.sort((a, b) => a - b);
    const s = beans.reduce((a, b) => a + b, 0);
    const n = beans.length;
    let ans = s;
    for (let i = 0; i < n; ++i) {
        ans = Math.min(ans, s - beans[i] * (n - i));
    }
    return ans;
}
