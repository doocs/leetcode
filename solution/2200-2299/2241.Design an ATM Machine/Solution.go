type ATM struct {
	d   [5]int
	cnt [5]int
}

func Constructor() ATM {
	return ATM{[5]int{20, 50, 100, 200, 500}, [5]int{}}
}

func (this *ATM) Deposit(banknotesCount []int) {
	for i, v := range banknotesCount {
		this.cnt[i] += v
	}
}

func (this *ATM) Withdraw(amount int) []int {
	ans := make([]int, 5)
	for i := 4; i >= 0; i-- {
		ans[i] = min(amount/this.d[i], this.cnt[i])
		amount -= ans[i] * this.d[i]
	}
	if amount > 0 {
		return []int{-1}
	}
	for i, v := range ans {
		this.cnt[i] -= v
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

/**
 * Your ATM object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Deposit(banknotesCount);
 * param_2 := obj.Withdraw(amount);
 */