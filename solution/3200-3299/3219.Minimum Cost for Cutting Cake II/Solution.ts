function minimumCost(m: number, n: number, horizontalCut: number[], verticalCut: number[]): number {
    horizontalCut.sort((a, b) => b - a);
    verticalCut.sort((a, b) => b - a);
    let ans = 0;
    let [i, j] = [0, 0];
    let [h, v] = [1, 1];
    while (i < m - 1 || j < n - 1) {
        if (j === n - 1 || (i < m - 1 && horizontalCut[i] > verticalCut[j])) {
            ans += horizontalCut[i] * v;
            h++;
            i++;
        } else {
            ans += verticalCut[j] * h;
            v++;
            j++;
        }
    }
    return ans;
}
