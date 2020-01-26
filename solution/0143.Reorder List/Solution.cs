public class Solution {
    public void ReorderList(ListNode head) {
        var mid = FindMiddleNode(head);
        if (mid == null || mid.next == null) return;
        var head2 = mid.next;
        mid.next = null;
        head2 = ReverseList(head2);
        MergeList(head, head2);
    }
    
    private ListNode FindMiddleNode(ListNode head)
    {
        var last = head;
        var mid = head;
        while (last != null)
        {
            last = last.next;
            if (last != null)
            {
                last = last.next;
                mid = mid.next;
            }
        }
        return mid;
    }
    
    private ListNode ReverseList(ListNode head)
    {
        var current = head;
        head = null;
        while (current != null)
        {
            var next = current.next;
            current.next = head;
            head = current;
            current = next;
        }
        return head;
    }
    
    private void MergeList(ListNode head1, ListNode head2)
    {
        var p1 = head1;
        var p2 = head2;
        while (p2 != null)
        {
            var p1Next = p1.next;
            var p2Next = p2.next;
            p1.next = p2;
            p2.next = p1Next;
            p1 = p1Next;
            p2 = p2Next;
        }
    }
}