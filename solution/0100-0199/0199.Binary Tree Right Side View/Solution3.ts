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
export function rightSideView(root: TreeNode | null): number[] {
    if (!root) return [];

    let queue = [root];
    const res: number[] = [];

    while (queue.length) {
        const nextQueue: TreeNode[] = [];
        res.push(queue.at(-1)!.val);

        for (const node of queue) {
            if (node.left) nextQueue.push(node.left);
            if (node.right) nextQueue.push(node.right);
        }

        queue = nextQueue;
    }

    return res;
}
