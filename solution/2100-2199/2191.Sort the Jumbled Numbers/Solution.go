func sortJumbled(mapping []int, nums []int) (ans []int) {
	n := len(nums)
	f := func(x int) (y int) {
		if x == 0 {
			return mapping[0]
		}
		for k := 1; x > 0; x /= 10 {
			v := mapping[x%10]
			y = k*v + y
			k *= 10
		}
		return
	}
	arr := make([][2]int, n)
	for i, x := range nums {
		arr[i] = [2]int{f(x), i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] < arr[j][0] || arr[i][0] == arr[j][0] && arr[i][1] < arr[j][1] })
	for _, p := range arr {
		ans = append(ans, nums[p[1]])
	}
	return
}