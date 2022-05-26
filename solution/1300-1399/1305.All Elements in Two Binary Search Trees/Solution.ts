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

function getAllElements(
    root1: TreeNode | null,
    root2: TreeNode | null,
): number[] {
    const res = [];
    const stacks = [[], []];
    while (
        root1 != null ||
        stacks[0].length !== 0 ||
        root2 != null ||
        stacks[1].length !== 0
    ) {
        if (root1 != null) {
            stacks[0].push(root1);
            root1 = root1.left;
        } else if (root2 != null) {
            stacks[1].push(root2);
            root2 = root2.left;
        } else {
            if (
                (stacks[0][stacks[0].length - 1] ?? { val: Infinity }).val <
                (stacks[1][stacks[1].length - 1] ?? { val: Infinity }).val
            ) {
                const { val, right } = stacks[0].pop();
                res.push(val);
                root1 = right;
            } else {
                const { val, right } = stacks[1].pop();
                res.push(val);
                root2 = right;
            }
        }
    }
    return res;
}
