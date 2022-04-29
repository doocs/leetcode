# [281. 锯齿迭代器](https://leetcode.cn/problems/zigzag-iterator)

[English Version](/solution/0200-0299/0281.Zigzag%20Iterator/README_EN.md)

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

<!-- 这里可写通用的实现逻辑 -->

定义 vectors 列表保存输入的所有一维向量，indexes 表示 vectors 列表每一项当前所遍历到的下标位置，cur 表示当前遍历到的 vector 列表，而 size 表示 vectors 列表元素个数。具体实现参考以下代码实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **...**

```

```

<!-- tabs:end -->
