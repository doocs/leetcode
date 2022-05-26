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

function mergeTwoLists(
    l1: ListNode | null,
    l2: ListNode | null,
): ListNode | null {
    const duumy = new ListNode();
    let cur = duumy;
    while (l1 && l2) {
        let node: ListNode;
        if (l1.val < l2.val) {
            node = l1;
            l1 = l1.next;
        } else {
            node = l2;
            l2 = l2.next;
        }
        cur.next = node;
        cur = node;
    }
    cur.next = l1 || l2;
    return duumy.next;
}
