function lengthOfLongestSubstring(s: string): number {
    let ans = 0;
    const n = s.length;
    const ss: boolean[] = new Array(128).fill(false);
    for (let i = 0, j = 0; i < n; ++i) {
        while (ss[s[i]]) {
            ss[s[j++]] = false;
        }
        ss[s[i]] = true;
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
