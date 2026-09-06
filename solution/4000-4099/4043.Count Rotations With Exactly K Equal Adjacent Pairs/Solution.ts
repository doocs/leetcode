function countRotations(s: string, k: number): number {
    const n = s.length;
    let score = 0;

    for (let i = 0; i < n - 1; i++) {
        score += s[i] === s[i + 1] ? 1 : 0;
    }

    let ans = score === k ? 1 : 0;

    for (let i = n; i < n * 2 - 1; i++) {
        score += s[i % n] === s[(i - 1) % n] ? 1 : 0;
        score -= s[i % n] === s[(i + 1) % n] ? 1 : 0;
        ans += score === k ? 1 : 0;
    }

    return ans;
}
