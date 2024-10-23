function numberOfSubstrings(s: string, k: number): number {
    let [ans, l] = [0, 0];
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        const x = c.charCodeAt(0) - 97;
        ++cnt[x];
        while (cnt[x] >= k) {
            --cnt[s[l++].charCodeAt(0) - 97];
        }
        ans += l;
    }
    return ans;
}
