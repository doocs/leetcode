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
	ans := 1 << 30
	for i, x := range mins[1:] {
		ans = min(ans, x-mins[i])
	}
	return ans
}