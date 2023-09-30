function minimumPossibleSum(n: number, target: number): number {
    const vis: boolean[] = Array(n + target).fill(false);
    let ans = 0;
    for (let i = 1; n; ++i, --n) {
        while (vis[i]) {
            ++i;
        }
        ans += i;
        if (target >= i) {
            vis[target - i] = true;
        }
    }
    return ans;
}
