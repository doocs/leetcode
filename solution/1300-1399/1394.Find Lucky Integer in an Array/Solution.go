func findLucky(arr []int) int {
	cnt := [510]int{}
	for _, x := range arr {
		cnt[x]++
	}
	ans := -1
	for x := 1; x < len(cnt); x++ {
		if cnt[x] == x {
			ans = x
		}
	}
	return ans
}