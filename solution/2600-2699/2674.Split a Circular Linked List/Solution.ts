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

function splitCircularLinkedList(list: ListNode | null): Array<ListNode | null> {
    let a = list;
    let b = list;
    while (b.next !== list && b.next.next !== list) {
        a = a.next;
        b = b.next.next;
    }
    if (b.next !== list) {
        b = b.next;
    }
    const list2 = a.next;
    b.next = list2;
    a.next = list;
    return [list, list2];
}
