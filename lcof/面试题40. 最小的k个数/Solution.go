func getLeastNumbers(arr []int, k int) []int {
	sort.Ints(arr)
	return arr[:k]
}