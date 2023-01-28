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

function mergeInBetween(
    list1: ListNode | null,
    a: number,
    b: number,
    list2: ListNode | null,
): ListNode | null {
    let p = list1;
    let q = list1;
    while (--a > 0) {
        p = p.next;
    }
    while (b-- > 0) {
        q = q.next;
    }
    p.next = list2;
    while (p.next) {
        p = p.next;
    }
    p.next = q.next;
    q.next = null;
    return list1;
}
