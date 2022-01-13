func dominantIndex(nums []int) int {
	mx, mid := 0, 0
	ans := 0
	for i, v := range nums {
		if v > mx {
			mid, mx = mx, v
			ans = i
		} else if v > mid {
			mid = v
		}
	}
	if mx >= mid*2 {
		return ans
	}
	return -1
}