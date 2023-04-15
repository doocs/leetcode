function combine(n: number, k: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (t.length === k) {
            ans.push(t.slice());
            return;
        }
        if (i > n) {
            return;
        }
        t.push(i);
        dfs(i + 1);
        t.pop();
        dfs(i + 1);
    };
    dfs(1);
    return ans;
}
