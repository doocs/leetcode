func minAbsoluteSumDiff(nums1 []int, nums2 []int) int {
	mod := int(1e9) + 7
	n := len(nums1)
	diff := make([]int, n)
	s := 0
	for i := 0; i < n; i++ {
		diff[i] = abs(nums1[i] - nums2[i])
		s = (s + diff[i]) % mod
	}
	if s == 0 {
		return 0
	}
	sort.Ints(nums1)
	mx := 0
	for i, b := range nums2 {
		d := diff[i]
		if d == 0 {
			continue
		}
		idx := search(nums1, b)
		a1, a2 := 1000000, 1000000
		if idx != n {
			a1 = nums1[idx]
		}
		if idx != 0 {
			a2 = nums1[idx-1]
		}
		c := min(abs(b-a1), abs(b-a2))
		mx = max(mx, d-c)
	}
	return (s - mx + mod) % mod
}

func search(nums []int, x int) int {
	left, right := 0, len(nums)
	for left < right {
		mid := (left + right) >> 1
		if nums[mid] >= x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}