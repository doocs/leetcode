# [16.06. Smallest Difference](https://leetcode.cn/problems/smallest-difference-lcci)

[中文文档](/lcci/16.06.Smallest%20Difference/README.md)

## Description

<p>Given two arrays of integers, compute the pair of values (one value in each array) with the smallest (non-negative) difference. Return the difference.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}

<strong>Output: </strong> 3, the pair (11, 8)

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 100000</code></li>
	<li><code>-2147483648 &lt;= a[i], b[i] &lt;= 2147483647</code></li>
	<li>The result is in the range [-2147483648, 2147483647]</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        a.sort()
        b.sort()
        i = j = 0
        res = inf
        while i < len(a) and j < len(b):
            res = min(res, abs(a[i] - b[j]))
            if a[i] > b[j]:
                j += 1
            else:
                i += 1
        return res
```

### **Java**

```java
class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0, j = 0;
        long res = Long.MAX_VALUE;
        while (i < a.length && j < b.length) {
            res = Math.min(res, Math.abs((long) a[i] - (long) b[j]));
            if (a[i] > b[j]) {
                ++j;
            } else {
                ++i;
            }
        }
        return (int) res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int smallestDifference(vector<int>& a, vector<int>& b) {
        sort(a.begin(), a.end());
        sort(b.begin(), b.end());
        int i = 0, j = 0;
        long res = LONG_MAX;
        while (i < a.size() && j < b.size()) {
            res = min(res, abs((long)a[i] - (long)b[j]));
            if (a[i] > b[j])
                ++j;
            else
                ++i;
        }
        return res;
    }
};
```

### **Go**

```go
func smallestDifference(a []int, b []int) int {
	sort.Ints(a)
	sort.Ints(b)
	i, j, res := 0, 0, 2147483647
	for i < len(a) && j < len(b) {
		res = min(res, abs(a[i]-b[j]))
		if a[i] > b[j] {
			j++
		} else {
			i++
		}
	}
	return res
}

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
