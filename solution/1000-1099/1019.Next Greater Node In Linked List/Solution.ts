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

function nextLargerNodes(head: ListNode | null): number[] {
    const nums: number[] = [];
    while (head) {
        nums.push(head.val);
        head = head.next;
    }
    const stk: number[] = [];
    const n = nums.length;
    const ans: number[] = new Array(n).fill(0);
    for (let i = n - 1; ~i; --i) {
        while (stk.length && stk[stk.length - 1] <= nums[i]) {
            stk.pop();
        }
        ans[i] = stk.length ? stk[stk.length - 1] : 0;
        stk.push(nums[i]);
    }
    return ans;
}
