# [2226. Maximum Candies Allocated to K Children](https://leetcode.com/problems/maximum-candies-allocated-to-k-children)

[中文文档](/solution/2200-2299/2226.Maximum%20Candies%20Allocated%20to%20K%20Children/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>candies</code>. Each element in the array denotes a pile of candies of size <code>candies[i]</code>. You can divide each pile into any number of <strong>sub piles</strong>, but you <strong>cannot</strong> merge two piles together.</p>

<p>You are also given an integer <code>k</code>. You should allocate piles of candies to <code>k</code> children such that each child gets the <strong>same</strong> number of candies. Each child can take <strong>at most one</strong> pile of candies and some piles of candies may go unused.</p>

<p>Return <em>the <strong>maximum number of candies</strong> each child can get.</em></p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> candies = [5,8,6], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> We can divide candies[1] into 2 piles of size 5 and 3, and candies[2] into 2 piles of size 5 and 1. We now have five piles of candies of sizes 5, 5, 3, 5, and 1. We can allocate the 3 piles of size 5 to 3 children. It can be proven that each child cannot receive more than 5 candies.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> candies = [2,5], k = 11
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are 11 children but only 7 candies in total, so it is impossible to ensure each child receives at least one candy. Thus, each child gets no candy and the answer is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= candies.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= candies[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>12</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumCandies(self, candies: List[int], k: int) -> int:
        left, right = 0, max(candies)
        while left < right:
            mid = (left + right + 1) >> 1
            cnt = sum(v // mid for v in candies)
            if cnt >= k:
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

```java
class Solution {
    public int maximumCandies(int[] candies, long k) {
        int left = 0, right = (int) 1e7;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            long cnt = 0;
            for (int v : candies) {
                cnt += v / mid;
            }
            if (cnt >= k) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumCandies(vector<int>& candies, long long k) {
        int left = 0, right = 1e7;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            long long cnt = 0;
            for (int& v : candies) cnt += v / mid;
            if (cnt >= k)
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }
};
```

### **Go**

```go
func maximumCandies(candies []int, k int64) int {
	left, right := 0, int(1e7)
	for left < right {
		mid := (left + right + 1) >> 1
		var cnt int64
		for _, v := range candies {
			cnt += int64(v / mid)
		}
		if cnt >= k {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
