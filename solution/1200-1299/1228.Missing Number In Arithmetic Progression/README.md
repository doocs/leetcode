# [1228. 等差数列中缺失的数字 🔒](https://leetcode.cn/problems/missing-number-in-arithmetic-progression)

[English Version](/solution/1200-1299/1228.Missing%20Number%20In%20Arithmetic%20Progression/README_EN.md)

<!-- tags:数组,数学 -->

<!-- difficulty:简单 -->

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

### 方法一：等差数列求和公式

等差数列求和公式为 $\frac{n(a_1 + a_n)}{2}$，其中 $n$ 为等差数列的项数，$a_1$ 为等差数列的首项，$a_n$ 为等差数列的末项。

因为题目中给出的数组是一个等差数列，且缺失了一个数，所以数组的项数为 $n + 1$，首项为 $a_1$，末项为 $a_n$，则数组的和为 $\frac{n + 1}{2}(a_1 + a_n)$。

因此，缺失的数为 $\frac{n + 1}{2}(a_1 + a_n) - \sum_{i = 0}^n a_i$。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def missingNumber(self, arr: List[int]) -> int:
        return (arr[0] + arr[-1]) * (len(arr) + 1) // 2 - sum(arr)
```

```java
class Solution {
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int x = (arr[0] + arr[n - 1]) * (n + 1) / 2;
        int y = Arrays.stream(arr).sum();
        return x - y;
    }
}
```

```cpp
class Solution {
public:
    int missingNumber(vector<int>& arr) {
        int n = arr.size();
        int x = (arr[0] + arr[n - 1]) * (n + 1) / 2;
        int y = accumulate(arr.begin(), arr.end(), 0);
        return x - y;
    }
};
```

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

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
