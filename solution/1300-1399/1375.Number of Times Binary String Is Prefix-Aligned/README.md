# [1375. 二进制字符串前缀一致的次数](https://leetcode.cn/problems/number-of-times-binary-string-is-prefix-aligned)

[English Version](/solution/1300-1399/1375.Number%20of%20Times%20Binary%20String%20Is%20Prefix-Aligned/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 、下标从 <strong>1</strong> 开始的二进制字符串，所有位最开始都是 <code>0</code> 。我们会按步翻转该二进制字符串的所有位（即，将 <code>0</code> 变为 <code>1</code>）。</p>

<p>给你一个下标从 <strong>1</strong> 开始的整数数组 <code>flips</code> ，其中 <code>flips[i]</code> 表示对应下标 <code>i</code> 的位将会在第 <code>i</code> 步翻转。</p>

<p>二进制字符串 <strong>前缀一致</strong> 需满足：在第 <code>i</code> 步之后，在 <strong>闭</strong> 区间&nbsp;<code>[1, i]</code> 内的所有位都是 1 ，而其他位都是 0 。</p>

<p>返回二进制字符串在翻转过程中 <strong>前缀一致</strong> 的次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>flips = [3,2,4,1,5]
<strong>输出：</strong>2
<strong>解释：</strong>二进制字符串最开始是 "00000" 。
执行第 1 步：字符串变为 "00100" ，不属于前缀一致的情况。
执行第 2 步：字符串变为 "01100" ，不属于前缀一致的情况。
执行第 3 步：字符串变为 "01110" ，不属于前缀一致的情况。
执行第 4 步：字符串变为 "11110" ，属于前缀一致的情况。
执行第 5 步：字符串变为 "11111" ，属于前缀一致的情况。
在翻转过程中，前缀一致的次数为 2 ，所以返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>flips = [4,1,2,3]
<strong>输出：</strong>1
<strong>解释：</strong>二进制字符串最开始是 "0000" 。
执行第 1 步：字符串变为 "0001" ，不属于前缀一致的情况。
执行第 2 步：字符串变为 "1001" ，不属于前缀一致的情况。
执行第 3 步：字符串变为 "1101" ，不属于前缀一致的情况。
执行第 4 步：字符串变为 "1111" ，属于前缀一致的情况。
在翻转过程中，前缀一致的次数为 1 ，所以返回 1 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == flips.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>flips</code> 是范围 <code>[1, n]</code> 中所有整数构成的一个排列</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接遍历**

遍历数组 $flips$，记录最大值，将最大值与当前遍历到的下标 $i$ 比较，若相等，答案累加。

时间复杂度 $O(n)$。

**方法二：树状数组**

时间复杂度 $O(nlogn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numTimesAllBlue(self, flips: List[int]) -> int:
        ans = mx = 0
        for i, v in enumerate(flips, 1):
            mx = max(mx, v)
            if mx == i:
                ans += 1
        return ans
```

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


class Solution:
    def numTimesAllBlue(self, flips: List[int]) -> int:
        n = len(flips)
        tree = BinaryIndexedTree(n)
        ans = mx = 0
        for v in flips:
            mx = max(mx, v)
            tree.update(v, 1)
            if tree.query(mx) == mx:
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numTimesAllBlue(int[] flips) {
        int ans = 0;
        int mx = 0;
        for (int i = 1; i <= flips.length; ++i) {
            mx = Math.max(mx, flips[i - 1]);
            if (mx == i) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & -x;
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
}

class Solution {
    public int numTimesAllBlue(int[] flips) {
        int n = flips.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        int ans = 0;
        int mx = 0;
        for (int v : flips) {
            mx = Math.max(mx, v);
            tree.update(v, 1);
            if (tree.query(mx) == mx) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numTimesAllBlue(vector<int>& flips) {
        int ans = 0, mx = 0;
        for (int i = 1; i <= flips.size(); ++i) {
            mx = max(mx, flips[i - 1]);
            ans += mx == i;
        }
        return ans;
    }
};
```

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

class Solution {
public:
    int numTimesAllBlue(vector<int>& flips) {
        int n = flips.size();
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        int ans = 0, mx = 0;
        for (int v : flips)
        {
            mx = max(mx, v);
            tree->update(v, 1);
            ans += tree->query(mx) == mx;
        }
        return ans;
    }
};
```

### **Go**

```go
func numTimesAllBlue(flips []int) int {
	ans, mx := 0, 0
	for i := 1; i <= len(flips); i++ {
		mx = max(mx, flips[i-1])
		if mx == i {
			ans++
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

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

func numTimesAllBlue(flips []int) int {
	n := len(flips)
	tree := newBinaryIndexedTree(n)
	ans, mx := 0, 0
	for _, v := range flips {
		mx = max(mx, v)
		tree.update(v, 1)
		if tree.query(mx) == mx {
			ans++
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
