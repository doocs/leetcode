function maximizeWin(prizePositions: number[], k: number): number {
    const n = prizePositions.length;
    const f: number[] = Array(n + 1).fill(0);
    let ans = 0;
    const search = (x: number): number => {
        let left = 0;
        let right = n;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (prizePositions[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    for (let i = 1; i <= n; ++i) {
        const x = prizePositions[i - 1];
        const j = search(x - k);
        ans = Math.max(ans, f[j] + i - j);
        f[i] = Math.max(f[i - 1], i - j);
    }
    return ans;
}
