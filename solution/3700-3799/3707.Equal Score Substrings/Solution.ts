function scoreBalance(s: string): boolean {
    let [l, r] = [0, 0];
    for (const c of s) {
        const x = c.charCodeAt(0) - 96;
        r += x;
    }
    for (let i = 0; i < s.length - 1; ++i) {
        const x = s[i].charCodeAt(0) - 96;
        l += x;
        r -= x;
        if (l === r) {
            return true;
        }
    }
    return false;
}
