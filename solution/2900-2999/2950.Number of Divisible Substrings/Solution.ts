function countDivisibleSubstrings(word: string): number {
    const d = ['ab', 'cde', 'fgh', 'ijk', 'lmn', 'opq', 'rst', 'uvw', 'xyz'];
    const mp: number[] = Array(26).fill(0);

    d.forEach((s, i) => {
        for (const c of s) {
            mp[c.charCodeAt(0) - 'a'.charCodeAt(0)] = i + 1;
        }
    });

    let ans = 0;
    for (let i = 0; i < 10; i++) {
        const cnt: { [key: number]: number } = { 0: 1 };
        let s = 0;
        for (const c of word) {
            s += mp[c.charCodeAt(0) - 'a'.charCodeAt(0)] - i;
            ans += cnt[s] || 0;
            cnt[s] = (cnt[s] || 0) + 1;
        }
    }
    return ans;
}
