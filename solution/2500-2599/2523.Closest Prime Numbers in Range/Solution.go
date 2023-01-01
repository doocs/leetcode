func closestPrimes(left int, right int) []int {
	cnt := 0
	st := make([]bool, right+1)
	prime := make([]int, right+1)
	for i := 2; i <= right; i++ {
		if !st[i] {
			prime[cnt] = i
			cnt++
		}
		for j := 0; prime[j] <= right/i; j++ {
			st[prime[j]*i] = true
			if i%prime[j] == 0 {
				break
			}
		}
	}
	i, j := -1, -1
	for k := 0; k < cnt; k++ {
		if prime[k] >= left && prime[k] <= right {
			if i == -1 {
				i = k
			}
			j = k
		}
	}
	ans := []int{-1, -1}
	if i == j || i == -1 {
		return ans
	}
	mi := 1 << 30
	for k := i; k < j; k++ {
		d := prime[k+1] - prime[k]
		if d < mi {
			mi = d
			ans[0], ans[1] = prime[k], prime[k+1]
		}
	}
	return ans
}