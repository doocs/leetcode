# [2833. 距离原点最远的点](https://leetcode.cn/problems/furthest-point-from-origin)

[English Version](/solution/2800-2899/2833.Furthest%20Point%20From%20Origin/README_EN.md)

<!-- tags:字符串,计数 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的字符串 <code>moves</code> ，该字符串仅由字符 <code>'L'</code>、<code>'R'</code> 和 <code>'_'</code> 组成。字符串表示你在一条原点为 <code>0</code> 的数轴上的若干次移动。</p>

<p>你的初始位置就在原点（<code>0</code>），第 <code>i</code> 次移动过程中，你可以根据对应字符选择移动方向：</p>

<ul>
	<li>如果 <code>moves[i] = 'L'</code> 或 <code>moves[i] = '_'</code> ，可以选择向左移动一个单位距离</li>
	<li>如果 <code>moves[i] = 'R'</code> 或 <code>moves[i] = '_'</code> ，可以选择向右移动一个单位距离</li>
</ul>

<p>移动 <code>n</code> 次之后，请你找出可以到达的距离原点 <strong>最远</strong> 的点，并返回 <strong>从原点到这一点的距离</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>moves = "L_RL__R"
<strong>输出：</strong>3
<strong>解释：</strong>可以到达的距离原点 0 最远的点是 -3 ，移动的序列为 "LLRLLLR" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>moves = "_R__LL_"
<strong>输出：</strong>5
<strong>解释：</strong>可以到达的距离原点 0 最远的点是 -5 ，移动的序列为 "LRLLLLL" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>moves = "_______"
<strong>输出：</strong>7
<strong>解释：</strong>可以到达的距离原点 0 最远的点是 7 ，移动的序列为 "RRRRRRR" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= moves.length == n &lt;= 50</code></li>
	<li><code>moves</code> 仅由字符 <code>'L'</code>、<code>'R'</code> 和 <code>'_'</code> 组成</li>
</ul>

## 解法

### 方法一：贪心

遇到字符 `'_'` 时，我们可以选择向左或向右移动，而题目需要我们求出离原点最远的点，因此，我们可以先进行一次遍历，贪心地把所有的 `'_'` 都移到左边，求出此时离原点最远的点，再进行一次遍历，贪心地把所有的 `'_'` 都移到右边，求出此时离原点最远的点，最后取两次遍历中的最大值即可。

进一步地，我们只需要统计出字符串中 `'L'`、`'R'` 的个数之差，再加上 `'_'` 的个数即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def furthestDistanceFromOrigin(self, moves: str) -> int:
        return abs(moves.count("L") - moves.count("R")) + moves.count("_")
```

```java
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        return Math.abs(count(moves, 'L') - count(moves, 'R')) + count(moves, '_');
    }

    private int count(String s, char c) {
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                ++cnt;
            }
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int furthestDistanceFromOrigin(string moves) {
        auto cnt = [&](char c) {
            return count(moves.begin(), moves.end(), c);
        };
        return abs(cnt('L') - cnt('R')) + cnt('_');
    }
};
```

```go
func furthestDistanceFromOrigin(moves string) int {
	count := func(c string) int { return strings.Count(moves, c) }
	return abs(count("L")-count("R")) + count("_")
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function furthestDistanceFromOrigin(moves: string): number {
    const count = (c: string) => moves.split('').filter(x => x === c).length;
    return Math.abs(count('L') - count('R')) + count('_');
}
```

<!-- tabs:end -->

<!-- end -->
