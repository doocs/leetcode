func maxCaloriesBurnt(heights []int) (ans int64) {
	sort.Ints(heights)
	pre := 0
	l, r := 0, len(heights)-1
	for l < r {
		ans += int64(heights[r]-pre) * int64(heights[r]-pre)
		ans += int64(heights[l]-heights[r]) * int64(heights[l]-heights[r])
		pre = heights[l]
		l++
		r--
	}
	ans += int64(heights[r]-pre) * int64(heights[r]-pre)
	return
}
