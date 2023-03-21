func addNegabinary(arr1 []int, arr2 []int) (ans []int) {
	i, j := len(arr1)-1, len(arr2)-1
	for c := 0; i >= 0 || j >= 0 || c != 0; i, j = i-1, j-1 {
		x := c
		if i >= 0 {
			x += arr1[i]
		}
		if j >= 0 {
			x += arr2[j]
		}
		c = 0
		if x > 1 {
			x -= 2
			c -= 1
		}
		if x < 0 {
			x += 2
			c += 1
		}
		ans = append(ans, x)
	}
	for len(ans) > 1 && ans[len(ans)-1] == 0 {
		ans = ans[:len(ans)-1]
	}
	for i, j = 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return ans
}