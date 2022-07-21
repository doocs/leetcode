func minSumSquareDiff(nums1 []int, nums2 []int, k1 int, k2 int) int64 {
	k := k1 + k2
	s, mx := 0, 0
	n := len(nums1)
	d := make([]int, n)
	for i, v := range nums1 {
		d[i] = abs(v - nums2[i])
		s += d[i]
		mx = max(mx, d[i])
	}
	if s <= k {
		return 0
	}
	left, right := 0, mx
	for left < right {
		mid := (left + right) >> 1
		t := 0
		for _, v := range d {
			t += max(v-mid, 0)
		}
		if t <= k {
			right = mid
		} else {
			left = mid + 1
		}
	}
	for i, v := range d {
		k -= max(v-left, 0)
		d[i] = min(v, left)
	}
	for i, v := range d {
		if k <= 0 {
			break
		}
		if v == left {
			d[i]--
			k--
		}
	}
	ans := 0
	for _, v := range d {
		ans += v * v
	}
	return int64(ans)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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