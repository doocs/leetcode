func findLucky(arr []int) int {
	cnt := [501]int{}
	for _, x := range arr {
		cnt[x]++
	}
	for x := len(cnt) - 1; x > 0; x-- {
		if x == cnt[x] {
			return x
		}
	}
	return -1
}