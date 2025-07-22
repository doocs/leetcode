func maximumRobots(chargeTimes []int, runningCosts []int, budget int64) (ans int) {
	q := Deque{}
	s := int64(0)
	l := 0
	for r, t := range chargeTimes {
		s += int64(runningCosts[r])
		for !q.Empty() && chargeTimes[q.Back()] <= t {
			q.PopBack()
		}
		q.PushBack(r)
		for !q.Empty() && int64(r-l+1)*s+int64(chargeTimes[q.Front()]) > budget {
			if q.Front() == l {
				q.PopFront()
			}
			s -= int64(runningCosts[l])
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
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
