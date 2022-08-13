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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumCardPickup(self, cards: List[int]) -> int:
        m = {}
        ans = 10**6
        for i, c in enumerate(cards):
            if c in m:
                ans = min(ans, i - m[c] + 1)
            m[c] = i
        return -1 if ans == 10**6 else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> m = new HashMap<>();
        int ans = 1000000;
        for (int i = 0; i < cards.length; ++i) {
            int c = cards[i];
            if (m.containsKey(c)) {
                ans = Math.min(ans, i - m.get(c) + 1);
            }
            m.put(c, i);
        }
        return ans == 1000000 ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumCardPickup(vector<int>& cards) {
        unordered_map<int, int> m;
        int ans = 1e6;
        for (int i = 0; i < cards.size(); ++i) {
            int c = cards[i];
            if (m.count(c)) ans = min(ans, i - m[c] + 1);
            m[c] = i;
        }
        return ans == 1e6 ? -1 : ans;
    }
};
```

### **Go**

```go
func minimumCardPickup(cards []int) int {
	m := map[int]int{}
	ans := 1000000
	for i, c := range cards {
		if j, ok := m[c]; ok {
			ans = min(ans, i-j+1)
		}
		m[c] = i
	}
	if ans == 1000000 {
		return -1
	}
	return ans
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
function minimumCardPickup(cards: number[]): number {
    const n = cards.length;
    let hashMap = new Map<number, number>();
    const max = Number.MAX_SAFE_INTEGER;
    let ans = max;
    for (let i = 0; i < n; i++) {
        let num = cards[i];
        if (hashMap.has(num)) {
            ans = Math.min(i - hashMap.get(num) + 1, ans);
        }
        hashMap.set(num, i);
    }
    return ans == max ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
