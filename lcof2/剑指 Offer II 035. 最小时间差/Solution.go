func findMinDifference(timePoints []string) int {
	if len(timePoints) > 24*60 {
		return 0
	}
	var mins []int
	for _, t := range timePoints {
		time := strings.Split(t, ":")
		h, _ := strconv.Atoi(time[0])
		m, _ := strconv.Atoi(time[1])
		mins = append(mins, h*60+m)
	}
	sort.Ints(mins)
	mins = append(mins, mins[0]+24*60)
	res := 24 * 60
	for i := 1; i < len(mins); i++ {
		res = min(res, mins[i]-mins[i-1])
	}
	return res
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}