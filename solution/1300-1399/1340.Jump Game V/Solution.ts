function maxJumps(arr: number[], d: number): number {
    const n = arr.length;
    const f: number[] = new Array(n).fill(0);
    const dfs = (i: number): number => {
        if (f[i] !== 0) {
            return f[i];
        }
        let ans = 1;
        for (let j = i - 1; j >= 0; j--) {
            if (i - j > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        for (let j = i + 1; j < n; j++) {
            if (j - i > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        f[i] = ans;
        return ans;
    };
    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans = Math.max(ans, dfs(i));
    }
    return ans;
}
