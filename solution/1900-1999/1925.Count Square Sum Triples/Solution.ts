function countTriples(n: number): number {
    let ans = 0;
    for (let a = 1; a < n; a++) {
        for (let b = 1; b < n; b++) {
            const x = a * a + b * b;
            const c = Math.floor(Math.sqrt(x));
            if (c <= n && c * c === x) {
                ans++;
            }
        }
    }
    return ans;
}
