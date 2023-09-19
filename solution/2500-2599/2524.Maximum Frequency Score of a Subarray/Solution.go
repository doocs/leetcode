func maxFrequencyScore(nums []int, k int) int {
	cnt := map[int]int{}
	for _, v := range nums[:k] {
		cnt[v]++
	}
	cur := 0
	const mod int = 1e9 + 7
	qpow := func(a, n int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	for k, v := range cnt {
		cur = (cur + qpow(k, v)) % mod
	}
	ans := cur
	for i := k; i < len(nums); i++ {
		a, b := nums[i-k], nums[i]
		if a != b {
			if cnt[b] > 0 {
				cur += (b - 1) * qpow(b, cnt[b]) % mod
			} else {
				cur += b
			}
			if cnt[a] > 1 {
				cur -= (a - 1) * qpow(a, cnt[a]-1) % mod
			} else {
				cur -= a
			}
			cur = (cur + mod) % mod
			ans = max(ans, cur)
			cnt[b]++
			cnt[a]--
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}