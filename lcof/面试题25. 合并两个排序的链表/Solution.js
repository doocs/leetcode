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
var mergeTwoLists = function (l1, l2) {
    // 法一 - 递归
    if (!l1) return l2;
    if (!l2) return l1;
    if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoLists(l2.next, l1);
        return l2;
    }
    // 法二 - 遍历
    // if(!l1 || !l2) return l1 ? l1 : l2
    // let a = l1
    // let b = l2
    // let res = l1
    // if(a.val > b.val) {
    //     let c = a
    //     a = b
    //     b = c
    //     res = l2
    // }
    // while(a && b) {
    //     while(a.next && a.next.val < b.val) {
    //         a = a.next
    //     }
    //     let tmp = a.next
    //     let rec = b.next
    //     a.next = b
    //     a.next.next = tmp
    //     a = a.next
    //     b = rec
    // }
    // return res
};
