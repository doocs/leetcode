# [1497. 检查数组对是否可以被 k 整除](https://leetcode.cn/problems/check-if-array-pairs-are-divisible-by-k)

[English Version](/solution/1400-1499/1497.Check%20If%20Array%20Pairs%20Are%20Divisible%20by%20k/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code> 和一个整数 <code>k</code> ，其中数组长度是偶数，值为 <code>n</code> 。</p>

<p>现在需要把数组恰好分成 <code>n /&nbsp;2</code> 对，以使每对数字的和都能够被 <code>k</code> 整除。</p>

<p>如果存在这样的分法，请返回 <em>True</em> ；否则，返回 <em>False</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,4,5,10,6,7,8,9], k = 5
<strong>输出：</strong>true
<strong>解释：</strong>划分后的数字对为 (1,9),(2,8),(3,7),(4,6) 以及 (5,10) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,4,5,6], k = 7
<strong>输出：</strong>true
<strong>解释：</strong>划分后的数字对为 (1,6),(2,5) 以及 (3,4) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,4,5,6], k = 10
<strong>输出：</strong>false
<strong>解释：</strong>无法在将数组中的数字分为三对的同时满足每对数字和能够被 10 整除的条件。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>arr.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> 为偶数<meta charset="UTF-8" /></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：统计余数**

两个数 $a$ 和 $b$ 的和能被 $k$ 整除，当且仅当这两个数分别对 $k$ 取模的结果之和能被 $k$ 整除。

因此，我们可以统计数组中每个数对 $k$ 取模的结果，即余数，记录在数组 `cnt` 中。然后我们遍历数组 `cnt`，对于范围在 $[1,..k-1]$ 的每个数 $i$，如果 $cnt[i]$ 和 $cnt[k-i]$ 的值不相等，说明无法将数组中的数字分为 $n/2$ 对，使得每对数字的和都能被 $k$ 整除。如果 $cnt[0]$ 的值不是偶数，也说明无法将数组中的数字分为 $n/2$ 对，使得每对数字的和都能被 $k$ 整除。

时间复杂度 $O(n)$，空间复杂度 $O(k)$。其中 $n$ 为数组 `arr` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canArrange(self, arr: List[int], k: int) -> bool:
        cnt = Counter(x % k for x in arr)
        return cnt[0] % 2 == 0 and all(cnt[i] == cnt[k - i] for i in range(1, k))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canArrange(int[] arr, int k) {
        int[] cnt = new int[k];
        for (int x : arr) {
            ++cnt[(x % k + k) % k];
        }
        for (int i = 1; i < k; ++i) {
            if (cnt[i] != cnt[k - i]) {
                return false;
            }
        }
        return cnt[0] % 2 == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canArrange(vector<int>& arr, int k) {
        vector<int> cnt(k);
        for (int& x : arr) {
            ++cnt[((x % k) + k) % k];
        }
        for (int i = 1; i < k; ++i) {
            if (cnt[i] != cnt[k - i]) {
                return false;
            }
        }
        return cnt[0] % 2 == 0;
    }
};
```

### **Go**

```go
func canArrange(arr []int, k int) bool {
	cnt := make([]int, k)
	for _, x := range arr {
		cnt[(x%k+k)%k]++
	}
	for i := 1; i < k; i++ {
		if cnt[i] != cnt[k-i] {
			return false
		}
	}
	return cnt[0]%2 == 0
}
```

### **...**

```

```

<!-- tabs:end -->
