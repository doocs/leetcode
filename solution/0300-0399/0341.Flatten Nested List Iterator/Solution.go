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
	nested *list.List
}

func Constructor(nestedList []*NestedInteger) *NestedIterator {
	nested := list.New()
	for _, v := range nestedList {
		nested.PushBack(v)
	}
	return &NestedIterator{nested: nested}
}

func (this *NestedIterator) Next() int {
	res := this.nested.Front().Value.(*NestedInteger)
	this.nested.Remove(this.nested.Front())
	return res.GetInteger()
}

func (this *NestedIterator) HasNext() bool {
	for this.nested.Len() > 0 && !this.nested.Front().Value.(*NestedInteger).IsInteger() {
		front := this.nested.Front().Value.(*NestedInteger)
		this.nested.Remove(this.nested.Front())
		nodes := front.GetList()
		for i := len(nodes) - 1; i >= 0; i-- {
			this.nested.PushFront(nodes[i])
		}
	}
	return this.nested.Len() > 0
}
