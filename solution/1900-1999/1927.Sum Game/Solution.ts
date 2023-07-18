function sumGame(num: string): boolean {
    const n = num.length;
    let [cnt1, cnt2, s1, s2] = [0, 0, 0, 0];
    for (let i = 0; i < n >> 1; ++i) {
        if (num[i] === '?') {
            ++cnt1;
        } else {
            s1 += num[i].charCodeAt(0) - '0'.charCodeAt(0);
        }
    }
    for (let i = n >> 1; i < n; ++i) {
        if (num[i] === '?') {
            ++cnt2;
        } else {
            s2 += num[i].charCodeAt(0) - '0'.charCodeAt(0);
        }
    }
    return (cnt1 + cnt2) % 2 === 1 || 2 * (s1 - s2) !== 9 * (cnt2 - cnt1);
}
