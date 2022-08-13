# [666. Path Sum IV](https://leetcode.com/problems/path-sum-iv)

[中文文档](/solution/0600-0699/0666.Path%20Sum%20IV/README.md)

## Description

<p>If the depth of a tree is smaller than <code>5</code>, then this tree can be represented by an array of three-digit integers. For each integer in this array:</p>

<ul>
	<li>The hundreds digit represents the depth <code>d</code> of this node where <code>1 &lt;= d &lt;= 4</code>.</li>
	<li>The tens digit represents the position <code>p</code> of this node in the level it belongs to where <code>1 &lt;= p &lt;= 8</code>. The position is the same as that in a full binary tree.</li>
	<li>The units digit represents the value <code>v</code> of this node where <code>0 &lt;= v &lt;= 9</code>.</li>
</ul>

<p>Given an array of <strong>ascending</strong> three-digit integers <code>nums</code> representing a binary tree with a depth smaller than <code>5</code>, return <em>the sum of all paths from the root towards the leaves</em>.</p>

<p>It is <strong>guaranteed</strong> that the given array represents a valid connected binary tree.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0666.Path%20Sum%20IV/images/pathsum4-1-tree.jpg" style="width: 212px; height: 183px;" />
<pre>
<strong>Input:</strong> nums = [113,215,221]
<strong>Output:</strong> 12
<strong>Explanation:</strong> The tree that the list represents is shown.
The path sum is (3 + 5) + (3 + 1) = 12.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0666.Path%20Sum%20IV/images/pathsum4-2-tree.jpg" style="width: 132px; height: 183px;" />
<pre>
<strong>Input:</strong> nums = [113,221]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The tree that the list represents is shown. 
The path sum is (3 + 1) = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 15</code></li>
	<li><code>110 &lt;= nums[i] &lt;= 489</code></li>
	<li><code>nums</code> represents a valid binary tree with depth less than <code>5</code>.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def pathSum(self, nums: List[int]) -> int:
        def dfs(node, t):
            if node not in mp:
                return
            t += mp[node]
            d, p = divmod(node, 10)
            l = (d + 1) * 10 + (p * 2) - 1
            r = l + 1
            nonlocal ans
            if l not in mp and r not in mp:
                ans += t
                return
            dfs(l, t)
            dfs(r, t)

        ans = 0
        mp = {num // 10: num % 10 for num in nums}
        dfs(11, 0)
        return ans
```

### **Java**

```java
class Solution {
    private int ans;
    private Map<Integer, Integer> mp;

    public int pathSum(int[] nums) {
        ans = 0;
        mp = new HashMap<>(nums.length);
        for (int num : nums) {
            mp.put(num / 10, num % 10);
        }
        dfs(11, 0);
        return ans;
    }

    private void dfs(int node, int t) {
        if (!mp.containsKey(node)) {
            return;
        }
        t += mp.get(node);
        int d = node / 10, p = node % 10;
        int l = (d + 1) * 10 + (p * 2) - 1;
        int r = l + 1;
        if (!mp.containsKey(l) && !mp.containsKey(r)) {
            ans += t;
            return;
        }
        dfs(l, t);
        dfs(r, t);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int ans;
    unordered_map<int, int> mp;

    int pathSum(vector<int>& nums) {
        ans = 0;
        mp.clear();
        for (int num : nums) mp[num / 10] = num % 10;
        dfs(11, 0);
        return ans;
    }

    void dfs(int node, int t) {
        if (!mp.count(node)) return;
        t += mp[node];
        int d = node / 10, p = node % 10;
        int l = (d + 1) * 10 + (p * 2) - 1;
        int r = l + 1;
        if (!mp.count(l) && !mp.count(r)) {
            ans += t;
            return;
        }
        dfs(l, t);
        dfs(r, t);
    }
};
```

### **Go**

```go
func pathSum(nums []int) int {
	ans := 0
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num/10] = num % 10
	}
	var dfs func(node, t int)
	dfs = func(node, t int) {
		if v, ok := mp[node]; ok {
			t += v
			d, p := node/10, node%10
			l := (d+1)*10 + (p * 2) - 1
			r := l + 1
			if _, ok1 := mp[l]; !ok1 {
				if _, ok2 := mp[r]; !ok2 {
					ans += t
					return
				}
			}
			dfs(l, t)
			dfs(r, t)
		}
	}
	dfs(11, 0)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
