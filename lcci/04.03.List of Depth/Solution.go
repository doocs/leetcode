/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func listOfDepth(tree *TreeNode) []*ListNode {
	queue := make([]*TreeNode, 0)
	queue = append(queue, tree)
	ans := make([]*ListNode, 0)
	for len(queue) > 0 {
		n := len(queue)
		head := new(ListNode)
		tail := head
		for i := 0; i < n; i++ {
			front := queue[0]
			queue = queue[1:]
			node := &ListNode{Val: front.Val}
			tail.Next = node
			tail = node
			if front.Left != nil {
				queue = append(queue, front.Left)
			}
			if front.Right != nil {
				queue = append(queue, front.Right)
			}
		}
		ans = append(ans, head.Next)
	}
	return ans
}
