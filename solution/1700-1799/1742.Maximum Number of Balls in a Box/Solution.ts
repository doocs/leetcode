function countBalls(lowLimit: number, highLimit: number): number {
    const cnt: number[] = Array(50).fill(0);
    for (let i = lowLimit; i <= highLimit; ++i) {
        let y = 0;
        for (let x = i; x; x = Math.floor(x / 10)) {
            y += x % 10;
        }
        ++cnt[y];
    }
    return Math.max(...cnt);
}
