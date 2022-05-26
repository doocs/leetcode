function binaryGap(n: number): number {
    let res = 0;
    let j = -1;
    for (let i = 0; n !== 0; i++) {
        if (n & 1) {
            if (j !== -1) {
                res = Math.max(res, i - j);
            }
            j = i;
        }
        n >>= 1;
    }
    return res;
}
