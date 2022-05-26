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

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function listOfDepth(tree: TreeNode | null): Array<ListNode | null> {
    const res = [];
    if (tree == null) {
        return res;
    }
    const queue = [tree];
    while (queue.length !== 0) {
        const n = queue.length;
        const dummy = new ListNode();
        let cur = dummy;
        for (let i = 0; i < n; i++) {
            const { val, left, right } = queue.shift();
            left && queue.push(left);
            right && queue.push(right);
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        res.push(dummy.next);
    }
    return res;
}
