class Solution {
    public void deleteNode(ListNode node) {
        // 保存下一个结点
        ListNode tmp = node.next;
        
        // 将下个结点的值赋给当前要删除的结点
        node.val = node.next.val;
        node.next = node.next.next;
        
        // tmp 置为空，让jvm进行垃圾回收
        tmp = null;
        
    }
}