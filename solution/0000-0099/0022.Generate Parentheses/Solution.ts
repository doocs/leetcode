function generateParenthesis(n: number): string[] {
    const ans: string[] = [];
    const dfs = (left: number, right: number, t: string) => {
        if (left == n && right == n) {
            ans.push(t);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, t + '(');
        }
        if (right < left) {
            dfs(left, right + 1, t + ')');
        }
    };
    dfs(0, 0, '');
    return ans;
}
