func arraysIntersection(arr1 []int, arr2 []int, arr3 []int) (ans []int) {
	for _, x := range arr1 {
		i := sort.SearchInts(arr2, x)
		j := sort.SearchInts(arr3, x)
		if i < len(arr2) && j < len(arr3) && arr2[i] == x && arr3[j] == x {
			ans = append(ans, x)
		}
	}
	return
}