func findShortestSubArray(nums []int) int {
	cnt := map[int]int{}
	left := map[int]int{}
	right := map[int]int{}
	var degree int
	for i, v := range nums {
		cnt[v]++
		if degree < cnt[v] {
			degree = cnt[v]
		}
		if _, ok := left[v]; !ok {
			left[v] = i
		}
		right[v] = i
	}
	ans := 100000
	for v, c := range cnt {
		if c == degree {
			t := right[v] - left[v] + 1
			if ans > t {
				ans = t
			}
		}
	}
	return ans
}