type Solution struct {
	s []int
}

func Constructor(w []int) Solution {
	n := len(w)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + w[i]
	}
	return Solution{s}
}

func (this *Solution) PickIndex() int {
	n := len(this.s)
	x := 1 + rand.Intn(this.s[n-1])
	left, right := 1, n-1
	for left < right {
		mid := (left + right) >> 1
		if this.s[mid] >= x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left - 1
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(w);
 * param_1 := obj.PickIndex();
 */