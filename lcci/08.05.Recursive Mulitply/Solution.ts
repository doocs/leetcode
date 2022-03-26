function multiply(A: number, B: number): number {
    if (A === 0 || B === 0) {
        return 0;
    }
    const [max, min] = [Math.max(A, B), Math.min(A, B)];
    return max + multiply(max, min - 1);
}
