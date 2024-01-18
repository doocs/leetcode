func heightChecker(heights []int) int {
	cnt := make([]int, 101)
	for _, h := range heights {
		cnt[h]++
	}
	ans := 0
	for i, j := 0, 0; i < 101; i++ {
		for cnt[i] > 0 {
			cnt[i]--
			if heights[j] != i {
				ans++
			}
			j++
		}
	}
	return ans
}