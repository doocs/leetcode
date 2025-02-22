/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */

const s = Symbol.for('s');

/**
 * @param {TreeNode} root
 */
var FindElements = function (root) {
    root.val = 0;
    this[s] = new Set();

    const dfs = (node, x = 0) => {
        if (!node) return;

        this[s].add(x);
        dfs(node.left, x * 2 + 1);
        dfs(node.right, x * 2 + 2);
    };

    dfs(root);
};

/**
 * @param {number} target
 * @return {boolean}
 */
FindElements.prototype.find = function (target) {
    return this[s].has(target);
};

/**
 * Your FindElements object will be instantiated and called as such:
 * var obj = new FindElements(root)
 * var param_1 = obj.find(target)
 */
