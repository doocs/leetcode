func findMaxConsecutiveOnes(nums []int) (ans int) {
	cnt := 0
	for _, x := range nums {
		if x == 1 {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 0
		}
	}
	return
}