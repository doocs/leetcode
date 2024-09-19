/**
 * Definition for _Node.
 * class _Node {
 *     val: number
 *     prev: _Node | null
 *     next: _Node | null
 *
 *     constructor(val?: number, prev? : _Node, next? : _Node) {
 *         this.val = (val===undefined ? 0 : val);
 *         this.prev = (prev===undefined ? null : prev);
 *         this.next = (next===undefined ? null : next);
 *     }
 * }
 */

function toArray(node: _Node | null): number[] {
    let cur = node;
    while (cur && cur.prev) {
        cur = cur.prev;
    }
    const ans: number[] = [];
    while (cur) {
        ans.push(cur.val);
        cur = cur.next;
    }
    return ans;
}
