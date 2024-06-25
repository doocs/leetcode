function judgeSquareSum(c: number): boolean {
    const n = Math.floor(Math.sqrt(c));
    for (let i = 2; i <= n; ++i) {
        if (c % i === 0) {
            let exp = 0;
            while (c % i === 0) {
                c /= i;
                ++exp;
            }
            if (i % 4 === 3 && exp % 2 !== 0) {
                return false;
            }
        }
    }
    return c % 4 !== 3;
}
