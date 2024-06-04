func findRightInterval(intervals [][]int) (ans []int) {
	arr := make([][2]int, len(intervals))
	for i, v := range intervals {
		arr[i] = [2]int{v[0], i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] < arr[j][0] })
	for _, e := range intervals {
		j := sort.Search(len(arr), func(i int) bool { return arr[i][0] >= e[1] })
		if j < len(arr) {
			ans = append(ans, arr[j][1])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}