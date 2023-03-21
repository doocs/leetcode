# [1983. Widest Pair of Indices With Equal Range Sum](https://leetcode.com/problems/widest-pair-of-indices-with-equal-range-sum)

[中文文档](/solution/1900-1999/1983.Widest%20Pair%20of%20Indices%20With%20Equal%20Range%20Sum/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> binary arrays <code>nums1</code> and <code>nums2</code>. Find the <strong>widest</strong> pair of indices <code>(i, j)</code> such that <code>i &lt;= j</code> and <code>nums1[i] + nums1[i+1] + ... + nums1[j] == nums2[i] + nums2[i+1] + ... + nums2[j]</code>.</p>

<p>The <strong>widest</strong> pair of indices is the pair with the <strong>largest</strong> <strong>distance</strong> between <code>i</code> and <code>j</code>. The <strong>distance</strong> between a pair of indices is defined as <code>j - i + 1</code>.</p>

<p>Return <em>the <strong>distance</strong> of the <strong>widest</strong> pair of indices. If no pair of indices meets the conditions, return </em><code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,1,0,1], nums2 = [0,1,1,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
If i = 1 and j = 3:
nums1[1] + nums1[2] + nums1[3] = 1 + 0 + 1 = 2.
nums2[1] + nums2[2] + nums2[3] = 1 + 1 + 0 = 2.
The distance between i and j is j - i + 1 = 3 - 1 + 1 = 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,1], nums2 = [1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
If i = 1 and j = 1:
nums1[1] = 1.
nums2[1] = 1.
The distance between i and j is j - i + 1 = 1 - 1 + 1 = 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0], nums2 = [1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no pairs of indices that meet the requirements.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1[i]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>nums2[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def widestPairOfIndices(self, nums1: List[int], nums2: List[int]) -> int:
        d = {0: -1}
        ans = s = 0
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            s += a - b
            if s in d:
                ans = max(ans, i - d[s])
            else:
                d[s] = i
        return ans
```

### **Java**

```java
class Solution {
    public int widestPairOfIndices(int[] nums1, int[] nums2) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int n = nums1.length;
        int s = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            s += nums1[i] - nums2[i];
            if (d.containsKey(s)) {
                ans = Math.max(ans, i - d.get(s));
            } else {
                d.put(s, i);
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
    int widestPairOfIndices(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> d;
        d[0] = -1;
        int ans = 0, s = 0;
        int n = nums1.size();
        for (int i = 0; i < n; ++i) {
            s += nums1[i] - nums2[i];
            if (d.count(s)) {
                ans = max(ans, i - d[s]);
            } else {
                d[s] = i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func widestPairOfIndices(nums1 []int, nums2 []int) (ans int) {
	d := map[int]int{0: -1}
	s := 0
	for i := range nums1 {
		s += nums1[i] - nums2[i]
		if j, ok := d[s]; ok {
			ans = max(ans, i-j)
		} else {
			d[s] = i
		}
	}
	return
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
