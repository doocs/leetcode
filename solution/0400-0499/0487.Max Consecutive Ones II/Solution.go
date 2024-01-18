func findMaxConsecutiveOnes(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i, v := range nums {
		if v == 1 {
			if i == 0 {
				left[i] = 1
			} else {
				left[i] = left[i-1] + 1
			}
		}
	}
	for i := n - 1; i >= 0; i-- {
		if nums[i] == 1 {
			if i == n-1 {
				right[i] = 1
			} else {
				right[i] = right[i+1] + 1
			}
		}
	}
	ans := 0
	for i := range nums {
		t := 0
		if i > 0 {
			t += left[i-1]
		}
		if i < n-1 {
			t += right[i+1]
		}
		ans = max(ans, t+1)
	}
	return ans
}