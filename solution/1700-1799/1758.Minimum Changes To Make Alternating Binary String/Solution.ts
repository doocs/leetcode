function minOperations(s: string): number {
    const n = s.length;
    let count = 0;
    for (let i = 0; i < n; i++) {
        count += s[i] !== '01'[i & 1] ? 1 : 0;
    }
    return Math.min(count, n - count);
}
