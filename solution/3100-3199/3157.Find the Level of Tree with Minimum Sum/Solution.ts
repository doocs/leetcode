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

function minimumLevel(root: TreeNode | null): number {
    const q: TreeNode[] = [root];
    let s = Infinity;
    let ans = 0;
    for (let level = 1; q.length; ++level) {
        const qq: TreeNode[] = [];
        let t = 0;
        for (const { val, left, right } of q) {
            t += val;
            left && qq.push(left);
            right && qq.push(right);
        }
        if (s > t) {
            s = t;
            ans = level;
        }
        q.splice(0, q.length, ...qq);
    }
    return ans;
}
