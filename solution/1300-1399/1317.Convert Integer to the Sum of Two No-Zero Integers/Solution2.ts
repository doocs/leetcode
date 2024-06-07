function getNoZeroIntegers(n: number): number[] {
    const f = (x: number): boolean => {
        for (; x; x = (x / 10) | 0) {
            if (x % 10 === 0) {
                return false;
            }
        }
        return true;
    };
    for (let a = 1; ; ++a) {
        const b = n - a;
        if (f(a) && f(b)) {
            return [a, b];
        }
    }
}
