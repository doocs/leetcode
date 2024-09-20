/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Prev *Node
 * }
 */

func toArray(node *Node) (ans []int) {
	for node != nil && node.Prev != nil {
		node = node.Prev
	}
	for ; node != nil; node = node.Next {
		ans = append(ans, node.Val)
	}
	return
}
