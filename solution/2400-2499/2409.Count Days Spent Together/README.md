# [2409. 统计共同度过的日子数](https://leetcode.cn/problems/count-days-spent-together)

[English Version](/solution/2400-2499/2409.Count%20Days%20Spent%20Together/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 计划分别去罗马开会。</p>

<p>给你四个字符串&nbsp;<code>arriveAlice</code>&nbsp;，<code>leaveAlice</code>&nbsp;，<code>arriveBob</code>&nbsp;和&nbsp;<code>leaveBob</code>&nbsp;。Alice 会在日期&nbsp;<code>arriveAlice</code>&nbsp;到&nbsp;<code>leaveAlice</code>&nbsp;之间在城市里（<strong>日期为闭区间</strong>），而 Bob 在日期&nbsp;<code>arriveBob</code>&nbsp;到&nbsp;<code>leaveBob</code>&nbsp;之间在城市里（<strong>日期为闭区间</strong>）。每个字符串都包含 5 个字符，格式为&nbsp;<code>"MM-DD"</code>&nbsp;，对应着一个日期的月和日。</p>

<p>请你返回 Alice和 Bob 同时在罗马的天数。</p>

<p>你可以假设所有日期都在 <strong>同一个</strong>&nbsp;自然年，而且 <strong>不是</strong>&nbsp;闰年。每个月份的天数分别为：<code>[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
<b>输出：</b>3
<b>解释：</b>Alice 从 8 月 15 号到 8 月 18 号在罗马。Bob 从 8 月 16 号到 8 月 19 号在罗马，他们同时在罗马的日期为 8 月 16、17 和 18 号。所以答案为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
<b>输出：</b>0
<b>解释：</b>Alice 和 Bob 没有同时在罗马的日子，所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>所有日期的格式均为&nbsp;<code>"MM-DD"</code>&nbsp;。</li>
	<li>Alice 和 Bob 的到达日期都 <strong>早于或等于</strong> 他们的离开日期。</li>
	<li>题目测试用例所给出的日期均为 <strong>非闰年</strong> 的有效日期。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们将日期转换为天数，然后计算两个人在罗马的天数。

时间复杂度 $O(C)$，空间复杂度 $O(C)$。其中 $C$ 为常数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countDaysTogether(self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str) -> int:
        a = max(arriveAlice, arriveBob)
        b = min(leaveAlice, leaveBob)
        days = (31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        x = sum(days[:int(a[:2]) - 1]) + int(a[3:])
        y = sum(days[:int(b[:2]) - 1]) + int(b[3:])
        return max(y - x + 1, 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] days = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(
        String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String a = arriveAlice.compareTo(arriveBob) < 0 ? arriveBob : arriveAlice;
        String b = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;
        int x = f(a), y = f(b);
        return Math.max(y - x + 1, 0);
    }

    private int f(String s) {
        int i = Integer.parseInt(s.substring(0, 2)) - 1;
        int res = 0;
        for (int j = 0; j < i; ++j) {
            res += days[j];
        }
        res += Integer.parseInt(s.substring(3));
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int countDaysTogether(string arriveAlice, string leaveAlice, string arriveBob, string leaveBob) {
        string a = arriveAlice < arriveBob ? arriveBob : arriveAlice;
        string b = leaveAlice < leaveBob ? leaveAlice : leaveBob;
        int x = f(a), y = f(b);
        return max(0, y - x + 1);
    }

    int f(string s) {
        int m, d;
        sscanf(s.c_str(), "%d-%d", &m, &d);
        int res = 0;
        for (int i = 0; i < m - 1; ++i) {
            res += days[i];
        }
        res += d;
        return res;
    }
};
```

### **Go**

```go
func countDaysTogether(arriveAlice string, leaveAlice string, arriveBob string, leaveBob string) int {
	days := []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	f := func(s string) int {
		m, _ := strconv.Atoi(s[:2])
		d, _ := strconv.Atoi(s[3:])
		res := 0
		for i := 0; i < m-1; i++ {
			res += days[i]
		}
		res += d
		return res
	}
	a, b := arriveAlice, leaveBob
	if arriveAlice < arriveBob {
		a = arriveBob
	}
	if leaveAlice < leaveBob {
		b = leaveAlice
	}
	x, y := f(a), f(b)
	ans := y - x + 1
	if ans < 0 {
		return 0
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
