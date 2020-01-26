public class Solution {
    public ListNode ReverseBetween(ListNode head, int m, int n) {
        ListNode part1Head = null;
        ListNode part1Tail = null;
        for (var i = 1; i < m; ++i)
        {
            if (part1Head == null)
            {
                part1Head = head;
            }
            part1Tail = head;
            head = head.next;
        }
        if (part1Tail != null) part1Tail.next = null;
        ListNode part2Head = null;
        ListNode part2Tail = null;
        for (var i = m; i <= n; ++i)
        {
            var next = head.next;
            head.next = part2Head;
            part2Head = head;
            if (part2Tail == null)
            {
                part2Tail = head;
            }
            head = next;
        }
        if (part1Tail != null)
        {
            part1Tail.next = part2Head;
        }
        part2Tail.next = head;
        return part1Head ?? part2Head;
    }
}