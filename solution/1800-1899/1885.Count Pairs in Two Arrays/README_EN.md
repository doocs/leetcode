# [1885. Count Pairs in Two Arrays](https://leetcode.com/problems/count-pairs-in-two-arrays)

[中文文档](/solution/1800-1899/1885.Count%20Pairs%20in%20Two%20Arrays/README.md)

## Description

<p>Given two integer arrays <code>nums1</code> and <code>nums2</code> of length <code>n</code>, count the pairs of indices <code>(i, j)</code> such that <code>i &lt; j</code> and <code>nums1[i] + nums1[j] &gt; nums2[i] + nums2[j]</code>.</p>

<p>Return <em>the <strong>number of pairs</strong> satisfying the condition.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,1,2,1], nums2 = [1,2,1,2]
<strong>Output:</strong> 1
<strong>Explanation</strong>: The pairs satisfying the condition are:
- (0, 2) where 2 + 2 &gt; 1 + 1.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,10,6,2], nums2 = [1,4,1,5]
<strong>Output:</strong> 5
<strong>Explanation</strong>: The pairs satisfying the condition are:
- (0, 1) where 1 + 10 &gt; 1 + 4.
- (0, 2) where 1 + 6 &gt; 1 + 1.
- (1, 2) where 10 + 6 &gt; 4 + 1.
- (1, 3) where 10 + 2 &gt; 4 + 5.
- (2, 3) where 6 + 2 &gt; 1 + 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPairs(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        d = [nums1[i] - nums2[i] for i in range(n)]
        d.sort()
        return sum(n - bisect_right(d, -v, lo=i + 1) for i, v in enumerate(d))
```

### **Java**

```java
class Solution {
    public long countPairs(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            d[i] = nums1[i] - nums2[i];
        }
        Arrays.sort(d);
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int left = i + 1, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (d[mid] > -d[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans += n - left;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countPairs(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<int> d(n);
        for (int i = 0; i < n; ++i) d[i] = nums1[i] - nums2[i];
        sort(d.begin(), d.end());
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = upper_bound(d.begin() + i + 1, d.end(), -d[i]) - d.begin();
            ans += n - j;
        }
        return ans;
    }
};
```

### **Go**

```go
func countPairs(nums1 []int, nums2 []int) int64 {
	n := len(nums1)
	d := make([]int, n)
	for i, v := range nums1 {
		d[i] = v - nums2[i]
	}
	sort.Ints(d)
	var ans int64
	for i, v := range d {
		left, right := i+1, n
		for left < right {
			mid := (left + right) >> 1
			if d[mid] > -v {
				right = mid
			} else {
				left = mid + 1
			}
		}
		ans += int64(n - left)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
