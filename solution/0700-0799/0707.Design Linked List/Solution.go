type MyLinkedList struct {
	e    []int
	ne   []int
	head int
	idx  int
	size int
}

func Constructor() MyLinkedList {
	e := make([]int, 1000)
	ne := make([]int, 1000)
	head, idx, size := -1, 0, 0
	return MyLinkedList{e, ne, head, idx, size}
}

func (this *MyLinkedList) Get(index int) int {
	if index < 0 || index >= this.size {
		return -1
	}
	i := this.head
	for ; index > 0; i, index = this.ne[i], index-1 {
	}
	return this.e[i]
}

func (this *MyLinkedList) AddAtHead(val int) {
	this.e[this.idx] = val
	this.ne[this.idx] = this.head
	this.head = this.idx
	this.idx++
	this.size++
}

func (this *MyLinkedList) AddAtTail(val int) {
	this.AddAtIndex(this.size, val)
}

func (this *MyLinkedList) AddAtIndex(index int, val int) {
	if index > this.size {
		return
	}
	if index <= 0 {
		this.AddAtHead(val)
		return
	}
	i := this.head
	for ; index > 1; i, index = this.ne[i], index-1 {
	}
	this.e[this.idx] = val
	this.ne[this.idx] = this.ne[i]
	this.ne[i] = this.idx
	this.idx++
	this.size++
}

func (this *MyLinkedList) DeleteAtIndex(index int) {
	if index < 0 || index >= this.size {
		return
	}
	this.size--
	if index == 0 {
		this.head = this.ne[this.head]
		return
	}
	i := this.head
	for ; index > 1; i, index = this.ne[i], index-1 {
	}
	this.ne[i] = this.ne[this.ne[i]]
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