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

<!-- 这里可写通用的实现逻辑 -->

要让左右两侧共 `k` 个元素和最大，可以转换为求中间连续数组 `n - k` 个元素和最小值 `mi`，然后用数组总和 `s` 减去 `mi` 得到答案。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxScore(self, cardPoints: List[int], k: int) -> int:
        n = len(cardPoints)
        s = [0] * (n + 1)
        for i in range(n):
            s[i + 1] = s[i] + cardPoints[i]
        mi = inf
        for i in range(n):
            j = i + (n - k) - 1
            if j < n:
                mi = min(mi, s[j + 1] - s[i])
        return s[-1] - mi
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + cardPoints[i];
        }
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            int j = i + (n - k) - 1;
            if (j < n) {
                mi = Math.min(mi, s[j + 1] - s[i]);
            }
        }
        return s[n] - mi;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxScore(vector<int>& cardPoints, int k) {
        int n = cardPoints.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + cardPoints[i];
        int mi = INT_MAX;
        for (int i = 0; i < n; ++i) {
            int j = i + (n - k) - 1;
            if (j < n) mi = min(mi, s[j + 1] - s[i]);
        }
        return s[n] - mi;
    }
};
```

### **Go**

```go
func maxScore(cardPoints []int, k int) int {
	n := len(cardPoints)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + cardPoints[i]
	}
	mi := math.MaxInt64
	for i := 0; i < n; i++ {
		j := i + (n - k) - 1
		if j < n {
			mi = min(mi, s[j+1]-s[i])
		}
	}
	return s[n] - mi
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxScore(cardPoints: number[], k: number): number {
    const n = cardPoints.length;
    let sum = cardPoints.slice(0, n - k).reduce((r, v) => r + v, 0);
    let min = sum;
    for (let i = 0; i < k; i++) {
        sum += cardPoints[n - k + i] - cardPoints[i];
        min = Math.min(min, sum);
    }
    return cardPoints.reduce((r, v) => r + v, 0) - min;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_score(card_points: Vec<i32>, k: i32) -> i32 {
        let (k, n) = (k as usize, card_points.len());
        let mut sum = card_points.iter().take(n - k).sum::<i32>();
        let mut min = sum;
        for i in 0..k {
            sum += card_points[n - k + i] - card_points[i];
            min = min.min(sum);
        }
        card_points.iter().sum::<i32>() - min
    }
}
```

### **...**

```

```

<!-- tabs:end -->
