func findShortestSubArray(nums []int) (ans int) {
	ans = 50000
	numsMap := make(map[int]int, len(nums))
	for _, num := range nums {
		numsMap[num]++
	}
	var maxDegree int
	for _, num := range numsMap {
		maxDegree = max(num, maxDegree)
	}
	degreeNums := getMaxDegreeElem(maxDegree, numsMap)
	for _, num := range degreeNums {
		f := findSubArray(num, nums)
		ans = min(ans, f)
	}
	return
}

func findSubArray(target int, nums []int) int {
	start := getStartIdx(target, nums)
	end := getEndIdx(target, nums)
	return (end - start) + 1
}

func getStartIdx(target int, nums []int) (start int) {
	for idx, num := range nums {
		if num == target {
			start = idx
			break
		}
	}
	return start
}

func getEndIdx(target int, nums []int) (end int) {
	for i := len(nums) - 1; i > 0; i-- {
		if nums[i] == target {
			end = i
			break
		}
	}
	return
}

func getMaxDegreeElem(maxDegree int, numsMap map[int]int) []int {
	var ans []int
	for key, value := range numsMap {
		if value == maxDegree {
			ans = append(ans, key)
		}
	}
	return ans
}

func min(a, b int) int {
	if a > b {
		return b
	}
	return a
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
