---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3206.Alternating%20Groups%20I/README_EN.md
rating: 1223
source: Biweekly Contest 134 Q1
tags:
    - Array
    - Sliding Window
---

<!-- problem:start -->

# [3206. Alternating Groups I](https://leetcode.com/problems/alternating-groups-i)

[中文文档](/solution/3200-3299/3206.Alternating%20Groups%20I/README.md)

## Description

<!-- description:start -->

<p>There is a circle of red and blue tiles. You are given an array of integers <code>colors</code>. The color of tile <code>i</code> is represented by <code>colors[i]</code>:</p>

<ul>
	<li><code>colors[i] == 0</code> means that tile <code>i</code> is <strong>red</strong>.</li>
	<li><code>colors[i] == 1</code> means that tile <code>i</code> is <strong>blue</strong>.</li>
</ul>

<p>Every 3 contiguous tiles in the circle with <strong>alternating</strong> colors (the middle tile has a different color from its <strong>left</strong> and <strong>right</strong> tiles) is called an <strong>alternating</strong> group.</p>

<p>Return the number of <strong>alternating</strong> groups.</p>

<p><strong>Note</strong> that since <code>colors</code> represents a <strong>circle</strong>, the <strong>first</strong> and the <strong>last</strong> tiles are considered to be next to each other.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">colors = [1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-53-171.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">colors = [0,1,0,0,1]</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-47-491.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<p>Alternating groups:</p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-50-441.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /></strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-48-211.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-49-351.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= colors.length &lt;= 100</code></li>
	<li><code>0 &lt;= colors[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We set $k = 3$, indicating that the length of the alternating group is $3$.

For convenience, we can unfold the ring into an array of length $2n$ and then traverse this array from left to right. We use a variable $\textit{cnt}$ to record the current length of the alternating group. If we encounter the same color, we reset $\textit{cnt}$ to $1$; otherwise, we increment $\textit{cnt}$. If $\textit{cnt} \ge k$ and the current position $i$ is greater than or equal to $n$, then we have found an alternating group, and we increment the answer by one.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{colors}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfAlternatingGroups(self, colors: List[int]) -> int:
        k = 3
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
    public int numberOfAlternatingGroups(int[] colors) {
        int k = 3;
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
    int numberOfAlternatingGroups(vector<int>& colors) {
        int k = 3;
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
func numberOfAlternatingGroups(colors []int) (ans int) {
	k := 3
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
function numberOfAlternatingGroups(colors: number[]): number {
    const k = 3;
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
