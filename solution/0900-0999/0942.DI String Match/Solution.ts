function diStringMatch(s: string): number[] {
    const n = s.length;
    const res = new Array(n + 1);
    let low = 0;
    let high = n;
    for (let i = 0; i < n; i++) {
        if (s[i] === 'I') {
            res[i] = low++;
        } else {
            res[i] = high--;
        }
    }
    res[n] = low;
    return res;
}
