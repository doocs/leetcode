func findClosestElements(arr []int, k int, x int) []int {
	sort.Slice(arr, func(i, j int) bool {
		v := abs(arr[i]-x) - abs(arr[j]-x)
		if v == 0 {
			return arr[i] < arr[j]
		}
		return v < 0
	})
	ans := arr[:k]
	sort.Ints(ans)
	return ans
}

func abs(x int) int {
	if x >= 0 {
		return x
	}
	return -x
}