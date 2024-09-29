function maximumTotalSum(maximumHeight: number[]): number {
    maximumHeight.sort((a, b) => a - b).reverse();
    let ans: number = 0;
    let mx: number = Infinity;
    for (let x of maximumHeight) {
        x = Math.min(x, mx - 1);
        if (x <= 0) {
            return -1;
        }
        ans += x;
        mx = x;
    }
    return ans;
}
