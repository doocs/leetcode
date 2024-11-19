---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2040.%20%E5%BF%83%E7%AE%97%E6%8C%91%E6%88%98/README.md
---

<!-- problem:start -->

# [LCP 40. 心算挑战](https://leetcode.cn/problems/uOAnQW)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

我们注意到，题目选取的是子序列，因此我们可以考虑先对数组进行排序。

接下来，我们先贪心地选取最大的 $\textit{cnt}$ 个数，如果这些数的和为偶数，则直接返回这个和 $ans$。

否则，我们有两种贪心策略：

1. 在最大的 $\textit{cnt}$ 个数中，找到一个最小的偶数 $mi1$，然后在剩下的 $n - \textit{cnt}$ 个数中，找到一个最大的奇数 $mx1$，将 $mi1$ 替换为 $mx1$，如果存在这样的替换，那么替换后的和 $ans - mi1 + mx1$ 一定是偶数；
1. 在最大的 $\textit{cnt}$ 个数中，找到一个最小的奇数 $mi2$，然后在剩下的 $n - \textit{cnt}$ 个数中，找到一个最大的偶数 $mx2$，将 $mi2$ 替换为 $mx2$，如果存在这样的替换，那么替换后的和 $ans - mi2 + mx2$ 一定是偶数。

我们取最大的偶数和作为答案。如果不存在偶数和，则返回 $0$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxmiumScore(self, cards: List[int], cnt: int) -> int:
        cards.sort()
        ans = sum(cards[-cnt:])
        if ans % 2 == 0:
            return ans
        n = len(cards)
        mx1 = mx2 = -inf
        for x in cards[: n - cnt]:
            if x & 1:
                mx1 = x
            else:
                mx2 = x
        mi1 = mi2 = inf
        for x in cards[-cnt:][::-1]:
            if x & 1:
                mi2 = x
            else:
                mi1 = x
        ans = max(ans - mi1 + mx1, ans - mi2 + mx2, -1)
        return 0 if ans < 0 else ans
```

#### Java

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
        final int inf = 1 << 29;
        int mx1 = -inf, mx2 = -inf;
        for (int i = 0; i < n - cnt; ++i) {
            if (cards[i] % 2 == 1) {
                mx1 = cards[i];
            } else {
                mx2 = cards[i];
            }
        }
        int mi1 = inf, mi2 = inf;
        for (int i = n - 1; i >= n - cnt; --i) {
            if (cards[i] % 2 == 1) {
                mi2 = cards[i];
            } else {
                mi1 = cards[i];
            }
        }
        ans = Math.max(ans - mi1 + mx1, ans - mi2 + mx2);
        return ans < 0 ? 0 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxmiumScore(vector<int>& cards, int cnt) {
        sort(cards.begin(), cards.end());
        int ans = 0;
        int n = cards.size();
        for (int i = 0; i < cnt; ++i) {
            ans += cards[n - i - 1];
        }
        if (ans % 2 == 0) {
            return ans;
        }
        const int inf = 1 << 29;
        int mx1 = -inf, mx2 = -inf;
        for (int i = 0; i < n - cnt; ++i) {
            if (cards[i] % 2) {
                mx1 = cards[i];
            } else {
                mx2 = cards[i];
            }
        }
        int mi1 = inf, mi2 = inf;
        for (int i = n - 1; i >= n - cnt; --i) {
            if (cards[i] % 2) {
                mi2 = cards[i];
            } else {
                mi1 = cards[i];
            }
        }
        ans = max(ans - mi1 + mx1, ans - mi2 + mx2);
        return ans < 0 ? 0 : ans;
    }
};
```

#### Go

```go
func maxmiumScore(cards []int, cnt int) int {
	sort.Ints(cards)
	ans := 0
	n := len(cards)
	for i := 0; i < cnt; i++ {
		ans += cards[n-1-i]
	}
	if ans%2 == 0 {
		return ans
	}
	const inf = 1 << 29
	mx1, mx2 := -inf, -inf
	for _, x := range cards[:n-cnt] {
		if x%2 == 1 {
			mx1 = x
		} else {
			mx2 = x
		}
	}
	mi1, mi2 := inf, inf
	for i := n - 1; i >= n-cnt; i-- {
		if cards[i]%2 == 1 {
			mi2 = cards[i]
		} else {
			mi1 = cards[i]
		}
	}
	ans = max(-1, max(ans-mi1+mx1, ans-mi2+mx2))
	if ans < 0 {
		return 0
	}
	return ans
}
```

#### TypeScript

```ts
function maxmiumScore(cards: number[], cnt: number): number {
    cards.sort((a, b) => a - b);
    let ans = 0;
    const n = cards.length;
    for (let i = 0; i < cnt; ++i) {
        ans += cards[n - i - 1];
    }
    if (ans % 2 === 0) {
        return ans;
    }
    const inf = 1 << 29;
    let mx1 = -inf,
        mx2 = -inf;
    for (let i = 0; i < n - cnt; ++i) {
        if (cards[i] % 2 === 1) {
            mx1 = cards[i];
        } else {
            mx2 = cards[i];
        }
    }
    let mi1 = inf,
        mi2 = inf;
    for (let i = n - 1; i >= n - cnt; --i) {
        if (cards[i] % 2 === 1) {
            mi2 = cards[i];
        } else {
            mi1 = cards[i];
        }
    }
    ans = Math.max(ans - mi1 + mx1, ans - mi2 + mx2);
    return ans < 0 ? 0 : ans;
}
```

#### Swift

```swift
class Solution {
    func maximumScore(_ cards: [Int], _ cnt: Int) -> Int {
        let sortedCards = cards.sorted()
        let n = sortedCards.count
        var ans = 0

        for i in 0..<cnt {
            ans += sortedCards[n - i - 1]
        }

        if ans % 2 == 0 {
            return ans
        }

        var smallestOddInside = Int.max
        var smallestEvenInside = Int.max
        var largestOddOutside = Int.min
        var largestEvenOutside = Int.min

        for i in (n - cnt)..<n {
            if sortedCards[i] % 2 == 1 {
                smallestOddInside = min(smallestOddInside, sortedCards[i])
            } else {
                smallestEvenInside = min(smallestEvenInside, sortedCards[i])
            }
        }

        for i in 0..<(n - cnt) {
            if sortedCards[i] % 2 == 1 {
                largestOddOutside = max(largestOddOutside, sortedCards[i])
            } else {
                largestEvenOutside = max(largestEvenOutside, sortedCards[i])
            }
        }

        var maxScore = -1
        if smallestOddInside != Int.max && largestEvenOutside != Int.min {
            maxScore = max(maxScore, ans - smallestOddInside + largestEvenOutside)
        }
        if smallestEvenInside != Int.max && largestOddOutside != Int.min {
            maxScore = max(maxScore, ans - smallestEvenInside + largestOddOutside)
        }

        return maxScore >= 0 ? maxScore : 0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
