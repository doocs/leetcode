func numberOfGoodPartitions(nums []int) int {
	qpow := func(a, n, mod int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	last := map[int]int{}
	for i, x := range nums {
		last[x] = i
	}
	const mod int = 1e9 + 7
	j, k := -1, 0
	for i, x := range nums {
		j = max(j, last[x])
		if i == j {
			k++
		}
	}
	return qpow(2, k-1, mod)
}