function findAnagrams(s: string, p: string): number[] {
    const m = s.length;
    const n = p.length;
    const ans: number[] = [];
    if (m < n) {
        return ans;
    }
    const cnt1: number[] = new Array(26).fill(0);
    const cnt2: number[] = new Array(26).fill(0);
    for (let i = 0; i < n; ++i) {
        ++cnt1[s[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        ++cnt2[p[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    if (cnt1.toString() === cnt2.toString()) {
        ans.push(0);
    }
    for (let i = n; i < m; ++i) {
        ++cnt1[s[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        --cnt1[s[i - n].charCodeAt(0) - 'a'.charCodeAt(0)];
        if (cnt1.toString() === cnt2.toString()) {
            ans.push(i - n + 1);
        }
    }
    return ans;
}
