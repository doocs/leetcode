/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * type NestedInteger struct {
 * }
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * func (n NestedInteger) IsInteger() bool {}
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * // So before calling this method, you should have a check
 * func (n NestedInteger) GetInteger() int {}
 *
 * // Set this NestedInteger to hold a single integer.
 * func (n *NestedInteger) SetInteger(value int) {}
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * func (n *NestedInteger) Add(elem NestedInteger) {}
 *
 * // Return the nested list that this NestedInteger holds, if it holds a nested list
 * // The list length is zero if this NestedInteger holds a single integer
 * // You can access NestedInteger's List element directly if you want to modify it
 * func (n NestedInteger) GetList() []*NestedInteger {}
 */
func deserialize(s string) *NestedInteger {
	if s[0] != '[' {
		ans := &NestedInteger{}
		v, _ := strconv.Atoi(s)
		ans.SetInteger(v)
		return ans
	}
	stack := []*NestedInteger{}
	num := 0
	negative := false
	for i := 0; i < len(s); i++ {
		c := s[i]
		if c == '-' {
			negative = true
		} else if c >= '0' && c <= '9' {
			num = num*10 + int(c-'0')
		} else if c == '[' {
			stack = append(stack, &NestedInteger{})
		} else if c == ',' || c == ']' {
			if s[i-1] >= '0' && s[i-1] <= '9' {
				value := num
				if negative {
					value = -value
				}
				child := &NestedInteger{}
				child.SetInteger(value)
				stack[len(stack)-1].Add(*child)
			}
			num = 0
			negative = false
			if c == ']' && len(stack) > 1 {
				child := stack[len(stack)-1]
				stack = stack[:len(stack)-1]
				stack[len(stack)-1].Add(*child)
			}
		}
	}
	return stack[0]
}
