type SortedStack struct {
	data []int
}

func Constructor() SortedStack {
	return SortedStack{make([]int, 0)}
}

func (s *SortedStack) Push(val int) {
	temp := make([]int, 0)
	for !s.IsEmpty() && s.Peek() < val {
		temp = append(temp, s.Peek())
		s.Pop()
	}
	s.data = append(s.data, val)
	for len(temp) > 0 {
		s.data = append(s.data, temp[len(temp)-1])
		temp = temp[:len(temp)-1]
	}
}

func (s *SortedStack) Pop() {
	if !s.IsEmpty() {
		s.data = s.data[:len(s.data)-1]
	}
}

func (s *SortedStack) Peek() int {
	if !s.IsEmpty() {
		return s.data[len(s.data)-1]
	}
	return -1
}

func (s *SortedStack) IsEmpty() bool {
	return len(s.data) == 0
}
