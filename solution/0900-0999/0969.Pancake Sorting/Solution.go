func pancakeSort(arr []int) []int {
	var ans []int
	n := len(arr)
	reverse := func(j int) {
		for i := 0; i < j; i, j = i+1, j-1 {
			arr[i], arr[j] = arr[j], arr[i]
		}
	}
	for i := n - 1; i > 0; i-- {
		j := i
		for ; j > 0 && arr[j] != i+1; j-- {
		}
		if j < i {
			if j > 0 {
				ans = append(ans, j+1)
				reverse(j)
			}
			ans = append(ans, i+1)
			reverse(i)
		}
	}
	return ans
}