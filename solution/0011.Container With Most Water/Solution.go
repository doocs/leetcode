func maxArea(height []int) int {
	maxArea := 0
	i := 0
	j := len(height) - 1
	for i != j {
		hi := height[i]
		hj := height[j]
		maxArea = maxInt(maxArea, (j-i) * minInt(hi, hj))
		if hi >= hj {
			j--
		} else {
			i++
		}
	}
	return maxArea
}

func minInt(a, b int) int {
	if a >= b {
		return b
	}
	return a
}

func maxInt(a, b int) int {
	if a >= b {
		return a
	}
	return b
}