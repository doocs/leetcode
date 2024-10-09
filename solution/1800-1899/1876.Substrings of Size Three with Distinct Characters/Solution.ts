function countGoodSubstrings(s: string): number {
    let ans = 0;
    const n = s.length;
    for (let l = 0, r = 0, mask = 0; r < n; ++r) {
        const x = s.charCodeAt(r) - 'a'.charCodeAt(0);
        while ((mask >> x) & 1) {
            const y = s.charCodeAt(l++) - 'a'.charCodeAt(0);
            mask ^= 1 << y;
        }
        mask |= 1 << x;
        ans += r - l + 1 >= 3 ? 1 : 0;
    }
    return ans;
}
