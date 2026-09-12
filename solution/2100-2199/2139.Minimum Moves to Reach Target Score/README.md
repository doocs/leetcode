---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2139.Minimum%20Moves%20to%20Reach%20Target%20Score/README.md
rating: 1417
source: 第 276 场周赛 Q2
tags:
    - 贪心
    - 数学
---

<!-- problem:start -->

# [2139. 得到目标值的最少行动次数](https://leetcode.cn/problems/minimum-moves-to-reach-target-score)

[English Version](/solution/2100-2199/2139.Minimum%20Moves%20to%20Reach%20Target%20Score/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你正在玩一个整数游戏。从整数 <code>1</code> 开始，期望得到整数 <code>target</code> 。</p>

<p>在一次行动中，你可以做下述两种操作之一：</p>

<ul>
	<li><strong>递增</strong>，将当前整数的值加 1（即， <code>x = x + 1</code>）。</li>
	<li><strong>加倍</strong>，使当前整数的值翻倍（即，<code>x = 2 * x</code>）。</li>
</ul>

<p>在整个游戏过程中，你可以使用 <strong>递增</strong> 操作 <strong>任意</strong> 次数。但是只能使用 <strong>加倍</strong> 操作 <strong>至多</strong> <code>maxDoubles</code> 次。</p>

<p>给你两个整数 <code>target</code> 和 <code>maxDoubles</code> ，返回从 1 开始得到<em> </em><code>target</code><em> </em>需要的最少行动次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>target = 5, maxDoubles = 0
<strong>输出：</strong>4
<strong>解释：</strong>一直递增 1 直到得到 target 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>target = 19, maxDoubles = 2
<strong>输出：</strong>7
<strong>解释：</strong>最初，x = 1 。
递增 3 次，x = 4 。
加倍 1 次，x = 8 。
递增 1 次，x = 9 。
加倍 1 次，x = 18 。
递增 1 次，x = 19 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>target = 10, maxDoubles = 4
<strong>输出：</strong>4
<strong>解释：</strong>
最初，x = 1 。 
递增 1 次，x = 2 。 
加倍 1 次，x = 4 。 
递增 1 次，x = 5 。 
加倍 1 次，x = 10 。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= maxDoubles &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：倒推 + 贪心

<!-- thinking:start -->

> **思考**
>
> 从 $1$ 加到 $\textit{target}$，加倍次数至多为 $\textit{maxDoubles}$。正向搜索加与乘的交错顺序分支很多。倒着看：偶数且仍有加倍次数时应优先折半，否则只能减一，因为加倍对应倒推时的除二，应尽量用在较大的偶数上。
>
> $\textit{target}$ 很大而加倍次数很少，倒推步数约为 $O(\min(\log \textit{target},\textit{maxDoubles}))$。无加倍次数时答案就是 $\textit{target}-1$。
>
> 递归处理：偶数且 $\textit{maxDoubles}>0$ 则除二并消耗一次加倍，否则减一。

<!-- thinking:end -->

我们不妨从最终的状态开始倒推，假设最终的状态为 $target$，那么我们可以得到 $target$ 的前一个状态为 $target - 1$ 或者 $target / 2$，这取决于 $target$ 的奇偶性以及 $maxDoubles$ 的值。

如果 $target=1$，那么不需要任何操作，直接返回 $0$ 即可。

如果 $maxDoubles=0$，那么我们只能使用递增操作，因此我们需要 $target-1$ 次操作。

如果 $target$ 是偶数且 $maxDoubles>0$，那么我们可以使用加倍操作，因此我们需要 $1$ 次操作，然后递归求解 $target/2$ 和 $maxDoubles-1$。

如果 $target$ 是奇数，那么我们只能使用递增操作，因此我们需要 $1$ 次操作，然后递归求解 $target-1$ 和 $maxDoubles$。

时间复杂度 $O(\min(\log target, maxDoubles))$，空间复杂度 $O(\min(\log target, maxDoubles))$。

我们也可以将上述过程改为迭代的方式，这样可以避免递归的空间开销。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, target: int, maxDoubles: int) -> int:
        if target == 1:
            return 0
        if maxDoubles == 0:
            return target - 1
        if target % 2 == 0 and maxDoubles:
            return 1 + self.minMoves(target >> 1, maxDoubles - 1)
        return 1 + self.minMoves(target - 1, maxDoubles)
