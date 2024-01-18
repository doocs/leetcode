type Pair struct {
	val   int
	index int
}

var (
	tmp   []Pair
	count []int
)

func countSmaller(nums []int) []int {
	tmp, count = make([]Pair, len(nums)), make([]int, len(nums))
	array := make([]Pair, len(nums))
	for i, v := range nums {
		array[i] = Pair{val: v, index: i}
	}
	sorted(array, 0, len(array)-1)
	return count
}

func sorted(arr []Pair, low, high int) {
	if low >= high {
		return
	}
	mid := low + (high-low)/2
	sorted(arr, low, mid)
	sorted(arr, mid+1, high)
	merge(arr, low, mid, high)
}

func merge(arr []Pair, low, mid, high int) {
	left, right := low, mid+1
	idx := low
	for left <= mid && right <= high {
		if arr[left].val <= arr[right].val {
			count[arr[left].index] += right - mid - 1
			tmp[idx], left = arr[left], left+1
		} else {
			tmp[idx], right = arr[right], right+1
		}
		idx++
	}
	for left <= mid {
		count[arr[left].index] += right - mid - 1
		tmp[idx] = arr[left]
		idx, left = idx+1, left+1
	}
	for right <= high {
		tmp[idx] = arr[right]
		idx, right = idx+1, right+1
	}
	// 排序
	for i := low; i <= high; i++ {
		arr[i] = tmp[i]
	}
}