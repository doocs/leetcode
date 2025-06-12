type ZigzagIterator struct {
	cur     int
	size    int
	indexes []int
	vectors [][]int
}

func Constructor(v1 []int, v2 []int) *ZigzagIterator {
	return &ZigzagIterator{
		cur:     0,
		size:    2,
		indexes: []int{0, 0},
		vectors: [][]int{v1, v2},
	}
}

func (this *ZigzagIterator) Next() int {
	vector := this.vectors[this.cur]
	index := this.indexes[this.cur]
	res := vector[index]
	this.indexes[this.cur]++
	this.cur = (this.cur + 1) % this.size
	return res
}

func (this *ZigzagIterator) HasNext() bool {
	start := this.cur
	for this.indexes[this.cur] == len(this.vectors[this.cur]) {
		this.cur = (this.cur + 1) % this.size
		if start == this.cur {
			return false
		}
	}
	return true
}
