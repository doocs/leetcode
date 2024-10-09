---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3208.Alternating%20Groups%20II/README_EN.md
rating: 1721
source: Biweekly Contest 134 Q3
tags:
    - Array
    - Sliding Window
---

<!-- problem:start -->

# [3208. Alternating Groups II](https://leetcode.com/problems/alternating-groups-ii)

[中文文档](/solution/3200-3299/3208.Alternating%20Groups%20II/README.md)

## Description

<!-- description:start -->

<p>There is a circle of red and blue tiles. You are given an array of integers <code>colors</code> and an integer <code>k</code>. The color of tile <code>i</code> is represented by <code>colors[i]</code>:</p>

<ul>
	<li><code>colors[i] == 0</code> means that tile <code>i</code> is <strong>red</strong>.</li>
	<li><code>colors[i] == 1</code> means that tile <code>i</code> is <strong>blue</strong>.</li>
</ul>

<p>An <strong>alternating</strong> group is every <code>k</code> contiguous tiles in the circle with <strong>alternating</strong> colors (each tile in the group except the first and last one has a different color from its <strong>left</strong> and <strong>right</strong> tiles).</p>

<p>Return the number of <strong>alternating</strong> groups.</p>

<p><strong>Note</strong> that since <code>colors</code> represents a <strong>circle</strong>, the <strong>first</strong> and the <strong>last</strong> tiles are considered to be next to each other.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">colors = [0,1,0,1,0], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3208.Alternating%20Groups%20II/images/screenshot-2024-05-28-183519.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></strong></p>

<p>Alternating groups:</p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3208.Alternating%20Groups%20II/images/screenshot-2024-05-28-182448.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3208.Alternating%20Groups%20II/images/screenshot-2024-05-28-182844.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3208.Alternating%20Groups%20II/images/screenshot-2024-05-28-183057.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">colors = [0,1,0,0,1,0,1], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3208.Alternating%20Groups%20II/images/screenshot-2024-05-28-183907.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></strong></p>

<p>Alternating groups:</p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3208.Alternating%20Groups%20II/images/screenshot-2024-05-28-184128.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3208.Alternating%20Groups%20II/images/screenshot-2024-05-28-184240.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">colors = [1,1,0,1], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3208.Alternating%20Groups%20II/images/screenshot-2024-05-28-184516.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= colors.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= colors[i] &lt;= 1</code></li>
	<li><code>3 &lt;= k &lt;= colors.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We can unfold the ring into an array of length $2n$ and then traverse this array from left to right. We use a variable $\textit{cnt}$ to record the current length of the alternating group. If we encounter the same color, we reset $\textit{cnt}$ to $1$; otherwise, we increment $\textit{cnt}$. If $\textit{cnt} \ge k$ and the current position $i$ is greater than or equal to $n$, then we have found an alternating group, and we increment the answer by one.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{colors}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfAlternatingGroups(self, colors: List[int], k: int) -> int:
        n = len(colors)
        ans = cnt = 0
        for i in range(n << 1):
            if i and colors[i % n] == colors[(i - 1) % n]:
                cnt = 1
            else:
                cnt += 1
            ans += i >= n and cnt >= k
        return ans
```

#### Java

```java
class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int ans = 0, cnt = 0;
        for (int i = 0; i < n << 1; ++i) {
            if (i > 0 && colors[i % n] == colors[(i - 1) % n]) {
                cnt = 1;
            } else {
                ++cnt;
            }
            ans += i >= n && cnt >= k ? 1 : 0;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfAlternatingGroups(vector<int>& colors, int k) {
        int n = colors.size();
        int ans = 0, cnt = 0;
        for (int i = 0; i < n << 1; ++i) {
            if (i && colors[i % n] == colors[(i - 1) % n]) {
                cnt = 1;
            } else {
                ++cnt;
            }
            ans += i >= n && cnt >= k ? 1 : 0;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfAlternatingGroups(colors []int, k int) (ans int) {
	n := len(colors)
	cnt := 0
	for i := 0; i < n<<1; i++ {
		if i > 0 && colors[i%n] == colors[(i-1)%n] {
			cnt = 1
		} else {
			cnt++
		}
		if i >= n && cnt >= k {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfAlternatingGroups(colors: number[], k: number): number {
    const n = colors.length;
    let [ans, cnt] = [0, 0];
    for (let i = 0; i < n << 1; ++i) {
        if (i && colors[i % n] === colors[(i - 1) % n]) {
            cnt = 1;
        } else {
            ++cnt;
        }
        ans += i >= n && cnt >= k ? 1 : 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
