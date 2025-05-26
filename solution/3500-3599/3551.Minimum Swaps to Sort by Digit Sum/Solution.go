func minSwaps(nums []int) int {
	n := len(nums)
	arr := make([][2]int, n)
	for i := 0; i < n; i++ {
		arr[i][0] = f(nums[i])
		arr[i][1] = nums[i]
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] != arr[j][0] {
			return arr[i][0] < arr[j][0]
		}
		return arr[i][1] < arr[j][1]
	})
	d := make(map[int]int, n)
	for i := 0; i < n; i++ {
		d[arr[i][1]] = i
	}
	vis := make([]bool, n)
	ans := n
	for i := 0; i < n; i++ {
		if !vis[i] {
			ans--
			j := i
			for !vis[j] {
				vis[j] = true
				j = d[nums[j]]
			}
		}
	}
	return ans
}

func f(x int) int {
	s := 0
	for x != 0 {
		s += x % 10
		x /= 10
	}
	return s
}