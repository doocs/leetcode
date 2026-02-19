function countBinarySubstrings(s: string): number {
    const n = s.length;
    let ans = 0;
    let i = 0;
    let pre = 0;

    while (i < n) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) {
            j++;
        }
        const cur = j - i;
        ans += Math.min(pre, cur);
        pre = cur;
        i = j;
    }

    return ans;
};
