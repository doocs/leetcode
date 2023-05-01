function powerfulIntegers(x: number, y: number, bound: number): number[] {
    const ans = new Set<number>();
    for (let a = 1; a <= bound; a *= x) {
        for (let b = 1; a + b <= bound; b *= y) {
            ans.add(a + b);
            if (y === 1) {
                break;
            }
        }
        if (x === 1) {
            break;
        }
    }
    return Array.from(ans);
}
