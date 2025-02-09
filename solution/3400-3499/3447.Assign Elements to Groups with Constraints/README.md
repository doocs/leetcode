---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3447.Assign%20Elements%20to%20Groups%20with%20Constraints/README.md
---

<!-- problem:start -->

# [3447. 将元素分配给有约束条件的组](https://leetcode.cn/problems/assign-elements-to-groups-with-constraints)

[English Version](/solution/3400-3499/3447.Assign%20Elements%20to%20Groups%20with%20Constraints/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>groups</code>，其中 <code>groups[i]</code> 表示第 <code>i</code> 组的大小。另给你一个整数数组 <code>elements</code>。</p>

<p>请你根据以下规则为每个组分配&nbsp;<strong>一个&nbsp;</strong>元素：</p>

<ul>
	<li>如果 <code>groups[i]</code> 能被 <code>elements[j]</code> 整除，则元素 <code>j</code> 可以分配给组 <code>i</code>。</li>
	<li>如果有多个元素满足条件，则分配下标最小的元素 &nbsp;<code>j</code> 。</li>
	<li>如果没有元素满足条件，则分配 -1 。</li>
</ul>

<p>返回一个整数数组 <code>assigned</code>，其中 <code>assigned[i]</code> 是分配给组 <code>i</code> 的元素的索引，若无合适的元素，则为 -1。</p>

<p><strong>注意：</strong>一个元素可以分配给多个组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">groups = [8,4,3,2,4], elements = [4,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,0,-1,1,0]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>elements[0] = 4</code> 被分配给组 0、1 和 4。</li>
	<li><code>elements[1] = 2</code> 被分配给组 3。</li>
	<li>无法为组 2 分配任何元素，分配 -1 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">groups = [2,3,5,7], elements = [5,3,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">[-1,1,0,-1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>elements[1] = 3</code> 被分配给组 1。</li>
	<li><code>elements[0] = 5</code> 被分配给组 2。</li>
	<li>无法为组 0 和组 3 分配任何元素，分配 -1 。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">groups = [10,21,30,41], elements = [2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,1,0,1]</span></p>

<p><strong>解释：</strong></p>

<p><code>elements[0] = 2</code> 被分配给所有偶数值的组，而 <code>elements[1] = 1</code> 被分配给所有奇数值的组。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= groups.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= elements.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= groups[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= elements[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们先找到数组 $\textit{groups}$ 中的最大值，记为 $\textit{mx}$。用一个数组 $\textit{d}$ 记录每个元素对应的下标，初始时 $\textit{d}[x] = -1$ 表示元素 $x$ 还没有被分配。

然后我们遍历数组 $\textit{elements}$，对于每个元素 $x$，如果 $x > \textit{mx}$ 或者 $\textit{d}[x] \neq -1$，说明元素 $x$ 无法被分配或者已经被分配，直接跳过。否则，我们从 $x$ 开始，每次加上 $x$，将 $\textit{d}[y]$ 设为 $j$，表示元素 $y$ 被分配给了下标 $j$。

最后我们遍历数组 $\textit{groups}$，根据 $\textit{d}$ 数组的记录，得到答案。

时间复杂度 $O(M \times \log m + n)$，空间复杂度 $O(M)$。其中 $n$ 和 $m$ 分别是数组 $\textit{groups}$ 和 $\textit{elements}$ 的长度；而 $M$ 是数组 $\textit{groups}$ 中的最大值。

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
