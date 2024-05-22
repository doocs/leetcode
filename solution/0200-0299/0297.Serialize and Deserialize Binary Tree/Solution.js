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
    if (root === null) {
        return null;
    }
    const ans = [];
    const q = [root];
    let index = 0;
    while (index < q.length) {
        const node = q[index++];
        if (node !== null) {
            ans.push(node.val.toString());
            q.push(node.left);
            q.push(node.right);
        } else {
            ans.push('#');
        }
    }
    return ans.join(',');
};

/**
 * Decodes your encoded data to tree.
 *
 * @param {string} data
 * @return {TreeNode}
 */
var deserialize = function (data) {
    if (data === null) {
        return null;
    }
    const vals = data.split(',');
    let i = 0;
    const root = new TreeNode(parseInt(vals[i++]));
    const q = [root];
    let index = 0;
    while (index < q.length) {
        const node = q[index++];
        if (vals[i] !== '#') {
            node.left = new TreeNode(+vals[i]);
            q.push(node.left);
        }
        i++;
        if (vals[i] !== '#') {
            node.right = new TreeNode(+vals[i]);
            q.push(node.right);
        }
        i++;
    }
    return root;
};

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
 */
