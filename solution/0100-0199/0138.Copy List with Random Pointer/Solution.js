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
    if (head == null) {
        return null;
    }
    let cur = head;
    while (cur != null) {
        let node = new Node(cur.val, cur.next);
        cur.next = node;
        cur = node.next;
    }

    cur = head;
    while (cur != null) {
        cur.next.random = cur.random == null ? null : cur.random.next;
        cur = cur.next.next;
    }

    let copy = head.next;
    cur = head;
    while (cur != null) {
        let next = cur.next;
        cur.next = next.next;
        next.next = next.next == null ? null : next.next.next;
        cur = cur.next;
    }
    return copy;
};
