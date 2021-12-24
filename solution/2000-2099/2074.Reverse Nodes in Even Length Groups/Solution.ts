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

function reverseEvenLengthGroups(head: ListNode | null): ListNode | null {
    let nums = [];
    let cur = head;
    while (cur) {
        nums.push(cur.val);
        cur = cur.next;
    }

    const n = nums.length;
    for (let i = 0, k = 1; i < n; i += k, k++) {
        // 最后一组， 可能出现不足
        k = Math.min(n - i, k);
        if (!(k & 1)) {
            let tmp = nums.splice(i, k);
            tmp.reverse();
            nums.splice(i, 0, ...tmp);
        }
    }

    cur = head;
    for (let num of nums) {
        cur.val = num;
        cur = cur.next;
    }
    return head;
}
