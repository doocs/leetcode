---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3447.Assign%20Elements%20to%20Groups%20with%20Constraints/README_EN.md
rating: 1730
source: Weekly Contest 436 Q2
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [3447. Assign Elements to Groups with Constraints](https://leetcode.com/problems/assign-elements-to-groups-with-constraints)

[中文文档](/solution/3400-3499/3447.Assign%20Elements%20to%20Groups%20with%20Constraints/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>groups</code>, where <code>groups[i]</code> represents the size of the <code>i<sup>th</sup></code> group. You are also given an integer array <code>elements</code>.</p>

<p>Your task is to assign <strong>one</strong> element to each group based on the following rules:</p>

<ul>
	<li>An element at index <code>j</code> can be assigned to a group <code>i</code> if <code>groups[i]</code> is <strong>divisible</strong> by <code>elements[j]</code>.</li>
	<li>If there are multiple elements that can be assigned, assign the element with the <strong>smallest index</strong> <code>j</code>.</li>
	<li>If no element satisfies the condition for a group, assign -1 to that group.</li>
</ul>

<p>Return an integer array <code>assigned</code>, where <code>assigned[i]</code> is the index of the element chosen for group <code>i</code>, or -1 if no suitable element exists.</p>

<p><strong>Note</strong>: An element may be assigned to more than one group.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">groups = [8,4,3,2,4], elements = [4,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0,-1,1,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>elements[0] = 4</code> is assigned to groups 0, 1, and 4.</li>
	<li><code>elements[1] = 2</code> is assigned to group 3.</li>
	<li>Group 2 cannot be assigned any element.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">groups = [2,3,5,7], elements = [5,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,1,0,-1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>elements[1] = 3</code> is assigned to group 1.</li>
	<li><code>elements[0] = 5</code> is assigned to group 2.</li>
	<li>Groups 0 and 3 cannot be assigned any element.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">groups = [10,21,30,41], elements = [2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1,0,1]</span></p>

<p><strong>Explanation:</strong></p>

<p><code>elements[0] = 2</code> is assigned to the groups with even values, and <code>elements[1] = 1</code> is assigned to the groups with odd values.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= groups.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= elements.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= groups[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= elements[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

First, we find the maximum value in the array $\textit{groups}$, denoted as $\textit{mx}$. We use an array $\textit{d}$ to record the index corresponding to each element. Initially, $\textit{d}[x] = -1$ indicates that the element $x$ has not been assigned yet.

Then, we traverse the array $\textit{elements}$. For each element $x$, if $x > \textit{mx}$ or $\textit{d}[x] \neq -1$, it means that the element $x$ cannot be assigned or has already been assigned, so we skip it directly. Otherwise, starting from $x$, we increment by $x$ each time and set $\textit{d}[y]$ to $j$, indicating that the element $y$ is assigned to the index $j$.

Finally, we traverse the array $\textit{groups}$ and obtain the answer based on the records in the $\textit{d}$ array.

The time complexity is $O(M \times \log m + n)$, and the space complexity is $O(M)$. Here, $n$ and $m$ are the lengths of the arrays $\textit{groups}$ and $\textit{elements}$, respectively, while $M$ is the maximum value in the array $\textit{groups}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def assignElements(self, groups: List[int], elements: List[int]) -> List[int]:
        mx = max(groups)
        d = [-1] * (mx + 1)
        for j, x in enumerate(elements):
            if x > mx or d[x] != -1:
                continue
            for y in range(x, mx + 1, x):
                if d[y] == -1:
                    d[y] = j
        return [d[x] for x in groups]
```

#### Java

```java
class Solution {
    public int[] assignElements(int[] groups, int[] elements) {
        int mx = Arrays.stream(groups).max().getAsInt();
        int[] d = new int[mx + 1];
        Arrays.fill(d, -1);
        for (int j = 0; j < elements.length; ++j) {
            int x = elements[j];
            if (x > mx || d[x] != -1) {
                continue;
            }
            for (int y = x; y <= mx; y += x) {
                if (d[y] == -1) {
                    d[y] = j;
                }
            }
        }
        int n = groups.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = d[groups[i]];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> assignElements(vector<int>& groups, vector<int>& elements) {
        int mx = ranges::max(groups);
        vector<int> d(mx + 1, -1);

        for (int j = 0; j < elements.size(); ++j) {
            int x = elements[j];
            if (x > mx || d[x] != -1) {
                continue;
            }
            for (int y = x; y <= mx; y += x) {
                if (d[y] == -1) {
                    d[y] = j;
                }
            }
        }

        vector<int> ans(groups.size());
        for (int i = 0; i < groups.size(); ++i) {
            ans[i] = d[groups[i]];
        }

        return ans;
    }
};
```

#### Go

```go
func assignElements(groups []int, elements []int) (ans []int) {
	mx := slices.Max(groups)
	d := make([]int, mx+1)
	for i := range d {
		d[i] = -1
	}
	for j, x := range elements {
		if x > mx || d[x] != -1 {
			continue
		}
		for y := x; y <= mx; y += x {
			if d[y] == -1 {
				d[y] = j
			}
		}
	}
	for _, x := range groups {
		ans = append(ans, d[x])
	}
	return
}
```

#### TypeScript

```ts
function assignElements(groups: number[], elements: number[]): number[] {
    const mx = Math.max(...groups);
    const d: number[] = Array(mx + 1).fill(-1);
    for (let j = 0; j < elements.length; ++j) {
        const x = elements[j];
        if (x > mx || d[x] !== -1) {
            continue;
        }
        for (let y = x; y <= mx; y += x) {
            if (d[y] === -1) {
                d[y] = j;
            }
        }
    }
    return groups.map(x => d[x]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
