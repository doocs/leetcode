func intersection(nums [][]int) []int {
	cnt := make([]int, 1001)
	for _, num := range nums {
		for _, v := range num {
			cnt[v]++
		}
	}
	var ans []int
	for i, v := range cnt {
		if v == len(nums) {
			ans = append(ans, i)
		}
	}
	return ans
}