# [1788. Maximize the Beauty of the Garden](https://leetcode.com/problems/maximize-the-beauty-of-the-garden)

[中文文档](/solution/1700-1799/1788.Maximize%20the%20Beauty%20of%20the%20Garden/README.md)

## Description

<p>There is a garden of <code>n</code> flowers, and each flower has an integer beauty value. The flowers are arranged in a line. You are given an integer array <code>flowers</code> of size <code>n</code> and each <code>flowers[i]</code> represents the beauty of the <code>i<sup>th</sup></code> flower.</p>

<p>A garden is <strong>valid</strong> if it meets these conditions:</p>

<ul>
    <li>The garden has at least two flowers.</li>
    <li>The first and the last flower of the garden have the same beauty value.</li>
</ul>

<p>As the appointed gardener, you have the ability to <strong>remove</strong> any (possibly none) flowers from the garden. You want to remove flowers in a way that makes the remaining garden <strong>valid</strong>. The beauty of the garden is the sum of the beauty of all the remaining flowers.</p>

<p>Return the maximum possible beauty of some <strong>valid</strong> garden after you have removed any (possibly none) flowers.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> flowers = [1,2,3,1,2]

<strong>Output:</strong> 8

<strong>Explanation:</strong> You can produce the valid garden [2,3,1,2] to have a total beauty of 2 + 3 + 1 + 2 = 8.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> flowers = [100,1,1,-3,1]

<strong>Output:</strong> 3

<strong>Explanation:</strong> You can produce the valid garden [1,1,1] to have a total beauty of 1 + 1 + 1 = 3.

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> flowers = [-1,-2,0,-1]

<strong>Output:</strong> -2

<strong>Explanation:</strong> You can produce the valid garden [-1,-1] to have a total beauty of -1 + -1 = -2.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>2 &lt;= flowers.length &lt;= 10<sup>5</sup></code></li>
    <li><code>-10<sup>4</sup> &lt;= flowers[i] &lt;= 10<sup>4</sup></code></li>
    <li>It is possible to create a valid garden by removing some (possibly none) flowers.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumBeauty(self, flowers: List[int]) -> int:
        s = [0] * (len(flowers) + 1)
        d = {}
        ans = -inf
        for i, v in enumerate(flowers):
            if v in d:
                ans = max(ans, s[i] - s[d[v] + 1] + v * 2)
            else:
                d[v] = i
            s[i + 1] = s[i] + max(v, 0)
        return ans
```

### **Java**

```java
class Solution {
    public int maximumBeauty(int[] flowers) {
        int[] s = new int[flowers.length + 1];
        Map<Integer, Integer> d = new HashMap<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < flowers.length; ++i) {
            int v = flowers[i];
            if (d.containsKey(v)) {
                ans = Math.max(ans, s[i] - s[d.get(v) + 1] + v * 2);
            } else {
                d.put(v, i);
            }
            s[i + 1] = s[i] + Math.max(v, 0);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumBeauty(vector<int>& flowers) {
        int n = flowers.size();
        vector<int> s(n + 1);
        unordered_map<int, int> d;
        int ans = INT_MIN;
        for (int i = 0; i < n; ++i) {
            int v = flowers[i];
            if (d.count(v)) {
                ans = max(ans, s[i] - s[d[v] + 1] + v * 2);
            } else {
                d[v] = i;
            }
            s[i + 1] = s[i] + max(v, 0);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumBeauty(flowers []int) int {
	n := len(flowers)
	s := make([]int, n+1)
	d := map[int]int{}
	ans := math.MinInt32
	for i, v := range flowers {
		if j, ok := d[v]; ok {
			ans = max(ans, s[i]-s[j+1]+v*2)
		} else {
			d[v] = i
		}
		s[i+1] = s[i] + max(v, 0)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
