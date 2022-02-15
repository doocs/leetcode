/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {void} Do not return anything, modify root in-place instead.
 */
const recoverTree = root => {
    const data = {
        prev: null,
        first: null,
        second: null,
    };
    let tmp = 0;

    helper(root, data);

    tmp = data.first.val;
    data.first.val = data.second.val;
    data.second.val = tmp;
};

const helper = (root, data) => {
    if (!root) return;

    helper(root.left, data);

    if (data.prev && data.prev.val >= root.val) {
        if (!data.first) data.first = data.prev;
        data.second = root;
    }

    data.prev = root;

    helper(root.right, data);
};
