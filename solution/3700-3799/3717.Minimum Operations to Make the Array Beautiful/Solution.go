func minOperations(nums []int) int {
	f := map[int]int{nums[0]: 0}

	for i := 1; i < len(nums); i++ {
		x := nums[i]
		g := make(map[int]int)
		for pre, s := range f {
			cur := (x + pre - 1) / pre * pre
			for cur <= 100 {
				val := s + (cur - x)
				if old, ok := g[cur]; !ok || old > val {
					g[cur] = val
				}
				cur += pre
			}
		}
		f = g
	}

	ans := math.MaxInt32
	for _, v := range f {
		ans = min(ans, v)
	}
	return ans
}
