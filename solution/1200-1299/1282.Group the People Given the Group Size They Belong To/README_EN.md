# [1282. Group the People Given the Group Size They Belong To](https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to)

[中文文档](/solution/1200-1299/1282.Group%20the%20People%20Given%20the%20Group%20Size%20They%20Belong%20To/README.md)

## Description

<p>There are <code>n</code> people&nbsp;that are split into some unknown number of groups. Each person is labeled with a&nbsp;<strong>unique ID</strong>&nbsp;from&nbsp;<code>0</code>&nbsp;to&nbsp;<code>n - 1</code>.</p>

<p>You are given an integer array&nbsp;<code>groupSizes</code>, where <code>groupSizes[i]</code>&nbsp;is the size of the group that person&nbsp;<code>i</code>&nbsp;is in. For example, if&nbsp;<code>groupSizes[1] = 3</code>, then&nbsp;person&nbsp;<code>1</code>&nbsp;must be in a&nbsp;group of size&nbsp;<code>3</code>.</p>

<p>Return&nbsp;<em>a list of groups&nbsp;such that&nbsp;each person&nbsp;<code>i</code>&nbsp;is in a group of size&nbsp;<code>groupSizes[i]</code></em>.</p>

<p>Each person should&nbsp;appear in&nbsp;<strong>exactly one group</strong>,&nbsp;and every person must be in a group. If there are&nbsp;multiple answers, <strong>return any of them</strong>. It is <strong>guaranteed</strong> that there will be <strong>at least one</strong> valid solution for the given input.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> groupSizes = [3,3,3,3,3,1,3]
<strong>Output:</strong> [[5],[0,1,2],[3,4,6]]
<b>Explanation:</b> 
The first group is [5]. The size is 1, and groupSizes[5] = 1.
The second group is [0,1,2]. The size is 3, and groupSizes[0] = groupSizes[1] = groupSizes[2] = 3.
The third group is [3,4,6]. The size is 3, and groupSizes[3] = groupSizes[4] = groupSizes[6] = 3.
Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> groupSizes = [2,1,3,3,3,2]
<strong>Output:</strong> [[1],[0,5],[2,3,4]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>groupSizes.length == n</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 500</code></li>
	<li><code>1 &lt;=&nbsp;groupSizes[i] &lt;= n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
