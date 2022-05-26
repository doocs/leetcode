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

两个数 a 和 b 的和能被 k 整除，当且仅当这两个数对 k 取模的结果 ak 和 bk 的和就能被 k 整除。

-   如果 ak = 0，需要找到另一个满足 bk = 0 的 b 进行配对；
-   如果 ak > 0，需要找到另一个满足 bk = k - ak 的 b 进行配对。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canArrange(self, arr: List[int], k: int) -> bool:
        mod = [0] * k
        for v in arr:
            mod[v % k] += 1
        return all(mod[i] == mod[k - i] for i in range(1, k)) and mod[0] % 2 == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canArrange(int[] arr, int k) {
        int[] mod = new int[k];
        for (int v : arr) {
            ++mod[(v % k + k) % k];
        }
        for (int i = 1; i < k; ++i) {
            if (mod[i] != mod[k - i]) {
                return false;
            }
        }
        return mod[0] % 2 == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canArrange(vector<int>& arr, int k) {
        vector<int> mod(k);
        for (int v : arr) ++mod[(v % k + k) % k];
        for (int i = 1; i < k; ++i)
            if (mod[i] != mod[k - i])
                return false;
        return mod[0] % 2 == 0;
    }
};
```

### **Go**

```go
func canArrange(arr []int, k int) bool {
	mod := make([]int, k)
	for _, v := range arr {
		mod[(v%k+k)%k]++
	}
	for i := 1; i < k; i++ {
		if mod[i] != mod[k-i] {
			return false
		}
	}
	return mod[0]%2 == 0
}
```

### **...**

```

```

<!-- tabs:end -->
