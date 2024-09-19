/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Prev *Node
 * }
 */

func toArray(node *Node) (ans []int) {
	cur := node
	for cur != nil && cur.Prev != nil {
		cur = cur.Prev
	}
	for cur != nil {
		ans = append(ans, cur.Val)
		cur = cur.Next
	}
	return
}
