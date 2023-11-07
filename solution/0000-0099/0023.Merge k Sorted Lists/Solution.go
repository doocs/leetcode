/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func mergeKLists(lists []*ListNode) *ListNode {
	pq := hp{}
	for _, head := range lists {
		if head != nil {
			pq = append(pq, head)
		}
	}
	heap.Init(&pq)
	dummy := &ListNode{}
	cur := dummy
	for len(pq) > 0 {
		cur.Next = heap.Pop(&pq).(*ListNode)
		cur = cur.Next
		if cur.Next != nil {
			heap.Push(&pq, cur.Next)
		}
	}
	return dummy.Next
}

type hp []*ListNode

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].Val < h[j].Val }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(*ListNode)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }