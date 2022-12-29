type ExamRoom struct {
	rbt   *redblacktree.Tree
	left  map[int]int
	right map[int]int
	n     int
}

func Constructor(n int) ExamRoom {
	dist := func(s []int) int {
		if s[0] == -1 || s[1] == n {
			return s[1] - s[0] - 1
		}
		return (s[1] - s[0]) >> 1
	}
	cmp := func(a, b interface{}) int {
		x, y := a.([]int), b.([]int)
		d1, d2 := dist(x), dist(y)
		if d1 == d2 {
			return x[0] - y[0]
		}
		return d2 - d1
	}
	this := ExamRoom{redblacktree.NewWith(cmp), map[int]int{}, map[int]int{}, n}
	this.add([]int{-1, n})
	return this
}

func (this *ExamRoom) Seat() int {
	s := this.rbt.Left().Key.([]int)
	p := (s[0] + s[1]) >> 1
	if s[0] == -1 {
		p = 0
	} else if s[1] == this.n {
		p = this.n - 1
	}
	this.del(s)
	this.add([]int{s[0], p})
	this.add([]int{p, s[1]})
	return p
}

func (this *ExamRoom) Leave(p int) {
	l, _ := this.left[p]
	r, _ := this.right[p]
	this.del([]int{l, p})
	this.del([]int{p, r})
	this.add([]int{l, r})
}

func (this *ExamRoom) add(s []int) {
	this.rbt.Put(s, struct{}{})
	this.left[s[1]] = s[0]
	this.right[s[0]] = s[1]
}

func (this *ExamRoom) del(s []int) {
	this.rbt.Remove(s)
	delete(this.left, s[1])
	delete(this.right, s[0])
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Seat();
 * obj.Leave(p);
 */