type NumberContainers struct {
	d map[int]int
	g map[int]*redblacktree.Tree
}

func Constructor() NumberContainers {
	return NumberContainers{map[int]int{}, map[int]*redblacktree.Tree{}}
}

func (this *NumberContainers) Change(index int, number int) {
	if oldNumber, ok := this.d[index]; ok {
		this.g[oldNumber].Remove(index)
	}
	this.d[index] = number
	if _, ok := this.g[number]; !ok {
		this.g[number] = redblacktree.NewWithIntComparator()
	}
	this.g[number].Put(index, nil)
}

func (this *NumberContainers) Find(number int) int {
	if ids, ok := this.g[number]; ok && ids.Size() > 0 {
		return ids.Left().Key.(int)
	}
	return -1
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Change(index,number);
 * param_2 := obj.Find(number);
 */