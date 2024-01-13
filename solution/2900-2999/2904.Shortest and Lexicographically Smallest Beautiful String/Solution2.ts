function shortestBeautifulSubstring(s: string, k: number): string {
    let [i, j, cnt] = [0, 0, 0];
    const n = s.length;
    let ans: string = '';
    while (j < n) {
        cnt += s[j] === '1' ? 1 : 0;
        while (cnt > k || (i < j && s[i] === '0')) {
            cnt -= s[i++] === '1' ? 1 : 0;
        }
        ++j;
        const t = s.slice(i, j);
        if (cnt === k && (ans === '' || j - i < ans.length || (j - i === ans.length && t < ans))) {
            ans = t;
        }
    }
    return ans;
}
