function shortestBeautifulSubstring(s: string, k: number): string {
    const n = s.length;
    let ans: string = '';
    for (let i = 0; i < n; ++i) {
        for (let j = i + k; j <= n; ++j) {
            const t = s.slice(i, j);
            const cnt = t.split('').filter(c => c === '1').length;
            if (
                cnt === k &&
                (ans === '' || j - i < ans.length || (j - i === ans.length && t < ans))
            ) {
                ans = t;
            }
        }
    }
    return ans;
}
