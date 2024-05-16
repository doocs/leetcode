---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1228.Missing%20Number%20In%20Arithmetic%20Progression/README_EN.md
rating: 1244
source: Biweekly Contest 11 Q1
tags:
    - Array
    - Math
---

# [1228. Missing Number In Arithmetic Progression 🔒](https://leetcode.com/problems/missing-number-in-arithmetic-progression)

[中文文档](/solution/1200-1299/1228.Missing%20Number%20In%20Arithmetic%20Progression/README.md)

## Description

<p>In some array <code>arr</code>, the values were in arithmetic progression: the values <code>arr[i + 1] - arr[i]</code> are all equal for every <code>0 &lt;= i &lt; arr.length - 1</code>.</p>

<p>A value from <code>arr</code> was removed that <strong>was not the first or last value in the array</strong>.</p>

<p>Given <code>arr</code>, return <em>the removed value</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [5,7,11,13]
<strong>Output:</strong> 9
<strong>Explanation:</strong> The previous array was [5,7,<strong>9</strong>,11,13].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [15,13,12]
<strong>Output:</strong> 14
<strong>Explanation:</strong> The previous array was [15,<strong>14</strong>,13,12].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
	<li>The given array is <strong>guaranteed</strong> to be a valid array.</li>
</ul>

## Solutions

### Solution 1: Arithmetic Series Sum Formula

The sum formula for an arithmetic series is $\frac{n(a_1 + a_n)}{2}$, where $n$ is the number of terms in the arithmetic series, $a_1$ is the first term of the arithmetic series, and $a_n$ is the last term of the arithmetic series.

Since the array given in the problem is an arithmetic series and is missing a number, the number of terms in the array is $n + 1$, the first term is $a_1$, and the last term is $a_n$, so the sum of the array is $\frac{n + 1}{2}(a_1 + a_n)$.

Therefore, the missing number is $\frac{n + 1}{2}(a_1 + a_n) - \sum_{i = 0}^n a_i$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Where $n$ is the length of the array.

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

### Solution 2

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
