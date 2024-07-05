/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function nodesBetweenCriticalPoints(head: ListNode | null): number[] {
    const ans: number[] = [Infinity, 0];
    let [first, last] = [-1, -1];
    for (let i = 0; head.next.next; head = head.next, ++i) {
        const [a, b, c] = [head.val, head.next.val, head.next.next.val];
        if (b < Math.min(a, c) || b > Math.max(a, c)) {
            if (last < 0) {
                first = i;
                last = i;
            } else {
                ans[0] = Math.min(ans[0], i - last);
                last = i;
                ans[1] = Math.max(ans[1], last - first);
            }
        }
    }
    return first === last ? [-1, -1] : ans;
}
