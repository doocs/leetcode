# [822. 翻转卡片游戏](https://leetcode.cn/problems/card-flipping-game)

[English Version](/solution/0800-0899/0822.Card%20Flipping%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在桌子上有 <code>n</code> 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。</p>

<p>我们可以先翻转任意张卡片，然后选择其中一张卡片。</p>

<p>如果选中的那张卡片背面的数字 <code>x</code> 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。</p>

<p>哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 <code>0</code> 。</p>

<p>其中, <code>fronts[i]</code>&nbsp;和&nbsp;<code>backs[i]</code>&nbsp;分别代表第&nbsp;<code>i</code>&nbsp;张卡片的正面和背面的数字。</p>

<p>如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
<strong>输出：</strong><code>2</code>
<strong>解释：</strong>假设我们翻转第二张卡片，那么在正面的数变成了 <code>[1,3,4,4,7]</code> ， 背面的数变成了 <code>[1,2,4,1,3]。</code>
接着我们选择第二张卡片，因为现在该卡片的背面的数是 2，2 与任意卡片上正面的数都不同，所以 2 就是我们想要的数字。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>fronts = [1], backs = [1]
<strong>输出：</strong>0
<strong>解释：</strong>
无论如何翻转都无法得到想要的数字，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == fronts.length == backs.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= fronts[i], backs[i] &lt;= 2000</code></li>
</ul>

## 解法

### 方法一：哈希表

我们注意到，对于位置 $i$，若 $fronts[i]$ 与 $backs[i]$ 元素相同，则一定不满足条件。

因此，我们先找出正面与背面相同的元素，记录在哈希表 $s$ 中。

接下来，遍历正面与背面的元素，若元素 $x$ 不在哈希表 $s$ 中，则更新答案的最小值。

最后，若找到一个满足条件的元素，返回答案，否则返回 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def flipgame(self, fronts: List[int], backs: List[int]) -> int:
        s = {a for a, b in zip(fronts, backs) if a == b}
        return min((x for x in chain(fronts, backs) if x not in s), default=0)
```

```java
class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> s = new HashSet<>();
        int n = fronts.length;
        for (int i = 0; i < n; ++i) {
            if (fronts[i] == backs[i]) {
                s.add(fronts[i]);
            }
        }
        int ans = 9999;
        for (int v : fronts) {
            if (!s.contains(v)) {
                ans = Math.min(ans, v);
            }
        }
        for (int v : backs) {
            if (!s.contains(v)) {
                ans = Math.min(ans, v);
            }
        }
        return ans % 9999;
    }
}
```

```cpp
class Solution {
public:
    int flipgame(vector<int>& fronts, vector<int>& backs) {
        unordered_set<int> s;
        int n = fronts.size();
        for (int i = 0; i < n; ++i) {
            if (fronts[i] == backs[i]) {
                s.insert(fronts[i]);
            }
        }
        int ans = 9999;
        for (int& v : fronts) {
            if (!s.count(v)) {
                ans = min(ans, v);
            }
        }
        for (int& v : backs) {
            if (!s.count(v)) {
                ans = min(ans, v);
            }
        }
        return ans % 9999;
    }
};
```

```go
func flipgame(fronts []int, backs []int) int {
	s := map[int]struct{}{}
	for i, a := range fronts {
		if a == backs[i] {
			s[a] = struct{}{}
		}
	}
	ans := 9999
	for _, v := range fronts {
		if _, ok := s[v]; !ok {
			ans = min(ans, v)
		}
	}
	for _, v := range backs {
		if _, ok := s[v]; !ok {
			ans = min(ans, v)
		}
	}
	return ans % 9999
}
```

```ts
function flipgame(fronts: number[], backs: number[]): number {
    const s: Set<number> = new Set();
    const n = fronts.length;
    for (let i = 0; i < n; ++i) {
        if (fronts[i] === backs[i]) {
            s.add(fronts[i]);
        }
    }
    let ans = 9999;
    for (const v of fronts) {
        if (!s.has(v)) {
            ans = Math.min(ans, v);
        }
    }
    for (const v of backs) {
        if (!s.has(v)) {
            ans = Math.min(ans, v);
        }
    }
    return ans % 9999;
}
```

```cs
public class Solution {
    public int Flipgame(int[] fronts, int[] backs) {
        var s = new HashSet<int>();
        int n = fronts.Length;
        for (int i = 0; i < n; ++i) {
            if (fronts[i] == backs[i]) {
                s.Add(fronts[i]);
            }
        }
        int ans = 9999;
        for (int i = 0; i < n; ++i) {
            if (!s.Contains(fronts[i])) {
                ans = Math.Min(ans, fronts[i]);
            }
            if (!s.Contains(backs[i])) {
                ans = Math.Min(ans, backs[i]);
            }
        }
        return ans % 9999;
    }
}
```

<!-- tabs:end -->

<!-- end -->
