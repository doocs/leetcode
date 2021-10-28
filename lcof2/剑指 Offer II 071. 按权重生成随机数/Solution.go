type Solution struct {
	presum []int
}

func Constructor(w []int) Solution {
	n := len(w)
	pre := make([]int, n+1)
	for i := 0; i < n; i++ {
		pre[i+1] = pre[i] + w[i]
	}
	return Solution{pre}
}

func (this *Solution) PickIndex() int {
	n := len(this.presum)
	x := rand.Intn(this.presum[n-1]) + 1
	left, right := 0, n-2
	for left < right {
		mid := (left + right) >> 1
		if this.presum[mid+1] >= x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(w);
 * param_1 := obj.PickIndex();
 */