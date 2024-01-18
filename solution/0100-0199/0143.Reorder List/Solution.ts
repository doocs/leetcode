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
 Do not return anything, modify head in-place instead.
 */
function reorderList(head: ListNode | null): void {
    const arr = [];
    let node = head;
    while (node.next != null) {
        arr.push(node);
        node = node.next;
    }
    let l = 0;
    let r = arr.length - 1;
    while (l < r) {
        const start = arr[l];
        const end = arr[r];
        [end.next.next, start.next, end.next] = [start.next, end.next, null];
        l++;
        r--;
    }
}
