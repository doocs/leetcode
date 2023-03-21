func arraysIntersection(arr1 []int, arr2 []int, arr3 []int) (ans []int) {
	cnt := [2001]int{}
	for _, x := range arr1 {
		cnt[x]++
	}
	for _, x := range arr2 {
		cnt[x]++
	}
	for _, x := range arr3 {
		cnt[x]++
		if cnt[x] == 3 {
			ans = append(ans, x)
		}
	}
	return
}