# [LCP 40. 心算挑战](https://leetcode.cn/problems/uOAnQW)

## 题目描述

<!-- 这里写题目描述 -->

「力扣挑战赛」心算项目的挑战比赛中，要求选手从 `N` 张卡牌中选出 `cnt` 张卡牌，若这 `cnt` 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 `cnt` 张卡牌数字总和。
给定数组 `cards` 和 `cnt`，其中 `cards[i]` 表示第 `i` 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。

**示例 1：**

> 输入：`cards = [1,2,8,9], cnt = 3`
>
> 输出：`18`
>
> 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。

**示例 2：**

> 输入：`cards = [3,3,1], cnt = 1`
>
> 输出：`0`
>
> 解释：不存在获取有效得分的卡牌方案。

**提示：**

-   `1 <= cnt <= cards.length <= 10^5`
-   `1 <= cards[i] <= 1000`

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

排序先取最大的 $cnt$ 个数，如果和为偶数则直接返回答案。

否则，找一个已取的最小奇数换成剩余未取的最大偶数，或者找一个已取的最小偶数换成剩下未取的最大奇数，取两者中较大的。

时间复杂度 $O(nlogn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxmiumScore(self, cards: List[int], cnt: int) -> int:
        cards.sort(reverse=True)
        t = cards[:cnt]
        ans = sum(t)
        if ans % 2 == 0:
            return ans
        a = min([v for v in t if v & 1], default=inf)
        b = min([v for v in t if v % 2 == 0], default=inf)
        c = max([v for v in cards[cnt:] if v % 2 == 0], default=-inf)
        d = max([v for v in cards[cnt:] if v & 1], default=-inf)
        return max(ans - a + c, ans - b + d, 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int ans = 0;
        int n = cards.length;
        for (int i = 0; i < cnt; ++i) {
            ans += cards[n - i - 1];
        }
        if (ans % 2 == 0) {
            return ans;
        }
        int inf = 0x3f3f3f3f;
        int a = inf, b = inf;
        for (int i = 0; i < cnt; ++i) {
            int v = cards[n - i - 1];
            if (v % 2 == 1) {
                a = Math.min(a, v);
            } else {
                b = Math.min(b, v);
            }
        }
        int c = -inf, d = -inf;
        for (int i = cnt; i < n; ++i) {
            int v = cards[n - i - 1];
            if (v % 2 == 0) {
                c = Math.max(c, v);
            } else {
                d = Math.max(d, v);
            }
        }
        return Math.max(0, Math.max(ans - a + c, ans - b + d));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxmiumScore(vector<int>& cards, int cnt) {
        sort(cards.begin(), cards.end());
        reverse(cards.begin(), cards.end());
        int ans = 0, n = cards.size();
        for (int i = 0; i < cnt; ++i) ans += cards[i];
        if (ans % 2 == 0) return ans;
        int inf = 0x3f3f3f3f;
        int a = inf, b = inf, c = -inf, d = -inf;
        for (int i = 0; i < cnt; ++i) {
            int v = cards[i];
            if (v % 2 == 1)
                a = min(a, v);
            else
                b = min(b, v);
        }
        for (int i = cnt; i < n; ++i) {
            int v = cards[i];
            if (v % 2 == 0)
                c = max(c, v);
            else
                d = max(d, v);
        }
        return max(0, max(ans - a + c, ans - b + d));
    }
};
```

### **Go**

```go
func maxmiumScore(cards []int, cnt int) int {
	ans := 0
	sort.Slice(cards, func(i, j int) bool { return cards[i] > cards[j] })
	for _, v := range cards[:cnt] {
		ans += v
	}
	if ans%2 == 0 {
		return ans
	}
	inf := 0x3f3f3f3f
	a, b, c, d := inf, inf, -inf, -inf
	for _, v := range cards[:cnt] {
		if v%2 == 1 {
			a = min(a, v)
		} else {
			b = min(b, v)
		}
	}
	for _, v := range cards[cnt:] {
		if v%2 == 0 {
			c = max(c, v)
		} else {
			d = max(d, v)
		}
	}
	return max(0, max(ans-a+c, ans-b+d))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
