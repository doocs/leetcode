/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 //insert to the front
func reversePrint(head *ListNode) []int {
    res := []int{}
    for head != nil {
        res = append([]int{head.Val}, res...)
        head = head.Next
    }
    return res
}