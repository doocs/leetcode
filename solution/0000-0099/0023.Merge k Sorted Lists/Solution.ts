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

function mergeKLists(lists: Array<ListNode | null>): ListNode | null {
    const n = lists.length;
    const dfs = (start: number, end: number) => {
        if (end - start <= 1) {
            return lists[start] ?? null;
        }

        const mid = (start + end) >> 1;
        let left = dfs(start, mid);
        let right = dfs(mid, end);

        const dummy = new ListNode();
        let cur = dummy;
        while (left || right) {
            let next: ListNode;
            if (
                (left ?? { val: Infinity }).val <
                (right ?? { val: Infinity }).val
            ) {
                next = left;
                left = left.next;
            } else {
                next = right;
                right = right.next;
            }
            cur.next = next;
            cur = next;
        }
        return dummy.next;
    };
    return dfs(0, n);
}
