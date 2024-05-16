---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1423.Maximum%20Points%20You%20Can%20Obtain%20from%20Cards/README.md
rating: 1573
source: 第 186 场周赛 Q2
tags:
    - 数组
    - 前缀和
    - 滑动窗口
---

# [1423. 可获得的最大点数](https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards)

[English Version](/solution/1400-1499/1423.Maximum%20Points%20You%20Can%20Obtain%20from%20Cards/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>几张卡牌<strong> 排成一行</strong>，每张卡牌都有一个对应的点数。点数由整数数组 <code>cardPoints</code> 给出。</p>

<p>每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 <code>k</code> 张卡牌。</p>

<p>你的点数就是你拿到手中的所有卡牌的点数之和。</p>

<p>给你一个整数数组 <code>cardPoints</code> 和整数 <code>k</code>，请你返回可以获得的最大点数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>cardPoints = [1,2,3,4,5,6,1], k = 3
<strong>输出：</strong>12
<strong>解释：</strong>第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>cardPoints = [2,2,2], k = 2
<strong>输出：</strong>4
<strong>解释：</strong>无论你拿起哪两张卡牌，可获得的点数总是 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>cardPoints = [9,7,7,9,7,7,9], k = 7
<strong>输出：</strong>55
<strong>解释：</strong>你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>cardPoints = [1,1000,1], k = 1
<strong>输出：</strong>1
<strong>解释：</strong>你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。 
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>cardPoints = [1,79,80,1,1,1,200,1], k = 3
<strong>输出：</strong>202
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= cardPoints.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= cardPoints[i] &lt;= 10^4</code></li>
	<li><code>1 &lt;= k &lt;= cardPoints.length</code></li>
</ul>

## 解法

### 方法一：滑动窗口

我们可以用一个长度为 $k$ 的滑动窗口来模拟这个过程。

初始时我们将窗口放在数组的末尾，即索引为 $n-k$ 到索引 $n-1$ 的这 $k$ 个位置，窗口内卡牌的点数之和记为 $s$，初始答案 $ans$ 的值也为 $s$。这其实是从数组的开头拿走 $0$ 张卡牌的情况。

接下来，我们考虑从数组的开头依次拿 $1, 2, ..., k$ 张卡牌的情况，假设取到的卡牌为 $cardPoints[i]$，那么我们将其加入 $s$，由于窗口的长度限制为 $k$，我们需要将 $cardPoints[n-k+i]$ 从 $s$ 中减去，这样我们就可以计算出拿到的 $k$ 张卡牌的点数之和，更新答案 $ans$。

时间复杂度 $O(k)$，其中 $k$ 给题目中给出的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maxScore(self, cardPoints: List[int], k: int) -> int:
        ans = s = sum(cardPoints[-k:])
        for i, x in enumerate(cardPoints[:k]):
            s += x - cardPoints[-k + i]
            ans = max(ans, s)
        return ans
```

```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int s = 0, n = cardPoints.length;
        for (int i = n - k; i < n; ++i) {
            s += cardPoints[i];
        }
        int ans = s;
        for (int i = 0; i < k; ++i) {
            s += cardPoints[i] - cardPoints[n - k + i];
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxScore(vector<int>& cardPoints, int k) {
        int n = cardPoints.size();
        int s = accumulate(cardPoints.end() - k, cardPoints.end(), 0);
        int ans = s;
        for (int i = 0; i < k; ++i) {
            s += cardPoints[i] - cardPoints[n - k + i];
            ans = max(ans, s);
        }
        return ans;
    }
};
```

```go
func maxScore(cardPoints []int, k int) int {
	n := len(cardPoints)
	s := 0
	for _, x := range cardPoints[n-k:] {
		s += x
	}
	ans := s
	for i := 0; i < k; i++ {
		s += cardPoints[i] - cardPoints[n-k+i]
		ans = max(ans, s)
	}
	return ans
}
```

```ts
function maxScore(cardPoints: number[], k: number): number {
    const n = cardPoints.length;
    let s = cardPoints.slice(-k).reduce((a, b) => a + b);
    let ans = s;
    for (let i = 0; i < k; ++i) {
        s += cardPoints[i] - cardPoints[n - k + i];
        ans = Math.max(ans, s);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn max_score(card_points: Vec<i32>, k: i32) -> i32 {
        let n = card_points.len();
        let k = k as usize;
        let mut s: i32 = card_points[n - k..].iter().sum();
        let mut ans: i32 = s;
        for i in 0..k {
            s += card_points[i] - card_points[n - k + i];
            ans = ans.max(s);
        }
        ans
    }
}
```

```js
/**
 * @param {number[]} cardPoints
 * @param {number} k
 * @return {number}
 */
var maxScore = function (cardPoints, k) {
    const n = cardPoints.length;
    let s = cardPoints.slice(-k).reduce((a, b) => a + b);
    let ans = s;
    for (let i = 0; i < k; ++i) {
        s += cardPoints[i] - cardPoints[n - k + i];
        ans = Math.max(ans, s);
    }
    return ans;
};
```

```cs
public class Solution {
    public int MaxScore(int[] cardPoints, int k) {
        int n = cardPoints.Length;
        int s = cardPoints[^k..].Sum();
        int ans = s;
        for (int i = 0; i < k; ++i) {
            s += cardPoints[i] - cardPoints[n - k + i];
            ans = Math.Max(ans, s);
        }
        return ans;
    }
}
```

```php
class Solution {
    /**
     * @param Integer[] $cardPoints
     * @param Integer $k
     * @return Integer
     */
    function maxScore($cardPoints, $k) {
        $n = count($cardPoints);
        $s = array_sum(array_slice($cardPoints, -$k));
        $ans = $s;
        for ($i = 0; $i < $k; ++$i) {
            $s += $cardPoints[$i] - $cardPoints[$n - $k + $i];
            $ans = max($ans, $s);
        }
        return $ans;
    }
}
```

```scala
object Solution {
    def maxScore(cardPoints: Array[Int], k: Int): Int = {
        val n = cardPoints.length
        var s = cardPoints.takeRight(k).sum
        var ans = s
        for (i <- 0 until k) {
            s += cardPoints(i) - cardPoints(n - k + i)
            ans = ans.max(s)
        }
        ans
    }
}
```

```swift
class Solution {
    func maxScore(_ cardPoints: [Int], _ k: Int) -> Int {
        let n = cardPoints.count
        var s = cardPoints.suffix(k).reduce(0, +)
        var ans = s
        for i in 0..<k {
            s += cardPoints[i] - cardPoints[n - k + i]
            ans = max(ans, s)
        }
        return ans
    }
}
```

```rb
# @param {Integer[]} card_points
# @param {Integer} k
# @return {Integer}
def max_score(card_points, k)
  n = card_points.length
  s = card_points[-k..].sum
  ans = s
  k.times do |i|
    s += card_points[i] - card_points[n - k + i]
    ans = [ans, s].max
  end
  ans
end
```

```kotlin
class Solution {
    fun maxScore(cardPoints: IntArray, k: Int): Int {
        val n = cardPoints.size
        var s = cardPoints.sliceArray(n - k until n).sum()
        var ans = s
        for (i in 0 until k) {
            s += cardPoints[i] - cardPoints[n - k + i]
            ans = maxOf(ans, s)
        }
        return ans
    }
}
```

```dart
class Solution {
  int maxScore(List<int> cardPoints, int k) {
    int n = cardPoints.length;
    int s = cardPoints.sublist(n - k).reduce((a, b) => a + b);
    int ans = s;
    for (int i = 0; i < k; ++i) {
      s += cardPoints[i] - cardPoints[n - k + i];
      ans = s > ans ? s : ans;
    }
    return ans;
  }
}
```

<!-- tabs:end -->

<!-- end -->
