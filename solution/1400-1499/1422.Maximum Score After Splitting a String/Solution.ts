function maxScore(s: string): number {
    const n = s.length;
    let res = 0;
    let score = 0;
    if (s[0] === '0') {
        score++;
    }
    for (let i = 1; i < n; i++) {
        if (s[i] === '1') {
            score++;
        }
    }
    res = Math.max(res, score);
    for (let i = 1; i < n - 1; i++) {
        if (s[i] === '0') {
            score++;
        } else if (s[i] === '1') {
            score--;
        }
        res = Math.max(res, score);
    }
    return res;
}
