# [495. 提莫攻击](https://leetcode.cn/problems/teemo-attacking)

[English Version](/solution/0400-0499/0495.Teemo%20Attacking/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄。他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。</p>

<p>当提莫攻击艾希，艾希的中毒状态正好持续&nbsp;<code>duration</code> 秒。</p>

<p>正式地讲，提莫在 <code>t</code> 发起攻击意味着艾希在时间区间 <code>[t, t + duration - 1]</code>（含 <code>t</code> 和 <code>t + duration - 1</code>）处于中毒状态。如果提莫在中毒影响结束 <strong>前</strong> 再次攻击，中毒状态计时器将会 <strong>重置</strong> ，在新的攻击之后，中毒影响将会在 <code>duration</code> 秒后结束。</p>

<p>给你一个 <strong>非递减</strong> 的整数数组 <code>timeSeries</code> ，其中 <code>timeSeries[i]</code> 表示提莫在 <code>timeSeries[i]</code> 秒时对艾希发起攻击，以及一个表示中毒持续时间的整数 <code>duration</code> 。</p>

<p>返回艾希处于中毒状态的 <strong>总</strong> 秒数。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>timeSeries = [1,4], duration = 2
<strong>输出：</strong>4
<strong>解释：</strong>提莫攻击对艾希的影响如下：
- 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
- 第 4 秒，提莫再次攻击艾希，艾希中毒状态又持续 2 秒，即第 4 秒和第 5 秒。
艾希在第 1、2、4、5 秒处于中毒状态，所以总中毒秒数是 4 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>timeSeries = [1,2], duration = 2
<strong>输出：</strong>3
<strong>解释：</strong>提莫攻击对艾希的影响如下：
- 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
- 第 2 秒，提莫再次攻击艾希，并重置中毒计时器，艾希中毒状态需要持续 2 秒，即第 2 秒和第 3 秒。
艾希在第 1、2、3 秒处于中毒状态，所以总中毒秒数是 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= timeSeries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= timeSeries[i], duration &lt;= 10<sup>7</sup></code></li>
	<li><code>timeSeries</code> 按 <strong>非递减</strong> 顺序排列</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：一次遍历**

我们先考虑最后一次攻击，此次攻击一定可以使得艾希处于中毒状态，所以总中毒时间至少为 `duration`。

接下来，我们考虑前 $n-1$ 次攻击，每一次攻击的中毒持续时间为 $min(duration, timeSeries[i] - timeSeries[i-1])$，其中 $i$ 从 1 开始。我们将这些中毒持续时间累加起来，即为总中毒时间。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `timeSeries` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findPoisonedDuration(self, timeSeries: List[int], duration: int) -> int:
        ans = duration
        for a, b in pairwise(timeSeries):
            ans += min(duration, b - a)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        int ans = duration;
        for (int i = 1; i < n; ++i) {
            ans += Math.min(duration, timeSeries[i] - timeSeries[i - 1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findPoisonedDuration(vector<int>& timeSeries, int duration) {
        int ans = duration;
        int n = timeSeries.size();
        for (int i = 1; i < n; ++i) {
            ans += min(duration, timeSeries[i] - timeSeries[i - 1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func findPoisonedDuration(timeSeries []int, duration int) (ans int) {
	ans = duration
	for i, x := range timeSeries[1:] {
		ans += min(duration, x-timeSeries[i])
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public int FindPoisonedDuration(int[] timeSeries, int duration) {
        int ans = duration;
        int n = timeSeries.Length;
        for (int i = 1; i < n; ++i) {
            ans += Math.Min(duration, timeSeries[i] - timeSeries[i - 1]);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function findPoisonedDuration(timeSeries: number[], duration: number): number {
    const n = timeSeries.length;
    let ans = duration;
    for (let i = 1; i < n; ++i) {
        ans += Math.min(duration, timeSeries[i] - timeSeries[i - 1]);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
