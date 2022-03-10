# [1282. 用户分组](https://leetcode-cn.com/problems/group-the-people-given-the-group-size-they-belong-to)

[English Version](/solution/1200-1299/1282.Group%20the%20People%20Given%20the%20Group%20Size%20They%20Belong%20To/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有&nbsp;<code>n</code>&nbsp;个人被分成数量未知的组。每个人都被标记为一个从 <code>0</code> 到 <code>n - 1</code> 的<strong>唯一ID</strong>&nbsp;。</p>

<p>给定一个整数数组 <code>groupSizes</code> ，其中<meta charset="UTF-8" />&nbsp;<code>groupSizes[i]</code>&nbsp;是第 <code>i</code> 个人所在的组的大小。例如，如果&nbsp;<code>groupSizes[1] = 3</code>&nbsp;，则第 <code>1</code> 个人必须位于大小为 <code>3</code> 的组中。</p>

<p>返回一个组列表，使每个人 <code>i</code> 都在一个大小为<meta charset="UTF-8" /><em>&nbsp;<code>groupSizes[i]</code>&nbsp;</em>的组中。</p>

<p>每个人应该&nbsp;<strong>恰好只&nbsp;</strong>出现在&nbsp;<strong>一个组&nbsp;</strong>中，并且每个人必须在一个组中。如果有多个答案，返回其中&nbsp;<strong>任何&nbsp;</strong>一个。可以&nbsp;<strong>保证&nbsp;</strong>给定输入&nbsp;<strong>至少有一个&nbsp;</strong>有效的解。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>groupSizes = [3,3,3,3,3,1,3]
<strong>输出：</strong>[[5],[0,1,2],[3,4,6]]
<strong>解释：
</strong>第一组是 [5]，大小为 1，groupSizes[5] = 1。
第二组是 [0,1,2]，大小为 3，groupSizes[0] = groupSizes[1] = groupSizes[2] = 3。
第三组是 [3,4,6]，大小为 3，groupSizes[3] = groupSizes[4] = groupSizes[6] = 3。 
其他可能的解决方案有 [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>groupSizes = [2,1,3,3,3,2]
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
