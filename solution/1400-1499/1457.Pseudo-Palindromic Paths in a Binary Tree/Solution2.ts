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

function pseudoPalindromicPaths(root: TreeNode | null): number {
    let ans = 0;
    const stk: [TreeNode | null, number][] = [[root, 0]];
    while (stk.length) {
        const cur = stk.pop()!;
        const node = cur[0];
        if (!node) {
            continue;
        }
        const mask = cur[1] ^ (1 << node.val);
        if (!node.left && !node.right) {
            if ((mask & (mask - 1)) === 0) {
                ++ans;
            }
        } else {
            stk.push([node.right, mask]);
            stk.push([node.left, mask]);
        }
    }
    return ans;
}
