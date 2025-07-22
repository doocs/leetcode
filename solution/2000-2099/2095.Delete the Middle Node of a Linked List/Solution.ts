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

function deleteMiddle(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let [slow, fast] = [dummy, head];
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
    }
    slow.next = slow.next.next;
    return dummy.next;
}
