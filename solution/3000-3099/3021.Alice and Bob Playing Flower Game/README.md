# [3021. Alice 和 Bob 玩鲜花游戏](https://leetcode.cn/problems/alice-and-bob-playing-flower-game)

[English Version](/solution/3000-3099/3021.Alice%20and%20Bob%20Playing%20Flower%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 在一个长满鲜花的环形草地玩一个回合制游戏。环形的草地上有一些鲜花，Alice 到&nbsp;Bob 之间顺时针有 <code>x</code>&nbsp;朵鲜花，逆时针有 <code>y</code>&nbsp;朵鲜花。</p>

<p>游戏过程如下：</p>

<ol>
	<li>Alice 先行动。</li>
	<li>每一次行动中，当前玩家必须选择顺时针或者逆时针，然后在这个方向上摘一朵鲜花。</li>
	<li>一次行动结束后，如果所有鲜花都被摘完了，那么 <strong>当前</strong>&nbsp;玩家抓住对手并赢得游戏的胜利。</li>
</ol>

<p>给你两个整数&nbsp;<code>n</code>&nbsp;和&nbsp;<code>m</code>&nbsp;，你的任务是求出满足以下条件的所有&nbsp;<code>(x, y)</code>&nbsp;对：</p>

<ul>
	<li>按照上述规则，Alice 必须赢得游戏。</li>
	<li>Alice 顺时针方向上的鲜花数目&nbsp;<code>x</code>&nbsp;必须在区间&nbsp;<code>[1,n]</code>&nbsp;之间。</li>
	<li>Alice 逆时针方向上的鲜花数目 <code>y</code>&nbsp;必须在区间&nbsp;<code>[1,m]</code>&nbsp;之间。</li>
</ul>

<p>请你返回满足题目描述的数对&nbsp;<code>(x, y)</code>&nbsp;的数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 3, m = 2
<b>输出：</b>3
<b>解释：</b>以下数对满足题目要求：(1,2) ，(3,2) ，(2,1) 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 1, m = 1
<b>输出：</b>0
<b>解释：</b>没有数对满足题目要求。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def flowerGame(self, n: int, m: int) -> int:
        count = (n + 1) // 2
        tol = (m + 1) // 2
        ecount = n // 2
        etol = m // 2
        return count * etol + ecount * tol

```

```java
class Solution {
    public long flowerGame(int n, int m) {
        long count = (n + 1) / 2;
        long tol = (m + 1) / 2;
        long ecount = n / 2;
        long etol = m / 2;
        return (count * etol + ecount * tol);
    }
}
```

```cpp
class Solution {
public:
    long long flowerGame(int n, int m) {
        long long count = (n + 1) / 2;
        long long tol = (m + 1) / 2;
        long long ecount = n / 2;
        long long etol = m / 2;
        return (count * etol + ecount * tol);
    }
};
```

```go
func flowerGame(n int, m int) int64 {
    count := int64((n + 1) / 2)
    tol := int64((m + 1) / 2)
    ecount := int64(n / 2)
    etol := int64(m / 2)
    return count*etol + ecount*tol
}
```

<!-- tabs:end -->

<!-- end -->
