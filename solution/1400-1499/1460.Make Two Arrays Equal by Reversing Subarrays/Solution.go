func canBeEqual(target []int, arr []int) bool {
	sort.Ints(target)
	sort.Ints(arr)
	for i, v := range target {
		if v != arr[i] {
			return false
		}
	}
	return true
}