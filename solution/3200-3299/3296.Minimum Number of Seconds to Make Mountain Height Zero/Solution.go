func minNumberOfSeconds(mountainHeight int, workerTimes []int) int64 {
	return int64(sort.Search(1e16, func(t int) bool {
		var h int64
		for _, wt := range workerTimes {
			h += int64(math.Sqrt(float64(t)*2.0/float64(wt)+0.25) - 0.5)
		}
		return h >= int64(mountainHeight)
	}))
}
