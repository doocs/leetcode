function canMakeArithmeticProgression(arr: number[]): boolean {
    const n = arr.length;
    const a = Math.min(...arr);
    const b = Math.max(...arr);

    if ((b - a) % (n - 1) !== 0) {
        return false;
    }

    const d = (b - a) / (n - 1);
    const s = new Set(arr);

    for (let i = 0; i < n; ++i) {
        if (!s.has(a + d * i)) {
            return false;
        }
    }
    return true;
}
