func resilientSubarray(nums []int, k int) int {
	ans, cnt := 0, 0
	for i, v := range nums {
		x := v % k
		if i > 0 && nums[i-1]%k == x {
			cnt++
		} else {
			cnt = 1
		}
		if int64(x)*int64(cnt-1)%int64(k) == 0 {
			ans = max(ans, cnt)
		}
	}
	return ans
}
