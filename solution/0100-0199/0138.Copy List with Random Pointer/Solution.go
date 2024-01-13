/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Random *Node
 * }
 */

func copyRandomList(head *Node) *Node {
	d := map[*Node]*Node{}
	dummy := &Node{}
	tail := dummy
	for cur := head; cur != nil; cur = cur.Next {
		tail.Next = &Node{Val: cur.Val}
		tail = tail.Next
		d[cur] = tail
	}
	tail = dummy.Next
	for cur := head; cur != nil; cur = cur.Next {
		tail.Random = d[cur.Random]
		tail = tail.Next
	}
	return dummy.Next
}