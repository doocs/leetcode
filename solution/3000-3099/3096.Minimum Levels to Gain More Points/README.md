# [3096. 得到更多分数的最少关卡数目](https://leetcode.cn/problems/minimum-levels-to-gain-more-points)

[English Version](/solution/3000-3099/3096.Minimum%20Levels%20to%20Gain%20More%20Points/README_EN.md)

<!-- tags:数组,前缀和 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的二进制数组&nbsp;<code>possible</code>&nbsp;。</p>

<p>Alice 和 Bob 正在玩一个有 <code>n</code> 个关卡的游戏，游戏中有一些关卡是 <strong>困难</strong>&nbsp;模式，其他的关卡是 <strong>简单</strong>&nbsp;模式。如果&nbsp;<code>possible[i] == 0</code>&nbsp;，那么第&nbsp;<code>i</code> 个关卡是 <strong>困难</strong>&nbsp;模式。一个玩家通过一个简单模式的关卡可以获得 <code>1</code>&nbsp;分，通过困难模式的关卡将失去 <code>1</code>&nbsp;分。</p>

<p>游戏的一开始，Alice 将从第 <code>0</code>&nbsp;级开始 <strong>按顺序</strong> 完成一些关卡，然后 Bob 会完成剩下的所有关卡。</p>

<p>假设两名玩家都采取最优策略，目的是&nbsp;<strong>最大化</strong>&nbsp;自己的得分，Alice 想知道自己&nbsp;<strong>最少</strong> 需要完成多少个关卡，才能获得比 Bob 更多的分数。</p>

<p>请你返回 Alice 获得比 Bob 更多的分数所需要完成的 <strong>最少</strong> 关卡数目，如果 <strong>无法</strong>&nbsp;达成，那么返回 <code>-1</code>&nbsp;。</p>

<p><strong>注意</strong>，每个玩家都至少需要完成&nbsp;<code>1</code> 个关卡。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>possible = [1,0,1,0]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>我们来看一下 Alice 可以完成的关卡数目：</p>

<ul>
	<li>如果 Alice 只完成关卡 0 ，Bob 完成剩下的所有关卡，那么 Alice 获得 1 分，Bob 获得 -1 + 1 - 1 = -1 分。</li>
	<li>如果 Alice 完成到关卡 1 ，Bob 完成剩下的所有关卡，那么 Alice 获得&nbsp;1 - 1 = 0 分，Bob 获得 1 - 1 = 0 分。</li>
	<li>如果 Alice 完成到关卡 2 ，Bob 完成剩下的所有关卡，那么 Alice 获得&nbsp;1 - 1 + 1 = 1 分，Bob 获得 -1 分。</li>
</ul>

<p>Alice 需要完成至少一个关卡获得更多的分数。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>possible = [1,1,1,1,1]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>我们来看一下 Alice 可以完成的关卡数目：</p>

<ul>
	<li>如果 Alice 只完成关卡 0 ，Bob 完成剩下的所有关卡，那么 Alice 获得 1 分，Bob 获得 4 分。</li>
	<li>如果 Alice 完成到关卡 1 ，Bob 完成剩下的所有关卡，那么 Alice 获得&nbsp;2 分，Bob 获得 3 分。</li>
	<li>如果 Alice 完成到关卡 2 ，Bob 完成剩下的所有关卡，那么 Alice 获得&nbsp;3 分，Bob 获得 2&nbsp;分。</li>
	<li>如果 Alice 完成到关卡 3&nbsp;，Bob 完成剩下的所有关卡，那么 Alice 获得 4&nbsp;分，Bob 获得 1&nbsp;分。</li>
</ul>

<p>Alice 需要完成至少三个关卡获得更多的分数。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>possible = [0,0]</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><strong>解释：</strong></p>

<p>两名玩家只能各完成 1 个关卡，Alice 完成关卡 0 得到 -1 分，Bob 完成关卡 1 得到 -1 分。两名玩家得分相同，所以 Alice 无法得到更多分数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == possible.length &lt;= 10<sup>5</sup></code></li>
	<li><code>possible[i]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;要么是&nbsp;<code>1</code> 。</li>
</ul>

## 解法

### 方法一：枚举

我们先计算得到两个玩家能得到的分数和，记为 $s$。

然后我们从小到大枚举玩家 $1$ 能完成的关卡数目 $i$，计算玩家 $1$ 得到的分数和 $t$，如果 $t > s - t$，那么玩家 $1$ 需要完成的关卡数目就是 $i$。

如果枚举完前 $n - 1$ 个关卡都没有找到满足条件的 $i$，那么就返回 $-1$。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumLevels(self, possible: List[int]) -> int:
        s = sum(-1 if x == 0 else 1 for x in possible)
        t = 0
        for i, x in enumerate(possible[:-1], 1):
            t += -1 if x == 0 else 1
            if t > s - t:
                return i
        return -1
```

```java
class Solution {
    public int minimumLevels(int[] possible) {
        int s = 0;
        for (int x : possible) {
            s += x == 0 ? -1 : 1;
        }
        int t = 0;
        for (int i = 1; i < possible.length; ++i) {
            t += possible[i - 1] == 0 ? -1 : 1;
            if (t > s - t) {
                return i;
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int minimumLevels(vector<int>& possible) {
        int s = 0;
        for (int x : possible) {
            s += x == 0 ? -1 : 1;
        }
        int t = 0;
        for (int i = 1; i < possible.size(); ++i) {
            t += possible[i - 1] == 0 ? -1 : 1;
            if (t > s - t) {
                return i;
            }
        }
        return -1;
    }
};
```

```go
func minimumLevels(possible []int) int {
	s := 0
	for _, x := range possible {
		if x == 0 {
			x = -1
		}
		s += x
	}
	t := 0
	for i, x := range possible[:len(possible)-1] {
		if x == 0 {
			x = -1
		}
		t += x
		if t > s-t {
			return i + 1
		}
	}
	return -1
}
```

```ts
function minimumLevels(possible: number[]): number {
    const s = possible.reduce((acc, x) => acc + (x === 0 ? -1 : 1), 0);
    let t = 0;
    for (let i = 1; i < possible.length; ++i) {
        t += possible[i - 1] === 0 ? -1 : 1;
        if (t > s - t) {
            return i;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- end -->
