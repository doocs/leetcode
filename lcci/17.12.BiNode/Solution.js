const convertBiNode = root => {
    const dfs = root => {
        if (!root) {
            return;
        }
        dfs(root.left);
        prev.right = root;
        root.left = null;
        prev = root;
        dfs(root.right);
    };
    const dummy = new TreeNode(0);
    let prev = dummy;
    dfs(root);
    return dummy.right;
};
