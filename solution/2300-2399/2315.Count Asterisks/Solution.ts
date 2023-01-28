function countAsterisks(s: string): number {
    let ans = 0;
    let ok = 1;
    for (const c of s) {
        if (c === '*') {
            ans += ok;
        } else if (c === '|') {
            ok ^= 1;
        }
    }
    return ans;
}
