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

function constructMaximumBinaryTree(nums: number[]): TreeNode | null {
    const n = nums.length;
    let root: TreeNode | null = null;
    const stk: [number, number, TreeNode | null, number][] = [[0, n, null, 0]];
    while (stk.length) {
        const [l, r, parent, side] = stk.pop()!;
        if (l >= r) {
            continue;
        }
        let i = l;
        for (let j = l + 1; j < r; ++j) {
            if (nums[j] > nums[i]) {
                i = j;
            }
        }
        const node = new TreeNode(nums[i]);
        if (parent === null) {
            root = node;
        } else if (side === 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        stk.push([i + 1, r, node, 1]);
        stk.push([l, i, node, 0]);
    }
    return root;
}
