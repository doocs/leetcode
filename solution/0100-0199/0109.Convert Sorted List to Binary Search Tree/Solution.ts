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

const find = (start: ListNode | null, end: ListNode | null) => {
    let fast = start;
    let slow = start;
    while (fast !== end && fast.next !== end) {
        fast = fast.next.next;
        slow = slow.next;
    }
    return slow;
};

const build = (start: ListNode | null, end: ListNode | null) => {
    if (start == end) {
        return null;
    }
    const node = find(start, end);
    return new TreeNode(node.val, build(start, node), build(node.next, end));
};

function sortedListToBST(head: ListNode | null): TreeNode | null {
    return build(head, null);
}
