func zeroFilledSubarray(nums []int) (ans int64) {
	cnt := 0
	for _, x := range nums {
		if x == 0 {
			cnt++
			ans += int64(cnt)
		} else {
			cnt = 0
		}
	}
	return
}
