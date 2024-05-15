func minimumTime(time []int, totalTrips int) int64 {
	mx := slices.Min(time) * totalTrips
	return int64(sort.Search(mx, func(x int) bool {
		cnt := 0
		for _, v := range time {
			cnt += x / v
		}
		return cnt >= totalTrips
	}))
}