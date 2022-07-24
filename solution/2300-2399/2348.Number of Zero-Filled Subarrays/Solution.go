func zeroFilledSubarray(nums []int) int64 {
	ans := 0
	cnt := 0
	for _, v := range nums {
		if v == 0 {
			cnt++
		} else {
			ans += (1 + cnt) * cnt / 2
			cnt = 0
		}
	}
	ans += (1 + cnt) * cnt / 2
	return int64(ans)
}