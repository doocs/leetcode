type MovingAverage struct {
	s    int
	cnt  int
	data []int
}

func Constructor(size int) MovingAverage {
	return MovingAverage{
		data: make([]int, size),
	}
}

func (this *MovingAverage) Next(val int) float64 {
	i := this.cnt % len(this.data)
	this.s += val - this.data[i]
	this.data[i] = val
	this.cnt++
	return float64(this.s) / float64(min(this.cnt, len(this.data)))
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * obj := Constructor(size);
 * param_1 := obj.Next(val);
 */