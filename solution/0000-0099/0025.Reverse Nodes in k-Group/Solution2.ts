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
    if (k === 1) {
        return head;
    }

    const dummy = new ListNode(0, head);
    let root = dummy;
    while (root != null) {
        let pre = root;
        let cur = root;

        let count = 0;
        while (count !== k) {
            count++;
            cur = cur.next;
            if (cur == null) {
                return dummy.next;
            }
        }

        const nextRoot = pre.next;
        pre.next = cur;

        let node = nextRoot;
        let next = node.next;
        node.next = cur.next;
        while (node != cur) {
            [next.next, node, next] = [node, next, next.next];
        }
        root = nextRoot;
    }

    return dummy.next;
}
