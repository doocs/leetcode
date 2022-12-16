func subsequenceSumOr(nums []int) int64 {
	cnt := make([]int, 64)
	ans := 0
	for _, v := range nums {
		for i := 0; i < 31; i++ {
			if v>>i&1 == 1 {
				cnt[i]++
			}
		}
	}
	for i := 0; i < 63; i++ {
		if cnt[i] > 0 {
			ans |= 1 << i
		}
		cnt[i+1] += cnt[i] / 2
	}
	return int64(ans)
}