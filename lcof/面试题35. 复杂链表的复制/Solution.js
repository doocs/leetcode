/**
 * // Definition for a Node.
 * function Node(val, next, random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */
/**
 * @param {Node} head
 * @return {Node}
 */
var copyRandomList = function (head) {
  function copy(node) {
    if (!node) return null;
    if (isRead.get(node)) return isRead.get(node);
    let newNode = new Node(node.val);
    isRead.set(node, newNode);
    newNode.random = copy(node.random);
    newNode.next = copy(node.next);
    return newNode;
  }
  let isRead = new Map();
  return copy(head);
};
