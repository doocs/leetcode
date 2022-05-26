type StockSpanner struct {
	stk [][]int
}

func Constructor() StockSpanner {
	return StockSpanner{
		stk: make([][]int, 0),
	}
}

func (this *StockSpanner) Next(price int) int {
	res := 1
	for len(this.stk) > 0 && this.stk[len(this.stk)-1][0] <= price {
		t := this.stk[len(this.stk)-1]
		res += t[1]
		this.stk = this.stk[:len(this.stk)-1]
	}
	this.stk = append(this.stk, []int{price, res})
	return res
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Next(price);
 */