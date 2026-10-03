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

function tree2str(root: TreeNode | null): string {
    const parts: string[] = [];
    const stk: [TreeNode | null, number][] = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop()!;
        if (state === 0) {
            if (!node) {
                continue;
            }
            parts.push(`${node.val}`);
            if (!node.left && !node.right) {
                continue;
            }
            parts.push('(');
            stk.push([node, 1]);
            stk.push([node.left, 0]);
            continue;
        }
        if (state === 1) {
            parts.push(')');
            if (node && node.right) {
                parts.push('(');
                stk.push([node, 2]);
                stk.push([node.right, 0]);
            }
            continue;
        }
        parts.push(')');
    }
    return parts.join('');
}
