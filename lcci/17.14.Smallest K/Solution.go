func smallestK(arr []int, k int) []int {
	sort.Ints(arr)
	ans := make([]int, k)
	for i, v := range arr[:k] {
		ans[i] = v
	}
	return ans
}