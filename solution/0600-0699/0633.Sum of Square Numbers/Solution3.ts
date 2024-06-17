function judgeSquareSum(c: number): boolean {
    const n = Math.sqrt(c);

    for (let i = 2; i <= n; i++) {
        let count = 0;

        if (c % i === 0) {
            while (c % i === 0) {
                count++;
                c /= i;
            }
            if (i % 4 === 3 && count % 2 !== 0) return false;
        }
    }
    return c % 4 !== 3;
}
