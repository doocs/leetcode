---
comments: true
difficulty: 中等
tags:
    - 设计
    - 数组
    - 双指针
    - 迭代器
---

<!-- problem:start -->

# [251. 展开二维向量 🔒](https://leetcode.cn/problems/flatten-2d-vector)

[English Version](/solution/0200-0299/0251.Flatten%202D%20Vector/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持&nbsp;<code>next</code> 和&nbsp;<code>hasNext</code>&nbsp;两种操作。</p>

<p>实现&nbsp;<code>Vector2D</code>&nbsp;类：</p>

<ul>
	<li><code>Vector2D(int[][] vec)</code>&nbsp;使用二维向量&nbsp;<code>vec</code>&nbsp;初始化对象</li>
	<li><code>next()</code>&nbsp;从二维向量返回下一个元素并将指针移动到下一个位置。你可以假设对&nbsp;<code>next</code>&nbsp;的所有调用都是合法的。</li>
	<li><code>hasNext()</code>&nbsp;当向量中还有元素返回&nbsp;<code>true</code>，否则返回 <code>false</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
[[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
<strong>输出：</strong>
[null, 1, 2, 3, true, true, 4, false]

<strong>解释：</strong>
Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
vector2D.next();    // return 1
vector2D.next();    // return 2
vector2D.next();    // return 3
vector2D.hasNext(); // return True
vector2D.hasNext(); // return True
vector2D.next();    // return 4
vector2D.hasNext(); // return False
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>0 &lt;= vec.length &lt;= 200</code></li>
	<li><code>0 &lt;= vec[i].length &lt;= 500</code></li>
	<li><code>-500 &lt;= vec[i][j] &lt;= 500</code></li>
	<li>最多调用&nbsp;<code>next</code> 和&nbsp;<code>hasNext</code>&nbsp;<code>10<sup>5</sup></code>&nbsp;次。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>尝试在代码中仅使用 <a href="http://www.cplusplus.com/reference/iterator/iterator/">C++ 提供的迭代器</a> 或 <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html">Java 提供的迭代器</a>。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

<!-- thinking:start -->

> **思考**
>
> 预先展开成一维数组简单，但浪费空间且未体现迭代器语义。用行、列两个下标定位下一个元素即可。
>
> $forward$ 跳过空行，使 $(i,j)$ 落在有效元素上；$next$ 取其值并右移，$hasNext$ 看 $i$ 是否仍在范围内。

<!-- thinking:end -->

我们定义两个指针 $i$ 和 $j$，分别指向当前二维向量的行和列，初始时 $i = 0$，$j = 0$。

接下来，我们设计一个函数 $forward()$，用于将 $i$ 和 $j$ 向后移动，直到指向一个非空的元素。

每次调用 `next` 方法时，我们先调用 $forward()$，然后返回当前指向的元素，最后将 $j$ 向后移动一位。

每次调用 `hasNext` 方法时，我们先调用 $forward()$，然后判断 $i$ 是否小于二维向量的行数，如果是，则返回 `true`，否则返回 `false`。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Vector2D:
    def __init__(self, vec: List[List[int]]):
        self.i = 0
        self.j = 0
        self.vec = vec

    def next(self) -> int:
        self.forward()
        ans = self.vec[self.i][self.j]
        self.j += 1
        return ans

    def hasNext(self) -> bool:
        self.forward()
        return self.i < len(self.vec)

    def forward(self):
        while self.i < len(self.vec) and self.j >= len(self.vec[self.i]):
            self.i += 1
            self.j = 0


# Your Vector2D object will be instantiated and called as such:
# obj = Vector2D(vec)
# param_1 = obj.next()
# param_2 = obj.hasNext()
```

#### Java

```java
class Vector2D {
    private int i;
    private int j;
    private int[][] vec;

    public Vector2D(int[][] vec) {
        this.vec = vec;
    }

    public int next() {
        forward();
        return vec[i][j++];
    }

    public boolean hasNext() {
        forward();
        return i < vec.length;
    }

    private void forward() {
        while (i < vec.length && j >= vec[i].length) {
            ++i;
            j = 0;
        }
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(vec);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

#### C++

```cpp
class Vector2D {
public:
    Vector2D(vector<vector<int>>& vec) {
        this->vec = move(vec);
    }

    int next() {
        forward();
        return vec[i][j++];
    }

    bool hasNext() {
        forward();
        return i < vec.size();
    }

private:
    int i = 0;
    int j = 0;
    vector<vector<int>> vec;

    void forward() {
        while (i < vec.size() && j >= vec[i].size()) {
            ++i;
            j = 0;
        }
    }
};

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D* obj = new Vector2D(vec);
 * int param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
```

#### Go

```go
type Vector2D struct {
	i, j int
	vec  [][]int
}

func Constructor(vec [][]int) Vector2D {
	return Vector2D{vec: vec}
}

func (this *Vector2D) Next() int {
	this.forward()
	ans := this.vec[this.i][this.j]
	this.j++
	return ans
}

func (this *Vector2D) HasNext() bool {
	this.forward()
	return this.i < len(this.vec)
}

func (this *Vector2D) forward() {
	for this.i < len(this.vec) && this.j >= len(this.vec[this.i]) {
		this.i++
		this.j = 0
	}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * obj := Constructor(vec);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
```

#### TypeScript

```ts
class Vector2D {
    i: number;
    j: number;
    vec: number[][];

    constructor(vec: number[][]) {
        this.i = 0;
        this.j = 0;
        this.vec = vec;
    }

    next(): number {
        this.forward();
        return this.vec[this.i][this.j++];
    }

    hasNext(): boolean {
        this.forward();
        return this.i < this.vec.length;
    }

    forward(): void {
        while (this.i < this.vec.length && this.j >= this.vec[this.i].length) {
            ++this.i;
            this.j = 0;
        }
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * var obj = new Vector2D(vec)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
