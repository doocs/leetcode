function maximumLengthSubstring(s: string): number {
    let ans = 0;
    const cnt: number[] = Array(26).fill(0);
    for (let i = 0, j = 0; j < s.length; ++j) {
        const idx = s[j].charCodeAt(0) - 'a'.charCodeAt(0);
        ++cnt[idx];
        while (cnt[idx] > 2) {
            --cnt[s[i++].charCodeAt(0) - 'a'.charCodeAt(0)];
        }
        ans = Math.max(ans, j - i + 1);
    }
    return ans;
}
