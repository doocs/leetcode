/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func mergeKLists(lists []*ListNode) *ListNode {
	if len(lists) == 0 {
		return nil
	}
	for len(lists) != 1 {
		newLists := make([]*ListNode, 0)
		for i:=0; i<len(lists); i ++ {
			if i < len(lists) - 1 {
				nl := merge2List(lists[i], lists[i+1])
				newLists = append(newLists, nl)
				i++
			} else if i == len(lists) - 1 && len(lists)%2 == 1 {
				newLists = append(newLists, lists[i])
			}
		}
		lists = newLists
	}
	return lists[0]
}

func merge2List(l1, l2 *ListNode) *ListNode {
	p := &ListNode{}
    h := p
	for l1 != nil || l2 != nil {
		if l1 == nil && l2 != nil {
			p.Next = l2
            p = p.Next
			l2 = l2.Next
		} else if l1 != nil && l2 == nil {
			p.Next = l1
            p = p.Next
			l1 = l1.Next
		} else {
			if l1.Val < l2.Val {
				p.Next = l1
                p = p.Next
				l1 = l1.Next
			} else {
				p.Next = l2
                p = p.Next
				l2 = l2.Next
			}
		}
	}
	return h.Next
}