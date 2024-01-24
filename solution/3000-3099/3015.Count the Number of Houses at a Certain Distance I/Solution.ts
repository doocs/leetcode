function countOfPairs(n: number, x: number, y: number): number[] {
    const ans: number[] = Array(n).fill(0);
    x--;
    y--;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            const a = j - i;
            const b = Math.abs(x - i) + Math.abs(y - j) + 1;
            const c = Math.abs(y - i) + Math.abs(x - j) + 1;
            ans[Math.min(a, b, c) - 1] += 2;
        }
    }
    return ans;
}
