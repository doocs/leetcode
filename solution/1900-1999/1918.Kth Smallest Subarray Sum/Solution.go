func kthSmallestSubarraySum(nums []int, k int) int {
	l, r := 1<<30, 0
	for _, x := range nums {
		l = min(l, x)
		r += x
	}
	f := func(s int) (cnt int) {
		t := 0
		for i, j := 0, 0; i < len(nums); i++ {
			t += nums[i]
			for t > s {
				t -= nums[j]
				j++
			}
			cnt += i - j + 1
		}
		return
	}
	for l < r {
		mid := (l + r) >> 1
		if f(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}