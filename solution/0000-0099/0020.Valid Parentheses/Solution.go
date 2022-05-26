func isValid(s string) bool {
	stack := newStack()
	for _, str := range s {
		if str == '(' || str == '[' || str == '{' {
			stack.push(byte(str))
		} else if str == ')' {
			if stack.pop() != (byte('(')) {
				return false
			}
		} else if str == ']' {
			if stack.pop() != (byte('[')) {
				return false
			}
		} else if str == '}' {
			if stack.pop() != (byte('{')) {
				return false
			}
		}
	}
	return stack.size() == 0
}

type Stack struct {
	data  []byte
	index int
}

func newStack() *Stack {
	return &Stack{
		data: make([]byte, 10),
	}
}

func (s *Stack) pop() byte {
	if s.index == 0 {
		return 0
	}
	s.index--
	r := s.data[s.index]
	return r
}

func (s *Stack) push(b byte) {
	if len(s.data)-1 <= s.index {
		newData := make([]byte, len(s.data))
		s.data = append(s.data, newData[:]...)
	}
	s.data[s.index] = b
	s.index++
}

func (s *Stack) size() int {
	return s.index
}
