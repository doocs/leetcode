func minimumTime(time []int, totalTrips int) int64 {
	left, right := 1, 10000000*totalTrips
	for left < right {
		mid := (left + right) >> 1
		cnt := 0
		for _, v := range time {
			cnt += mid / v
		}
		if cnt >= totalTrips {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return int64(left)
}