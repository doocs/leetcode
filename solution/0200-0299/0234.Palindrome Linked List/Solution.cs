public class Solution {
    public bool IsPalindrome(ListNode head) {
        if (head == null) return true;
        var count = Count(head);
        var temp = head;
        var c = 1;
        while (c < count / 2)
        {
            ++c;
            temp = temp.next;
        }
        var head2 = Reverse(temp.next);
        temp.next = null;

        while (head != null && head2 != null)
        {
            if (head.val != head2.val) return false;
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    private int Count(ListNode head)
    {
        var count = 0;
        while (head != null)
        {
            ++count;
            head = head.next;
        }
        return count;
    }

    private ListNode Reverse(ListNode head)
    {
        var temp = head;
        head = null;
        while (temp != null)
        {
            var temp2 = temp.next;
            temp.next = head;
            head = temp;
            temp = temp2;
        }
        return head;
    }
}