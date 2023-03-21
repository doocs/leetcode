func pourWater(heights []int, volume int, k int) []int {
	for ; volume > 0; volume-- {
		find := false
		for _, d := range [2]int{-1, 1} {
			i, j := k, k
			for i+d >= 0 && i+d < len(heights) && heights[i+d] <= heights[i] {
				if heights[i+d] < heights[i] {
					j = i + d
				}
				i += d
			}
			if j != k {
				find = true
				heights[j]++
				break
			}
		}
		if !find {
			heights[k]++
		}
	}
	return heights
}