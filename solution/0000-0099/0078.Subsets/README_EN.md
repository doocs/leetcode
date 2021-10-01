# [78. Subsets](https://leetcode.com/problems/subsets)

[中文文档](/solution/0000-0099/0078.Subsets/README.md)

## Description

<p>Given an integer array <code>nums</code> of <strong>unique</strong> elements, return <em>all possible subsets (the power set)</em>.</p>

<p>The solution set <strong>must not</strong> contain duplicate subsets. Return the solution in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> [[],[0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li>All the numbers of&nbsp;<code>nums</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = []

        def dfs(i, n, t):
            res.append(t.copy())
            if i == n:
                return
            for j in range(i, n):
                t.append(nums[j])
                dfs(j + 1, n, t)
                t.pop()

        dfs(0, len(nums), [])
        return res
```

### **Java**

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int i, int[] nums, List<Integer> t, List<List<Integer>> res) {
        res.add(new ArrayList<>(t));
        if (i == nums.length) {
            return;
        }
        for (int j = i; j < nums.length; ++j) {
            t.add(nums[j]);
            dfs(j + 1, nums, t, res);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> res;
        vector<int> t;
        dfs(0, nums, t, res);
        return res;
    }

    void dfs(int i, vector<int>& nums, vector<int> t, vector<vector<int>>& res) {
        res.push_back(t);
        if (i == nums.size()) return;
        for (int j = i; j < nums.size(); ++j)
        {
            t.push_back(nums[j]);
            dfs(j + 1, nums, t, res);
            t.pop_back();
        }
    }
};
```

### **Go**

```go
func subsets(nums []int) [][]int {
	var res [][]int
	var t []int
	dfs(0, nums, t, &res)
	return res
}

func dfs(i int, nums, t []int, res *[][]int) {
	cp := make([]int, len(t))
	copy(cp, t)
	*res = append(*res, cp)
	if i == len(nums) {
		return
	}
	for j := i; j < len(nums); j++ {
		t = append(t, nums[j])
		dfs(j+1, nums, t, res)
		t = t[:len(t)-1]
	}
}
```

### **...**

```

```

<!-- tabs:end -->
