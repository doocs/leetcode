func findTheDistanceValue(arr1 []int, arr2 []int, d int) int {
	res := 0
	for _, a := range arr1 {
		exist := false
		for _, b := range arr2 {
			if math.Abs(float64(a-b)) <= float64(d) {
				exist = true
				break
			}
		}
		if !exist {
			res++
		}
	}
	return res
}