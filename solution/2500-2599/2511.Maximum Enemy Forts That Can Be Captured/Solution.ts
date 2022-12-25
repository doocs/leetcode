function captureForts(forts: number[]): number {
    const n = forts.length;
    let ans = 0;
    let i = 0;
    while (i < n) {
        let j = i + 1;
        if (forts[i] !== 0) {
            while (j < n && forts[j] === 0) {
                j++;
            }
            if (j < n && forts[i] + forts[j] === 0) {
                ans = Math.max(ans, j - i - 1);
            }
        }
        i = j;
    }
    return ans;
}
