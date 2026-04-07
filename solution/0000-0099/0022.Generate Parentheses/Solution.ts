function generateParenthesis(n: number): string[] {
    const dfs = (l: number, r: number, t: string) => {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.push(t);
            return;
        }
        dfs(l + 1, r, t + '(');
        dfs(l, r + 1, t + ')');
    };
    const ans: string[] = [];
    dfs(0, 0, '');
    return ans;
}
