/**
 * Definition for _Node.
 * class _Node {
 *     val: number
 *     next: _Node | null
 *     random: _Node | null
 *
 *     constructor(val?: number, next?: _Node, random?: _Node) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *         this.random = (random===undefined ? null : random)
 *     }
 * }
 */

function copyRandomList(head: _Node | null): _Node | null {
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
}
