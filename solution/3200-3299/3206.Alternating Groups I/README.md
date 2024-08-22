---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3206.Alternating%20Groups%20I/README.md
rating: 1223
source: 第 134 场双周赛 Q1
tags:
    - 数组
    - 滑动窗口
---

<!-- problem:start -->

# [3206. 交替组 I](https://leetcode.cn/problems/alternating-groups-i)

[English Version](/solution/3200-3299/3206.Alternating%20Groups%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>colors</code>&nbsp;，它表示一个由红色和蓝色瓷砖组成的环，第 <code>i</code>&nbsp;块瓷砖的颜色为&nbsp;<code>colors[i]</code>&nbsp;：</p>

<ul>
	<li><code>colors[i] == 0</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;块瓷砖的颜色是 <strong>红色</strong>&nbsp;。</li>
	<li><code>colors[i] == 1</code>&nbsp;表示第 <code>i</code>&nbsp;块瓷砖的颜色是 <strong>蓝色</strong>&nbsp;。</li>
</ul>

<p>环中连续 3 块瓷砖的颜色如果是 <strong>交替</strong>&nbsp;颜色（也就是说中间瓷砖的颜色与它<strong>&nbsp;左边</strong>&nbsp;和 <strong>右边</strong>&nbsp;的颜色都不同），那么它被称为一个 <strong>交替</strong>&nbsp;组。</p>

<p>请你返回 <strong>交替</strong>&nbsp;组的数目。</p>

<p><b>注意</b>&nbsp;，由于&nbsp;<code>colors</code>&nbsp;表示一个 <strong>环</strong>&nbsp;，<strong>第一块</strong>&nbsp;瓷砖和 <strong>最后一块</strong>&nbsp;瓷砖是相邻的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>colors = [1,1,1]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-53-171.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>colors = [0,1,0,0,1]</span></p>

<p><b>输出：</b>3</p>

<p><b>解释：</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-47-491.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<p>交替组包括：</p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-50-441.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /></strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-48-211.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3206.Alternating%20Groups%20I/images/image_2024-05-16_23-49-351.png" style="width: 150px; height: 150px; padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= colors.length &lt;= 100</code></li>
	<li><code>0 &lt;= colors[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们令 $k = 3$，表示交替组的长度为 $3$。

为了方便处理，我们可以将环展开成一个长度为 $2n$ 的数组，然后从左到右遍历这个数组，用一个变量 $\textit{cnt}$ 记录当前交替组的长度，如果遇到了相同的颜色，就将 $\textit{cnt}$ 重置为 $1$，否则将 $\textit{cnt}$ 加一。如果 $\textit{cnt} \ge k$，并且当前位置 $i$ 大于等于 $n$，那么就找到了一个交替组，答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{colors}$ 的长度。空间复杂度 $O(1)$。

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
