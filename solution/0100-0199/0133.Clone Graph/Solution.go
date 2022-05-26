/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Neighbors []*Node
 * }
 */

func cloneGraph(node *Node) *Node {
	visited := map[*Node]*Node{}
	var clone func(node *Node) *Node
	clone = func(node *Node) *Node {
		if node == nil {
			return nil
		}
		if _, ok := visited[node]; ok {
			return visited[node]
		}
		c := &Node{node.Val, []*Node{}}
		visited[node] = c
		for _, e := range node.Neighbors {
			c.Neighbors = append(c.Neighbors, clone(e))
		}
		return c
	}

	return clone(node)
}