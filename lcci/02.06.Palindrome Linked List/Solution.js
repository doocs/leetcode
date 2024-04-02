/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {boolean}
 */
var isPalindrome = function (head) {
    if (!head) {
        return true;
    }
    let slow = head;
    let fast = head.next;
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
    }
    let p = slow.next;
    slow.next = null;
    const dummy = new ListNode(0);
    while (p) {
        const next = p.next;
        p.next = dummy.next;
        dummy.next = p;
        p = next;
    }
    p = dummy.next;
    while (p) {
        if (head.val !== p.val) {
            return false;
        }
        head = head.next;
        p = p.next;
    }
    return true;
};
