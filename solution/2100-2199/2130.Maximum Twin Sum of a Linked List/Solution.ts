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

function pairSum(head: ListNode | null): number {
    const arr = [];
    let node = head;
    while (node) {
        arr.push(node.val);
        node = node.next;
    }
    const n = arr.length;
    let ans = 0;
    for (let i = 0; i < n >> 1; i++) {
        ans = Math.max(ans, arr[i] + arr[n - 1 - i]);
    }
    return ans;
}
