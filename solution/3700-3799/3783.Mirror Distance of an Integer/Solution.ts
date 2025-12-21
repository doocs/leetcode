function mirrorDistance(n: number): number {
    const reverse = (x: number): number => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    return Math.abs(n - reverse(n));
}
