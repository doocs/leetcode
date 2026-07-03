type Cashier struct {
	i        int
	n        int
	discount int
	d        map[int]int
}

func Constructor(n int, discount int, products []int, prices []int) Cashier {
	d := make(map[int]int)
	for i := 0; i < len(products); i++ {
		d[products[i]] = prices[i]
	}
	return Cashier{i: 0, n: n, discount: discount, d: d}
}

func (this *Cashier) GetBill(product []int, amount []int) float64 {
	this.i = (this.i + 1) % this.n
	x := 0
	for i := 0; i < len(product); i++ {
		x += this.d[product[i]] * amount[i]
	}
	if this.i == 0 {
		return float64(x) - float64(this.discount)*float64(x)/100.0
	}
	return float64(x)
}

/**
 * Your Cashier object will be instantiated and called as such:
 * obj := Constructor(n, discount, products, prices);
 * param_1 := obj.GetBill(product,amount);
 */
