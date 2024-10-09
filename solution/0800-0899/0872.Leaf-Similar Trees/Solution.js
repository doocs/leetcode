var leafSimilar = function (root1, root2) {
    const l1 = [];
    const l2 = [];
    const dfs = (root, nums) => {
        if (root.left === root.right) {
            nums.push(root.val);
            return;
        }
        root.left && dfs(root.left, nums);
        root.right && dfs(root.right, nums);
    };
    dfs(root1, l1);
    dfs(root2, l2);
    return l1.join(',') === l2.join(',');
};
