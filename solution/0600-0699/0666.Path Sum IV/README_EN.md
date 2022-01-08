# [666. Path Sum IV](https://leetcode.com/problems/path-sum-iv)

[中文文档](/solution/0600-0699/0666.Path%20Sum%20IV/README.md)

## Description

<p>If the depth of a tree is smaller than <code>5</code>, then this tree can be represented by a list of three-digits integers.</p>

<p>For each integer in this list:</p>

<ol>
	<li>The hundreds digit represents the depth <code>D</code> of this node, <code>1 &lt;= D &lt;= 4.</code></li>
	<li>The tens digit represents the position <code>P</code> of this node in the level it belongs to, <code>1 &lt;= P &lt;= 8</code>. The position is the same as that in a full binary tree.</li>
	<li>The units digit represents the value <code>V</code> of this node, <code>0 &lt;= V &lt;= 9.</code></li>
</ol>

<p>Given a list of <code>ascending</code> three-digits integers representing a binary tree with the depth smaller than 5, you need to return the sum of all paths from the root towards the leaves.</p>

<p>It&#39;s guaranteed that the given list represents a valid connected binary tree.</p>

<p><b>Example 1:</b></p>

<pre>
<b>Input:</b> [113, 215, 221]
<b>Output:</b> 12
<b>Explanation:</b> 
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.
</pre>

<p>&nbsp;</p>

<p><b>Example 2:</b></p>

<pre>
<b>Input:</b> [113, 221]
<b>Output:</b> 4
<b>Explanation:</b> 
The tree that the list represents is: 
    3
     \
      1

The path sum is (3 + 1) = 4.
</pre>

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
        if (!mp.count(l) && !mp.count(r))
        {
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
