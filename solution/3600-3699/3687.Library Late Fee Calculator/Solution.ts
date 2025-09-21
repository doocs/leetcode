function lateFee(daysLate: number[]): number {
    const f = (x: number): number => {
        if (x === 1) {
            return 1;
        } else if (x > 5) {
            return 3 * x;
        }
        return 2 * x;
    };
    return daysLate.reduce((acc, days) => acc + f(days), 0);
}
