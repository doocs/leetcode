func twoSum(nums []int, target int) []int {
	var numsMap map[int]int
	numsMap = make(map[int]int)
	for i := 0; i < len(nums); i++ {
		if _, ok := numsMap[target-nums[i]]; ok {

			return []int{numsMap[target-nums[i]], i}
		}
		numsMap[nums[i]] = i
	}
	return nil
}
