func findMagicIndex(nums []int) int {
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return -1
		}
		mid := (i + j) >> 1
		if l := dfs(i, mid-1); l != -1 {
			return l
		}
		if nums[mid] == mid {
			return mid
		}
		return dfs(mid+1, j)
	}
	return dfs(0, len(nums)-1)
}