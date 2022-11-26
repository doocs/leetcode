# [1282. 用户分组](https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to)

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

**方法一：哈希表或数组**

我们用一个哈希表 $g$ 来存放每个 $groupSize$ 都有哪些人。然后对每个 $groupSize$ 中的人划分为 $k$ 等份，每一等份有 $groupSize$ 个人。

由于题目中的 $n$ 范围较小，我们也可以直接创建一个大小为 $n+1$ 的数组来存放数据，运行效率较高。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def groupThePeople(self, groupSizes: List[int]) -> List[List[int]]:
        g = defaultdict(list)
        for i, v in enumerate(groupSizes):
            g[v].append(i)
        return [v[j : j + i] for i, v in g.items() for j in range(0, len(v), i)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int n = groupSizes.length;
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            g[groupSizes[i]].add(i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < g.length; ++i) {
            List<Integer> v = g[i];
            for (int j = 0; j < v.size(); j += i) {
                ans.add(v.subList(j, j + i));
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
    vector<vector<int>> groupThePeople(vector<int>& groupSizes) {
        int n = groupSizes.size();
        vector<vector<int>> g(n + 1);
        for (int i = 0; i < n; ++i) g[groupSizes[i]].push_back(i);
        vector<vector<int>> ans;
        for (int i = 0; i < g.size(); ++i) {
            for (int j = 0; j < g[i].size(); j += i) {
                vector<int> t(g[i].begin() + j, g[i].begin() + j + i);
                ans.push_back(t);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func groupThePeople(groupSizes []int) [][]int {
	n := len(groupSizes)
	g := make([][]int, n+1)
	for i, v := range groupSizes {
		g[v] = append(g[v], i)
	}
	ans := [][]int{}
	for i, v := range g {
		for j := 0; j < len(v); j += i {
			ans = append(ans, v[j:j+i])
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function groupThePeople(groupSizes: number[]): number[][] {
    const res = [];
    const map = new Map<number, number[]>();
    const n = groupSizes.length;
    for (let i = 0; i < n; i++) {
        const size = groupSizes[i];
        map.set(size, [...(map.get(size) ?? []), i]);
        const arr = map.get(size);
        if (arr.length === size) {
            res.push(arr);
            map.set(size, []);
        }
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn group_the_people(group_sizes: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        let mut map = HashMap::new();
        for i in 0..group_sizes.len() {
            let size = group_sizes[i] as usize;
            let arr = map.entry(size).or_insert(vec![]);
            arr.push(i as i32);
            if arr.len() == size {
                res.push(arr.clone());
                arr.clear();
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
