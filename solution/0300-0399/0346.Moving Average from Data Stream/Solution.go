type MovingAverage struct {
	size  int
	data  []int
	sum   int
	count int
}

/** Initialize your data structure here. */
func Constructor(size int) MovingAverage {
	return MovingAverage{
		size:  size,
		data:  make([]int, size),
		sum:   0,
		count: 0,
	}
}

func (this *MovingAverage) Next(val int) float64 {
	idx := this.count % this.size
	oldVal := this.data[idx]
	this.data[idx] = val
	this.sum += val - oldVal
	this.count++
	return float64(this.sum) / float64(min(this.count, this.size))
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