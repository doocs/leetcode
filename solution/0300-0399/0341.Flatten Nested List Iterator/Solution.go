/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * type NestedInteger struct {
 * }
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * func (this NestedInteger) IsInteger() bool {}
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * // So before calling this method, you should have a check
 * func (this NestedInteger) GetInteger() int {}
 *
 * // Set this NestedInteger to hold a single integer.
 * func (n *NestedInteger) SetInteger(value int) {}
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * func (this *NestedInteger) Add(elem NestedInteger) {}
 *
 * // Return the nested list that this NestedInteger holds, if it holds a nested list
 * // The list length is zero if this NestedInteger holds a single integer
 * // You can access NestedInteger's List element directly if you want to modify it
 * func (this NestedInteger) GetList() []*NestedInteger {}
 */

type NestedIterator struct {
	iterator      []int
	index, length int
}

func Constructor(nestedList []*NestedInteger) *NestedIterator {
	result := make([]int, 0)
	var traversal func(nodes []*NestedInteger)
	traversal = func(nodes []*NestedInteger) {
		for _, child := range nodes {
			if child.IsInteger() {
				result = append(result, child.GetInteger())
			} else {
				traversal(child.GetList())
			}
		}
	}
	traversal(nestedList)
	return &NestedIterator{iterator: result, index: 0, length: len(result)}
}

func (this *NestedIterator) Next() int {
	res := this.iterator[this.index]
	this.index++
	return res
}

func (this *NestedIterator) HasNext() bool {
	return this.index < this.length
}