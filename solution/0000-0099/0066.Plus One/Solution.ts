function plusOne(digits: number[]): number[] {
    const n = digits.length;
    for (let i = n - 1; i >= 0; i--) {
        if (10 > ++digits[i]) {
            return digits;
        }
        digits[i] %= 10;
    }
    return [1, ...digits];
}
