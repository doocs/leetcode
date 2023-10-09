function smallestDifference(a: number[], b: number[]): number {
    a.sort((a, b) => a - b);
    b.sort((a, b) => a - b);
    let [i, j] = [0, 0];
    let ans = Infinity;
    while (i < a.length && j < b.length) {
        ans = Math.min(ans, Math.abs(a[i] - b[j]));
        if (a[i] < b[j]) {
            ++i;
        } else {
            ++j;
        }
    }
    return ans;
}
