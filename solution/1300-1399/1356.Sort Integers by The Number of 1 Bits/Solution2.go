func sortByBits(arr []int) []int {
	sort.Slice(arr, func(i, j int) bool {
		a, b := bits.OnesCount(uint(arr[i])), bits.OnesCount(uint(arr[j]))
		return a < b || (a == b && arr[i] < arr[j])
	})
	return arr
}