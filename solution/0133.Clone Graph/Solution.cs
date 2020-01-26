using System.Collections.Generic;

public class Solution {
    public UndirectedGraphNode CloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        var dict = new Dictionary<int, UndirectedGraphNode>();
        var queue = new Queue<UndirectedGraphNode>();
        queue.Enqueue(CloneLabel(node));
        dict.Add(node.label, queue.Peek());
        while (queue.Count > 0)
        {
            var current = queue.Dequeue();
            var newNeighbors = new List<UndirectedGraphNode>(current.neighbors.Count);
            foreach (var oldNeighbor in current.neighbors)
            {
                UndirectedGraphNode newNeighbor;
                if (!dict.TryGetValue(oldNeighbor.label, out newNeighbor))
                {
                    newNeighbor = CloneLabel(oldNeighbor);
                    queue.Enqueue(newNeighbor);
                    dict.Add(newNeighbor.label, newNeighbor);
                }
                newNeighbors.Add(newNeighbor);
            }
            current.neighbors = newNeighbors;
        }
        return dict[node.label];
    }

    private UndirectedGraphNode CloneLabel(UndirectedGraphNode node)
    {
        return new UndirectedGraphNode(node.label)
        {
            neighbors = new List<UndirectedGraphNode>(node.neighbors)
        };
    }
}