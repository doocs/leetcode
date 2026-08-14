function maximumLengthSubstring(s: string): number {
    let ans = 0;
    const cnt: number[] = Array(26).fill(0);
    for (let l = 0, r = 0; r < s.length; ++r) {
        const idx = s[r].charCodeAt(0) - 97;
        ++cnt[idx];
        while (cnt[idx] > 2) {
            --cnt[s[l++].charCodeAt(0) - 97];
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
