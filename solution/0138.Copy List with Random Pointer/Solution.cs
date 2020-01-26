using System;
using System.Collections.Generic;

public class Solution {
    public RandomListNode CopyRandomList(RandomListNode head) {
        var dict = new Dictionary<int, Tuple<int?, int?>>();
        var current = head;
        while (current != null)
        {
            dict.Add(current.label, Tuple.Create(current.next == null ? (int?) null : current.next.label, current.random == null ? (int?) null : current.random.label));
            current = current.next;
        }

        var dict2 = new Dictionary<int, RandomListNode>();
        foreach (var label in dict.Keys)
        {
            dict2.Add(label, new RandomListNode(label));
        }
        foreach (var pair in dict)
        {
            var next = pair.Value.Item1;
            if (next.HasValue) dict2[pair.Key].next = dict2[next.Value];
            var random = pair.Value.Item2;
            if (random.HasValue) dict2[pair.Key].random = dict2[random.Value];
        }

        return head == null ? null : dict2[head.label];
    }
}