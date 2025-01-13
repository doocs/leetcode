---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3376.Minimum%20Time%20to%20Break%20Locks%20I/README.md
rating: 1793
source: 第 145 场双周赛 Q2
tags:
    - 位运算
    - 深度优先搜索
    - 数组
    - 动态规划
    - 回溯
    - 状态压缩
---

<!-- problem:start -->

# [3376. 破解锁的最少时间 I](https://leetcode.cn/problems/minimum-time-to-break-locks-i)

[English Version](/solution/3300-3399/3376.Minimum%20Time%20to%20Break%20Locks%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Bob 被困在了一个地窖里，他需要破解 <code>n</code>&nbsp;个锁才能逃出地窖，每一个锁都需要一定的 <strong>能量</strong>&nbsp;才能打开。每一个锁需要的能量存放在一个数组&nbsp;<code>strength</code>&nbsp;里，其中&nbsp;<code>strength[i]</code>&nbsp;表示打开第 <code>i</code>&nbsp;个锁需要的能量。</p>

<p>Bob 有一把剑，它具备以下的特征：</p>

<ul>
	<li>一开始剑的能量为 0 。</li>
	<li>剑的能量增加因子&nbsp;<code><font face="monospace">X</font></code>&nbsp;一开始的值为 1 。</li>
	<li>每分钟，剑的能量都会增加当前的&nbsp;<code>X</code>&nbsp;值。</li>
	<li>打开第 <code>i</code>&nbsp;把锁，剑的能量需要到达 <strong>至少</strong>&nbsp;<code>strength[i]</code>&nbsp;。</li>
	<li>打开一把锁以后，剑的能量会变回 0 ，<code>X</code>&nbsp;的值会增加一个给定的值 <code>K</code>&nbsp;。</li>
</ul>

<p>你的任务是打开所有 <code>n</code>&nbsp;把锁并逃出地窖，请你求出需要的 <strong>最少</strong>&nbsp;分钟数。</p>

<p>请你返回 Bob<strong>&nbsp;</strong>打开所有 <code>n</code>&nbsp;把锁需要的 <strong>最少</strong>&nbsp;时间。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>strength = [3,4,1], K = 1</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><b>解释：</b></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">时间</th>
			<th style="border: 1px solid black;">能量</th>
			<th style="border: 1px solid black;">X</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">更新后的 X</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">打开第 3&nbsp;把锁</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">打开第 2 把锁</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">打开第 1 把锁</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>无法用少于 4 分钟打开所有的锁，所以答案为 4 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>strength = [2,5,4], K = 2</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><b>解释：</b></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">时间</th>
			<th style="border: 1px solid black;">能量</th>
			<th style="border: 1px solid black;">X</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">更新后的 X</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">打开第 1 把锁</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">打开第 2 把锁</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">打开第 3 把锁</td>
			<td style="border: 1px solid black;">7</td>
		</tr>
	</tbody>
</table>

<p>无法用少于 5 分钟打开所有的锁，所以答案为 5 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == strength.length</code></li>
	<li><code>1 &lt;= n &lt;= 8</code></li>
	<li><code>1 &lt;= K &lt;= 10</code></li>
	<li><code>1 &lt;= strength[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMinimumTime(self, strength: List[int], K: int) -> int:
        @cache
        def dfs(i: int) -> int:
            if i == (1 << len(strength)) - 1:
                return 0
            cnt = i.bit_count()
            x = 1 + cnt * K
            ans = inf
            for j, s in enumerate(strength):
                if i >> j & 1 ^ 1:
                    ans = min(ans, dfs(i | 1 << j) + (s + x - 1) // x)
            return ans

        return dfs(0)
```

#### Java

```java
class Solution {
    private List<Integer> strength;
    private Integer[] f;
    private int k;
    private int n;

    public int findMinimumTime(List<Integer> strength, int K) {
        n = strength.size();
        f = new Integer[1 << n];
        k = K;
        this.strength = strength;
        return dfs(0);
    }

    private int dfs(int i) {
        if (i == (1 << n) - 1) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int cnt = Integer.bitCount(i);
        int x = 1 + cnt * k;
        f[i] = 1 << 30;
        for (int j = 0; j < n; ++j) {
            if ((i >> j & 1) == 0) {
                f[i] = Math.min(f[i], dfs(i | 1 << j) + (strength.get(j) + x - 1) / x);
            }
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMinimumTime(vector<int>& strength, int K) {
        int n = strength.size();
        int f[1 << n];
        memset(f, -1, sizeof(f));
        int k = K;
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (i == (1 << n) - 1) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            int cnt = __builtin_popcount(i);
            int x = 1 + k * cnt;
            f[i] = INT_MAX;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1 ^ 1) {
                    f[i] = min(f[i], dfs(i | 1 << j) + (strength[j] + x - 1) / x);
                }
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

#### Go

```go
func findMinimumTime(strength []int, K int) int {
	n := len(strength)
	f := make([]int, 1<<n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i == 1<<n-1 {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		x := 1 + K*bits.OnesCount(uint(i))
		f[i] = 1 << 30
		for j, s := range strength {
			if i>>j&1 == 0 {
				f[i] = min(f[i], dfs(i|1<<j)+(s+x-1)/x)
			}
		}
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function findMinimumTime(strength: number[], K: number): number {
    const n = strength.length;
    const f: number[] = Array(1 << n).fill(-1);
    const dfs = (i: number): number => {
        if (i === (1 << n) - 1) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        f[i] = Infinity;
        const x = 1 + K * bitCount(i);
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) == 0) {
                f[i] = Math.min(f[i], dfs(i | (1 << j)) + Math.ceil(strength[j] / x));
            }
        }
        return f[i];
    };
    return dfs(0);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
