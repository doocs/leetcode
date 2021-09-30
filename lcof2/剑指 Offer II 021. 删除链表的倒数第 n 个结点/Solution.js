/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} n
 * @return {ListNode}
 */
 var removeNthFromEnd = function(head, n) {
  const dummy = new ListNode(0, head);
  let fast = dummy, slow = dummy;
  while (n--) {
      fast = fast.next;
  }
  while (fast.next) {
      slow = slow.next;
      fast = fast.next;
  }
  slow.next = slow.next.next;
  return dummy.next;
};