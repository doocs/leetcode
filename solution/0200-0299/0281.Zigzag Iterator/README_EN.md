---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0281.Zigzag%20Iterator/README_EN.md
tags:
    - Design
    - Queue
    - Array
    - Iterator
---

<!-- problem:start -->

# [281. Zigzag Iterator 🔒](https://leetcode.com/problems/zigzag-iterator)

[中文文档](/solution/0200-0299/0281.Zigzag%20Iterator/README.md)

## Description

<!-- description:start -->

<p>Given two vectors of integers <code>v1</code> and <code>v2</code>, implement an iterator to return their elements alternately.</p>

<p>Implement the <code>ZigzagIterator</code> class:</p>

<ul>
	<li><code>ZigzagIterator(List&lt;int&gt; v1, List&lt;int&gt; v2)</code> initializes the object with the two vectors <code>v1</code> and <code>v2</code>.</li>
	<li><code>boolean hasNext()</code> returns <code>true</code> if the iterator still has elements, and <code>false</code> otherwise.</li>
	<li><code>int next()</code> returns the current element of the iterator and moves the iterator to the next element.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> v1 = [1,2], v2 = [3,4,5,6]
<strong>Output:</strong> [1,3,2,4,5,6]
<strong>Explanation:</strong> By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> v1 = [1], v2 = []
<strong>Output:</strong> [1]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> v1 = [], v2 = [1]
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= v1.length, v2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= v1.length + v2.length &lt;= 2000</code></li>
	<li><code>-2<sup>31</sup> &lt;= v1[i], v2[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What if you are given <code>k</code> vectors? How well can your code be extended to such cases?</p>

<p><strong>Clarification for the follow-up question:</strong></p>

<p>The &quot;Zigzag&quot; order is not clearly defined and is ambiguous for <code>k &gt; 2</code> cases. If &quot;Zigzag&quot; does not look right to you, replace &quot;Zigzag&quot; with &quot;Cyclic&quot;.</p>

<p><strong>Follow-up Example:</strong></p>

<pre>
<strong>Input:</strong> v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9]
<strong>Output:</strong> [1,4,8,2,5,9,3,6,7]
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class ZigzagIterator:
    def __init__(self, v1: List[int], v2: List[int]):
        self.cur = 0
        self.size = 2
        self.indexes = [0] * self.size
        self.vectors = [v1, v2]

    def next(self) -> int:
        vector = self.vectors[self.cur]
        index = self.indexes[self.cur]
        res = vector[index]
        self.indexes[self.cur] = index + 1
        self.cur = (self.cur + 1) % self.size
        return res

    def hasNext(self) -> bool:
        start = self.cur
        while self.indexes[self.cur] == len(self.vectors[self.cur]):
            self.cur = (self.cur + 1) % self.size
            if self.cur == start:
                return False
        return True


# Your ZigzagIterator object will be instantiated and called as such:
# i, v = ZigzagIterator(v1, v2), []
# while i.hasNext(): v.append(i.next())
```

#### Java

```java
public class ZigzagIterator {
    private int cur;
    private int size;
    private List<Integer> indexes = new ArrayList<>();
    private List<List<Integer>> vectors = new ArrayList<>();

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        cur = 0;
        size = 2;
        indexes.add(0);
        indexes.add(0);
        vectors.add(v1);
        vectors.add(v2);
    }

    public int next() {
        List<Integer> vector = vectors.get(cur);
        int index = indexes.get(cur);
        int res = vector.get(index);
        indexes.set(cur, index + 1);
        cur = (cur + 1) % size;
        return res;
    }

    public boolean hasNext() {
        int start = cur;
        while (indexes.get(cur) == vectors.get(cur).size()) {
            cur = (cur + 1) % size;
            if (start == cur) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
```

#### Go

```go
type ZigzagIterator struct {
	cur     int
	size    int
	indexes []int
	vectors [][]int
}

func Constructor(v1, v2 []int) *ZigzagIterator {
	return &ZigzagIterator{
		cur:     0,
		size:    2,
		indexes: []int{0, 0},
		vectors: [][]int{v1, v2},
	}
}

func (this *ZigzagIterator) next() int {
	vector := this.vectors[this.cur]
	index := this.indexes[this.cur]
	res := vector[index]
	this.indexes[this.cur]++
	this.cur = (this.cur + 1) % this.size
	return res
}

func (this *ZigzagIterator) hasNext() bool {
	start := this.cur
	for this.indexes[this.cur] == len(this.vectors[this.cur]) {
		this.cur = (this.cur + 1) % this.size
		if start == this.cur {
			return false
		}
	}
	return true
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * obj := Constructor(param_1, param_2);
 * for obj.hasNext() {
 *	 ans = append(ans, obj.next())
 * }
 */
```

#### Rust

```rust
struct ZigzagIterator {
    v1: Vec<i32>,
    v2: Vec<i32>,
    /// `false` represents `v1`, `true` represents `v2`
    flag: bool,
}

impl ZigzagIterator {
    fn new(v1: Vec<i32>, v2: Vec<i32>) -> Self {
        Self {
            v1,
            v2,
            // Initially beginning with `v1`
            flag: false,
        }
    }

    fn next(&mut self) -> i32 {
        if !self.flag {
            // v1
            if self.v1.is_empty() && !self.v2.is_empty() {
                self.flag = true;
                let ret = self.v2.remove(0);
                return ret;
            }
            if self.v2.is_empty() {
                let ret = self.v1.remove(0);
                return ret;
            }
            let ret = self.v1.remove(0);
            self.flag = true;
            return ret;
        } else {
            // v2
            if self.v2.is_empty() && !self.v1.is_empty() {
                self.flag = false;
                let ret = self.v1.remove(0);
                return ret;
            }
            if self.v1.is_empty() {
                let ret = self.v2.remove(0);
                return ret;
            }
            let ret = self.v2.remove(0);
            self.flag = false;
            return ret;
        }
    }

    fn has_next(&self) -> bool {
        !self.v1.is_empty() || !self.v2.is_empty()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
