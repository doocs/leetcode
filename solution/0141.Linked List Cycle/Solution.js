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
var hasCycle = function(head) {
    flag = false;
    
    dfs(head);

    return flag;
};
function dfs(node) {
  if (flag)
    return;

  if (node === null)
    return;

  if (node.flag) {
    flag = true;
    return;
  }

  if (node.next === null)
    return;

  node.flag = true;

  dfs(node.next);
}