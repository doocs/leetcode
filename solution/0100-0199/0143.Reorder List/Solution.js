/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {void} Do not return anything, modify head in-place instead.
 */
var reorderList = function (head) {
    if (!head || !head.next) {
        return;
    }
    let slow = head;
    let fast = head.next;
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
    }

    let cur = slow.next;
    slow.next = null;

    let pre = null;
    while (cur) {
        const t = cur.next;
        cur.next = pre;
        pre = cur;
        cur = t;
    }
    cur = head;

    while (pre) {
        const t = pre.next;
        pre.next = cur.next;
        cur.next = pre;
        cur = pre.next;
        pre = t;
    }
};
