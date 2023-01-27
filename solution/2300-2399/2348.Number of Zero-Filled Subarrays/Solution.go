func zeroFilledSubarray(nums []int) (ans int64) {
	cnt := 0
	for _, v := range nums {
		if v != 0 {
			cnt = 0
		} else {
			cnt++
		}
		ans += int64(cnt)
	}
	return
}