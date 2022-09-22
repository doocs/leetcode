type MyLinkedList struct {
	dummy *ListNode
	cnt   int
}

func Constructor() MyLinkedList {
	return MyLinkedList{&ListNode{}, 0}
}

func (this *MyLinkedList) Get(index int) int {
	if index < 0 || index >= this.cnt {
		return -1
	}
	cur := this.dummy.Next
	for ; index > 0; index-- {
		cur = cur.Next
	}
	return cur.Val
}

func (this *MyLinkedList) AddAtHead(val int) {
	this.AddAtIndex(0, val)
}

func (this *MyLinkedList) AddAtTail(val int) {
	this.AddAtIndex(this.cnt, val)
}

func (this *MyLinkedList) AddAtIndex(index int, val int) {
	if index > this.cnt {
		return
	}
	pre := this.dummy
	for ; index > 0; index-- {
		pre = pre.Next
	}
	pre.Next = &ListNode{val, pre.Next}
	this.cnt++
}

func (this *MyLinkedList) DeleteAtIndex(index int) {
	if index < 0 || index >= this.cnt {
		return
	}
	pre := this.dummy
	for ; index > 0; index-- {
		pre = pre.Next
	}
	t := pre.Next
	pre.Next = t.Next
	t.Next = nil
	this.cnt--
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Get(index);
 * obj.AddAtHead(val);
 * obj.AddAtTail(val);
 * obj.AddAtIndex(index,val);
 * obj.DeleteAtIndex(index);
 */