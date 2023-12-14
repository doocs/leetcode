func subsets(nums []int) [][]int {
	var res [][]int
	var t []int
	dfs(0, nums, t, &res)
	return res
}

func dfs(i int, nums, t []int, res *[][]int) {
	*res = append(*res, slices.Clone(t))
	if i == len(nums) {
		return
	}
	for j := i; j < len(nums); j++ {
		t = append(t, nums[j])
		dfs(j+1, nums, t, res)
		t = t[:len(t)-1]
	}
}