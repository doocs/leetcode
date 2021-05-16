# [284. 顶端迭代器](https://leetcode-cn.com/problems/peeking-iterator)

[English Version](/solution/0200-0299/0284.Peeking%20Iterator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个迭代器类的接口，接口包含两个方法：&nbsp;<code>next()</code>&nbsp;和&nbsp;<code>hasNext()</code>。设计并实现一个支持&nbsp;<code>peek()</code>&nbsp;操作的顶端迭代器 -- 其本质就是把原本应由&nbsp;<code>next()</code>&nbsp;方法返回的元素&nbsp;<code>peek()</code>&nbsp;出来。</p>

<p><strong>示例:</strong></p>

<pre>假设迭代器被初始化为列表&nbsp;<strong><code>[1,2,3]</code></strong>。

调用&nbsp;<strong><code>next() </code></strong>返回 <strong>1</strong>，得到列表中的第一个元素。
现在调用&nbsp;<strong><code>peek()</code></strong>&nbsp;返回 <strong>2</strong>，下一个元素。在此之后调用&nbsp;<strong><code>next() </code></strong>仍然返回 <strong>2</strong>。
最后一次调用&nbsp;<strong><code>next()</code></strong>&nbsp;返回 <strong>3</strong>，末尾元素。在此之后调用&nbsp;<strong><code>hasNext()</code></strong>&nbsp;应该返回 <strong>false</strong>。
</pre>

<p><strong>进阶：</strong>你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
