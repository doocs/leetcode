/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 *
 * Report by leetcode.com
 * Runtime: 12 ms, Memory Usage: 5 MB
 */
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	head := &ListNode{}
	currentNode1 := l1
	currentNode2 := l2
	currentHead := head
	sum := 0
	nextSum := 0

	for true {
		if currentNode1 != nil || currentNode2 != nil {
			if currentNode1 == nil {
				sum = nextSum + currentNode2.Val
				nextSum = sum / 10
				currentNode2 = currentNode2.Next
			} else if currentNode2 == nil {
				sum = nextSum + currentNode1.Val
				nextSum = sum / 10
				currentNode1 = currentNode1.Next
			} else {
				sum = nextSum + currentNode1.Val + currentNode2.Val
				nextSum = sum / 10
				currentNode1 = currentNode1.Next
				currentNode2 = currentNode2.Next
			}
			currentHead.Val = sum % 10
			// If there are elements present in the nodes then
			// make a new node for future addition otherwise we
			// will get unnecessary (0 --> <nil>) node in the end.
			if currentNode1 != nil || currentNode2 != nil {
				currentHead.Next = &ListNode{}
				currentHead = currentHead.Next
			} else if nextSum != 0 {
				// If nextSum is not 0 this means that there was some carry value
				// left in it which should be further.
				currentHead.Next = &ListNode{nextSum, nil}
			}
		} else {
			break
		}
	}

	return head
}