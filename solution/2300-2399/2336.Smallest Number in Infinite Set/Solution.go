type SmallestInfiniteSet struct {
	black map[int]bool
}

func Constructor() SmallestInfiniteSet {
	s := map[int]bool{}
	return SmallestInfiniteSet{s}
}

func (this *SmallestInfiniteSet) PopSmallest() int {
	i := 1
	for ; this.black[i]; i++ {
	}
	this.black[i] = true
	return i
}

func (this *SmallestInfiniteSet) AddBack(num int) {
	this.black[num] = false
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.PopSmallest();
 * obj.AddBack(num);
 */