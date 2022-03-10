# [1497. Check If Array Pairs Are Divisible by k](https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k)

[中文文档](/solution/1400-1499/1497.Check%20If%20Array%20Pairs%20Are%20Divisible%20by%20k/README.md)

## Description

<p>Given an array of integers <code>arr</code> of even length <code>n</code> and an integer <code>k</code>.</p>

<p>We want to divide the array into exactly <code>n / 2</code> pairs such that the sum of each pair is divisible by <code>k</code>.</p>

<p>Return <code>true</code><em> If you can find a way to do that or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4,5,10,6,7,8,9], k = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4,5,6], k = 7
<strong>Output:</strong> true
<strong>Explanation:</strong> Pairs are (1,6),(2,5) and(3,4).
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4,5,6], k = 10
<strong>Output:</strong> false
<strong>Explanation:</strong> You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>arr.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> is even.</li>
	<li><code>-10<sup>9</sup> &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canArrange(self, arr: List[int], k: int) -> bool:
        mod = [0] * k
        for v in arr:
            mod[v % k] += 1
        return all(mod[i] == mod[k - i] for i in range(1, k)) and mod[0] % 2 == 0
```

### **Java**

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
