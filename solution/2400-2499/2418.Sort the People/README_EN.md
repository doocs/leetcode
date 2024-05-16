---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2418.Sort%20the%20People/README_EN.md
rating: 1193
source: Weekly Contest 312 Q1
tags:
    - Array
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [2418. Sort the People](https://leetcode.com/problems/sort-the-people)

[中文文档](/solution/2400-2499/2418.Sort%20the%20People/README.md)

## Description

<p>You are given an array of strings <code>names</code>, and an array <code>heights</code> that consists of <strong>distinct</strong> positive integers. Both arrays are of length <code>n</code>.</p>

<p>For each index <code>i</code>, <code>names[i]</code> and <code>heights[i]</code> denote the name and height of the <code>i<sup>th</sup></code> person.</p>

<p>Return <code>names</code><em> sorted in <strong>descending</strong> order by the people&#39;s heights</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> names = [&quot;Mary&quot;,&quot;John&quot;,&quot;Emma&quot;], heights = [180,165,170]
<strong>Output:</strong> [&quot;Mary&quot;,&quot;Emma&quot;,&quot;John&quot;]
<strong>Explanation:</strong> Mary is the tallest, followed by Emma and John.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> names = [&quot;Alice&quot;,&quot;Bob&quot;,&quot;Bob&quot;], heights = [155,185,150]
<strong>Output:</strong> [&quot;Bob&quot;,&quot;Alice&quot;,&quot;Bob&quot;]
<strong>Explanation:</strong> The first Bob is the tallest, followed by Alice and the second Bob.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == names.length == heights.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= names[i].length &lt;= 20</code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>names[i]</code> consists of lower and upper case English letters.</li>
	<li>All the values of <code>heights</code> are distinct.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

According to the problem description, we can create an index array $idx$ of length $n$, where $idx[i]=i$. Then we sort each index in $idx$ in descending order according to the corresponding height in $heights$. Finally, we traverse each index $i$ in the sorted $idx$ and add $names[i]$ to the answer array.

We can also create an array $arr$ of length $n$, where each element is a tuple $(heights[i], i)$. Then we sort $arr$ in descending order by height. Finally, we traverse each element $(heights[i], i)$ in the sorted $arr$ and add $names[i]$ to the answer array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the arrays $names$ and $heights$.

<!-- tabs:start -->

```python
class Solution:
    def sortPeople(self, names: List[str], heights: List[int]) -> List[str]:
        idx = list(range(len(heights)))
        idx.sort(key=lambda i: -heights[i])
        return [names[i] for i in idx]
```

```java
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> heights[j] - heights[i]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = names[idx[i]];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> sortPeople(vector<string>& names, vector<int>& heights) {
        int n = names.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return heights[j] < heights[i]; });
        vector<string> ans;
        for (int i : idx) {
            ans.push_back(names[i]);
        }
        return ans;
    }
};
```

```go
func sortPeople(names []string, heights []int) (ans []string) {
	n := len(names)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return heights[idx[j]] < heights[idx[i]] })
	for _, i := range idx {
		ans = append(ans, names[i])
	}
	return
}
```

```ts
function sortPeople(names: string[], heights: number[]): string[] {
    const n = names.length;
    const idx = new Array(n);
    for (let i = 0; i < n; ++i) {
        idx[i] = i;
    }
    idx.sort((i, j) => heights[j] - heights[i]);
    const ans: string[] = [];
    for (const i of idx) {
        ans.push(names[i]);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn sort_people(names: Vec<String>, heights: Vec<i32>) -> Vec<String> {
        let mut combine: Vec<(String, i32)> = names.into_iter().zip(heights.into_iter()).collect();
        combine.sort_by(|a, b| b.1.cmp(&a.1));
        combine
            .iter()
            .map(|s| s.0.clone())
            .collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def sortPeople(self, names: List[str], heights: List[int]) -> List[str]:
        return [name for _, name in sorted(zip(heights, names), reverse=True)]
```

```java
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {heights[i], i};
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = names[arr[i][1]];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> sortPeople(vector<string>& names, vector<int>& heights) {
        int n = names.size();
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(-heights[i], i);
        }
        sort(arr.begin(), arr.end());
        vector<string> ans;
        for (int i = 0; i < n; ++i) {
            ans.emplace_back(names[arr[i].second]);
        }
        return ans;
    }
};
```

```go
func sortPeople(names []string, heights []int) []string {
	n := len(names)
	arr := make([][2]int, n)
	for i, h := range heights {
		arr[i] = [2]int{h, i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] > arr[j][0] })
	ans := make([]string, n)
	for i, x := range arr {
		ans[i] = names[x[1]]
	}
	return ans
}
```

```ts
function sortPeople(names: string[], heights: number[]): string[] {
    return names
        .map<[string, number]>((s, i) => [s, heights[i]])
        .sort((a, b) => b[1] - a[1])
        .map(([v]) => v);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
