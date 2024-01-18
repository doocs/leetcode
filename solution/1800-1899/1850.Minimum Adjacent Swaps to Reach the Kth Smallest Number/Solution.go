func getMinSwaps(num string, k int) (ans int) {
	s := []byte(num)
	for ; k > 0; k-- {
		nextPermutation(s)
	}
	d := [10][]int{}
	for i, c := range num {
		j := int(c - '0')
		d[j] = append(d[j], i)
	}
	idx := [10]int{}
	n := len(s)
	arr := make([]int, n)
	for i, c := range s {
		j := int(c - '0')
		arr[i] = d[j][idx[j]]
		idx[j]++
	}
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			if arr[j] > arr[i] {
				ans++
			}
		}
	}
	return
}

func nextPermutation(nums []byte) bool {
	n := len(nums)
	i := n - 2
	for i >= 0 && nums[i] >= nums[i+1] {
		i--
	}
	if i < 0 {
		return false
	}
	j := n - 1
	for j >= 0 && nums[j] <= nums[i] {
		j--
	}
	nums[i], nums[j] = nums[j], nums[i]
	for i, j = i+1, n-1; i < j; i, j = i+1, j-1 {
		nums[i], nums[j] = nums[j], nums[i]
	}
	return true
}