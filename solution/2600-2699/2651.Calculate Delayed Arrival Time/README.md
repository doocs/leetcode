# [2651. 计算列车到站时间](https://leetcode.cn/problems/calculate-delayed-arrival-time)

[English Version](/solution/2600-2699/2651.Calculate%20Delayed%20Arrival%20Time/README_EN.md)

<!-- tags:数学 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数 <code>arrivalTime</code> 表示列车正点到站的时间（单位：小时），另给你一个正整数 <code>delayedTime</code> 表示列车延误的小时数。</p>

<p>返回列车实际到站的时间。</p>

<p>注意，该问题中的时间采用 24 小时制。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arrivalTime = 15, delayedTime = 5 
<strong>输出：</strong>20 
<strong>解释：</strong>列车正点到站时间是 15:00 ，延误 5 小时，所以列车实际到站的时间是 15 + 5 = 20（20:00）。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arrivalTime = 13, delayedTime = 11
<strong>输出：</strong>0
<strong>解释：</strong>列车正点到站时间是 13:00 ，延误 11 小时，所以列车实际到站的时间是 13 + 11 = 24（在 24 小时制中表示为 00:00 ，所以返回 0）。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arrivaltime &lt;&nbsp;24</code></li>
	<li><code>1 &lt;= delayedTime &lt;= 24</code></li>
</ul>

## 解法

### 方法一：数学

我们直接计算列车实际到站的时间，即为 $arrivalTime + delayedTime$，但是由于时间采用 24 小时制，所以我们需要对结果取模，即 $(arrivalTime + delayedTime) \bmod 24$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findDelayedArrivalTime(self, arrivalTime: int, delayedTime: int) -> int:
        return (arrivalTime + delayedTime) % 24
```

```java
class Solution {
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}
```

```cpp
class Solution {
public:
    int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
};
```

```go
func findDelayedArrivalTime(arrivalTime int, delayedTime int) int {
	return (arrivalTime + delayedTime) % 24
}
```

```ts
function findDelayedArrivalTime(arrivalTime: number, delayedTime: number): number {
    return (arrivalTime + delayedTime) % 24;
}
```

```rust
impl Solution {
    pub fn find_delayed_arrival_time(arrival_time: i32, delayed_time: i32) -> i32 {
        (arrival_time + delayed_time) % 24
    }
}
```

<!-- tabs:end -->

<!-- end -->
