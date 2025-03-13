function minimumSum(n: number, k: number): number {
    let s = 0;
    let i = 1;
    const vis: boolean[] = Array(n + k + 1).fill(false);
    while (n--) {
        while (vis[i]) {
            ++i;
        }
        if (k >= i) {
            vis[k - i] = true;
        }
        s += i++;
    }
    return s;
}
