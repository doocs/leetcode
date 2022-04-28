# [1636. Sort Array by Increasing Frequency](https://leetcode.com/problems/sort-array-by-increasing-frequency)

[中文文档](/solution/1600-1699/1636.Sort%20Array%20by%20Increasing%20Frequency/README.md)

## Description

<p>Given an array of integers <code>nums</code>, sort the array in <strong>increasing</strong> order based on the frequency of the values. If multiple values have the same frequency, sort them in <strong>decreasing</strong> order.</p>

<p>Return the <em>sorted array</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,2,2,3]
<strong>Output:</strong> [3,1,1,2,2,2]
<strong>Explanation:</strong> &#39;3&#39; has a frequency of 1, &#39;1&#39; has a frequency of 2, and &#39;2&#39; has a frequency of 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,3,2]
<strong>Output:</strong> [1,3,3,2,2]
<strong>Explanation:</strong> &#39;2&#39; and &#39;3&#39; both have a frequency of 2, so they are sorted in decreasing order.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,1,-6,4,5,-6,1,4,1]
<strong>Output:</strong> [5,-1,4,4,-6,-6,1,1,1]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def frequencySort(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        cnt = sorted(cnt.items(), key=lambda x: (x[1], -x[0]))
        ans = []
        for v, freq in cnt:
            ans.extend([v] * freq)
        return ans
```

### **Java**

```java
class Solution {
    public int[] frequencySort(int[] nums) {
        int[] cnt = new int[201];
        for (int v : nums) {
            ++cnt[v + 100];
        }
        List<int[]> t = new ArrayList<>();
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                t.add(new int[]{cnt[i], i});
            }
        }
        t.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int[] ans = new int[nums.length];
        int i = 0;
        for (int[] e : t) {
            for (int j = 0; j < e[0]; ++j) {
                ans[i++] = e[1] - 100;
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
    vector<int> frequencySort(vector<int>& nums) {
        vector<int> cnt(201);
        for (int& v : nums) ++cnt[v + 100];
        vector<vector<int>> t;
        for (int i = 0; i < cnt.size(); ++i)
            if (cnt[i])
                t.push_back({cnt[i], -i});
        sort(t.begin(), t.end());
        vector<int> ans;
        for (auto& e : t)
            for (int j = 0; j < e[0]; ++j)
                ans.push_back(-e[1] - 100);
        return ans;
    }
};
```

### **Go**

```go
func frequencySort(nums []int) []int {
	cnt := make([]int, 201)
	for _, v := range nums {
		cnt[v+100]++
	}
	var t [][]int
	for i, v := range cnt {
		if v > 0 {
			t = append(t, []int{v, i})
		}
	}
	sort.Slice(t, func(i, j int) bool {
		if t[i][0] == t[j][0] {
			return t[i][1] > t[j][1]
		}
		return t[i][0] < t[j][0]
	})
	var ans []int
	for _, e := range t {
		for i := 0; i < e[0]; i++ {
			ans = append(ans, e[1]-100)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
