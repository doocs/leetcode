var d = [...]int{20, 50, 100, 200, 500}

const m = len(d)

type ATM [m]int

func Constructor() ATM {
	return ATM{}
}

func (this *ATM) Deposit(banknotesCount []int) {
	for i, x := range banknotesCount {
		this[i] += x
	}
}

func (this *ATM) Withdraw(amount int) []int {
	ans := make([]int, m)
	for i := m - 1; i >= 0; i-- {
		ans[i] = min(amount/d[i], this[i])
		amount -= ans[i] * d[i]
	}
	if amount > 0 {
		return []int{-1}
	}
	for i, x := range ans {
		this[i] -= x
	}
	return ans
}

/**
 * Your ATM object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Deposit(banknotesCount);
 * param_2 := obj.Withdraw(amount);
 */
