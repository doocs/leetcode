func minOperations(nums []int) int {
	n := len(nums)

	zero := 0
	for i, x := range nums {
		if x == 0 {
			zero = i
			break
		}
	}

	check := func(step int) bool {
		for i := 1; i < n; i++ {
			prev := (zero + (i-1)*step + n) % n
			curr := (zero + i*step + n) % n

			if nums[prev] > nums[curr] {
				return false
			}
		}

		return true
	}

	ans := math.MaxInt

	if check(1) {
		ans = min(ans, zero)
		ans = min(ans, n-zero+2)
	}

	if check(-1) {
		ans = min(ans, zero+2)
		ans = min(ans, n-zero)
	}

	if ans == math.MaxInt {
		return -1
	}

	return ans
}