/**
 * // Definition for a Node.
 * function Node(val, next, random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */

/**
 * @param {Node} head
 * @return {Node}
 */
var copyRandomList = function (head) {
    if (!head) {
        return null;
    }
    for (let cur = head; cur; ) {
        const node = new Node(cur.val, cur.next, null);
        cur.next = node;
        cur = node.next;
    }
    for (let cur = head; cur; cur = cur.next.next) {
        if (cur.random) {
            cur.next.random = cur.random.next;
        }
    }
    const ans = head.next;
    for (let cur = head; cur; ) {
        const nxt = cur.next;
        if (nxt) {
            cur.next = nxt.next;
        }
        cur = nxt;
    }
    return ans;
};
