func minimumDifference(nums []int, k int) int {
	m := bits.Len(uint(slices.Max(nums)))
	cnt := make([]int, m)
	ans := math.MaxInt32
	s, i := -1, 0
	for j, x := range nums {
		s &= x
		ans = min(ans, abs(s-k))
		for h := 0; h < m; h++ {
			if x>>h&1 == 0 {
				cnt[h]++
			}
		}
		for i < j && s < k {
			y := nums[i]
			for h := 0; h < m; h++ {
				if y>>h&1 == 0 {
					cnt[h]--
					if cnt[h] == 0 {
						s |= 1 << h
					}
				}
			}
			ans = min(ans, abs(s-k))
			i++
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}