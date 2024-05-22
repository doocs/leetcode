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
 * @param {number} target
 * @return {number}
 */
var closestValue = function (root, target) {
    let ans = 0;
    let diff = Infinity;

    const dfs = node => {
        if (!node) {
            return;
        }

        const nxt = Math.abs(target - node.val);
        if (nxt < diff || (nxt === diff && node.val < ans)) {
            diff = nxt;
            ans = node.val;
        }

        node = target < node.val ? node.left : node.right;
        dfs(node);
    };

    dfs(root);
    return ans;
};
