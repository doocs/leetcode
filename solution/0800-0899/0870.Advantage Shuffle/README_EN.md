# [870. Advantage Shuffle](https://leetcode.com/problems/advantage-shuffle)

[中文文档](/solution/0800-0899/0870.Advantage%20Shuffle/README.md)

## Description

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> both of the same length. The <strong>advantage</strong> of <code>nums1</code> with respect to <code>nums2</code> is the number of indices <code>i</code> for which <code>nums1[i] &gt; nums2[i]</code>.</p>

<p>Return <em>any permutation of </em><code>nums1</code><em> that maximizes its <strong>advantage</strong> with respect to </em><code>nums2</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums1 = [2,7,11,15], nums2 = [1,10,4,11]
<strong>Output:</strong> [2,11,7,15]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums1 = [12,24,8,32], nums2 = [13,25,32,11]
<strong>Output:</strong> [24,32,8,12]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2.length == nums1.length</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def advantageCount(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        t = [(v, i) for i, v in enumerate(nums2)]
        t.sort()
        n = len(nums2)
        ans = [0] * n
        i, j = 0, n - 1
        for v in nums1:
            if v <= t[i][0]:
                ans[t[j][1]] = v
                j -= 1
            else:
                ans[t[i][1]] = v
                i += 1
        return ans
```

### **Java**

```java
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] t = new int[n][2];
        for (int i = 0; i < n; ++i) {
            t[i] = new int[]{nums2[i], i};
        }
        Arrays.sort(t, (a, b) -> a[0] - b[0]);
        Arrays.sort(nums1);
        int[] ans = new int[n];
        int i = 0, j = n - 1;
        for (int v : nums1) {
            if (v <= t[i][0]) {
                ans[t[j--][1]] = v;
            } else {
                ans[t[i++][1]] = v;
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
    vector<int> advantageCount(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<pair<int, int>> t;
        for (int i = 0; i < n; ++i) t.push_back({nums2[i], i});
        sort(t.begin(), t.end());
        sort(nums1.begin(), nums1.end());
        int i = 0, j = n - 1;
        vector<int> ans(n);
        for (int v : nums1) {
            if (v <= t[i].first)
                ans[t[j--].second] = v;
            else
                ans[t[i++].second] = v;
        }
        return ans;
    }
};
```

### **Go**

```go
func advantageCount(nums1 []int, nums2 []int) []int {
	n := len(nums1)
	t := make([][]int, n)
	for i, v := range nums2 {
		t[i] = []int{v, i}
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i][0] < t[j][0]
	})
	sort.Ints(nums1)
	ans := make([]int, n)
	i, j := 0, n-1
	for _, v := range nums1 {
		if v <= t[i][0] {
			ans[t[j][1]] = v
			j--
		} else {
			ans[t[i][1]] = v
			i++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
