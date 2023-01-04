type Cashier struct {
	i        int
	n        int
	discount int
	d        map[int]int
}

func Constructor(n int, discount int, products []int, prices []int) Cashier {
	d := map[int]int{}
	for i, product := range products {
		d[product] = prices[i]
	}
	return Cashier{0, n, discount, d}
}

func (this *Cashier) GetBill(product []int, amount []int) (ans float64) {
	this.i++
	dis := 0
	if this.i%this.n == 0 {
		dis = this.discount
	}
	for j, p := range product {
		x := float64(this.d[p] * amount[j])
		ans += x - (float64(dis)*x)/100.0
	}
	return
}

/**
 * Your Cashier object will be instantiated and called as such:
 * obj := Constructor(n, discount, products, prices);
 * param_1 := obj.GetBill(product,amount);
 */