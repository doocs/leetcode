# [284. Peeking Iterator](https://leetcode.com/problems/peeking-iterator)

[中文文档](/solution/0200-0299/0284.Peeking%20Iterator/README.md)

## Description

<p>Design an iterator that supports the <code>peek</code> operation on a list in addition to the <code>hasNext</code> and the <code>next</code> operations.</p>

<p>Implement the <code>PeekingIterator</code> class:</p>

<ul>
	<li><code>PeekingIterator(int[] nums)</code> Initializes the object with the given integer array <code>nums</code>.</li>
	<li><code>int next()</code> Returns the next element in the array and moves the pointer to the next element.</li>
	<li><code>bool hasNext()</code> Returns <code>true</code> if there are still elements in the array.</li>
	<li><code>int peek()</code> Returns the next element in the array <strong>without</strong> moving the pointer.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;PeekingIterator&quot;, &quot;next&quot;, &quot;peek&quot;, &quot;next&quot;, &quot;next&quot;, &quot;hasNext&quot;]
[[[1, 2, 3]], [], [], [], [], []]
<strong>Output</strong>
[null, 1, 2, 2, 3, false]

<strong>Explanation</strong>
PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [<u><strong>1</strong></u>,2,3]
peekingIterator.next();    // return 1, the pointer moves to the next element [1,<u><strong>2</strong></u>,3].
peekingIterator.peek();    // return 2, the pointer does not move [1,<u><strong>2</strong></u>,3].
peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,<u><strong>3</strong></u>]
peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
peekingIterator.hasNext(); // return False
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li>All the calls to <code>next</code> and <code>peek</code> are valid.</li>
	<li>At most <code>1000</code> calls will be made to <code>next</code>, <code>hasNext</code>, and <code>peek</code>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> How would you extend your design to be generic and work with all types, not just integer?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Below is the interface for Iterator, which is already defined for you.
#
# class Iterator:
#     def __init__(self, nums):
#         """
#         Initializes an iterator object to the beginning of a list.
#         :type nums: List[int]
#         """
#
#     def hasNext(self):
#         """
#         Returns true if the iteration has more elements.
#         :rtype: bool
#         """
#
#     def next(self):
#         """
#         Returns the next element in the iteration.
#         :rtype: int
#         """

class PeekingIterator:
    def __init__(self, iterator):
        """
        Initialize your data structure here.
        :type iterator: Iterator
        """
        self.iterator = iterator
        self.has_peeked = False
        self.peeked_element = None

    def peek(self):
        """
        Returns the next element in the iteration without advancing the iterator.
        :rtype: int
        """
        if not self.has_peeked:
            self.peeked_element = self.iterator.next()
            self.has_peeked = True
        return self.peeked_element

    def next(self):
        """
        :rtype: int
        """
        if not self.has_peeked:
            return self.iterator.next()
        result = self.peeked_element
        self.has_peeked = False
        self.peeked_element = None
        return result

    def hasNext(self):
        """
        :rtype: bool
        """
        return self.has_peeked or self.iterator.hasNext()


# Your PeekingIterator object will be instantiated and called as such:
# iter = PeekingIterator(Iterator(nums))
# while iter.hasNext():
#     val = iter.peek()   # Get the next element but not advance the iterator.
#     iter.next()         # Should return the same value as [val].
```

### **Java**

```java
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private boolean hasPeeked;
    private Integer peekedElement;

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if (!hasPeeked) {
            peekedElement = iterator.next();
            hasPeeked = true;
        }
        return peekedElement;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (!hasPeeked) {
            return iterator.next();
        }
        Integer result = peekedElement;
        hasPeeked = false;
        peekedElement = null;
        return result;
	}

	@Override
	public boolean hasNext() {
	    return hasPeeked || iterator.hasNext();
	}
}
```

### **...**

```

```

<!-- tabs:end -->
