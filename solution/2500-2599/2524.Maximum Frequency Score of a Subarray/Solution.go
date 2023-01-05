func maxFrequencyScore(nums []int, k int) int {
	cnt := map[int]int{}
	for _, v := range nums[:k] {
		cnt[v]++
	}
	cur := 0
	const mod int = 1e9 + 7
	for k, v := range cnt {
		cur = (cur + qmi(k, v, mod)) % mod
	}
	ans := cur
	for i := k; i < len(nums); i++ {
		a, b := nums[i-k], nums[i]
		if a != b {
			if cnt[b] > 0 {
				cur += (b - 1) * qmi(b, cnt[b], mod) % mod
			} else {
				cur += b
			}
			if cnt[a] > 1 {
				cur -= (a - 1) * qmi(a, cnt[a]-1, mod) % mod
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

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}