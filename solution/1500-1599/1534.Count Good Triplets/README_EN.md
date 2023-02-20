# [1534. Count Good Triplets](https://leetcode.com/problems/count-good-triplets)

[中文文档](/solution/1500-1599/1534.Count%20Good%20Triplets/README.md)

## Description

<p>Given an array of integers <code>arr</code>, and three integers&nbsp;<code>a</code>,&nbsp;<code>b</code>&nbsp;and&nbsp;<code>c</code>. You need to find the number of good triplets.</p>

<p>A triplet <code>(arr[i], arr[j], arr[k])</code>&nbsp;is <strong>good</strong> if the following conditions are true:</p>

<ul>

    <li><code>0 &lt;= i &lt; j &lt; k &lt;&nbsp;arr.length</code></li>

    <li><code>|arr[i] - arr[j]| &lt;= a</code></li>

    <li><code>|arr[j] - arr[k]| &lt;= b</code></li>

    <li><code>|arr[i] - arr[k]| &lt;= c</code></li>

</ul>

<p>Where <code>|x|</code> denotes the absolute value of <code>x</code>.</p>

<p>Return<em> the number of good triplets</em>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3

<strong>Output:</strong> 4

<strong>Explanation:</strong>&nbsp;There are 4 good triplets: [(3,0,1), (3,0,1), (3,1,1), (0,1,1)].

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> arr = [1,1,2,2,3], a = 0, b = 0, c = 1

<strong>Output:</strong> 0

<strong>Explanation: </strong>No triplet satisfies all conditions.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>3 &lt;= arr.length &lt;= 100</code></li>
    <li><code>0 &lt;= arr[i] &lt;= 1000</code></li>
    <li><code>0 &lt;= a, b, c &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countGoodTriplets(self, arr: List[int], a: int, b: int, c: int) -> int:
        ans, n = 0, len(arr)
        for i in range(n):
            for j in range(i + 1, n):
                for k in range(j + 1, n):
                    ans += abs(arr[i] - arr[j]) <= a \
                        and abs(arr[j] - arr[k]) <= b and abs(arr[i] - arr[k]) <= c
        return ans
```

### **Java**

```java
class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b
                        && Math.abs(arr[i] - arr[k]) <= c) {
                        ++ans;
                    }
                }
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
    int countGoodTriplets(vector<int>& arr, int a, int b, int c) {
        int n = arr.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    ans += abs(arr[i] - arr[j]) <= a && abs(arr[j] - arr[k]) <= b && abs(arr[i] - arr[k]) <= c;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countGoodTriplets(arr []int, a int, b int, c int) (ans int) {
	n := len(arr)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for k := j + 1; k < n; k++ {
				if abs(arr[i]-arr[j]) <= a && abs(arr[j]-arr[k]) <= b && abs(arr[i]-arr[k]) <= c {
					ans++
				}
			}
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
