/**
 * // Definition for a _Node.
 * function _Node(val, next, random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */

/**
 * @param {_Node} head
 * @return {_Node}
 */
var copyRandomList = function (head) {
    const d = new Map();
    const dummy = new _Node();
    let tail = dummy;
    for (let cur = head; cur; cur = cur.next) {
        const node = new _Node(cur.val);
        tail.next = node;
        tail = node;
        d.set(cur, node);
    }
    for (let cur = head; cur; cur = cur.next) {
        d.get(cur).random = cur.random ? d.get(cur.random) : null;
    }
    return dummy.next;
};
