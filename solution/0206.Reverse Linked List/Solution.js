const reverseList = function(head){
  if(head == null){
      return head;
  }
  let cur = head;
  let pre = null, tmp = null;
  while(cur != null){
      tmp = cur.next;
      cur.next = pre;
      pre = cur;
      cur = tmp;
  }
  return pre;
}