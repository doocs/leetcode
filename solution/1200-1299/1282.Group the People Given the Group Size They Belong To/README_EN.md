---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1282.Group%20the%20People%20Given%20the%20Group%20Size%20They%20Belong%20To/README_EN.md
rating: 1267
source: Weekly Contest 166 Q2
tags:
    - Greedy
    - Array
    - Hash Table
---

<!-- problem:start -->

# [1282. Group the People Given the Group Size They Belong To](https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to)

[中文文档](/solution/1200-1299/1282.Group%20the%20People%20Given%20the%20Group%20Size%20They%20Belong%20To/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> people&nbsp;that are split into some unknown number of groups. Each person is labeled with a&nbsp;<strong>unique ID</strong>&nbsp;from&nbsp;<code>0</code>&nbsp;to&nbsp;<code>n - 1</code>.</p>

<p>You are given an integer array&nbsp;<code>groupSizes</code>, where <code>groupSizes[i]</code>&nbsp;is the size of the group that person&nbsp;<code>i</code>&nbsp;is in. For example, if&nbsp;<code>groupSizes[1] = 3</code>, then&nbsp;person&nbsp;<code>1</code>&nbsp;must be in a&nbsp;group of size&nbsp;<code>3</code>.</p>

<p>Return&nbsp;<em>a list of groups&nbsp;such that&nbsp;each person&nbsp;<code>i</code>&nbsp;is in a group of size&nbsp;<code>groupSizes[i]</code></em>.</p>

<p>Each person should&nbsp;appear in&nbsp;<strong>exactly one group</strong>,&nbsp;and every person must be in a group. If there are&nbsp;multiple answers, <strong>return any of them</strong>. It is <strong>guaranteed</strong> that there will be <strong>at least one</strong> valid solution for the given input.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> groupSizes = [3,3,3,3,3,1,3]
<strong>Output:</strong> [[5],[0,1,2],[3,4,6]]
<b>Explanation:</b> 
The first group is [5]. The size is 1, and groupSizes[5] = 1.
The second group is [0,1,2]. The size is 3, and groupSizes[0] = groupSizes[1] = groupSizes[2] = 3.
The third group is [3,4,6]. The size is 3, and groupSizes[3] = groupSizes[4] = groupSizes[6] = 3.
Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
</pre>

<p><strong class="example">Example 2:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table or Array

We use a hash table $g$ to store which people are in each group size $groupSize$. Then we partition each group size into $k$ equal parts, with each part containing $groupSize$ people.

Since the range of $n$ in the problem is small, we can also directly create an array of size $n+1$ to store the data, which is more efficient.

Time complexity is $O(n)$, and space complexity is $O(n)$. Here, $n$ is the length of $groupSizes$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def groupThePeople(self, groupSizes: List[int]) -> List[List[int]]:
        g = defaultdict(list)
        for i, v in enumerate(groupSizes):
            g[v].append(i)
        return [v[j : j + i] for i, v in g.items() for j in range(0, len(v), i)]
```

#### Java

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

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> groupThePeople(vector<int>& groupSizes) {
        int n = groupSizes.size();
        vector<vector<int>> g(n + 1);
        for (int i = 0; i < n; ++i) {
            g[groupSizes[i]].push_back(i);
        }
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

#### Go

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

#### TypeScript

```ts
function groupThePeople(groupSizes: number[]): number[][] {
    const n: number = groupSizes.length;
    const g: number[][] = Array.from({ length: n + 1 }, () => []);

    for (let i = 0; i < groupSizes.length; i++) {
        const size: number = groupSizes[i];
        g[size].push(i);
    }
    const ans: number[][] = [];
    for (let i = 1; i <= n; i++) {
        const group: number[] = [];
        for (let j = 0; j < g[i].length; j += i) {
            group.push(...g[i].slice(j, j + i));
            ans.push([...group]);
            group.length = 0;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn group_the_people(group_sizes: Vec<i32>) -> Vec<Vec<i32>> {
        let n: usize = group_sizes.len();
        let mut g: Vec<Vec<usize>> = vec![Vec::new(); n + 1];

        for (i, &size) in group_sizes.iter().enumerate() {
            g[size as usize].push(i);
        }

        let mut ans: Vec<Vec<i32>> = Vec::new();
        for (i, v) in g.into_iter().enumerate() {
            for j in (0..v.len()).step_by(i.max(1)) {
                ans.push(
                    v[j..(j + i).min(v.len())]
                        .iter()
                        .map(|&x| x as i32)
                        .collect(),
                );
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
