func earliestFullBloom(plantTime []int, growTime []int) int {
	arr := [][]int{}
	for i, a := range plantTime {
		arr = append(arr, []int{a, growTime[i]})
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][1] > arr[j][1]
	})
	ans, t := 0, 0
	for _, e := range arr {
		t += e[0]
		ans = max(ans, t+e[1])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}