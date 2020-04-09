using System;
using System.Collections.Generic;
using System.Linq;

class Comparer : IComparer<Tuple<ListNode, int>>
{
    public int Compare(Tuple<ListNode, int> left, Tuple<ListNode, int> right)
    {
        var result = left.Item1.val.CompareTo(right.Item1.val);
        if (result == 0)
        {
            result = left.Item2.CompareTo(right.Item2);
        }
        return result;
    }
}

public class Solution {
    public ListNode MergeKLists(ListNode[] lists) {
        var heap = new SortedSet<Tuple<ListNode, int>>(new Comparer());
        for (var i = 0; i < lists.Length; ++i)
        {
            if (lists[i] != null) heap.Add(Tuple.Create(lists[i], i));
        }
        ListNode head = null;
        ListNode current = null;
        while (heap.Any())
        {
            var min = heap.Min;
            heap.Remove(min);
            if (min.Item1.next != null) heap.Add(Tuple.Create(min.Item1.next, min.Item2));
            if (head == null)
            {
                head = min.Item1;
                current = head;
            }
            else
            {
                current.next = min.Item1;
                current = current.next;
            }
        }
        return head;
    }
}