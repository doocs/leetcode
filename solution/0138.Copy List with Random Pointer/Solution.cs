using System;
using System.Collections.Generic;

public class Solution {
    public Node CopyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        var map = new Dictionary<Node, Node>();
        var current = head;
        while (current != null) {
            var newCurrent = new Node(current.val);
            map.Add(current, newCurrent);
            current = current.next;
        }

        foreach (var entry in map) {
            var oldNode = entry.Key;
            var newNode = entry.Value;
            if (oldNode.next != null) {
                newNode.next = map[oldNode.next];
            }
            if (oldNode.random != null) {
                newNode.random = map[oldNode.random];
            }
        }

        return map[head];
    }
}