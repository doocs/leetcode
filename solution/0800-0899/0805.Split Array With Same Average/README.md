# [805. 数组的均值分割](https://leetcode.cn/problems/split-array-with-same-average)

[English Version](/solution/0800-0899/0805.Split%20Array%20With%20Same%20Average/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定你一个整数数组<meta charset="UTF-8" />&nbsp;<code>nums</code></p>

<p>我们要将<meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;数组中的每个元素移动到&nbsp;<code>A</code>&nbsp;数组 或者&nbsp;<code>B</code>&nbsp;数组中，使得&nbsp;<code>A</code>&nbsp;数组和<meta charset="UTF-8" />&nbsp;<code>B</code>&nbsp;数组不为空，并且<meta charset="UTF-8" />&nbsp;<code>average(A) == average(B)</code>&nbsp;。</p>

<p>如果可以完成则返回<code>true</code>&nbsp;， 否则返回 <code>false</code>&nbsp;&nbsp;。</p>

<p><strong>注意：</strong>对于数组<meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;, <meta charset="UTF-8" />&nbsp;<code>average(arr)</code>&nbsp;是<meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;的所有元素的和除以<meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,5,6,7,8]
<strong>输出:</strong> true
<strong>解释: </strong>我们可以将数组分割为 [1,4,5,8] 和 [2,3,6,7], 他们的平均值都是4.5。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [3,1]
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 30</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：折半查找 + 二进制枚举**

根据题目要求，要判断是否可以将数组 `nums` 划分为两个子数组 $A$ 和 $B$，使得两个子数组的平均值相等。

我们记数组 `nums` 的和为 $s$，元素个数为 $n$。子数组 $A$ 的和以及个数分别为 $s_1$ 和 $k$，那么子数组 $B$ 的和为 $s_2 = s - s_1$，个数为 $n - k$，即：

$$
\frac{s_1}{k} = \frac{s_2}{n - k} = \frac{s-s_1}{n-k}
$$

整理可得：

$$
s_1 \times (n-k) = (s-s_1) \times k
$$

化简可得：

$$
\frac{s_1}{k} = \frac{s}{n}
$$

也就是说，要我们找出一个子数组 $A$，使得其平均值等于数组 `nums` 的平均值。我们考虑将数组 `nums` 每个元素都减去数组 `nums` 的平均值，这样问题就转化为了在数组 `nums` 中找出一个子数组，使得其和为 $0$。

但是，数组 `nums` 的平均值可能不是整数，浮点数计算可能存在精度问题，我们不妨将数组 `nums` 中的每个元素都乘以 $n$，即 $nums[i] \leftarrow nums[i] \times n$，上述式子就变成：

$$
\frac{s_1\times n}{k} = s
$$

此时我们将数组 `nums` 中每个元素都减去整数 $s$，题目就转化为：在数组 $nums$ 中找出一个子数组 $A$，使得其和为 $0$。

数组 `nums` 的长度范围为 $[1, 30]$，如果我们使用暴力枚举子数组的方法，时间复杂度为 $O(2^n)$，会超时。我们可以使用折半查找的方法，将时间复杂度降低到 $O(2^{n/2})$。

我们将数组 `nums` 分成左右两部分，那么子数组 $A$ 可能存在三种情况：

1. 子数组 $A$ 完全在数组 `nums` 的左半部分；
2. 子数组 $A$ 完全在数组 `nums` 的右半部分；
3. 子数组 $A$ 一部分在数组 `nums` 的左半部分，一部分在数组 `nums` 的右半部分。

我们可以使用二进制枚举的方法，先枚举左半部分所有子数组的和，如果存在一个子数组和为 $0$，直接返回 `true`，否则我们将其存入哈希表 `vis` 中；然后枚举右半部分所有子数组的和，如果存在一个子数组和为 $0$，直接返回 `true`，否则我们判断此时哈希表 `vis` 中是否存在该和的相反数，如果存在，直接返回 `true`。

需要注意的是，我们不能同时全选左半部分和右半部分，因为这样会导致子数组 $B$ 为空，这是不符合题意的。在实现上，我们只需要考虑数组的 $n-1$ 个数。

时间复杂度 $O(n\times 2^{\frac{n}{2}})$，空间复杂度 $O(2^{\frac{n}{2}})$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def splitArraySameAverage(self, nums: List[int]) -> bool:
        n = len(nums)
        if n == 1:
            return False
        s = sum(nums)
        for i, v in enumerate(nums):
            nums[i] = v * n - s
        m = n >> 1
        vis = set()
        for i in range(1, 1 << m):
            t = sum(v for j, v in enumerate(nums[:m]) if i >> j & 1)
            if t == 0:
                return True
            vis.add(t)
        for i in range(1, 1 << (n - m)):
            t = sum(v for j, v in enumerate(nums[m:]) if i >> j & 1)
            if t == 0 or (i != (1 << (n - m)) - 1 and -t in vis):
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        }
        int s = Arrays.stream(nums).sum();
        for (int i = 0; i < n; ++i) {
            nums[i] = nums[i] * n - s;
        }
        int m = n >> 1;
        Set<Integer> vis = new HashSet<>();
        for (int i = 1; i < 1 << m; ++i) {
            int t = 0;
            for (int j = 0; j < m; ++j) {
                if (((i >> j) & 1) == 1) {
                    t += nums[j];
                }
            }
            if (t == 0) {
                return true;
            }
            vis.add(t);
        }
        for (int i = 1; i < 1 << (n - m); ++i) {
            int t = 0;
            for (int j = 0; j < (n - m); ++j) {
                if (((i >> j) & 1) == 1) {
                    t += nums[m + j];
                }
            }
            if (t == 0 || (i != (1 << (n - m)) - 1) && vis.contains(-t)) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool splitArraySameAverage(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return false;
        int s = accumulate(nums.begin(), nums.end(), 0);
        for (int& v : nums) v = v * n - s;
        int m = n >> 1;
        unordered_set<int> vis;
        for (int i = 1; i < 1 << m; ++i) {
            int t = 0;
            for (int j = 0; j < m; ++j) if (i >> j & 1) t += nums[j];
            if (t == 0) return true;
            vis.insert(t);
        }
        for (int i = 1; i < 1 << (n - m); ++i) {
            int t = 0;
            for (int j = 0; j < (n - m); ++j) if (i >> j & 1) t += nums[m + j];
            if (t == 0 || (i != (1 << (n - m)) - 1 && vis.count(-t))) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func splitArraySameAverage(nums []int) bool {
	n := len(nums)
	if n == 1 {
		return false
	}
	s := 0
	for _, v := range nums {
		s += v
	}
	for i, v := range nums {
		nums[i] = v*n - s
	}
	m := n >> 1
	vis := map[int]bool{}
	for i := 1; i < 1<<m; i++ {
		t := 0
		for j, v := range nums[:m] {
			if (i >> j & 1) == 1 {
				t += v
			}
		}
		if t == 0 {
			return true
		}
		vis[t] = true
	}
	for i := 1; i < 1<<(n-m); i++ {
		t := 0
		for j, v := range nums[m:] {
			if (i >> j & 1) == 1 {
				t += v
			}
		}
		if t == 0 || (i != (1<<(n-m))-1 && vis[-t]) {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
