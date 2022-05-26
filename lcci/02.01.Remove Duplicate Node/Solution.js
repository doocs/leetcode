/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var removeDuplicateNodes = function (head) {
    if (head == null || head.next == null) return head;
    const cache = new Set([]);
    cache.add(head.val);
    let cur = head,
        fast = head.next;
    while (fast !== null) {
        if (!cache.has(fast.val)) {
            cur.next = fast;
            cur = cur.next;
            cache.add(fast.val);
        }
        fast = fast.next;
    }
    cur.next = null;
    return head;
};
