# [307. 区域和检索 - 数组可修改](https://leetcode-cn.com/problems/range-sum-query-mutable)

[English Version](/solution/0300-0399/0307.Range%20Sum%20Query%20-%20Mutable/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>nums</code> ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。</p>

<p>实现 <code>NumArray</code> 类：</p>

<div class="original__bRMd">
<div>
<ul>
	<li><code>NumArray(int[] nums)</code> 用整数数组 <code>nums</code> 初始化对象</li>
	<li><code>void update(int index, int val)</code> 将 <code>nums[index]</code> 的值更新为 <code>val</code></li>
	<li><code>int sumRange(int left, int right)</code> 返回子数组 <code>nums[left, right]</code> 的总和（即，<code>nums[left] + nums[left + 1], ..., nums[right]</code>）</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>：
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
<strong>输出</strong>：
[null, 9, null, 8]

<strong>解释</strong>：
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
numArray.update(1, 2);   // nums = [1,2,5]
numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 8
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>-100 <= nums[i] <= 100</code></li>
	<li><code>0 <= index < nums.length</code></li>
	<li><code>-100 <= val <= 100</code></li>
	<li><code>0 <= left <= right < nums.length</code></li>
	<li>最多调用 <code>3 * 10<sup>4</sup></code> 次 <code>update</code> 和 <code>sumRange</code> 方法</li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

树状数组。

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 `O(log n)`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s

class NumArray:

    def __init__(self, nums: List[int]):
        self.tree = BinaryIndexedTree(len(nums))
        for i, v in enumerate(nums, 1):
            self.tree.update(i, v)

    def update(self, index: int, val: int) -> None:
        prev = self.sumRange(index, index)
        self.tree.update(index + 1, val - prev)

    def sumRange(self, left: int, right: int) -> int:
        return self.tree.query(right + 1) - self.tree.query(left)


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# obj.update(index,val)
# param_2 = obj.sumRange(left,right)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}

class NumArray {
    private BinaryIndexedTree tree;

    public NumArray(int[] nums) {
        int n = nums.length;
        tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            tree.update(i + 1, nums[i]);
        }
    }
    
    public void update(int index, int val) {
        int prev = sumRange(index, index);
        tree.update(index + 1, val - prev);
    }
    
    public int sumRange(int left, int right) {
        return tree.query(right + 1) - tree.query(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
```

### **C++**

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n): n(_n), c(_n + 1){}

    void update(int x, int delta) {
        while (x <= n)
        {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0)
        {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};


class NumArray {
public:
    BinaryIndexedTree* tree;

    NumArray(vector<int>& nums) {
        int n = nums.size();
        tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) tree->update(i + 1, nums[i]);
    }
    
    void update(int index, int val) {
        int prev = sumRange(index, index);
        tree->update(index + 1, val - prev);
    }
    
    int sumRange(int left, int right) {
        return tree->query(right + 1) - tree->query(left);
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */
```

### **Go**

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

type NumArray struct {
	tree *BinaryIndexedTree
}

func Constructor(nums []int) NumArray {
	tree := newBinaryIndexedTree(len(nums))
	for i, v := range nums {
		tree.update(i+1, v)
	}
	return NumArray{tree}
}

func (this *NumArray) Update(index int, val int) {
	prev := this.SumRange(index, index)
	this.tree.update(index+1, val-prev)
}

func (this *NumArray) SumRange(left int, right int) int {
	return this.tree.query(right+1) - this.tree.query(left)
}

/**
 * Your NumArray object will be instantiated and called as such:
 * obj := Constructor(nums);
 * obj.Update(index,val);
 * param_2 := obj.SumRange(left,right);
 */
```

### **...**

```

```

<!-- tabs:end -->
