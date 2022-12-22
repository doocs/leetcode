# [491. Non-decreasing Subsequences](https://leetcode.com/problems/non-decreasing-subsequences)

[中文文档](/solution/0400-0499/0491.Non-decreasing%20Subsequences/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>all the different possible non-decreasing subsequences of the given array with at least two elements</em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,6,7,7]
<strong>Output:</strong> [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,4,3,2,1]
<strong>Output:</strong> [[4,4]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 15</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findSubsequences(self, nums: List[int]) -> List[List[int]]:
        def dfs(u, last, t):
            if u == len(nums):
                if len(t) > 1:
                    ans.append(t[:])
                return
            if nums[u] >= last:
                t.append(nums[u])
                dfs(u + 1, nums[u], t)
                t.pop()
            if nums[u] != last:
                dfs(u + 1, last, t)

        ans = []
        dfs(0, -1000, [])
        return ans
```

### **Java**

```java
class Solution {
    private int[] nums;
    private List<List<Integer>> ans;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        ans = new ArrayList<>();
        dfs(0, -1000, new ArrayList<>());
        return ans;
    }

    private void dfs(int u, int last, List<Integer> t) {
        if (u == nums.length) {
            if (t.size() > 1) {
                ans.add(new ArrayList<>(t));
            }
            return;
        }
        if (nums[u] >= last) {
            t.add(nums[u]);
            dfs(u + 1, nums[u], t);
            t.remove(t.size() - 1);
        }
        if (nums[u] != last) {
            dfs(u + 1, last, t);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> findSubsequences(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        dfs(0, -1000, t, nums, ans);
        return ans;
    }

    void dfs(int u, int last, vector<int>& t, vector<int>& nums, vector<vector<int>>& ans) {
        if (u == nums.size()) {
            if (t.size() > 1) ans.push_back(t);
            return;
        }
        if (nums[u] >= last) {
            t.push_back(nums[u]);
            dfs(u + 1, nums[u], t, nums, ans);
            t.pop_back();
        }
        if (nums[u] != last) dfs(u + 1, last, t, nums, ans);
    }
};
```

### **Go**

```go
func findSubsequences(nums []int) [][]int {
	var ans [][]int
	var dfs func(u, last int, t []int)
	dfs = func(u, last int, t []int) {
		if u == len(nums) {
			if len(t) > 1 {
				cp := make([]int, len(t))
				copy(cp, t)
				ans = append(ans, cp)
			}
			return
		}
		if nums[u] >= last {
			t = append(t, nums[u])
			dfs(u+1, nums[u], t)
			t = t[:len(t)-1]
		}
		if nums[u] != last {
			dfs(u+1, last, t)
		}
	}
	var t []int
	dfs(0, -1000, t)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
