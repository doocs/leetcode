type MovingAverage struct {
	arr []int
	cnt int
	s   int
}

func Constructor(size int) MovingAverage {
	arr := make([]int, size)
	return MovingAverage{arr, 0, 0}
}

func (this *MovingAverage) Next(val int) float64 {
	idx := this.cnt % len(this.arr)
	this.s += val - this.arr[idx]
	this.arr[idx] = val
	this.cnt++
	return float64(this.s) / float64(min(this.cnt, len(this.arr)))
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * obj := Constructor(size);
 * param_1 := obj.Next(val);
 */