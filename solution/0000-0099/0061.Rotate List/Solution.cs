public class Solution {
    public ListNode RotateRight(ListNode head, int k) {
        var length = 0;
        var temp = head;
        var last = head;
        while (temp != null)
        {
            ++length;
            last = temp;
            temp = temp.next;
        }
        if (length == 0) return null;

        k %= length;
        if (k == 0) return head;
        k = length - k;

        ListNode kNode = head;
        for (var i = 1; i < k; ++i)
        {
            kNode = kNode.next;
        }

        last.next = head;
        head = kNode.next;
        kNode.next = null;
        return head;
    }
}