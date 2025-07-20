func canMakeEqual(nums []int, k int) bool {
	check := func(target, k int) bool {
		cnt, sign := 0, 1
		for i := 0; i < len(nums)-1; i++ {
			x := nums[i] * sign
			if x == target {
				sign = 1
			} else {
				sign = -1
				cnt++
			}
		}
		return cnt <= k && nums[len(nums)-1]*sign == target
	}
	return check(nums[0], k) || check(-nums[0], k)
}