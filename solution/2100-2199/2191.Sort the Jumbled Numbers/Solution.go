func sortJumbled(mapping []int, nums []int) (ans []int) {
	n := len(nums)
	arr := make([][2]int, n)
	for i, x := range nums {
		y := 0
		if x == 0 {
			y = mapping[0]
		}
		k := 1
		for ; x > 0; x /= 10 {
			y += k * mapping[x%10]
			k *= 10
		}
		arr[i] = [2]int{y, i}
	}
	sort.Slice(arr, func(i, j int) bool {
		a, b := arr[i], arr[j]
		return a[0] < b[0] || a[0] == b[0] && a[1] < b[1]
	})
	for _, x := range arr {
		ans = append(ans, nums[x[1]])
	}
	return
}