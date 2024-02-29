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

function gameResult(head: ListNode | null): string {
    let [odd, even] = [0, 0];
    for (; head; head = head.next.next) {
        const [a, b] = [head.val, head.next.val];
        odd += a < b ? 1 : 0;
        even += a > b ? 1 : 0;
    }
    if (odd > even) {
        return 'Odd';
    }
    if (odd < even) {
        return 'Even';
    }
    return 'Tie';
}
