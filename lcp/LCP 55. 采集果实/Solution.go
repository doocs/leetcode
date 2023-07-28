func getMinimumTime(time []int, fruits [][]int, limit int) (ans int) {
	for _, f := range fruits {
		i, num := f[0], f[1]
		ans += (num + limit - 1) / limit * time[i]
	}
	return
}