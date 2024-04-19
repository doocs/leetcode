# [251. 展开二维向量 🔒](https://leetcode.cn/problems/flatten-2d-vector)

[English Version](/solution/0200-0299/0251.Flatten%202D%20Vector/README_EN.md)

<!-- tags:设计,数组,双指针,迭代器 -->

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

### 方法一：双指针

我们定义两个指针 $i$ 和 $j$，分别指向当前二维向量的行和列，初始时 $i = 0$，$j = 0$。

接下来，我们设计一个函数 $forward()$，用于将 $i$ 和 $j$ 向后移动，直到指向一个非空的元素。

每次调用 `next` 方法时，我们先调用 $forward()$，然后返回当前指向的元素，最后将 $j$ 向后移动一位。

每次调用 `hasNext` 方法时，我们先调用 $forward()$，然后判断 $i$ 是否小于二维向量的行数，如果是，则返回 `true`，否则返回 `false`。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

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

<!-- end -->
