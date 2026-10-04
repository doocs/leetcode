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
function levelMedian(root: TreeNode | null, level: number): number {
    const nums: number[] = [];
    const stk: [TreeNode | null, number, number][] = [[root, 0, 0]];
    while (stk.length) {
        const [node, i, state] = stk.pop()!;
        if (node === null) {
            continue;
        }
        if (state === 0) {
            stk.push([node, i, 1]);
            stk.push([node.left, i + 1, 0]);
            continue;
        }
        if (i === level) {
            nums.push(node.val);
        }
        stk.push([node.right, i + 1, 0]);
    }
    if (nums.length === 0) {
        return -1;
    }
    return nums[nums.length >> 1];
}
