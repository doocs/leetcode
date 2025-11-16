func countStableSubarrays(nums []int, queries [][]int) []int64 {
	n := len(nums)
	seg := []int{}
	s := []int64{0}

	l := 0
	for r := 0; r < n; r++ {
		if r == n-1 || nums[r] > nums[r+1] {
			seg = append(seg, l)
			k := int64(r - l + 1)
			s = append(s, s[len(s)-1]+k*(k+1)/2)
			l = r + 1
		}
	}

	ans := make([]int64, len(queries))
	for idx, q := range queries {
		left, right := q[0], q[1]

		i := sort.SearchInts(seg, left+1)
		j := sort.SearchInts(seg, right+1) - 1

		if i > j {
			k := int64(right - left + 1)
			ans[idx] = k * (k + 1) / 2
		} else {
			a := int64(seg[i] - left)
			b := int64(right - seg[j] + 1)
			ans[idx] = a*(a+1)/2 + s[j] - s[i] + b*(b+1)/2
		}
	}

	return ans
}