```

#### Java

```java
class Solution {
    public int minMoves(int target, int maxDoubles) {
        if (target == 1) {
            return 0;
        }
        if (maxDoubles == 0) {
            return target - 1;
        }
        if (target % 2 == 0 && maxDoubles > 0) {
            return 1 + minMoves(target >> 1, maxDoubles - 1);
        }
        return 1 + minMoves(target - 1, maxDoubles);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(int target, int maxDoubles) {
        if (target == 1) {
            return 0;
        }
        if (maxDoubles == 0) {
            return target - 1;
        }
        if (target % 2 == 0 && maxDoubles > 0) {
            return 1 + minMoves(target >> 1, maxDoubles - 1);
        }
        return 1 + minMoves(target - 1, maxDoubles);
    }
};
```

#### Go

```go
func minMoves(target int, maxDoubles int) int {
	if target == 1 {
		return 0
	}
	if maxDoubles == 0 {
		return target - 1
	}
	if target%2 == 0 && maxDoubles > 0 {
		return 1 + minMoves(target>>1, maxDoubles-1)
	}
	return 1 + minMoves(target-1, maxDoubles)
}
```

#### TypeScript

```ts
function minMoves(target: number, maxDoubles: number): number {
    if (target === 1) {
        return 0;
    }
    if (maxDoubles === 0) {
        return target - 1;
    }
    if (target % 2 === 0 && maxDoubles) {
        return 1 + minMoves(target >> 1, maxDoubles - 1);
    }
    return 1 + minMoves(target - 1, maxDoubles);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一的递归深度与倒推步数相同，可改成迭代以去掉栈空间。
>
> 当仍有加倍次数且 $\textit{target}>1$ 时，奇数先减一，偶数则右移并消耗加倍；加倍用尽后把剩余的 $\textit{target}-1$ 一次加上。
>
> 逻辑与方法一相同，只是写成循环。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, target: int, maxDoubles: int) -> int:
        ans = 0
        while maxDoubles and target > 1:
            ans += 1
            if target % 2 == 1:
                target -= 1
            else:
                maxDoubles -= 1
                target >>= 1
        ans += target - 1
        return ans
```

#### Java

```java
class Solution {
    public int minMoves(int target, int maxDoubles) {
        int ans = 0;
        while (maxDoubles > 0 && target > 1) {
            ++ans;
            if (target % 2 == 1) {
                --target;
            } else {
                --maxDoubles;
                target >>= 1;
            }
        }
        ans += target - 1;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(int target, int maxDoubles) {
        int ans = 0;
        while (maxDoubles > 0 && target > 1) {
            ++ans;
            if (target % 2 == 1) {
                --target;
            } else {
                --maxDoubles;
                target >>= 1;
            }
        }
        ans += target - 1;
        return ans;
    }
};
```

#### Go

```go
func minMoves(target int, maxDoubles int) (ans int) {
	for maxDoubles > 0 && target > 1 {
		ans++
		if target&1 == 1 {
			target--
		} else {
			maxDoubles--
			target >>= 1
		}
	}
	ans += target - 1
	return
}
```

#### TypeScript

```ts
function minMoves(target: number, maxDoubles: number): number {
    let ans = 0;
    while (maxDoubles && target > 1) {
        ++ans;
        if (target & 1) {
            --target;
        } else {
            --maxDoubles;
            target >>= 1;
        }
    }
    ans += target - 1;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
