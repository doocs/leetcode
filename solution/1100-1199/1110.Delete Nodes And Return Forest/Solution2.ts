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
export function delNodes(root: T, to_delete: number[]): Array<T> {
    if (!root) return [];

    const del = new Set(to_delete);
    const res: T[] = [];
    let q: TreeNode[] = [root];

    while (q.length) {
        const qNext: TreeNode[] = [];

        for (const node of q) {
            if (node.left) {
                qNext.push(node.left);

                if (del.has(node.left.val)) {
                    node.left = null;
                }
            }

            if (node.right) {
                qNext.push(node.right);

                if (del.has(node.right.val)) {
                    node.right = null;
                }
            }

            if (del.has(node.val)) {
                if (node.left) res.push(node.left);
                if (node.right) res.push(node.right);
            }
        }

        q = qNext;
    }

    if (!del.has(root.val)) res.push(root);

    return res;
}

type T = TreeNode | null;
