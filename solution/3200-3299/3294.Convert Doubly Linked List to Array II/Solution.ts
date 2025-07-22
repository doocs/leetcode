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
    while (node && node.prev) {
        node = node.prev;
    }
    const ans: number[] = [];
    for (; node; node = node.next) {
        ans.push(node.val);
    }
    return ans;
}
