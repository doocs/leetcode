function checkInclusion(s1: string, s2: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m > n) {
        return false;
    }
    const cnt1 = new Array(26).fill(0);
    const cnt2 = new Array(26).fill(0);
    for (let i = 0; i < m; ++i) {
        ++cnt1[s1[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        ++cnt2[s2[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    if (cnt1.toString() === cnt2.toString()) {
        return true;
    }
    for (let i = m; i < n; ++i) {
        ++cnt2[s2[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        --cnt2[s2[i - m].charCodeAt(0) - 'a'.charCodeAt(0)];
        if (cnt1.toString() === cnt2.toString()) {
            return true;
        }
    }
    return false;
}
