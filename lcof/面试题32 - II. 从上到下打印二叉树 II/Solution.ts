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

function levelOrder(root: TreeNode | null): number[][] {
    const res = [];
    if (root == null) {
        return res;
    }
    const levelFn = (nodes: TreeNode[]) => {
        if (nodes.length === 0) {
            return res;
        }
        const nextNodes = [];
        res.push(
            nodes.map(({ val, left, right }) => {
                left && nextNodes.push(left);
                right && nextNodes.push(right);
                return val;
            }),
        );
        return levelFn(nextNodes);
    };
    return levelFn([root]);
}
