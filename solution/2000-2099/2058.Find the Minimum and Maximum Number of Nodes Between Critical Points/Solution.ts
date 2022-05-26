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
    let idx = 1;
    let pre = head.val;
    head = head.next;
    let nums = [];
    while (head.next != null) {
        let val = head.val,
            post = head.next.val;
        if (pre < val && val > post) {
            nums.push(idx);
        }
        if (pre > val && val < post) {
            nums.push(idx);
        }
        pre = val;
        idx++;
        head = head.next;
    }
    let n = nums.length;
    if (n < 2) return [-1, -1];
    let min = Infinity;
    for (let i = 1; i < n; i++) {
        min = Math.min(nums[i] - nums[i - 1], min);
    }
    return [min, nums[n - 1] - nums[0]];
}
