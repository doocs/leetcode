# [251. 展开二维向量](https://leetcode.cn/problems/flatten-2d-vector)

[English Version](/solution/0200-0299/0251.Flatten%202D%20Vector/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 <code>next</code> 和 <code>hasNext</code> 两种操作。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

iterator.next(); // 返回 1
iterator.next(); // 返回 2
iterator.next(); // 返回 3
iterator.hasNext(); // 返回 true
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 4
iterator.hasNext(); // 返回 false
</pre>

<p> </p>

<p><strong>注意：</strong></p>

<ol>
	<li>请记得 <strong>重置 </strong>在 Vector2D 中声明的类变量（静态变量），因为类变量会 <strong>在多个测试用例中保持不变</strong>，影响判题准确。请 <a href="https://support.leetcode.cn/hc/kb/section/1071534/" target="_blank">查阅</a> 这里。</li>
	<li>你可以假定 <code>next()</code> 的调用总是合法的，即当 <code>next()</code> 被调用时，二维向量总是存在至少一个后续元素。</li>
</ol>

<p> </p>

<p><strong>进阶：</strong>尝试在代码中仅使用 <a href="http://www.cplusplus.com/reference/iterator/iterator/">C++ 提供的迭代器</a> 或 <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html">Java 提供的迭代器</a>。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Vector2D:
    def __init__(self, vec: List[List[int]]):
        self.flatten = []
        for item in vec:
            for e in item:
                self.flatten.append(e)
        self.cur = -1

    def next(self) -> int:
        self.cur += 1
        return self.flatten[self.cur]

    def hasNext(self) -> bool:
        return self.cur < len(self.flatten) - 1


# Your Vector2D object will be instantiated and called as such:
# obj = Vector2D(vec)
# param_1 = obj.next()
# param_2 = obj.hasNext()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
