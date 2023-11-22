function isDecomposable(s: string): boolean {
    const n = s.length;
    let cnt2 = 0;
    for (let i = 0; i < n; ) {
        let j = i;
        while (j < n && s[j] === s[i]) {
            ++j;
        }
        if ((j - i) % 3 === 1) {
            return false;
        }
        if ((j - i) % 3 === 2 && ++cnt2 > 1) {
            return false;
        }
        i = j;
    }
    return cnt2 === 1;
}
