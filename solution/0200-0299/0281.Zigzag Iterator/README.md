# [281. 锯齿迭代器 🔒](https://leetcode.cn/problems/zigzag-iterator)

[English Version](/solution/0200-0299/0281.Zigzag%20Iterator/README_EN.md)

<!-- tags:设计,队列,数组,迭代器 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给出两个一维的向量，请你实现一个迭代器，交替返回它们中间的元素。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>
v1 = [1,2]
v2 = [3,4,5,6] 

<strong>输出:</strong> <code>[1,3,2,4,5,6]

<strong>解析:</strong></code>&nbsp;通过连续调用 <em>next</em> 函数直到 <em>hasNext</em> 函数返回 <code>false，</code>
&nbsp;    <em>next</em> 函数返回值的次序应依次为: <code>[1,3,2,4,5,6]。</code></pre>

<p><strong>拓展：</strong>假如给你&nbsp;<code>k</code>&nbsp;个一维向量呢？你的代码在这种情况下的扩展性又会如何呢?</p>

<p><strong>拓展声明：</strong><br>
&nbsp;&ldquo;锯齿&rdquo; 顺序对于&nbsp;<code>k &gt; 2</code>&nbsp;的情况定义可能会有些歧义。所以，假如你觉得 &ldquo;锯齿&rdquo; 这个表述不妥，也可以认为这是一种&nbsp;&ldquo;循环&rdquo;。例如：</p>

<pre><strong>输入:</strong>
[1,2,3]
[4,5,6,7]
[8,9]

<strong>输出: </strong><code>[1,4,8,2,5,9,3,6,7]</code>.
</pre>

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- end -->
