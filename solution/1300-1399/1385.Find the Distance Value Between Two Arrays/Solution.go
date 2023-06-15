func findTheDistanceValue(arr1 []int, arr2 []int, d int) (ans int) {
	sort.Ints(arr2)
	for _, a := range arr1 {
		i := sort.SearchInts(arr2, a-d)
		if i == len(arr2) || arr2[i] > a+d {
			ans++
		}
	}
	return
}