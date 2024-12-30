/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Random *Node
 * }
 */

func copyRandomList(head *Node) *Node {
	dummy := &Node{}
	tail := dummy
	d := map[*Node]*Node{}
	for cur := head; cur != nil; cur = cur.Next {
		node := &Node{Val: cur.Val}
		d[cur] = node
		tail.Next = node
		tail = node
	}
	for cur := head; cur != nil; cur = cur.Next {
		if cur.Random != nil {
			d[cur].Random = d[cur.Random]
		}
	}
	return dummy.Next
}
