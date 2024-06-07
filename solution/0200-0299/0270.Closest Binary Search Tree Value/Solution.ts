/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function closestValue(root: TreeNode | null, target: number): number {
    let ans = 0;
    let diff = Number.POSITIVE_INFINITY;

    const dfs = (node: TreeNode | null): void => {
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
}
