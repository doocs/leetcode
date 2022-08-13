# [666. 路径总和 IV](https://leetcode.cn/problems/path-sum-iv)

[English Version](/solution/0600-0699/0666.Path%20Sum%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对于一棵深度小于&nbsp;<code>5</code>&nbsp;的树，可以用一组三位十进制整数来表示。对于每个整数：</p>

<ul>
	<li>百位上的数字表示这个节点的深度 <code>d</code>，<code>1 &lt;= d&nbsp;&lt;= 4</code>。</li>
	<li>十位上的数字表示这个节点在当前层所在的位置 <code>P</code>， <code>1 &lt;= p&nbsp;&lt;= 8</code>。位置编号与一棵满二叉树的位置编号相同。</li>
	<li>个位上的数字表示这个节点的权值 <code>v</code>，<code>0 &lt;= v&nbsp;&lt;= 9</code>。</li>
</ul>

<p>给定一个包含三位整数的&nbsp;<strong>升序&nbsp;</strong>数组&nbsp;<code>nums</code>&nbsp;，表示一棵深度小于 <code>5</code> 的二叉树，请你返回 <em>从根到所有叶子结点的路径之和&nbsp;</em>。</p>

<p><strong>保证&nbsp;</strong>给定的数组表示一个有效的连接二叉树。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0666.Path%20Sum%20IV/images/pathsum4-1-tree.jpg" /></p>

<pre>
<strong>输入:</strong> nums = [113, 215, 221]
<strong>输出:</strong> 12
<strong>解释:</strong> 列表所表示的树如上所示。
路径和 = (3 + 5) + (3 + 1) = 12.
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0666.Path%20Sum%20IV/images/pathsum4-2-tree.jpg" /></p>

<pre>
<strong>输入:</strong> nums = [113, 221]
<strong>输出:</strong> 4
<strong>解释:</strong> 列表所表示的树如上所示。
路径和 = (3 + 1) = 4.
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 15</code></li>
	<li><code>110 &lt;= nums[i] &lt;= 489</code></li>
	<li><code>nums</code>&nbsp;表示深度小于&nbsp;<code>5</code> 的有效二叉树</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
