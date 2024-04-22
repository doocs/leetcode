var leafSimilar = function (root1, root2) {
    const dfs = root => {
        if (!root) {
            return [];
        }
        let ans = [...dfs(root.left), ...dfs(root.right)];
        if (!ans.length) {
            ans = [root.val];
        }
        return ans;
    };
    const l1 = dfs(root1);
    const l2 = dfs(root2);
    return l1.toString() === l2.toString();
};
