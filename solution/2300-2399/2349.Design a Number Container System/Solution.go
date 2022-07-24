type NumberContainers struct {
	mp map[int]int
	t  map[int]*redblacktree.Tree
}

func Constructor() NumberContainers {
	return NumberContainers{map[int]int{}, map[int]*redblacktree.Tree{}}
}

func (this *NumberContainers) Change(index int, number int) {
	if num, ok := this.mp[index]; ok {
		this.t[num].Remove(index)
	}
	this.mp[index] = number
	if this.t[number] == nil {
		this.t[number] = redblacktree.NewWithIntComparator()
	}
	this.t[number].Put(index, nil)
}

func (this *NumberContainers) Find(number int) int {
	s, ok := this.t[number]
	if !ok || s.Size() == 0 {
		return -1
	}
	return s.Left().Key.(int)
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Change(index,number);
 * param_2 := obj.Find(number);
 */