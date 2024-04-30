func numsGame(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	finder := newMedianFinder()
	for i, x := range nums {
		finder.AddNum(x - i)
		ans[i] = finder.Cal()
	}
	return ans
}

type MedianFinder struct {
	q1 hp
	q2 hp
	s1 int
	s2 int
}

func newMedianFinder() *MedianFinder {
	return &MedianFinder{hp{}, hp{}, 0, 0}
}

func (this *MedianFinder) AddNum(num int) {
	heap.Push(&this.q1, num)
	this.s1 += num
	num = heap.Pop(&this.q1).(int)
	heap.Push(&this.q2, -num)
	this.s1 -= num
	this.s2 += num
	if this.q2.Len()-this.q1.Len() > 1 {
		num = -heap.Pop(&this.q2).(int)
		heap.Push(&this.q1, num)
		this.s1 += num
		this.s2 -= num
	}
}

func (this *MedianFinder) FindMedian() int {
	if this.q2.Len() > this.q1.Len() {
		return -this.q2.IntSlice[0]
	}
	return (this.q1.IntSlice[0] - this.q2.IntSlice[0]) / 2
}

func (this *MedianFinder) Cal() int {
	x := this.FindMedian()
	return (this.s1 - x*this.q1.Len() + x*this.q2.Len() - this.s2) % 1000000007
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}