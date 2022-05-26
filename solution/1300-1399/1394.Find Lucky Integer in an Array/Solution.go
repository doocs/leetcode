func findLucky(arr []int) int {
	n := 510
	counter := make([]int, n)
	for _, e := range arr {
		counter[e]++
	}
	ans := -1
	for i := 1; i < n; i++ {
		if i == counter[i] && ans < i {
			ans = i
		}
	}
	return ans
}