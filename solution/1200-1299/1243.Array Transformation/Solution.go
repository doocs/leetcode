func transformArray(arr []int) []int {
	f := true
	for f {
		f = false
		t := make([]int, len(arr))
		copy(t, arr)
		for i := 1; i < len(arr)-1; i++ {
			if t[i] > t[i-1] && t[i] > t[i+1] {
				arr[i]--
				f = true
			}
			if t[i] < t[i-1] && t[i] < t[i+1] {
				arr[i]++
				f = true
			}
		}
	}
	return arr
}