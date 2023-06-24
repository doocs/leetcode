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
    const ans: Array<ListNode | null> = [];
    const q: Array<TreeNode | null> = [tree];
    while (q.length) {
        const dummy = new ListNode();
        let cur = dummy;
        for (let k = q.length; k; --k) {
            const { val, left, right } = q.shift()!;
            cur.next = new ListNode(val);
            cur = cur.next;
            left && q.push(left);
            right && q.push(right);
        }
        ans.push(dummy.next);
    }
    return ans;
}
