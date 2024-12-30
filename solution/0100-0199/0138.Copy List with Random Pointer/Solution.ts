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
    const d: Map<_Node, _Node> = new Map();
    const dummy = new _Node();
    let tail = dummy;
    for (let cur = head; cur; cur = cur.next) {
        const node = new _Node(cur.val);
        tail.next = node;
        tail = node;
        d.set(cur, node);
    }
    for (let cur = head; cur; cur = cur.next) {
        d.get(cur)!.random = cur.random ? d.get(cur.random)! : null;
    }
    return dummy.next;
}
