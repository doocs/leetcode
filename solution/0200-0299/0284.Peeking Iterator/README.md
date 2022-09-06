# [284. 顶端迭代器](https://leetcode.cn/problems/peeking-iterator)

[English Version](/solution/0200-0299/0284.Peeking%20Iterator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你在设计一个迭代器，在集成现有迭代器拥有的&nbsp;<code>hasNext</code> 和 <code>next</code> 操作的基础上，还额外支持 <code>peek</code> 操作。</p>

<p>实现 <code>PeekingIterator</code> 类：</p>

<ul>
	<li><code>PeekingIterator(Iterator&lt;int&gt; nums)</code> 使用指定整数迭代器&nbsp;<code>nums</code> 初始化迭代器。</li>
	<li><code>int next()</code> 返回数组中的下一个元素，并将指针移动到下个元素处。</li>
	<li><code>bool hasNext()</code> 如果数组中存在下一个元素，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
	<li><code>int peek()</code> 返回数组中的下一个元素，但 <strong>不</strong> 移动指针。</li>
</ul>

<p><strong>注意：</strong>每种语言可能有不同的构造函数和迭代器&nbsp;<code>Iterator</code>，但均支持 <code>int next()</code> 和 <code>boolean hasNext()</code> 函数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
[[[1, 2, 3]], [], [], [], [], []]
<strong>输出：</strong>
[null, 1, 2, 2, 3, false]

<strong>解释：</strong>
PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [<u><strong>1</strong></u>,2,3]
peekingIterator.next();    // 返回 1 ，指针移动到下一个元素 [1,<u><strong>2</strong></u>,3]
peekingIterator.peek();    // 返回 2 ，指针未发生移动 [1,<u><strong>2</strong></u>,3]
peekingIterator.next();    // 返回 2 ，指针移动到下一个元素 [1,2,<u><strong>3</strong></u>]
peekingIterator.next();    // 返回 3 ，指针移动到下一个元素 [1,2,3]
peekingIterator.hasNext(); // 返回 False
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li>对 <code>next</code> 和 <code>peek</code> 的调用均有效</li>
	<li><code>next</code>、<code>hasNext</code> 和 <code>peek </code>最多调用&nbsp; <code>1000</code> 次</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

定义一个变量 peekElement 专门用来保存下一个值，布尔变量 hasPeeked 标记是否保存了下一个元素。

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

### **C++**

```cpp
/*
 * Below is the interface for Iterator, which is already defined for you.
 * **DO NOT** modify the interface for Iterator.
 *
 *  class Iterator {
 *		struct Data;
 * 		Data* data;
 *  public:
 *		Iterator(const vector<int>& nums);
 * 		Iterator(const Iterator& iter);
 *
 * 		// Returns the next element in the iteration.
 *		int next();
 *
 *		// Returns true if the iteration has more elements.
 *		bool hasNext() const;
 *	};
 */

class PeekingIterator : public Iterator {
public:
    PeekingIterator(const vector<int>& nums)
        : Iterator(nums) {
        // Initialize any member here.
        // **DO NOT** save a copy of nums and manipulate it directly.
        // You should only use the Iterator interface methods.
        hasPeeked = false;
    }

    // Returns the next element in the iteration without advancing the iterator.
    int peek() {
        if (!hasPeeked) {
            peekedElement = Iterator::next();
            hasPeeked = true;
        }
        return peekedElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    int next() {
        if (!hasPeeked) return Iterator::next();
        hasPeeked = false;
        return peekedElement;
    }

    bool hasNext() const {
        return hasPeeked || Iterator::hasNext();
    }

private:
    bool hasPeeked;
    int peekedElement;
};
```

### **Go**

```go
/*   Below is the interface for Iterator, which is already defined for you.
 *
 *   type Iterator struct {
 *
 *   }
 *
 *   func (this *Iterator) hasNext() bool {
 *		// Returns true if the iteration has more elements.
 *   }
 *
 *   func (this *Iterator) next() int {
 *		// Returns the next element in the iteration.
 *   }
 */

type PeekingIterator struct {
	iter          *Iterator
	hasPeeked     bool
	peekedElement int
}

func Constructor(iter *Iterator) *PeekingIterator {
	return &PeekingIterator{iter, iter.hasNext(), iter.next()}
}

func (this *PeekingIterator) hasNext() bool {
	return this.hasPeeked || this.iter.hasNext()
}

func (this *PeekingIterator) next() int {
	if !this.hasPeeked {
		return this.iter.next()
	}
	this.hasPeeked = false
	return this.peekedElement
}

func (this *PeekingIterator) peek() int {
	if !this.hasPeeked {
		this.peekedElement = this.iter.next()
		this.hasPeeked = true
	}
	return this.peekedElement
}
```

### **...**

```

```

<!-- tabs:end -->
