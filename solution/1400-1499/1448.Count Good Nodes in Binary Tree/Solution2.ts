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

function goodNodes(root: TreeNode | null): number {
    let ans = 0;
    const stk: [TreeNode | null, number][] = [[root, -1e6]];
    while (stk.length) {
        const [node, limit] = stk.pop()!;
        if (!node) {
            continue;
        }
        let mx = limit;
        if (mx <= node.val) {
            ++ans;
            mx = node.val;
        }
        stk.push([node.right, mx]);
        stk.push([node.left, mx]);
    }
    return ans;
}
