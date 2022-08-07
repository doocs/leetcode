func taskSchedulerII(tasks []int, space int) int64 {
	mp := map[int]int64{}
	var ans int64
	for _, x := range tasks {
		ans++
		if v, ok := mp[x]; ok {
			ans = max(ans, v)
		}
		mp[x] = ans + int64(space) + 1
	}
	return ans
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}