func relativeSortArray(arr1 []int, arr2 []int) []int {
	mp := make([]int, 1001)
	for _, x := range arr1 {
		mp[x]++
	}
	i := 0
	for _, x := range arr2 {
		for mp[x] > 0 {
			arr1[i] = x
			mp[x]--
			i++
		}
	}
	for j, cnt := range mp {
		for cnt > 0 {
			arr1[i] = j
			i++
			cnt--
		}
	}
	return arr1
}