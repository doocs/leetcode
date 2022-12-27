function minimumMoves(s: string): number {
    const n = s.length;
    let ans = 0;
    let i = 0;
    while (i < n) {
        if (s[i] === 'X') {
            ans++;
            i += 3;
        } else {
            i++;
        }
    }
    return ans;
}
