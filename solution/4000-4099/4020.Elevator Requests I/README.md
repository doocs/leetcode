---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4020.Elevator%20Requests%20I/README.md
rating: 1187
source: 第 189 场双周赛 Q1
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [4020. 电梯请求 I](https://leetcode.cn/problems/elevator-requests-i)

[English Version](/solution/4000-4099/4020.Elevator%20Requests%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> ，表示一栋楼房的楼层数，楼层编号从 0 到 <code>n - 1</code> 。</p>

<p>同时给你一个整数数组 <code>requests</code> ，其中 <code>requests</code> 表示楼层请求的序列。</p>

<p>一部电梯初始在 0 层，遵循以下规则：</p>

<ul>
	<li>电梯每秒移动一层。</li>
	<li>电梯按给定的顺序处理请求。</li>
	<li>如果电梯已经在请求的楼层，则不需要移动。</li>
	<li>处理完一个请求后，电梯立即开始向下一个请求的楼层移动。</li>
</ul>

<p>返回处理所有请求所需的 <strong>总时间</strong> （以秒为单位）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, requests = [2,1,4,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>requests[0] = 2</code>：从 0 层移动到 2 层需要 2 秒。</li>
	<li><code>requests[1] = 1</code>：从 2 层移动到 1 层需要 1 秒。</li>
	<li><code>requests[2] = 4</code>：从 1 层移动到 4 层需要 3 秒。</li>
	<li><code>requests[3] = 3</code>：从 4 层移动到 3 层需要 1 秒。</li>
</ul>

<p>所需的总时间是 <code>2 + 1 + 3 + 1 = 7</code> 秒。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, requests = [2,0,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>requests[0] = 2</code>：从 0 层移动到 2 层需要 2 秒。</li>
	<li><code>requests[1] = 0</code>：从 2 层移动到 0 层需要 2 秒。</li>
	<li><code>requests[2] = 0</code>：不需要移动。</li>
</ul>

<p>所需的总时间是 <code>2 + 2 + 0 = 4</code> 秒。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= requests.length &lt;= 100</code></li>
	<li><code>0 &lt;= requests[i] &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

电梯从 $0$ 层出发，按给定顺序处理请求。相邻两次请求之间的移动时间为两层楼层编号之差的绝对值。第一个请求从 $0$ 层到 $\textit{requests}[0]$，耗时即为 $\textit{requests}[0]$；之后将相邻请求的楼层差绝对值累加即可。

时间复杂度 $O(m)$，空间复杂度 $O(1)$。其中 $m$ 是请求的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def elevatorRequests(self, n: int, requests: list[int]) -> int:
        return requests[0] + sum(abs(x - y) for x, y in pairwise(requests))
```

#### Java

```java
class Solution {
    public int elevatorRequests(int n, int[] requests) {
        int ans = requests[0];
        for (int i = 1; i < requests.length; ++i) {
            ans += Math.abs(requests[i - 1] - requests[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int elevatorRequests(int n, vector<int>& requests) {
        int ans = requests[0];
        for (int i = 1; i < requests.size(); ++i) {
            ans += abs(requests[i - 1] - requests[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func elevatorRequests(n int, requests []int) int {
	ans := requests[0]
	for i, x := range requests[1:] {
		ans += abs(x - requests[i])
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function elevatorRequests(n: number, requests: number[]): number {
    let ans: number = requests[0];
    for (let i = 1; i < requests.length; ++i) {
        ans += Math.abs(requests[i] - requests[i - 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
