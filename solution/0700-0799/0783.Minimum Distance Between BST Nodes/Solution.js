var minDiffInBST = function (root) {
    let ans = Number.MAX_SAFE_INTEGER,
        prev = Number.MAX_SAFE_INTEGER;
    const dfs = root => {
        if (!root) {
            return;
        }
        dfs(root.left);
        ans = Math.min(ans, Math.abs(root.val - prev));
        prev = root.val;
        dfs(root.right);
    };
    dfs(root);
    return ans;
};
