---
comments: true
difficulty: 中等
tags:
    - 设计
    - 队列
    - 数组
    - 迭代器
---

<!-- problem:start -->

# [281. 锯齿迭代器 🔒](https://leetcode.cn/problems/zigzag-iterator)

[English Version](/solution/0200-0299/0281.Zigzag%20Iterator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出两个整数向量&nbsp;<code>v1</code>&nbsp;和&nbsp;<code>v2</code>，请你实现一个迭代器，交替返回它们的元素。</p>

<p>实现&nbsp;<code>ZigzagIterator</code>&nbsp;类：</p>

<ul>
	<li><code>ZigzagIterator(List&lt;int&gt; v1, List&lt;int&gt; v2)</code>&nbsp;用两个向量&nbsp;<code>v1</code>&nbsp;和&nbsp;<code>v2</code>&nbsp;初始化对象。</li>
	<li><code>boolean hasNext()</code>&nbsp;如果迭代器还有元素返回&nbsp;<code>true</code>，否则返回 <code>false</code>。</li>
	<li><code>int next()</code>&nbsp;返回迭代器的当前元素并将迭代器移动到下一个元素。</li>
</ul>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入：</strong>v1 = [1,2], v2 = [3,4,5,6]
<strong>输出：</strong>[1,3,2,4,5,6]
<strong>解释：</strong>通过重复调用 next 直到 hasNext 返回 false，那么 next 返回的元素的顺序应该是：[1,3,2,4,5,6]。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入：</strong>v1 = [1], v2 = []
<strong>输出：</strong>[1]
</pre>

<p><strong class="example">示例 3:</strong></p>

<pre>
<strong>输入：</strong>v1 = [], v2 = [1]
<strong>输出：</strong>[1]
</pre>

<p><strong>拓展：</strong>假如给你&nbsp;<code>k</code>&nbsp;个向量呢？你的代码在这种情况下的扩展性又会如何呢?</p>

<p><strong>拓展声明：</strong><br />
&nbsp;“锯齿” 顺序对于&nbsp;<code>k &gt; 2</code>&nbsp;的情况定义可能会有些歧义。所以，假如你觉得 “锯齿” 这个表述不妥，也可以认为这是一种&nbsp;“循环”。例如：</p>

<pre>
<strong>输入：</strong>v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9]
<strong>输出：</strong>[1,4,8,2,5,9,3,6,7]
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 交替输出两个向量，某侧耗尽后应跳到另一侧。用当前向量下标 $cur$ 与各自指针，取完后 $cur$ 模 $2$ 前进。
>
> $hasNext$ 在当前向量用尽时循环换到下一个，若转回起点则已空。

<!-- thinking:end -->

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
