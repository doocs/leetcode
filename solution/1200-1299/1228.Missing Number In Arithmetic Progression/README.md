# [1228. 等差数列中缺失的数字](https://leetcode.cn/problems/missing-number-in-arithmetic-progression)

[English Version](/solution/1200-1299/1228.Missing%20Number%20In%20Arithmetic%20Progression/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在某个数组&nbsp;<code>arr</code>&nbsp;中，值符合等差数列的数值规律：在&nbsp;<code>0 &lt;= i &lt; arr.length - 1</code>&nbsp;的前提下，<code>arr[i+1] - arr[i]</code>&nbsp;的值都相等。</p>

<p>我们会从该数组中删除一个 <strong>既不是第一个 </strong>也<strong>&nbsp;不是最后一个的值</strong>，得到一个新的数组&nbsp;&nbsp;<code>arr</code>。</p>

<p>给你这个缺值的数组&nbsp;<code>arr</code>，返回 <em>被删除的那个数</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [5,7,11,13]
<strong>输出：</strong>9
<strong>解释：</strong>原来的数组是 [5,7,<strong>9</strong>,11,13]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [15,13,12]
<strong>输出：</strong>14
<strong>解释：</strong>原来的数组是 [15,<strong>14</strong>,13,12]。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
	<li>给定的数组 <strong>保证</strong> 是一个有效的数组。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def missingNumber(self, arr: List[int]) -> int:
        n = len(arr)
        d = (arr[-1] - arr[0]) // n
        for i in range(1, n):
            if arr[i] != arr[i - 1] + d:
                return arr[i - 1] + d
        return arr[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int d = (arr[n - 1] - arr[0]) / n;
        for (int i = 1; i < n; ++i) {
            if (arr[i] != arr[i - 1] + d) {
                return arr[i - 1] + d;
            }
        }
        return arr[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int missingNumber(vector<int>& arr) {
        int n = arr.size();
        int d = (arr[n - 1] - arr[0]) / n;
        for (int i = 1; i < n; ++i)
            if (arr[i] != arr[i - 1] + d) return arr[i - 1] + d;
        return arr[0];
    }
};
```

### **Go**

```go
func missingNumber(arr []int) int {
	n := len(arr)
	d := (arr[n-1] - arr[0]) / n
	for i := 1; i < n; i++ {
		if arr[i] != arr[i-1]+d {
			return arr[i-1] + d
		}
	}
	return arr[0]
}
```

### **...**

```

```

<!-- tabs:end -->
