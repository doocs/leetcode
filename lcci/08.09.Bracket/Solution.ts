function generateParenthesis(n: number): string[] {
    let ans = [];
    dfs(0, 0, n, '', ans);
    return ans;
}

function dfs(left: number, right: number, n: number, t: string, ans: string[]) {
    if (left == n && right == n) {
        ans.push(t);
        return;
    }
    if (left < n) {
        dfs(left + 1, right, n, t + '(', ans);
    }
    if (right < left) {
        dfs(left, right + 1, n, t + ')', ans);
    }
}
