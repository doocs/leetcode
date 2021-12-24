# [1282. 用户分组](https://leetcode-cn.com/problems/group-the-people-given-the-group-size-they-belong-to)

[English Version](/solution/1200-1299/1282.Group%20the%20People%20Given%20the%20Group%20Size%20They%20Belong%20To/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有&nbsp;<code>n</code>&nbsp;位用户参加活动，他们的&nbsp;<strong>ID</strong>&nbsp;从 <code>0</code> 到 <code>n - 1</code>，每位用户都 <strong>恰好</strong> 属于某一用户组。给你一个长度为 <code>n</code> 的数组&nbsp;<code>groupSizes</code>，其中包含每位用户所处的用户组的大小，请你返回用户分组情况（存在的用户组以及每个组中用户的 ID）。</p>

<p>你可以任何顺序返回解决方案，ID 的顺序也不受限制。此外，题目给出的数据保证至少存在一种解决方案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>groupSizes = [3,3,3,3,3,1,3]
<strong>输出：</strong>[[5],[0,1,2],[3,4,6]]
<strong>解释：</strong> 
其他可能的解决方案有 [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>groupSizes = [2,1,3,3,3,2]
<strong>输出：</strong>[[1],[0,5],[2,3,4]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>groupSizes.length == n</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 500</code></li>
	<li><code>1 &lt;=&nbsp;groupSizes[i] &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def groupThePeople(self, groupSizes: List[int]) -> List[List[int]]:
        mp = defaultdict(list)
        for i, x in enumerate(groupSizes):
            mp[x].append(i)
        res = []
        for x, indexes in mp.items():
            l = len(indexes)
            for i in range(0, l, x):
                res.append(indexes[i: i + x])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < groupSizes.length; ++i) {
            mp.computeIfAbsent(groupSizes[i], k -> new ArrayList<>()).add(i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : mp.entrySet()) {
            int x = entry.getKey();
            List<Integer> indexes = entry.getValue();
            for (int i = 0; i < indexes.size(); i += x) {
                res.add(new ArrayList<>(indexes.subList(i, i + x)));
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> groupThePeople(vector<int>& groupSizes) {
        unordered_map<int, vector<int>> mp;
        for (int i = 0; i < groupSizes.size(); ++i) mp[groupSizes[i]].push_back(i);
        vector<vector<int>> res;
        for (auto& entry : mp)
        {
            int x = entry.first;
            auto indexes = entry.second;
            for (int i = 0; i < indexes.size(); i += x)
            {
                vector<int> t(indexes.begin() + i, indexes.begin() + i + x);
                res.push_back(t);
            }
        }
        return res;
    }
};
```

### **Go**

```go
func groupThePeople(groupSizes []int) [][]int {
	mp := make(map[int][]int)
	for i, x := range groupSizes {
		mp[x] = append(mp[x], i)
	}
	var res [][]int
	for x, indexes := range mp {
		for i := 0; i < len(indexes); i += x {
			res = append(res, indexes[i:i+x])
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
