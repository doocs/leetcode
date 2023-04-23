# [2654. 使数组所有元素变成 1 的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1)

[English Version](/solution/2600-2699/2654.Minimum%20Number%20of%20Operations%20to%20Make%20All%20Array%20Elements%20Equal%20to%201/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的 <strong>正</strong>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;。你可以对数组执行以下操作 <strong>任意</strong>&nbsp;次：</p>

<ul>
	<li>选择一个满足&nbsp;<code>0 &lt;= i &lt; n - 1</code>&nbsp;的下标 <code>i</code>&nbsp;，将&nbsp;<code>nums[i]</code> 或者&nbsp;<code>nums[i+1]</code>&nbsp;两者之一替换成它们的最大公约数。</li>
</ul>

<p>请你返回使数组 <code>nums</code>&nbsp;中所有元素都等于 <code>1</code>&nbsp;的 <strong>最少</strong>&nbsp;操作次数。如果无法让数组全部变成 <code>1</code>&nbsp;，请你返回 <code>-1</code>&nbsp;。</p>

<p>两个正整数的最大公约数指的是能整除这两个数的最大正整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,6,3,4]
<b>输出：</b>4
<b>解释：</b>我们可以执行以下操作：
- 选择下标 i = 2 ，将 nums[2] 替换为 gcd(3,4) = 1 ，得到 nums = [2,6,1,4] 。
- 选择下标 i = 1 ，将 nums[1] 替换为 gcd(6,1) = 1 ，得到 nums = [2,1,1,4] 。
- 选择下标 i = 0 ，将 nums[0] 替换为 gcd(2,1) = 1 ，得到 nums = [1,1,1,4] 。
- 选择下标 i = 2 ，将 nums[3] 替换为 gcd(1,4) = 1 ，得到 nums = [1,1,1,1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,10,6,14]
<b>输出：</b>-1
<b>解释：</b>无法将所有元素都变成 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

我们先统计数组 $nums$ 中 $1$ 的个数 $cnt$，如果 $cnt \gt 0$，那么只需要 $n - cnt$ 次操作，就可以将整个数组变成 $1$。

否则，我们需要首先将数组中的一个元素变成 $1$，然后剩余的最小操作次数就是 $n - 1$。

考虑如何将数组中的一个元素变成 $1$，并且使得操作次数尽可能小。实际上，我们只需要找到一个最小的连续子数组区间 $nums[i,..j]$，使得子数组中所有元素的最大公约数为 $1$，子数组区间长度为 $mi = \min(mi, j - i + 1)$。最后我们总的操作次数就是 $n - 1 + mi - 1$。

时间复杂度 $O(n \times (n + \log M))$，空间复杂度 $(\log M)$。其中 $n$ 和 $M$ 分别是数组 $nums$ 的长度以及数组 $nums$ 中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        cnt = nums.count(1)
        if cnt:
            return n - cnt
        mi = n + 1
        for i in range(n):
            g = 0
            for j in range(i, n):
                g = gcd(g, nums[j])
                if g == 1:
                    mi = min(mi, j - i + 1)
        return -1 if mi > n else n - 1 + mi - 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for (int x : nums) {
            if (x == 1) {
                ++cnt;
            }
        }
        if (cnt > 0) {
            return n - cnt;
        }
        int mi = n + 1;
        for (int i = 0; i < n; ++i) {
            int g = 0;
            for (int j = i; j < n; ++j) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    mi = Math.min(mi, j - i + 1);
                }
            }
        }
        return mi > n ? -1 : n - 1 + mi - 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();
        int cnt = 0;
        for (int x : nums) {
            if (x == 1) {
                ++cnt;
            }
        }
        if (cnt) {
            return n - cnt;
        }
        int mi = n + 1;
        for (int i = 0; i < n; ++i) {
            int g = 0;
            for (int j = i; j < n; ++j) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    mi = min(mi, j - i + 1);
                }
            }
        }
        return mi > n ? -1 : n - 1 + mi - 1;
    }
};
```

### **Go**

```go
func minOperations(nums []int) int {
	n := len(nums)
	cnt := 0
	for _, x := range nums {
		if x == 1 {
			cnt++
		}
	}
	if cnt > 0 {
		return n - cnt
	}
	mi := n + 1
	for i := 0; i < n; i++ {
		g := 0
		for j := i; j < n; j++ {
			g = gcd(g, nums[j])
			if g == 1 {
				mi = min(mi, j-i+1)
			}
		}
	}
	if mi > n {
		return -1
	}
	return n - 1 + mi - 1
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minOperations(nums: number[]): number {
    const n = nums.length;
    let cnt = 0;
    for (const x of nums) {
        if (x === 1) {
            ++cnt;
        }
    }
    if (cnt > 0) {
        return n - cnt;
    }
    let mi = n + 1;
    for (let i = 0; i < n; ++i) {
        let g = 0;
        for (let j = i; j < n; ++j) {
            g = gcd(g, nums[j]);
            if (g === 1) {
                mi = Math.min(mi, j - i + 1);
            }
        }
    }
    return mi > n ? -1 : n - 1 + mi - 1;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
```

### **...**

```

```

<!-- tabs:end -->
