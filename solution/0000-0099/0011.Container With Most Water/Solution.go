func maxArea(height []int) (ans int) {
	i, j := 0, len(height)-1
	for i < j {
		t := min(height[i], height[j]) * (j - i)
		ans = max(ans, t)
		if height[i] < height[j] {
			i++
		} else {
			j--
		}
	}
	return
}