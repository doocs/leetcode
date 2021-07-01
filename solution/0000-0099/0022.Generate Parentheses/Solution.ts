function generateParenthesis(n: number): string[] {
    let ans = [];
    dfs(n, 0, 0, '', ans);
    return ans;
};

function dfs(n: number, left: number, right: number, str: string, ans: string[]) {
    if (str.length == 2 * n) {
        ans.push(str);
        return;
    }
    if (left < n) {
        dfs(n, left + 1, right, str + '(', ans);
    }
    if (right < left) {
        dfs(n, left, right + 1, str + ')', ans);
    }
}