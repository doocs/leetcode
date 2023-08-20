function minimumSum(n: number, k: number): number {
    let s = 0;
    let i = 1;
    const vis: boolean[] = Array(n * n + k + 1);
    while (n--) {
        while (vis[i]) {
            ++i;
        }
        vis[i] = true;
        if (k >= i) {
            vis[k - i] = true;
        }
        s += i;
    }
    return s;
}
