/**
 * Definition for Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Parent *Node
 * }
 */

 func lowestCommonAncestor(p *Node, q *Node) *Node {
	vis := map[*Node]bool{}
	for node := p; node != nil; node = node.Parent {
		vis[node] = true
	}
	for node := q; ; node = node.Parent {
		if vis[node] {
			return node
		}
	}
}