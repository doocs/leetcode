/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
 var addTwoNumbers = function(l1, l2) {
  const dummy = new ListNode();
  let carry = 0;
  let cur = dummy;
  while (l1 || l2 || carry) {
      const s = (l1?.val || 0) + (l2?.val || 0) + carry;
      carry = Math.floor(s / 10);
      cur.next = new ListNode(s % 10);
      cur = cur.next;
      l1 = l1?.next;
      l2 = l2?.next;
  }
  return dummy.next;
};