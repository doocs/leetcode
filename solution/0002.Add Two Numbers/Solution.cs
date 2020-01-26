public class Solution {
    public ListNode AddTwoNumbers(ListNode l1, ListNode l2) {
        return AddInternal(l1, l2, false);
    }
    
    private ListNode AddInternal(ListNode l1, ListNode l2, bool plusOne)
    {
        if (l1 == null && l2 == null)
        {
            if (plusOne)
            {
                return new ListNode(1);
            }
            return null;
        }
        
        var val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + (plusOne ? 1 : 0); 
        plusOne = val >= 10;
        val %= 10;
        return new ListNode(val)
        {
            //next = AddInternal(l1?.next, l2?.next, plusOne);
            next = AddInternal(l1 == null ? null : l1.next, l2 == null ? null : l2.next, plusOne)
        };
    }
}