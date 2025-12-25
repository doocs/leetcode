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

function mergeKLists(lists: Array<ListNode | null>): ListNode | null {
    const pq = new PriorityQueue<ListNode>((a, b) => a.val - b.val);
    lists.filter(head => head).forEach(head => pq.enqueue(head));
    const dummy: ListNode = new ListNode();
    let cur: ListNode = dummy;
    while (!pq.isEmpty()) {
        const node = pq.dequeue();
        cur.next = node;
        cur = cur.next;
        if (node.next) {
            pq.enqueue(node.next);
        }
    }
    return dummy.next;
}
