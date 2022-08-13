# [2162. 设置时间的最少代价](https://leetcode.cn/problems/minimum-cost-to-set-cooking-time)

[English Version](/solution/2100-2199/2162.Minimum%20Cost%20to%20Set%20Cooking%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>常见的微波炉可以设置加热时间，且加热时间满足以下条件：</p>

<ul>
	<li>至少为 <code>1</code>&nbsp;秒钟。</li>
	<li>至多为&nbsp;<code>99</code>&nbsp;分&nbsp;<code>99</code>&nbsp;秒。</li>
</ul>

<p>你可以 <strong>最多</strong>&nbsp;输入&nbsp;<strong>4 个数字</strong>&nbsp;来设置加热时间。如果你输入的位数不足 4 位，微波炉会自动加 <strong>前缀</strong>&nbsp;<strong>0</strong>&nbsp;来补足 4 位。微波炉会将设置好的四位数中，<strong>前</strong>&nbsp;两位当作分钟数，<strong>后</strong>&nbsp;两位当作秒数。它们所表示的总时间就是加热时间。比方说：</p>

<ul>
	<li>你输入&nbsp;<code>9</code> <code>5</code> <code>4</code>&nbsp;（三个数字），被自动补足为&nbsp;<code>0954</code>&nbsp;，并表示&nbsp;<code>9</code>&nbsp;分&nbsp;<code>54</code>&nbsp;秒。</li>
	<li>你输入&nbsp;<code>0</code> <code>0</code> <code>0</code> <code>8</code>&nbsp;（四个数字），表示&nbsp;<code>0</code>&nbsp;分&nbsp;<code>8</code>&nbsp;秒。</li>
	<li>你输入&nbsp;<code>8</code> <code>0</code> <code>9</code> <code>0</code>&nbsp;，表示&nbsp;<code>80</code>&nbsp;分&nbsp;<code>90</code>&nbsp;秒。</li>
	<li>你输入&nbsp;<code>8</code> <code>1</code> <code>3</code> <code>0</code>&nbsp;，表示&nbsp;<code>81</code>&nbsp;分&nbsp;<code>30</code>&nbsp;秒。</li>
</ul>

<p>给你整数&nbsp;<code>startAt</code>&nbsp;，<code>moveCost</code>&nbsp;，<code>pushCost</code>&nbsp;和&nbsp;<code>targetSeconds</code>&nbsp;。<strong>一开始</strong>，你的手指在数字&nbsp;<code>startAt</code>&nbsp;处。将手指移到<strong>&nbsp;任何其他数字</strong>&nbsp;，需要花费&nbsp;<code>moveCost</code>&nbsp;的单位代价。<strong>每</strong>&nbsp;输入你手指所在位置的数字一次，需要花费&nbsp;<code>pushCost</code>&nbsp;的单位代价。</p>

<p>要设置&nbsp;<code>targetSeconds</code>&nbsp;秒的加热时间，可能会有多种设置方法。你想要知道这些方法中，总代价最小为多少。</p>

<p>请你能返回设置&nbsp;<code>targetSeconds</code>&nbsp;秒钟加热时间需要花费的最少代价。</p>

<p>请记住，虽然微波炉的秒数最多可以设置到 <code>99</code>&nbsp;秒，但一分钟等于&nbsp;<code>60</code>&nbsp;秒。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2162.Minimum%20Cost%20to%20Set%20Cooking%20Time/images/1.png" style="width: 506px; height: 210px;"></p>

<pre><b>输入：</b>startAt = 1, moveCost = 2, pushCost = 1, targetSeconds = 600
<b>输出：</b>6
<b>解释：</b>以下为设置加热时间的所有方法。
- 1 0 0 0 ，表示 10 分 0 秒。
&nbsp; 手指一开始就在数字 1 处，输入 1 （代价为 1），移到 0 处（代价为 2），输入 0（代价为 1），输入 0（代价为 1），输入 0（代价为 1）。
&nbsp; 总代价为：1 + 2 + 1 + 1 + 1 = 6 。这是所有方案中的最小代价。
- 0 9 6 0，表示 9 分 60 秒。它也表示 600 秒。
&nbsp; 手指移到 0 处（代价为 2），输入 0 （代价为 1），移到 9 处（代价为 2），输入 9（代价为 1），移到 6 处（代价为 2），输入 6（代价为 1），移到 0 处（代价为 2），输入 0（代价为 1）。
&nbsp; 总代价为：2 + 1 + 2 + 1 + 2 + 1 + 2 + 1 = 12 。
- 9 6 0，微波炉自动补全为 0960 ，表示 9 分 60 秒。
&nbsp; 手指移到 9 处（代价为 2），输入 9 （代价为 1），移到 6 处（代价为 2），输入 6（代价为 1），移到 0 处（代价为 2），输入 0（代价为 1）。
&nbsp; 总代价为：2 + 1 + 2 + 1 + 2 + 1 = 9 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2162.Minimum%20Cost%20to%20Set%20Cooking%20Time/images/2.png" style="width: 505px; height: 73px;"></p>

<pre><b>输入：</b>startAt = 0, moveCost = 1, pushCost = 2, targetSeconds = 76
<b>输出：</b>6
<b>解释：</b>最优方案为输入两个数字 7 6，表示 76 秒。
手指移到 7 处（代价为 1），输入 7 （代价为 2），移到 6 处（代价为 1），输入 6（代价为 2）。总代价为：1 + 2 + 1 + 2 = 6
其他可行方案为 0076 ，076 ，0116 和 116 ，但是它们的代价都比 6 大。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= startAt &lt;= 9</code></li>
	<li><code>1 &lt;= moveCost, pushCost &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= targetSeconds &lt;= 6039</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCostSetTime(
        self, startAt: int, moveCost: int, pushCost: int, targetSeconds: int
    ) -> int:
        def f(m, s):
            if not 0 <= m < 100 or not 0 <= s < 100:
                return inf
            arr = [m // 10, m % 10, s // 10, s % 10]
            i = 0
            while i < 4 and arr[i] == 0:
                i += 1
            t = 0
            prev = startAt
            for v in arr[i:]:
                if v != prev:
                    t += moveCost
                t += pushCost
                prev = v
            return t

        m, s = divmod(targetSeconds, 60)
        ans = min(f(m, s), f(m - 1, s + 60))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int m = targetSeconds / 60;
        int s = targetSeconds % 60;
        return Math.min(f(m, s, startAt, moveCost, pushCost), f(m - 1, s + 60, startAt, moveCost, pushCost));
    }

    private int f(int m, int s, int prev, int moveCost, int pushCost) {
        if (m < 0 || m > 99 || s < 0 || s > 99) {
            return Integer.MAX_VALUE;
        }
        int[] arr = new int[]{m / 10, m % 10, s / 10, s % 10};
        int i = 0;
        for (; i < 4 && arr[i] == 0; ++i);
        int t = 0;
        for (; i < 4; ++i) {
            if (arr[i] != prev) {
                t += moveCost;
            }
            t += pushCost;
            prev = arr[i];
        }
        return t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int m = targetSeconds / 60, s = targetSeconds % 60;
        return min(f(m, s, startAt, moveCost, pushCost), f(m - 1, s + 60, startAt, moveCost, pushCost));
    }

    int f(int m, int s, int prev, int moveCost, int pushCost) {
        if (m < 0 || m > 99 || s < 0 || s > 99) return INT_MAX;
        vector<int> arr = {m / 10, m % 10, s / 10, s % 10};
        int i = 0;
        for (; i < 4 && arr[i] == 0; ++i)
            ;
        int t = 0;
        for (; i < 4; ++i) {
            if (arr[i] != prev) t += moveCost;
            t += pushCost;
            prev = arr[i];
        }
        return t;
    }
};
```

### **Go**

```go
func minCostSetTime(startAt int, moveCost int, pushCost int, targetSeconds int) int {
	m, s := targetSeconds/60, targetSeconds%60
	f := func(m, s int) int {
		if m < 0 || m > 99 || s < 0 || s > 99 {
			return 0x3f3f3f3f
		}
		arr := []int{m / 10, m % 10, s / 10, s % 10}
		i := 0
		for ; i < 4 && arr[i] == 0; i++ {
		}
		t := 0
		prev := startAt
		for ; i < 4; i++ {
			if arr[i] != prev {
				t += moveCost
			}
			t += pushCost
			prev = arr[i]
		}
		return t
	}
	return min(f(m, s), f(m-1, s+60))
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

```

### **...**

```

```

<!-- tabs:end -->
