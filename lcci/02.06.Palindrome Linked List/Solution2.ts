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

function isPalindrome(head: ListNode | null): boolean {
    let root = head;
    const dfs = (node: ListNode | null): boolean => {
        if (node == null) {
            return true;
        }
        if (dfs(node.next) && node.val === root.val) {
            root = root.next;
            return true;
        }
        return false;
    };
    return dfs(head);
}
