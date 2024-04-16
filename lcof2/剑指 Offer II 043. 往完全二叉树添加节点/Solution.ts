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

class CBTInserter {
    private tree: TreeNode[] = [];

    constructor(root: TreeNode | null) {
        if (root === null) {
            return;
        }
        const q: TreeNode[] = [root];
        while (q.length) {
            const t: TreeNode[] = [];
            for (const node of q) {
                this.tree.push(node);
                node.left !== null && t.push(node.left);
                node.right !== null && t.push(node.right);
            }
            q.splice(0, q.length, ...t);
        }
    }

    insert(val: number): number {
        const p = this.tree[(this.tree.length - 1) >> 1];
        const node = new TreeNode(val);
        this.tree.push(node);
        if (p.left === null) {
            p.left = node;
        } else {
            p.right = node;
        }
        return p.val;
    }

    get_root(): TreeNode | null {
        return this.tree[0];
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * var obj = new CBTInserter(root)
 * var param_1 = obj.insert(val)
 * var param_2 = obj.get_root()
 */
