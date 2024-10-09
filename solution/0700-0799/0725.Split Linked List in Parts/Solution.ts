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

function splitListToParts(head: ListNode | null, k: number): Array<ListNode | null> {
    let n = 0;
    for (let cur = head; cur !== null; cur = cur.next) {
        n++;
    }
    const cnt = (n / k) | 0;
    const mod = n % k;
    const ans: Array<ListNode | null> = Array(k).fill(null);
    let cur = head;
    for (let i = 0; i < k && cur !== null; i++) {
        ans[i] = cur;
        let m = cnt + (i < mod ? 1 : 0);
        for (let j = 1; j < m; j++) {
            cur = cur.next!;
        }
        let next = cur.next;
        cur.next = null;
        cur = next;
    }
    return ans;
}
