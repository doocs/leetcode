function findAnagrams(s: string, p: string): number[] {
    let n = s.length,
        m = p.length;
    let cnt = new Array(26).fill(0);
    let ans = [];
    for (let i = 0; i < m; i++) {
        cnt[p.charCodeAt(i) - 97]--;
        cnt[s.charCodeAt(i) - 97]++;
    }
    if (cnt.every(v => v == 0)) {
        ans.push(0);
    }
    for (let i = m; i < n; i++) {
        cnt[s.charCodeAt(i) - 97]++;
        cnt[s.charCodeAt(i - m) - 97]--;
        if (cnt.every(v => v == 0)) {
            ans.push(i - m + 1);
        }
    }
    return ans;
}
