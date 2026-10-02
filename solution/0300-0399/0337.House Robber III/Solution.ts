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

function rob(root: TreeNode | null): number {
    if (root === null) {
        return 0;
    }
    const order: TreeNode[] = [];
    const stack = [root];
    while (stack.length > 0) {
        const node = stack.pop()!;
        order.push(node);
        if (node.left) stack.push(node.left);
        if (node.right) stack.push(node.right);
    }
    const dp = new Map<TreeNode, [number, number]>();
    for (let i = order.length - 1; i >= 0; --i) {
        const node = order[i];
        const [la, lb] = node.left ? dp.get(node.left)! : [0, 0];
        const [ra, rb] = node.right ? dp.get(node.right)! : [0, 0];
        dp.set(node, [node.val + lb + rb, Math.max(la, lb) + Math.max(ra, rb)]);
    }
    return Math.max(...dp.get(root)!);
}
