using System.Collections.Generic;

public class Solution {
    public Node CloneGraph(Node node) {
        if (node == null) return null;
        var dict = new Dictionary<int, Node>();
        var queue = new Queue<Node>();
        queue.Enqueue(CloneVal(node));
        dict.Add(node.val, queue.Peek());
        while (queue.Count > 0)
        {
            var current = queue.Dequeue();
            var newNeighbors = new List<Node>(current.neighbors.Count);
            foreach (var oldNeighbor in current.neighbors)
            {
                Node newNeighbor;
                if (!dict.TryGetValue(oldNeighbor.val, out newNeighbor))
                {
                    newNeighbor = CloneVal(oldNeighbor);
                    queue.Enqueue(newNeighbor);
                    dict.Add(newNeighbor.val, newNeighbor);
                }
                newNeighbors.Add(newNeighbor);
            }
            current.neighbors = newNeighbors;
        }
        return dict[node.val];
    }

    private Node CloneVal(Node node)
    {
        return new Node(node.val, new List<Node>(node.neighbors));
    }
}