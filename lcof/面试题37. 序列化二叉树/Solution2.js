/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * Encodes a tree to a single string.
 *
 * @param {TreeNode} root
 * @return {string}
 */
var serialize = function (root) {
    if (root == null) {
        return '#';
    }
    const { val, left, right } = root;
    return `${val},${serialize(left)},${serialize(right)}`;
};

/**
 * Decodes your encoded data to tree.
 *
 * @param {string} data
 * @return {TreeNode}
 */
var deserialize = function (data) {
    const vals = data.split(',');
    let index = 0;
    const dfs = () => {
        if (vals[index] == '#') {
            index++;
            return null;
        }
        const res = new TreeNode(vals[index++]);
        res.left = dfs();
        res.right = dfs();
        return res;
    };
    return dfs();
};

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
 */
