# [1304. 和为零的 N 个不同整数](https://leetcode.cn/problems/find-n-unique-integers-sum-up-to-zero)

[English Version](/solution/1300-1399/1304.Find%20N%20Unique%20Integers%20Sum%20up%20to%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>，请你返回 <strong>任意&nbsp;</strong>一个由 <code>n</code>&nbsp;个 <strong>各不相同&nbsp;</strong>的整数组成的数组，并且这 <code>n</code> 个数相加和为 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 5
<strong>输出：</strong>[-7,-1,1,3,4]
<strong>解释：</strong>这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>[-1,0,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：构造**

我们可以从 $1$ 开始，依次将正数和负数交替放入结果数组中，一共循环 $\frac{n}{2}$ 次，如果 $n$ 为奇数，则最后再将 $0$ 放入结果数组中。

时间复杂度 $O(n)$，其中 $n$ 为给定的整数。忽略答案的空间消耗，空间复杂度 $O(1)$。

**方法二：构造 + 数学**

我们也可以将 $1$ 到 $n-1$ 的所有整数放入结果数组中，最后再把前 $n-1$ 个整数的和 $\frac{n(n-1)}{2}$ 的相反数放入结果数组中。

时间复杂度 $O(n)$，其中 $n$ 为给定的整数。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumZero(self, n: int) -> List[int]:
        ans = []
        for i in range(n >> 1):
            ans.append(i + 1)
            ans.append(-(i + 1))
        if n & 1:
            ans.append(0)
        return ans
```

```python
class Solution:
    def sumZero(self, n: int) -> List[int]:
        ans = list(range(1, n))
        ans.append(-sum(ans))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        for (int i = 1, j = 0; i <= n / 2; ++i) {
            ans[j++] = i;
            ans[j++] = -i;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        for (int i = 1; i < n; ++i) {
            ans[i] = i;
        }
        ans[0] = -(n * (n - 1) / 2);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> sumZero(int n) {
        vector<int> ans(n);
        for (int i = 1, j = 0; i <= n / 2; ++i) {
            ans[j++] = i;
            ans[j++] = -i;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> sumZero(int n) {
        vector<int> ans(n);
        iota(ans.begin(), ans.end(), 1);
        ans[n - 1] = -(n - 1) * n / 2;
        return ans;
    }
};
```

### **Go**

```go
func sumZero(n int) []int {
	ans := make([]int, n)
	for i, j := 1, 0; i <= n/2; i, j = i+1, j+1 {
		ans[j] = i
		j++
		ans[j] = -i
	}
	return ans
}
```

```go
func sumZero(n int) []int {
	ans := make([]int, n)
	for i := 1; i < n; i++ {
		ans[i] = i
	}
	ans[0] = -n * (n - 1) / 2
	return ans
}
```

### **TypeScript**

```ts
function sumZero(n: number): number[] {
    const ans = new Array(n).fill(0);
    for (let i = 1, j = 0; i <= n / 2; ++i) {
        ans[j++] = i;
        ans[j++] = -i;
    }
    return ans;
}
```

```ts
function sumZero(n: number): number[] {
    const ans = new Array(n).fill(0);
    for (let i = 1; i < n; ++i) {
        ans[i] = i;
    }
    ans[0] = -((n * (n - 1)) / 2);
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
