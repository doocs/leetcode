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
    if (head === null) {
        return null;
    }
    let cur = head;
    while (cur !== null) {
        const node = new _Node(cur.val);
        node.next = cur.next;
        cur.next = node;
        cur = node.next;
    }
    cur = head;
    while (cur !== null) {
        cur.next.random = cur.random === null ? null : cur.random.next;
        cur = cur.next.next;
    }
    cur = head;
    const ans = head.next;
    while (cur.next !== null) {
        const node = cur.next;
        cur.next = node.next;
        cur = node;
    }
    return ans;
};
