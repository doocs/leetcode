func mostFrequentEven(nums []int) int {
	cnt := map[int]int{}
	for _, v := range nums {
		if v%2 == 0 {
			cnt[v]++
		}
	}
	ans, mx := -1, 0
	for v, t := range cnt {
		if mx < t || (mx == t && ans > v) {
			mx = t
			ans = v
		}
	}
	return ans
}