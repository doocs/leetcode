/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */

/**
 * Author: Mcnwork2018 
 */
var addTwoNumbers = function (l1, l2) {
	let c1 = l1, c2 = l2, c3 = 0, l3 = 0, carry = 0;
	while (c1 || c2 || carry) {
		var v1 = 0, v2 = 0;
		if (c1) {
			v1 = c1.val;
			c1 = c1.next;
		}
		if (c2) {
			v2 = c2.val;
			c2 = c2.next;
		}
		var sum = v1 + v2 + carry;
		carry = (sum - sum % 10) / 10;
		if (!c3) {
			l3 = new ListNode(sum % 10);
			c3 = l3;
		} else {
			c3.next = new ListNode(sum % 10);
			c3 = c3.next;
		}
	}
	return l3;
}