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
    const d = new Map();
    const dummy = new Node(0);
    let tail = dummy;
    for (let cur = head; cur; cur = cur.next) {
        tail.next = new Node(cur.val);
        tail = tail.next;
        d.set(cur, tail);
    }
    tail = dummy.next;
    for (let cur = head; cur; cur = cur.next) {
        tail.random = d.get(cur.random);
        tail = tail.next;
    }
    return dummy.next;
};
