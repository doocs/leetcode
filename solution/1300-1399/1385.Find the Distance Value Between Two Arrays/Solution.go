func findTheDistanceValue(arr1 []int, arr2 []int, d int) int {
	check := func(arr []int, a int) bool {
		for _, b := range arr {
			if -d <= a-b && a-b <= d {
				return false
			}
		}
		return true
	}

	ans := 0
	for _, a := range arr1 {
		if check(arr2, a) {
			ans++
		}
	}
	return ans
}