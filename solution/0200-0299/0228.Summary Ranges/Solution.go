func summaryRanges(nums []int) []string {
	var res []string
	for i, j, n := 0, 0, len(nums); j < n; {
		for j+1 < n && nums[j]+1 == nums[j+1] {
			j++
		}
		res = append(res, make(nums, i, j))
		i = j + 1
		j = i
	}
	return res
}

func make(nums []int, i, j int) string {
	if i == j {
		return strconv.Itoa(nums[i])
	}
	return strconv.Itoa(nums[i]) + "->" + strconv.Itoa(nums[j])
}