---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0455.Assign%20Cookies/README_EN.md
tags:
    - Greedy
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [455. Assign Cookies](https://leetcode.com/problems/assign-cookies)

[中文文档](/solution/0400-0499/0455.Assign%20Cookies/README.md)

## Description

<!-- description:start -->

<p>Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.</p>

<p>Each child <code>i</code> has a greed factor <code>g[i]</code>, which is the minimum size of a cookie that the child will be content with; and each cookie <code>j</code> has a size <code>s[j]</code>. If <code>s[j] &gt;= g[i]</code>, we can assign the cookie <code>j</code> to the child <code>i</code>, and the child <code>i</code> will be content. Your goal is to maximize the number of your content children and output the maximum number.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> g = [1,2,3], s = [1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> g = [1,2], s = [1,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= g.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= g[i], s[j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as <a href="https://leetcode.com/problems/maximum-matching-of-players-with-trainers/description/" target="_blank"> 2410: Maximum Matching of Players With Trainers.</a></p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Two Pointers

According to the problem description, we should prioritize giving cookies to children with smaller appetites, so as to satisfy as many children as possible.

Therefore, we first sort the two arrays, and then use two pointers $i$ and $j$ to point to the head of arrays $g$ and $s$ respectively. Each time we compare the size of $g[i]$ and $s[j]$:

- If $s[j] < g[i]$, it means that the current cookie $s[j]$ cannot satisfy the current child $g[i]$. We should allocate a larger cookie to the current child, so $j$ should move to the right by one. If $j$ goes out of bounds, it means that the current child cannot be satisfied. At this time, the number of successfully allocated children is $i$, and we can return directly.
- If $s[j] \ge g[i]$, it means that the current cookie $s[j]$ can satisfy the current child $g[i]$. We allocate the current cookie to the current child, so both $i$ and $j$ should move to the right by one.

If we have traversed the array $g$, it means that all children have been allocated cookies, and we can return the total number of children.

The time complexity is $O(m \times \log m + n \times \log n)$, and the space complexity is $O(\log m + \log n)$. Where $m$ and $n$ are the lengths of arrays $g$ and $s$ respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findContentChildren(self, g: List[int], s: List[int]) -> int:
        g.sort()
        s.sort()
        j = 0
        for i, x in enumerate(g):
            while j < len(s) and s[j] < g[i]:
                j += 1
            if j >= len(s):
                return i
            j += 1
        return len(g)
```

#### Java

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length;
        int n = s.length;
        for (int i = 0, j = 0; i < m; ++i) {
            while (j < n && s[j] < g[i]) {
                ++j;
            }
            if (j++ >= n) {
                return i;
            }
        }
        return m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findContentChildren(vector<int>& g, vector<int>& s) {
        sort(g.begin(), g.end());
        sort(s.begin(), s.end());
        int m = g.size(), n = s.size();
        for (int i = 0, j = 0; i < m; ++i) {
            while (j < n && s[j] < g[i]) {
                ++j;
            }
            if (j++ >= n) {
                return i;
            }
        }
        return m;
    }
};
```

#### Go

```go
func findContentChildren(g []int, s []int) int {
	sort.Ints(g)
	sort.Ints(s)
	j := 0
	for i, x := range g {
		for j < len(s) && s[j] < x {
			j++
		}
		if j >= len(s) {
			return i
		}
		j++
	}
	return len(g)
}
```

#### TypeScript

```ts
function findContentChildren(g: number[], s: number[]): number {
    g.sort((a, b) => a - b);
    s.sort((a, b) => a - b);
    const m = g.length;
    const n = s.length;
    for (let i = 0, j = 0; i < m; ++i) {
        while (j < n && s[j] < g[i]) {
            ++j;
        }
        if (j++ >= n) {
            return i;
        }
    }
    return m;
}
```

#### JavaScript

```js
/**
 * @param {number[]} g
 * @param {number[]} s
 * @return {number}
 */
var findContentChildren = function (g, s) {
    g.sort((a, b) => a - b);
    s.sort((a, b) => a - b);
    const m = g.length;
    const n = s.length;
    for (let i = 0, j = 0; i < m; ++i) {
        while (j < n && s[j] < g[i]) {
            ++j;
        }
        if (j++ >= n) {
            return i;
        }
    }
    return m;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
