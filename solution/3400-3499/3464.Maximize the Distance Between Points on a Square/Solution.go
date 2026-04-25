func maxDistance(side int, points [][]int, k int) int {
	nums := make([]int64, 0, len(points))
	for _, p := range points {
		x, y := int64(p[0]), int64(p[1])
		s := int64(side)
		if x == 0 {
			nums = append(nums, y)
		} else if y == s {
			nums = append(nums, s+x)
		} else if x == s {
			nums = append(nums, s*3-y)
		} else {
			nums = append(nums, s*4-x)
		}
	}
	sort.Slice(nums, func(i, j int) bool {
		return nums[i] < nums[j]
	})

	check := func(lo int) bool {
		total := int64(side) * 4
		l64 := int64(lo)
		for _, start := range nums {
			end := start + total - l64
			cur := start
			ok := true
			for i := 0; i < k-1; i++ {
				target := cur + l64
				idx := sort.Search(len(nums), func(i int) bool {
					return nums[i] >= target
				})
				if idx == len(nums) || nums[idx] > end {
					ok = false
					break
				}
				cur = nums[idx]
			}
			if ok {
				return true
			}
		}
		return false
	}

	l, r := 1, side
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
