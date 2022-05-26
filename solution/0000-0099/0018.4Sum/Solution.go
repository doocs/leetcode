import "strconv"

type Entry struct {
	a1, a2, a3, a4 int
}

func(e *Entry) String() string {
	return strconv.FormatInt(int64(e.a1), 10) +
		"," + strconv.FormatInt(int64(e.a2), 10) +
		"," + strconv.FormatInt(int64(e.a3), 10) +
		"," + strconv.FormatInt(int64(e.a4), 10)
}

func fourSum(nums []int, target int) [][]int {
	flag := make(map[string]bool)
	sort(nums)
	result := make([][]int, 0)
	begin := 0
	lenNums := len(nums) - 1
	for begin + 2 < lenNums {
		left := begin + 1
		right := lenNums
		lt := target - nums[begin]
		for left + 1 < right {
			i := left + 1
			j := right
			subTarget := lt - nums[left]
			for i < j {
				if nums[i] + nums[j] > subTarget {
					j--
				} else if nums[i] + nums[j] < subTarget {
					i++
				} else {
					entry := &Entry{
						a1:nums[begin],
						a2:nums[left],
						a3:nums[i],
						a4:nums[j],
					}
					if !flag[entry.String()] {
						r := []int{nums[begin], nums[left], nums[i], nums[j]}
						result = append(result, r)
						flag[entry.String()] = true
					}
					i++
					j--
				}
			}
			left++
		}
		begin++
	}
	return result
}

// quick sort
func sort(array []int) {
	if len(array) == 0 {
		return
	}
	left := 0
	right := len(array) - 1
	obj := array[left]
	for left < right {
		for left < right && array[right] >= obj {
			right--
		}
		array[left] = array[right]

		for left < right && array[left] <= obj {
			left++
		}
		array[right] = array[left]
	}
	array[left] = obj
	sort(array[:left])
	sort(array[right+1:])
}