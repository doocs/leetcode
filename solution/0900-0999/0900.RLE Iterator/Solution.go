type RLEIterator struct {
	encoding []int
	curr     int
	i        int
}

func Constructor(encoding []int) RLEIterator {
	return RLEIterator{encoding: encoding, curr: 0, i: 0}
}

func (this *RLEIterator) Next(n int) int {
	for this.i < len(this.encoding) {
		if this.curr+n > this.encoding[this.i] {
			n -= this.encoding[this.i] - this.curr
			this.curr = 0
			this.i += 2
		} else {
			this.curr += n
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