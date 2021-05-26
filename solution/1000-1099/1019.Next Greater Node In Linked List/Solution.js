/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {number[]}
 */
 var nextLargerNodes = function(head) {
    let nums = [];
    while (head != null) {
        nums.push(head.val);
        head = head.next;
    }
    const n = nums.length;
    let larger = new Array(n).fill(0);
    let stack = [];
    for (let i = 0; i < n; i++) {
        let num = nums[i];
        while (stack.length > 0 && nums[stack[stack.length - 1]] < num) {
            larger[stack.pop()] = num;
        }
        stack.push(i);
    }
    return larger;
};