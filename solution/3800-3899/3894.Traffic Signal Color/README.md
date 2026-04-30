---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3894.Traffic%20Signal%20Color/README.md
rating: 1222
source: 第 180 场双周赛 Q1
---

<!-- problem:start -->

# [3894. 交通信号灯的颜色](https://leetcode.cn/problems/traffic-signal-color)

[English Version](/solution/3800-3899/3894.Traffic%20Signal%20Color/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>timer</code>，表示交通信号灯上的剩余时间（以秒为单位）。</p>

<p>信号灯遵循以下规则：</p>

<ul>
	<li>如果 <code>timer == 0</code>，信号灯为 <code>"Green"</code></li>
	<li>如果 <code>timer == 30</code>，信号灯为 <code>"Orange"</code></li>
	<li>如果 <code>30 &lt; timer &lt;= 90</code>，信号灯为 <code>"Red"</code></li>
</ul>

<p>返回信号灯的当前状态。如果均不满足上述条件，返回 <code>"Invalid"</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">timer = 60</span></p>

<p><strong>输出：</strong> <span class="example-io">"Red"</span></p>

<p><strong>解释：</strong></p>

<p>因为 <code>timer = 60</code>，且 <code>30 &lt; timer &lt;= 90</code>，所以答案是 <code>"Red"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">timer = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">"Invalid"</span></p>

<p><strong>解释：</strong></p>

<p>因为 <code>timer = 5</code>，不满足任何给定的条件，所以答案是 <code>"Invalid"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= timer &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们根据题目描述的条件进行判断，返回对应的字符串即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trafficSignal(self, timer: int) -> str:
        if timer == 0:
            return "Green"
        if timer == 30:
            return "Orange"
        if 30 < timer <= 90:
            return "Red"
        return "Invalid"
```

#### Java

```java
class Solution {
    public String trafficSignal(int timer) {
        if (timer == 0) {
            return "Green";
        }
        if (timer == 30) {
            return "Orange";
        }
        if (timer > 30 && timer <= 90) {
            return "Red";
        }
        return "Invalid";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string trafficSignal(int timer) {
        if (timer == 0) {
            return "Green";
        }
        if (timer == 30) {
            return "Orange";
        }
        if (timer > 30 && timer <= 90) {
            return "Red";
        }
        return "Invalid";
    }
};
```

#### Go

```go
func trafficSignal(timer int) string {
	switch {
	case timer == 0:
		return "Green"
	case timer == 30:
		return "Orange"
	case timer > 30 && timer <= 90:
		return "Red"
	default:
		return "Invalid"
	}
}
```

#### TypeScript

```ts
function trafficSignal(timer: number): string {
    if (timer === 0) {
        return 'Green';
    }
    if (timer === 30) {
        return 'Orange';
    }
    if (timer > 30 && timer <= 90) {
        return 'Red';
    }
    return 'Invalid';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
