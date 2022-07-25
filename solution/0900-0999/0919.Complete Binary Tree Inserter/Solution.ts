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
    private root: TreeNode;
    private queue: TreeNode[];

    constructor(root: TreeNode | null) {
        this.root = root;
        this.queue = [this.root];
        while (true) {
            if (this.queue[0].left == null) {
                break;
            }
            this.queue.push(this.queue[0].left);
            if (this.queue[0].right == null) {
                break;
            }
            this.queue.push(this.queue[0].right);
            this.queue.shift();
        }
    }

    insert(val: number): number {
        if (this.queue[0].left != null && this.queue[0].right != null) {
            this.queue.shift();
        }
        const newNode = new TreeNode(val);
        this.queue.push(newNode);
        if (this.queue[0].left == null) {
            this.queue[0].left = newNode;
            return this.queue[0].val;
        }
        if (this.queue[0].right == null) {
            this.queue[0].right = newNode;
            return this.queue[0].val;
        }
        return 0;
    }

    get_root(): TreeNode | null {
        return this.root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * var obj = new CBTInserter(root)
 * var param_1 = obj.insert(val)
 * var param_2 = obj.get_root()
 */
