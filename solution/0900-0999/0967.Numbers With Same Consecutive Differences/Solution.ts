function numsSameConsecDiff(n: number, k: number): number[] {
    const ans: number[] = [];
    const boundary = 10 ** (n - 1);
    const dfs = (x: number) => {
        if (x >= boundary) {
            ans.push(x);
            return;
        }
        const last = x % 10;
        if (last + k < 10) {
            dfs(x * 10 + last + k);
        }
        if (k > 0 && last - k >= 0) {
            dfs(x * 10 + last - k);
        }
    };
    for (let i = 1; i < 10; i++) {
        dfs(i);
    }
    return ans;
}
