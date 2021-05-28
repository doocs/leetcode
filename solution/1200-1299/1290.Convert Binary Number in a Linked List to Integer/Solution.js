/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {number}
 */
 var getDecimalValue = function(head) {
    let res = 0;
    while (head !== null) {
        res *= 2;
        if (head.val) res += 1;
        head = head.next;
    }
    return res;
};