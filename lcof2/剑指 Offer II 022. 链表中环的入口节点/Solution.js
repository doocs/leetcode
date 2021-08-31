/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} head
 * @return {ListNode}
 */
 var detectCycle = function(head) {
    let slow = head;
    let fast = head;
    let hasCycle = false;
    while (!hasCycle && fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        hasCycle = slow == fast;
    }
    if (!hasCycle) {
        return null;
    }
    let p = head;
    while (p != slow) {
        p = p.next;
        slow = slow.next;
    }
    return p;
};