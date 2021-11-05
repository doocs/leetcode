type TopVotedCandidate struct {
	times      []int
	winPersons []int
}

func Constructor(persons []int, times []int) TopVotedCandidate {
	mx, curWin, n := -1, -1, len(persons)
	counter := make([]int, n+1)
	winPersons := make([]int, n)
	for i, p := range persons {
		counter[p]++
		if counter[p] >= mx {
			mx = counter[p]
			curWin = p
		}
		winPersons[i] = curWin
	}
	return TopVotedCandidate{
		times, winPersons,
	}
}

func (this *TopVotedCandidate) Q(t int) int {
	left, right := 0, len(this.winPersons)-1
	for left < right {
		mid := (left + right + 1) >> 1
		if this.times[mid] <= t {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return this.winPersons[left]
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * obj := Constructor(persons, times);
 * param_1 := obj.Q(t);
 */