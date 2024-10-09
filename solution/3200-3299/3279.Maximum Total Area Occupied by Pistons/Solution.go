func maxArea(height int, positions []int, directions string) int64 {
	delta := make(map[int]int)
	diff := 0
	var res int64 = 0
	for i, pos := range positions {
		dir := directions[i]
		res += int64(pos)

		if dir == 'U' {
			diff++
			delta[height-pos] -= 2
			delta[height*2-pos] += 2
		} else {
			diff--
			delta[pos] += 2
			delta[height+pos] -= 2
		}
	}
	ans := res
	pre := 0
	keys := make([]int, 0, len(delta))
	for key := range delta {
		keys = append(keys, key)
	}
	sort.Ints(keys)
	for _, cur := range keys {
		d := delta[cur]
		res += int64(cur-pre) * int64(diff)
		pre = cur
		diff += d
		ans = max(ans, res)
	}
	return ans
}
