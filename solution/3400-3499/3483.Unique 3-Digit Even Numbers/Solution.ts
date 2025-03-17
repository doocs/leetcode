function totalNumbers(digits: number[]): number {
    const s = new Set<number>();
    const n = digits.length;
    for (let i = 0; i < n; ++i) {
        if (digits[i] % 2 === 1) {
            continue;
        }
        for (let j = 0; j < n; ++j) {
            if (i === j) {
                continue;
            }
            for (let k = 0; k < n; ++k) {
                if (digits[k] === 0 || k === i || k === j) {
                    continue;
                }
                s.add(digits[k] * 100 + digits[j] * 10 + digits[i]);
            }
        }
    }
    return s.size;
}
