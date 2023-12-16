type RLEIterator struct {
	encoding []int
	i, j     int
}

func Constructor(encoding []int) RLEIterator {
	return RLEIterator{encoding, 0, 0}
}

func (this *RLEIterator) Next(n int) int {
	for this.i < len(this.encoding) {
		if this.encoding[this.i]-this.j < n {
			n -= (this.encoding[this.i] - this.j)
			this.i += 2
			this.j = 0
		} else {
			this.j += n
			return this.encoding[this.i+1]
		}
	}
	return -1
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * obj := Constructor(encoding);
 * param_1 := obj.Next(n);
 */