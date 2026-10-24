---
comments: true
difficulty: 简单
rating: 1562
source: 第 87 场双周赛 Q1
tags:
    - 数学
    - 字符串
---

<!-- problem:start -->

# [2409. 统计共同度过的日子数](https://leetcode.cn/problems/count-days-spent-together)

[English Version](/solution/2400-2499/2409.Count%20Days%20Spent%20Together/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

<!-- thinking:start -->

> **思考**
>
> 共同天数是两段闭区间的交集长度。逐日枚举全年可以做完，日期却是 $\texttt{MM-DD}$，比较和计数都要先落到同一个序号上。
>
> 现场把前面各月天数加起来，每次转换都重复同一段常数工作。该格式的字典序与日历顺序一致，交集由较晚的到达日和较早的离开日决定；非闰年各月长度固定，预先求前缀和 $f$ 后，第 $m$ 月 $d$ 日直接对应年内第 $f_{m-1}+d$ 天。
>
> 两端日期经 $f$ 换成序号后相减加一，即得交集长度；左端晚于右端则为空。

<!-- thinking:end -->

我们将两个到达日与离开日分别取较晚者和较早者，再用月份天数的前缀和换成当年的第几天，最后计算闭区间长度。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
f = list(accumulate([31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], initial=0))


class Solution:
    def countDaysTogether(
        self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str
    ) -> int:
        start = max(arriveAlice, arriveBob)
        end = min(leaveAlice, leaveBob)
        d1 = f[int(start[:2]) - 1] + int(start[3:])
        d2 = f[int(end[:2]) - 1] + int(end[3:])
        return max(d2 - d1 + 1, 0)
```

#### Java

```java
class Solution {
    static final int[] f = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    public int countDaysTogether(
        String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String start = arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob;
        String end = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;
        int d1
            = f[Integer.parseInt(start.substring(0, 2)) - 1] + Integer.parseInt(start.substring(3));
        int d2 = f[Integer.parseInt(end.substring(0, 2)) - 1] + Integer.parseInt(end.substring(3));
        return Math.max(d2 - d1 + 1, 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    static constexpr int f[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    int countDaysTogether(string arriveAlice, string leaveAlice, string arriveBob, string leaveBob) {
        string start = max(arriveAlice, arriveBob);
        string end = min(leaveAlice, leaveBob);
        int d1 = f[stoi(start.substr(0, 2)) - 1] + stoi(start.substr(3));
        int d2 = f[stoi(end.substr(0, 2)) - 1] + stoi(end.substr(3));
        return max(d2 - d1 + 1, 0);
    }
};
```

#### Go

```go
var f = [12]int{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334}

func countDaysTogether(arriveAlice string, leaveAlice string, arriveBob string, leaveBob string) int {
	start := arriveAlice
	if arriveBob > start {
		start = arriveBob
	}
	end := leaveAlice
	if leaveBob < end {
		end = leaveBob
	}
	d1 := f[int(start[0]-'0')*10+int(start[1]-'0')-1] + int(start[3]-'0')*10 + int(start[4]-'0')
	d2 := f[int(end[0]-'0')*10+int(end[1]-'0')-1] + int(end[3]-'0')*10 + int(end[4]-'0')
	return max(d2-d1+1, 0)
}
```

#### TypeScript

```ts
const f = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];

function countDaysTogether(
    arriveAlice: string,
    leaveAlice: string,
    arriveBob: string,
    leaveBob: string,
): number {
    const start = arriveAlice > arriveBob ? arriveAlice : arriveBob;
    const end = leaveAlice < leaveBob ? leaveAlice : leaveBob;
    const d1 = f[Number(start.slice(0, 2)) - 1] + Number(start.slice(3));
    const d2 = f[Number(end.slice(0, 2)) - 1] + Number(end.slice(3));
    return Math.max(d2 - d1 + 1, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn count_days_together(
        arrive_alice: String,
        leave_alice: String,
        arrive_bob: String,
        leave_bob: String,
    ) -> i32 {
        let f = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];
        let start = arrive_alice.max(arrive_bob);
        let end = leave_alice.min(leave_bob);
        let d1 = f[start[..2].parse::<usize>().unwrap() - 1] + start[3..].parse::<i32>().unwrap();
        let d2 = f[end[..2].parse::<usize>().unwrap() - 1] + end[3..].parse::<i32>().unwrap();
        (d2 - d1 + 1).max(0)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
