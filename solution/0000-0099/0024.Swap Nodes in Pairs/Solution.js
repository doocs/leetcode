/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
 var swapPairs = function(head) {
    const dummy = new ListNode(0, head);
    let pre = dummy;
    let cur = head;
    while (cur && cur.next) {
        const t = cur.next;
        cur.next = t.next;
        t.next = cur;
        pre.next = t;
        pre = cur;
        cur = cur.next;
    }
    return dummy.next;
};