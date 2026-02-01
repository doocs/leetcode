func longestAlternating(nums []int) int {
	n := len(nums)
	l1 := make([]int, n)
	l2 := make([]int, n)
	r1 := make([]int, n)
	r2 := make([]int, n)

	for i := 0; i < n; i++ {
		l1[i] = 1
		l2[i] = 1
		r1[i] = 1
		r2[i] = 1
	}

	ans := 0

	for i := 1; i < n; i++ {
		if nums[i-1] < nums[i] {
			l1[i] = l2[i-1] + 1
		} else if nums[i-1] > nums[i] {
			l2[i] = l1[i-1] + 1
		}

		ans = max(ans, l1[i], l2[i])
	}

	for i := n - 2; i >= 0; i-- {
		if nums[i+1] > nums[i] {
			r1[i] = r2[i+1] + 1
		} else if nums[i+1] < nums[i] {
			r2[i] = r1[i+1] + 1
		}
	}

	for i := 1; i < n-1; i++ {
		if nums[i-1] < nums[i+1] {
			if l2[i-1]+r2[i+1] > ans {
				ans = l2[i-1] + r2[i+1]
			}
		} else if nums[i-1] > nums[i+1] {
			if l1[i-1]+r1[i+1] > ans {
				ans = l1[i-1] + r1[i+1]
			}
		}
	}

	return ans
}
