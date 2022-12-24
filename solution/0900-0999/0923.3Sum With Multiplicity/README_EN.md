# [923. 3Sum With Multiplicity](https://leetcode.com/problems/3sum-with-multiplicity)

[中文文档](/solution/0900-0999/0923.3Sum%20With%20Multiplicity/README.md)

## Description

<p>Given an integer array <code>arr</code>, and an integer <code>target</code>, return the number of tuples <code>i, j, k</code> such that <code>i &lt; j &lt; k</code> and <code>arr[i] + arr[j] + arr[k] == target</code>.</p>

<p>As the answer can be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1,2,2,3,3,4,4,5,5], target = 8
<strong>Output:</strong> 20
<strong>Explanation: </strong>
Enumerating by the values (arr[i], arr[j], arr[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1,2,2,2,2], target = 5
<strong>Output:</strong> 12
<strong>Explanation: </strong>
arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,1,3], target = 6
<strong>Output:</strong> 1
<strong>Explanation:</strong> (1, 2, 3) occured one time in the array so we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 3000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 100</code></li>
	<li><code>0 &lt;= target &lt;= 300</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def threeSumMulti(self, arr: List[int], target: int) -> int:
        cnt = Counter(arr)
        ans = 0
        mod = 10**9 + 7
        for j, b in enumerate(arr):
            cnt[b] -= 1
            for i in range(j):
                a = arr[i]
                c = target - a - b
                ans = (ans + cnt[c]) % mod
        return ans
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int threeSumMulti(int[] arr, int target) {
        int[] cnt = new int[101];
        for (int v : arr) {
            ++cnt[v];
        }
        long ans = 0;
        for (int j = 0; j < arr.length; ++j) {
            int b = arr[j];
            --cnt[b];
            for (int i = 0; i < j; ++i) {
                int a = arr[i];
                int c = target - a - b;
                if (c >= 0 && c <= 100) {
                    ans = (ans + cnt[c]) % MOD;
                }
            }
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int threeSumMulti(vector<int>& arr, int target) {
        int cnt[101] = {0};
        for (int& v : arr) {
            ++cnt[v];
        }
        long ans = 0;
        for (int j = 0; j < arr.size(); ++j) {
            int b = arr[j];
            --cnt[b];
            for (int i = 0; i < j; ++i) {
                int a = arr[i];
                int c = target - a - b;
                if (c >= 0 && c <= 100) {
                    ans += cnt[c];
                    ans %= mod;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func threeSumMulti(arr []int, target int) int {
	const mod int = 1e9 + 7
	cnt := [101]int{}
	for _, v := range arr {
		cnt[v]++
	}
	ans := 0
	for j, b := range arr {
		cnt[b]--
		for i := 0; i < j; i++ {
			a := arr[i]
			c := target - a - b
			if c >= 0 && c <= 100 {
				ans += cnt[c]
				ans %= mod
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
