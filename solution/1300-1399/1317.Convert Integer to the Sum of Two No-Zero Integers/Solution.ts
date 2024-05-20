function getNoZeroIntegers(n: number): number[] {
    for (let a = 1; ; ++a) {
        const b = n - a;
        if (!`${a}${b}`.includes('0')) {
            return [a, b];
        }
    }
}
