# [2523. 范围内最接近的两个质数](https://leetcode.cn/problems/closest-prime-numbers-in-range)

[English Version](/solution/2500-2599/2523.Closest%20Prime%20Numbers%20in%20Range/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个正整数&nbsp;<code>left</code> 和&nbsp;<code>right</code>&nbsp;，请你找到两个整数&nbsp;<code>num1</code> 和&nbsp;<code>num2</code>&nbsp;，它们满足：</p>

<ul>
	<li><code>left &lt;= nums1 &lt; nums2 &lt;= right&nbsp;</code>&nbsp;。</li>
	<li><code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;都是 <strong>质数</strong>&nbsp;。</li>
	<li><code>nums2 - nums1</code>&nbsp;是满足上述条件的质数对中的 <strong>最小值</strong>&nbsp;。</li>
</ul>

<p>请你返回正整数数组&nbsp;<code>ans = [nums1, nums2]</code>&nbsp;。如果有多个整数对满足上述条件，请你返回&nbsp;<code>nums1</code>&nbsp;最小的质数对。如果不存在符合题意的质数对，请你返回&nbsp;<code>[-1, -1]</code>&nbsp;。</p>

<p>如果一个整数大于&nbsp;<code>1</code>&nbsp;，且只能被&nbsp;<code>1</code> 和它自己整除，那么它是一个质数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>left = 10, right = 19
<b>输出：</b>[11,13]
<b>解释：</b>10 到 19 之间的质数为 11 ，13 ，17 和 19 。
质数对的最小差值是 2 ，[11,13] 和 [17,19] 都可以得到最小差值。
由于 11 比 17 小，我们返回第一个质数对。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>left = 4, right = 6
<b>输出：</b>[-1,-1]
<b>解释：</b>给定范围内只有一个质数，所以题目条件无法被满足。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：线性筛**

对于给定的范围 $[left, right]$，我们可以使用线性筛求出所有质数，然后从小到大遍历质数，找到相邻的两个质数，其差值最小的质数对即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n = right$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def closestPrimes(self, left: int, right: int) -> List[int]:
        cnt = 0
        st = [False] * (right + 1)
        prime = [0] * (right + 1)
        for i in range(2, right + 1):
            if not st[i]:
                prime[cnt] = i
                cnt += 1
            j = 0
            while prime[j] <= right // i:
                st[prime[j] * i] = 1
                if i % prime[j] == 0:
                    break
                j += 1
        p = [v for v in prime[:cnt] if left <= v <= right]
        mi = inf
        ans = [-1, -1]
        for a, b in pairwise(p):
            if (d := b - a) < mi:
                mi = d
                ans = [a, b]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] closestPrimes(int left, int right) {
        int cnt = 0;
        boolean[] st = new boolean[right + 1];
        int[] prime = new int[right + 1];
        for (int i = 2; i <= right; ++i) {
            if (!st[i]) {
                prime[cnt++] = i;
            }
            for (int j = 0; prime[j] <= right / i; ++j) {
                st[prime[j] * i] = true;
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
        int i = -1, j = -1;
        for (int k = 0; k < cnt; ++k) {
            if (prime[k] >= left && prime[k] <= right) {
                if (i == -1) {
                    i = k;
                }
                j = k;
            }
        }
        int[] ans = new int[] {-1, -1};
        if (i == j || i == -1) {
            return ans;
        }
        int mi = 1 << 30;
        for (int k = i; k < j; ++k) {
            int d = prime[k + 1] - prime[k];
            if (d < mi) {
                mi = d;
                ans[0] = prime[k];
                ans[1] = prime[k + 1];
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
    vector<int> closestPrimes(int left, int right) {
        int cnt = 0;
        bool st[right + 1];
        memset(st, 0, sizeof st);
        int prime[right + 1];
        for (int i = 2; i <= right; ++i) {
            if (!st[i]) {
                prime[cnt++] = i;
            }
            for (int j = 0; prime[j] <= right / i; ++j) {
                st[prime[j] * i] = true;
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
        int i = -1, j = -1;
        for (int k = 0; k < cnt; ++k) {
            if (prime[k] >= left && prime[k] <= right) {
                if (i == -1) {
                    i = k;
                }
                j = k;
            }
        }
        vector<int> ans = {-1, -1};
        if (i == j || i == -1) return ans;
        int mi = 1 << 30;
        for (int k = i; k < j; ++k) {
            int d = prime[k + 1] - prime[k];
            if (d < mi) {
                mi = d;
                ans[0] = prime[k];
                ans[1] = prime[k + 1];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func closestPrimes(left int, right int) []int {
	cnt := 0
	st := make([]bool, right+1)
	prime := make([]int, right+1)
	for i := 2; i <= right; i++ {
		if !st[i] {
			prime[cnt] = i
			cnt++
		}
		for j := 0; prime[j] <= right/i; j++ {
			st[prime[j]*i] = true
			if i%prime[j] == 0 {
				break
			}
		}
	}
	i, j := -1, -1
	for k := 0; k < cnt; k++ {
		if prime[k] >= left && prime[k] <= right {
			if i == -1 {
				i = k
			}
			j = k
		}
	}
	ans := []int{-1, -1}
	if i == j || i == -1 {
		return ans
	}
	mi := 1 << 30
	for k := i; k < j; k++ {
		d := prime[k+1] - prime[k]
		if d < mi {
			mi = d
			ans[0], ans[1] = prime[k], prime[k+1]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
