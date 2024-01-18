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

interface Item {
    index: number;
    val: number;
}

function nextLargerNodes(head: ListNode | null): number[] {
    const res: number[] = [];
    const stack: Item[] = [];
    let cur = head;
    for (let i = 0; cur != null; i++) {
        res.push(0);
        const { val, next } = cur;
        while (stack.length !== 0 && stack[stack.length - 1].val < val) {
            res[stack.pop().index] = val;
        }
        stack.push({
            val,
            index: i,
        });
        cur = next;
    }
    return res;
}
