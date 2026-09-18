/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Random *Node
 * }
 */

func copyRandomBinaryTree(root *Node) *NodeCopy {
	seen := make(map[*Node]*NodeCopy)
	var dfs func(root *Node) *NodeCopy
	dfs = func(root *Node) *NodeCopy {
		if root == nil {
			return nil
		}
		if v, ok := seen[root]; ok {
			return v
		}
		copy := &NodeCopy{Val: root.Val}
		seen[root] = copy
		copy.Left = dfs(root.Left)
		copy.Right = dfs(root.Right)
		copy.Random = dfs(root.Random)
		return copy
	}
	return dfs(root)
}
