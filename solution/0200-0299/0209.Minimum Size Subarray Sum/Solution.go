func minSubArrayLen(target int, nums []int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	ans := n + 1
	for i, v := range s {
		t := v + target
		left, right := 0, n+1
		for left < right {
			mid := (left + right) >> 1
			if s[mid] >= t {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if left != n+1 && ans > left-i {
			ans = left - i
		}
	}
	if ans == n+1 {
		return 0
	}
	return ans
}