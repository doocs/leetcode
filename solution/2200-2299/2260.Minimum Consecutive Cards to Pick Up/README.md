# [2260. 必须拿起的最小连续卡牌数](https://leetcode.cn/problems/minimum-consecutive-cards-to-pick-up)

[English Version](/solution/2200-2299/2260.Minimum%20Consecutive%20Cards%20to%20Pick%20Up/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>cards</code> ，其中 <code>cards[i]</code> 表示第 <code>i</code> 张卡牌的 <strong>值</strong> 。如果两张卡牌的值相同，则认为这一对卡牌 <strong>匹配</strong> 。</p>

<p>返回你必须拿起的最小连续卡牌数，以使在拿起的卡牌中有一对匹配的卡牌。如果无法得到一对匹配的卡牌，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>cards = [3,4,2,3,4,7]
<strong>输出：</strong>4
<strong>解释：</strong>拿起卡牌 [3,4,2,3] 将会包含一对值为 3 的匹配卡牌。注意，拿起 [4,2,3,4] 也是最优方案。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>cards = [1,0,5,3]
<strong>输出：</strong>-1
<strong>解释：</strong>无法找出含一对匹配卡牌的一组连续卡牌。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= cards.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= cards[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们初始化答案为 $+\infty$，遍历数组，对于每个数字 $x$，如果 $last[x]$ 存在，则表示 $x$ 有一对匹配卡牌，此时更新答案为 $ans = min(ans, i - last[x] + 1)$，最后如果答案为 $+\infty$，则返回 $-1$，否则返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumCardPickup(self, cards: List[int]) -> int:
        last = {}
        ans = inf
        for i, x in enumerate(cards):
            if x in last:
                ans = min(ans, i - last[x] + 1)
            last[x] = i
        return -1 if ans == inf else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> last = new HashMap<>();
        int n = cards.length;
        int ans = n + 1;
        for (int i = 0; i < n; ++i) {
            if (last.containsKey(cards[i])) {
                ans = Math.min(ans, i - last.get(cards[i]) + 1);
            }
            last.put(cards[i], i);
        }
        return ans > n ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumCardPickup(vector<int>& cards) {
        unordered_map<int, int> last;
        int n = cards.size();
        int ans = n + 1;
        for (int i = 0; i < n; ++i) {
            if (last.count(cards[i])) {
                ans = min(ans, i - last[cards[i]] + 1);
            }
            last[cards[i]] = i;
        }
        return ans > n ? -1 : ans;
    }
};
```

### **Go**

```go
func minimumCardPickup(cards []int) int {
	last := map[int]int{}
	n := len(cards)
	ans := n + 1
	for i, x := range cards {
		if j, ok := last[x]; ok && ans > i-j+1 {
			ans = i - j + 1
		}
		last[x] = i
	}
	if ans > n {
		return -1
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumCardPickup(cards: number[]): number {
    const n = cards.length;
    const last = new Map<number, number>();
    let ans = n + 1;
    for (let i = 0; i < n; ++i) {
        if (last.has(cards[i])) {
            ans = Math.min(ans, i - last.get(cards[i]) + 1);
        }
        last.set(cards[i], i);
    }
    return ans > n ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
