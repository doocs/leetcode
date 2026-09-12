---
comments: true
difficulty: 中等
tags:
    - 数组
    - 字符串
    - 回溯
    - 枚举
---

<!-- problem:start -->

# [949. 给定数字能组成的最大时间](https://leetcode.cn/problems/largest-time-for-given-digits)

[English Version](/solution/0900-0999/0949.Largest%20Time%20for%20Given%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。</p>

<p>24 小时格式为 <code>"HH:MM"</code> ，其中 <code>HH</code> 在 <code>00</code> 到 <code>23</code> 之间，<code>MM</code> 在 <code>00</code> 到 <code>59</code> 之间。最小的 24 小时制时间是 <code>00:00</code> ，而最大的是 <code>23:59</code> 。从 00:00 （午夜）开始算起，过得越久，时间越大。</p>

<p>以长度为 5 的字符串，按 <code>"HH:MM"</code> 格式返回答案。如果不能确定有效时间，则返回空字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,4]
<strong>输出：</strong>"23:41"
<strong>解释：</strong>有效的 24 小时制时间是 "12:34"，"12:43"，"13:24"，"13:42"，"14:23"，"14:32"，"21:34"，"21:43"，"23:14" 和 "23:41" 。这些时间中，"23:41" 是最大时间。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [5,5,5,5]
<strong>输出：</strong>""
<strong>解释：</strong>不存在有效的 24 小时制时间，因为 "55:55" 无效。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,0,0,0]
<strong>输出：</strong>"00:00"
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,0,1,0]
<strong>输出：</strong>"10:00"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>arr.length == 4</code></li>
	<li><code>0 <= arr[i] <= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举时分 + 计数验证

<!-- thinking:start -->

> **思考**
>
> 四个数字组成最大合法时刻。合法时分一共 $24\times 60$ 种，从大到小枚举 $h,m$，用计数数组判断四位是否恰为给定数字，第一个命中即为答案。

<!-- thinking:end -->

从大到小枚举合法时分 $h \in [0,23]$、$m \in [0,59]$，用计数数组判断四个数字是否恰好等于给定数组。第一个命中的就是最大合法时间。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestTimeFromDigits(self, arr: List[int]) -> str:
        cnt = [0] * 10
        for v in arr:
            cnt[v] += 1
        for h in range(23, -1, -1):
            for m in range(59, -1, -1):
                t = [0] * 10
                t[h // 10] += 1
                t[h % 10] += 1
                t[m // 10] += 1
                t[m % 10] += 1
                if cnt == t:
                    return f'{h:02}:{m:02}'
        return ''
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：暴力枚举（全排列）

<!-- thinking:start -->

> **思考**
>
> 枚举时分是常数级，也可以直接枚举四个下标的排列构造时分，合法者取最大。排列数 $4!=24$，同样可接受。

<!-- thinking:end -->

枚举 4 个数字的排列，判断构成的时分是否合法，并取最大值。

时间复杂度 $O(4^3)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestTimeFromDigits(self, arr: List[int]) -> str:
        ans = -1
        for i in range(4):
            for j in range(4):
                for k in range(4):
                    if i != j and i != k and j != k:
                        h = arr[i] * 10 + arr[j]
                        m = arr[k] * 10 + arr[6 - i - j - k]
                        if h < 24 and m < 60:
                            ans = max(ans, h * 60 + m)
        return '' if ans < 0 else f'{ans // 60:02}:{ans % 60:02}'
```

#### Java

```java
class Solution {
    public String largestTimeFromDigits(int[] arr) {
        int ans = -1;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i != j && j != k && i != k) {
                        int h = arr[i] * 10 + arr[j];
                        int m = arr[k] * 10 + arr[6 - i - j - k];
                        if (h < 24 && m < 60) {
                            ans = Math.max(ans, h * 60 + m);
                        }
                    }
                }
            }
        }
        return ans < 0 ? "" : String.format("%02d:%02d", ans / 60, ans % 60);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string largestTimeFromDigits(vector<int>& arr) {
        int ans = -1;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i != j && j != k && i != k) {
                        int h = arr[i] * 10 + arr[j];
                        int m = arr[k] * 10 + arr[6 - i - j - k];
                        if (h < 24 && m < 60) {
                            ans = max(ans, h * 60 + m);
                        }
                    }
                }
            }
        }
        if (ans < 0) return "";
        int h = ans / 60, m = ans % 60;
        return to_string(h / 10) + to_string(h % 10) + ":" + to_string(m / 10) + to_string(m % 10);
    }
};
```

#### Go

```go
func largestTimeFromDigits(arr []int) string {
	ans := -1
	for i := 0; i < 4; i++ {
		for j := 0; j < 4; j++ {
			for k := 0; k < 4; k++ {
				if i != j && j != k && i != k {
					h := arr[i]*10 + arr[j]
					m := arr[k]*10 + arr[6-i-j-k]
					if h < 24 && m < 60 {
						ans = max(ans, h*60+m)
					}
				}
			}
		}
	}
	if ans < 0 {
		return ""
	}
	return fmt.Sprintf("%02d:%02d", ans/60, ans%60)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
