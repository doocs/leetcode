func longestSubarray(nums []int, limit int) int {
	l, r := 0, len(nums)
	check := func(k int) bool {
		minq := Deque{}
		maxq := Deque{}
		for i, x := range nums {
			for !minq.Empty() && i-minq.Front()+1 > k {
				minq.PopFront()
			}
			for !maxq.Empty() && i-maxq.Front()+1 > k {
				maxq.PopFront()
			}
			for !minq.Empty() && nums[minq.Back()] >= x {
				minq.PopBack()
			}
			for !maxq.Empty() && nums[maxq.Back()] <= x {
				maxq.PopBack()
			}
			minq.PushBack(i)
			maxq.PushBack(i)
			if i >= k-1 && nums[maxq.Front()]-nums[minq.Front()] <= limit {
				return true
			}
		}
		return false
	}
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}

// template
type Deque struct{ l, r []int }

func (q Deque) Empty() bool {
	return len(q.l) == 0 && len(q.r) == 0
}

func (q Deque) Size() int {
	return len(q.l) + len(q.r)
}

func (q *Deque) PushFront(v int) {
	q.l = append(q.l, v)
}

func (q *Deque) PushBack(v int) {
	q.r = append(q.r, v)
}

func (q *Deque) PopFront() (v int) {
	if len(q.l) > 0 {
		q.l, v = q.l[:len(q.l)-1], q.l[len(q.l)-1]
	} else {
		v, q.r = q.r[0], q.r[1:]
	}
	return
}

func (q *Deque) PopBack() (v int) {
	if len(q.r) > 0 {
		q.r, v = q.r[:len(q.r)-1], q.r[len(q.r)-1]
	} else {
		v, q.l = q.l[0], q.l[1:]
	}
	return
}

func (q Deque) Front() int {
	if len(q.l) > 0 {
		return q.l[len(q.l)-1]
	}
	return q.r[0]
}

func (q Deque) Back() int {
	if len(q.r) > 0 {
		return q.r[len(q.r)-1]
	}
	return q.l[0]
}

func (q Deque) Get(i int) int {
	if i < len(q.l) {
		return q.l[len(q.l)-1-i]
	}
	return q.r[i-len(q.l)]
}