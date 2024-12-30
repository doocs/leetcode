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

function reverseKGroup(head: ListNode | null, k: number): ListNode | null {
    const dummy = new ListNode(0, head);
    let pre = dummy;
    while (pre !== null) {
        let cur: ListNode | null = pre;
        for (let i = 0; i < k; i++) {
            cur = cur?.next || null;
            if (cur === null) {
                return dummy.next;
            }
        }

        const node = pre.next;
        const nxt = cur?.next || null;
        cur!.next = null;
        pre.next = reverse(node);
        node!.next = nxt;
        pre = node!;
    }

    return dummy.next;
}

function reverse(head: ListNode | null): ListNode | null {
    let dummy: ListNode | null = null;
    let cur = head;

    while (cur !== null) {
        const nxt = cur.next;
        cur.next = dummy;
        dummy = cur;
        cur = nxt;
    }

    return dummy;
}
