func minMaxSubarraySum(nums []int, k int) int64 {
	var total, windowMax, windowMin int64
	type item struct {
		idx, num int
		shares   int64
	}
	maxStack, minStack := []item{}, []item{}
	for end := 0; end < len(nums); end++ {
		start := end - k + 1
		if start < 0 {
			start = 0
		}
		if start > 0 {
			maxStack[0].shares--
			windowMax -= int64(maxStack[0].num)
			if maxStack[0].idx < start {
				maxStack = maxStack[1:]
			}
			minStack[0].shares--
			windowMin -= int64(minStack[0].num)
			if minStack[0].idx < start {
				minStack = minStack[1:]
			}
		}
		num := int64(nums[end])
		maxShares := int64(1)
		windowMax += num
		for len(maxStack) > 0 && int64(maxStack[len(maxStack)-1].num) <= num {
			prev := maxStack[len(maxStack)-1]
			maxStack = maxStack[:len(maxStack)-1]
			maxShares += prev.shares
			windowMax += (num - int64(prev.num)) * prev.shares
		}
		maxStack = append(maxStack, item{end, nums[end], maxShares})
		minShares := int64(1)
		windowMin += num
		for len(minStack) > 0 && int64(minStack[len(minStack)-1].num) >= num {
			prev := minStack[len(minStack)-1]
			minStack = minStack[:len(minStack)-1]
			minShares += prev.shares
			windowMin += (num - int64(prev.num)) * prev.shares
		}
		minStack = append(minStack, item{end, nums[end], minShares})
		total += windowMax + windowMin
	}
	return total
}
