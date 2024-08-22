/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Prev *Node
 * }
 */

func toArray(head *Node) (ans []int) {
	for ; head != nil; head = head.Next {
		ans = append(ans, head.Val)
	}
	return
}
