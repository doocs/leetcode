func minimumCost(nums []int, k int) int {
	const mod int64 = 1_000_000_007
	var cnt int64
	cur := int64(k)

	for _, x := range nums {
		diff := int64(x) - cur
		if diff > 0 {
			m := (diff + int64(k) - 1) / int64(k)
			cur += m * int64(k)
			cnt += m
		}
		cur -= int64(x)
	}

	cnt %= mod
	return int((cnt + 1) * cnt / 2 % mod)
}
