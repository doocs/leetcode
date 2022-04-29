# [2073. 买票需要的时间](https://leetcode.cn/problems/time-needed-to-buy-tickets)

[English Version](/solution/2000-2099/2073.Time%20Needed%20to%20Buy%20Tickets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个人前来排队买票，其中第 <code>0</code> 人站在队伍 <strong>最前方</strong> ，第 <code>(n - 1)</code> 人站在队伍 <strong>最后方</strong> 。</p>

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>tickets</code> ，数组长度为 <code>n</code> ，其中第 <code>i</code> 人想要购买的票数为 <code>tickets[i]</code> 。</p>

<p>每个人买票都需要用掉 <strong>恰好 1 秒</strong> 。一个人 <strong>一次只能买一张票</strong> ，如果需要购买更多票，他必须走到&nbsp; <strong>队尾</strong> 重新排队（<strong>瞬间 </strong>发生，不计时间）。如果一个人没有剩下需要买的票，那他将会 <strong>离开</strong> 队伍。</p>

<p>返回位于位置 <code>k</code>（下标从 <strong>0</strong> 开始）的人完成买票需要的时间（以秒为单位）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>tickets = [2,3,2], k = 2
<strong>输出：</strong>6
<strong>解释：</strong> 
- 第一轮，队伍中的每个人都买到一张票，队伍变为 [1, 2, 1] 。
- 第二轮，队伍中的每个都又都买到一张票，队伍变为 [0, 1, 0] 。
位置 2 的人成功买到 2 张票，用掉 3 + 3 = 6 秒。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>tickets = [5,1,1,1], k = 0
<strong>输出：</strong>8
<strong>解释：</strong>
- 第一轮，队伍中的每个人都买到一张票，队伍变为 [4, 0, 0, 0] 。
- 接下来的 4 轮，只有位置 0 的人在买票。
位置 0 的人成功买到 5 张票，用掉 4 + 1 + 1 + 1 + 1 = 8 秒。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == tickets.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= tickets[i] &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

第 k 个人买完之前，排在 k 后面的人最多能买 `tickets[k] - 1` 次，排在 k 前面的人最多能买 `tickets[k]` 次

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def timeRequiredToBuy(self, tickets: List[int], k: int) -> int:
        ans = 0
        for i, t in enumerate(tickets):
            if i <= k:
                ans += min(tickets[k], t)
            else:
                ans += min(tickets[k] - 1, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int ans = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                ans += Math.min(tickets[k], tickets[i]);
            } else {
                ans += Math.min(tickets[k] - 1, tickets[i]);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function timeRequiredToBuy(tickets: number[], k: number): number {
    const n = tickets.length;
    let target = tickets[k] - 1;
    let ans = 0;
    // round1
    for (let i = 0; i < n; i++) {
        let num = tickets[i];
        if (num <= target) {
            ans += num;
            tickets[i] = 0;
        } else {
            ans += target;
            tickets[i] -= target;
        }
    }

    // round2
    for (let i = 0; i <= k; i++) {
        let num = tickets[i];
        ans += num > 0 ? 1 : 0;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int timeRequiredToBuy(vector<int>& tickets, int k) {
        int ans = 0;
        for (int i = 0; i < tickets.size(); ++i) {
            if (i <= k) {
                ans += min(tickets[k], tickets[i]);
            } else {
                ans += min(tickets[k] - 1, tickets[i]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func timeRequiredToBuy(tickets []int, k int) int {
	ans := 0
	for i, t := range tickets {
		if i <= k {
			ans += min(tickets[k], t)
		} else {
			ans += min(tickets[k]-1, t)
		}
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

### **...**

```

```

<!-- tabs:end -->
