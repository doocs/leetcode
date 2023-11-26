function uniqueLetterString(s: string): number {
    const d: number[][] = Array.from({ length: 26 }, () => [-1]);
    for (let i = 0; i < s.length; ++i) {
        d[s.charCodeAt(i) - 'A'.charCodeAt(0)].push(i);
    }
    let ans = 0;
    for (const v of d) {
        v.push(s.length);

        for (let i = 1; i < v.length - 1; ++i) {
            ans += (v[i] - v[i - 1]) * (v[i + 1] - v[i]);
        }
    }
    return ans;
}
