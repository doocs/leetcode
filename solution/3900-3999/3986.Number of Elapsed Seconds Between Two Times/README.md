---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3986.Number%20of%20Elapsed%20Seconds%20Between%20Two%20Times/README.md
rating: 1205
source: 第 510 场周赛 Q1
---

<!-- problem:start -->

# [3986. 统计起止时间经过的秒数](https://leetcode.cn/problems/number-of-elapsed-seconds-between-two-times)

[English Version](/solution/3900-3999/3986.Number%20of%20Elapsed%20Seconds%20Between%20Two%20Times/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个有效时间 <code>startTime</code> 和 <code>endTime</code>，它们均以字符串形式表示，格式为 <code>"HH:MM:SS"</code>。</p>

<p>返回从 <code>startTime</code> 到 <code>endTime</code> 经过的秒数（包含两个端点）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">startTime = "01:00:00", endTime = "01:00:25"</span></p>

<p><strong>输出：</strong> <span class="example-io">25</span></p>

<p><strong>解释：</strong></p>

<p><code>endTime</code> 比 <code>startTime</code> 晚 25 秒。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">startTime = "12:34:56", endTime = "13:00:00"</span></p>

<p><strong>输出：</strong> <span class="example-io">1504</span></p>

<p><strong>解释：</strong></p>

<p><code>endTime</code> 比 <code>startTime</code> 晚 25 分 4 秒，共计 1504 秒。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>startTime.length == 8</code></li>
	<li><code>endTime.length == 8</code></li>
	<li><code>startTime</code> 和 <code>endTime</code> 均为格式 <code>"HH:MM:SS"</code> 的有效时间</li>
	<li><code>00 &lt;= HH &lt;= 23</code></li>
	<li><code>00 &lt;= MM &lt;= 59</code></li>
	<li><code>00 &lt;= SS &lt;= 59</code></li>
	<li><code>endTime</code> 不早于 <code>startTime</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

将时间字符串转换为从 $00$:$00$:$00$ 起经过的秒数，即 $HH \times 3600 + MM \times 60 + SS$，再计算两个时间对应秒数之差即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def secondsBetweenTimes(self, startTime: str, endTime: str) -> int:
        def f(s: str) -> int:
            return int(s[:2]) * 3600 + int(s[3:5]) * 60 + int(s[6:])

        return f(endTime) - f(startTime)
```

#### Java

```java
class Solution {
    public int secondsBetweenTimes(String startTime, String endTime) {
        return f(endTime) - f(startTime);
    }

    private int f(String s) {
        return Integer.parseInt(s.substring(0, 2)) * 3600 + Integer.parseInt(s.substring(3, 5)) * 60
            + Integer.parseInt(s.substring(6));
    }
}
```

#### C++

```cpp
class Solution {
public:
    int secondsBetweenTimes(string startTime, string endTime) {
        return f(endTime) - f(startTime);
    }

private:
    int f(const string& s) {
        return stoi(s.substr(0, 2)) * 3600
            + stoi(s.substr(3, 2)) * 60
            + stoi(s.substr(6));
    }
};
```

#### Go

```go
func secondsBetweenTimes(startTime string, endTime string) int {
	return f(endTime) - f(startTime)
}

func f(s string) int {
	h, _ := strconv.Atoi(s[:2])
	m, _ := strconv.Atoi(s[3:5])
	sec, _ := strconv.Atoi(s[6:])
	return h*3600 + m*60 + sec
}
```

#### TypeScript

```ts
function secondsBetweenTimes(startTime: string, endTime: string): number {
    return f(endTime) - f(startTime);
}

function f(s: string): number {
    return parseInt(s.slice(0, 2)) * 3600 + parseInt(s.slice(3, 5)) * 60 + parseInt(s.slice(6));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
