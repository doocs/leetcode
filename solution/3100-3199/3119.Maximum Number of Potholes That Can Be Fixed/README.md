---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3119.Maximum%20Number%20of%20Potholes%20That%20Can%20Be%20Fixed/README.md
tags:
    - 贪心
    - 字符串
    - 排序
---

<!-- problem:start -->

# [3119. 最大数量的可修复坑洼 🔒](https://leetcode.cn/problems/maximum-number-of-potholes-that-can-be-fixed)

[English Version](/solution/3100-3199/3119.Maximum%20Number%20of%20Potholes%20That%20Can%20Be%20Fixed/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串&nbsp;<code>road</code>，只包含字符&nbsp;<code>"x"</code>&nbsp;和&nbsp;<code>"."</code>，其中每个&nbsp;<code>"x"</code>&nbsp;代表一个坑洼，每个&nbsp;<code>"."</code>&nbsp;代表一个平滑的道路，以及一个整数&nbsp;<code>budget</code>。</p>

<p>在一次修复操作中，您可以以 <code>n + 1</code> 的价格修复 <code>n</code> 个连续坑洼。</p>

<p>返回可以修复的坑洼的 <strong>最大</strong> 数量，以便所有修复的价格总和 <strong>不会超过</strong> 给定的预算&nbsp;<code>budget</code>。</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">road = "..", budget = 5</span></p>

<p><strong>输出:</strong> <span class="example-io">0</span></p>

<p><strong>解释:</strong></p>

<p>没有坑洼需要修复。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">road = "..xxxxx", budget = 4</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<p>我们修复了前三个坑洼（它们是连续的）。任务所需的预算为&nbsp;<code>3 + 1 = 4</code>。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">road = "x.x.xxx...x", budget = 14</span></p>

<p><strong>输出:</strong> <span class="example-io">6</span></p>

<p><strong>解释:</strong></p>

<p>我们可以修复所有的坑洼。总花费为&nbsp;<code>(1 + 1) + (1 + 1) + (3 + 1) + (1 + 1) = 10</code>&nbsp;在我们的预算 14&nbsp;之内。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= road.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>5</sup> + 1</code></li>
	<li><code>road</code>&nbsp;只包含字符&nbsp;<code>'.'</code>&nbsp;和&nbsp;<code>'x'</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 贪心

<!-- thinking:start -->

> **思考**
>
> 连续 $k$ 个坑的修补代价为 $k+1$，预算有限。若把每段坑独立枚举修多长，段数多时组合爆炸。
>
> 同样预算下优先修更长的段能固定更多坑：长度为 $k$ 的段修完后，余下未修部分等价于若干长度为 $k-1$ 的段。
>
> 为此先统计各长度的段数 $cnt[k]$，从大到小取 $t=\min(\textit{budget}/(k+1),cnt[k])$ 计入答案，剩余段并入 $cnt[k-1]$。预算耗尽即停。

<!-- thinking:end -->

我们首先统计出每个连续的坑洼的数量，记录在数组 $cnt$ 中，即 $cnt[k]$ 表示有 $cnt[k]$ 个长度为 $k$ 的连续坑洼。

由于我们要尽可能多地修补坑洼，而对于长度为 $k$ 的连续坑洼，我们需要花费 $k + 1$ 的代价，应该优先修补长度较长的坑洼，这样可以使得代价最小。

因此，我们从最长的坑洼开始修补，对于长度为 $k$ 的坑洼，我们最多可以修补的个数为 $t = \min(\textit{budget} / (k + 1), \textit{cnt}[k])$，我们将修补的个数乘以长度 $k$ 加到答案中，然后更新剩余的预算。对于长度为 $k$ 的其余 $cnt[k] - t$ 个坑洼，我们将它们合并到长度为 $k - 1$ 的坑洼中。继续这个过程，直到遍历完所有的坑洼。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $road$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPotholes(self, road: str, budget: int) -> int:
        road += "."
        n = len(road)
        cnt = [0] * n
        k = 0
        for c in road:
            if c == "x":
                k += 1
            elif k:
                cnt[k] += 1
                k = 0
        ans = 0
        for k in range(n - 1, 0, -1):
            if cnt[k] == 0:
                continue
            t = min(budget // (k + 1), cnt[k])
            ans += t * k
            budget -= t * (k + 1)
            if budget == 0:
                break
            cnt[k - 1] += cnt[k] - t
        return ans
```

#### Java

```java
class Solution {
    public int maxPotholes(String road, int budget) {
        road += ".";
        int n = road.length();
        int[] cnt = new int[n];
        int k = 0;
        for (char c : road.toCharArray()) {
            if (c == 'x') {
                ++k;
            } else if (k > 0) {
                ++cnt[k];
                k = 0;
            }
        }
        int ans = 0;
        for (k = n - 1; k > 0 && budget > 0; --k) {
            int t = Math.min(budget / (k + 1), cnt[k]);
            ans += t * k;
            budget -= t * (k + 1);
            cnt[k - 1] += cnt[k] - t;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPotholes(string road, int budget) {
        road.push_back('.');
        int n = road.size();
        vector<int> cnt(n);
        int k = 0;
        for (char& c : road) {
            if (c == 'x') {
                ++k;
            } else if (k) {
                ++cnt[k];
                k = 0;
            }
        }
        int ans = 0;
        for (k = n - 1; k && budget; --k) {
            int t = min(budget / (k + 1), cnt[k]);
            ans += t * k;
            budget -= t * (k + 1);
            cnt[k - 1] += cnt[k] - t;
        }
        return ans;
    }
};
```

#### Go

```go
func maxPotholes(road string, budget int) (ans int) {
	road += "."
	n := len(road)
	cnt := make([]int, n)
	k := 0
	for _, c := range road {
		if c == 'x' {
			k++
		} else if k > 0 {
			cnt[k]++
			k = 0
		}
	}
	for k = n - 1; k > 0 && budget > 0; k-- {
		t := min(budget/(k+1), cnt[k])
		ans += t * k
		budget -= t * (k + 1)
		cnt[k-1] += cnt[k] - t
	}
	return
}
```

#### TypeScript

```ts
function maxPotholes(road: string, budget: number): number {
    road += '.';
    const n = road.length;
    const cnt: number[] = Array(n).fill(0);
    let k = 0;
    for (const c of road) {
        if (c === 'x') {
            ++k;
        } else if (k) {
            ++cnt[k];
            k = 0;
        }
    }
    let ans = 0;
    for (k = n - 1; k && budget; --k) {
        const t = Math.min(Math.floor(budget / (k + 1)), cnt[k]);
        ans += t * k;
        budget -= t * (k + 1);
        cnt[k - 1] += cnt[k] - t;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_potholes(road: String, budget: i32) -> i32 {
        let mut cs: Vec<char> = road.chars().collect();
        cs.push('.');
        let n = cs.len();
        let mut cnt: Vec<i32> = vec![0; n];
        let mut k = 0;

        for c in cs.iter() {
            if *c == 'x' {
                k += 1;
            } else if k > 0 {
                cnt[k] += 1;
                k = 0;
            }
        }

        let mut ans = 0;
        let mut budget = budget;

        for k in (1..n).rev() {
            if budget == 0 {
                break;
            }
            let t = std::cmp::min(budget / ((k as i32) + 1), cnt[k]);
            ans += t * (k as i32);
            budget -= t * ((k as i32) + 1);
            cnt[k - 1] += cnt[k] - t;
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int MaxPotholes(string road, int budget) {
        road += '.';
        int n = road.Length;
        int[] cnt = new int[n];
        int k = 0;
        foreach (char c in road) {
            if (c == 'x') {
                ++k;
            } else if (k > 0) {
                ++cnt[k];
                k = 0;
            }
        }
        int ans = 0;
        for (k = n - 1; k > 0 && budget > 0; --k) {
            int t = Math.Min(budget / (k + 1), cnt[k]);
            ans += t * k;
            budget -= t * (k + 1);
            cnt[k - 1] += cnt[k] - t;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
