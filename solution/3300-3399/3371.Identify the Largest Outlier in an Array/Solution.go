func getLargestOutlier(nums []int) int {
	s := 0
	cnt := map[int]int{}
	for _, x := range nums {
		s += x
		cnt[x]++
	}
	ans := math.MinInt32
	for x, v := range cnt {
		t := s - x
		if t%2 != 0 || cnt[t/2] == 0 {
			continue
		}
		if x != t/2 || v > 1 {
			ans = max(ans, x)
		}
	}
	return ans
}
