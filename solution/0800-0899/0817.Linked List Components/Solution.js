/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number[]} nums
 * @return {number}
 */
var numComponents = function (head, nums) {
    const s = new Set(nums);
    let ans = 0;
    while (head) {
        while (head && !s.has(head.val)) {
            head = head.next;
        }
        ans += head != null;
        while (head && s.has(head.val)) {
            head = head.next;
        }
    }
    return ans;
};
