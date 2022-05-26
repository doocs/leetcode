func sortTransformedArray(nums []int, a int, b int, c int) []int {
	n := len(nums)
	i, j, k := 0, n-1, 0
	if a >= 0 {
		k = n - 1
	}
	res := make([]int, n)
	for i <= j {
		v1, v2 := f(a, b, c, nums[i]), f(a, b, c, nums[j])
		if a < 0 {
			if v1 <= v2 {
				res[k] = v1
				i++
			} else {
				res[k] = v2
				j--
			}
			k++
		} else {
			if v1 >= v2 {
				res[k] = v1
				i++
			} else {
				res[k] = v2
				j--
			}
			k--
		}
	}
	return res
}

func f(a, b, c, x int) int {
	return a*x*x + b*x + c
}