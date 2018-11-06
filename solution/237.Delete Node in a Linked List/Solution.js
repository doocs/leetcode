const deleteNode = function(node){
  node.val = node.next.val;
  node.next = node.next.next;
}