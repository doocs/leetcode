---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2437.Number%20of%20Valid%20Clock%20Times/README.md
rating: 1426
source: 第 89 场双周赛 Q1
tags:
    - 字符串
    - 枚举
---

# [2437. 有效时间的数目](https://leetcode.cn/problems/number-of-valid-clock-times)

[English Version](/solution/2400-2499/2437.Number%20of%20Valid%20Clock%20Times/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为&nbsp;<code>5</code>&nbsp;的字符串&nbsp;<code>time</code>&nbsp;，表示一个电子时钟当前的时间，格式为&nbsp;<code>"hh:mm"</code>&nbsp;。<strong>最早</strong>&nbsp;可能的时间是&nbsp;<code>"00:00"</code>&nbsp;，<strong>最晚</strong>&nbsp;可能的时间是&nbsp;<code>"23:59"</code>&nbsp;。</p>

<p>在字符串&nbsp;<code>time</code>&nbsp;中，被字符&nbsp;<code>?</code>&nbsp;替换掉的数位是 <strong>未知的</strong>&nbsp;，被替换的数字可能是&nbsp;<code>0</code>&nbsp;到&nbsp;<code>9</code>&nbsp;中的任何一个。</p>

<p>请你返回一个整数<em>&nbsp;</em><code>answer</code>&nbsp;，将每一个 <code>?</code>&nbsp;都用<em>&nbsp;</em><code>0</code>&nbsp;到<em>&nbsp;</em><code>9</code>&nbsp;中一个数字替换后，可以得到的有效时间的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>time = "?5:00"
<b>输出：</b>2
<b>解释：</b>我们可以将 ? 替换成 0 或 1 ，得到 "05:00" 或者 "15:00" 。注意我们不能替换成 2 ，因为时间 "25:00" 是无效时间。所以我们有两个选择。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>time = "0?:0?"
<b>输出：</b>100
<b>解释：</b>两个 ? 都可以被 0 到 9 之间的任意数字替换，所以我们总共有 100 种选择。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>time = "??:??"
<b>输出：</b>1440
<b>解释：</b>小时总共有 24 种选择，分钟总共有 60 种选择。所以总共有 24 * 60 = 1440 种选择。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>time</code>&nbsp;是一个长度为 <code>5</code>&nbsp;的有效字符串，格式为&nbsp;<code>"hh:mm"</code>&nbsp;。</li>
	<li><code>"00" &lt;= hh &lt;= "23"</code></li>
	<li><code>"00" &lt;= mm &lt;= "59"</code></li>
	<li>字符串中有的数位是&nbsp;<code>'?'</code>&nbsp;，需要用&nbsp;<code>0</code>&nbsp;到&nbsp;<code>9</code>&nbsp;之间的数字替换。</li>
</ul>

## 解法

### 方法一：枚举

我们可以直接枚举从 $00:00$ 到 $23:59$ 的所有时间，然后判断每个时间是否有效，是则答案加一。

枚举结束后将答案返回即可。

时间复杂度 $O(24 \times 60)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countTime(self, time: str) -> int:
        def check(s: str, t: str) -> bool:
            return all(a == b or b == '?' for a, b in zip(s, t))

        return sum(
            check(f'{h:02d}:{m:02d}', time) for h in range(24) for m in range(60)
        )
```

```java
class Solution {
    public int countTime(String time) {
        int ans = 0;
        for (int h = 0; h < 24; ++h) {
            for (int m = 0; m < 60; ++m) {
                String s = String.format("%02d:%02d", h, m);
                int ok = 1;
                for (int i = 0; i < 5; ++i) {
                    if (s.charAt(i) != time.charAt(i) && time.charAt(i) != '?') {
                        ok = 0;
                        break;
                    }
                }
                ans += ok;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countTime(string time) {
        int ans = 0;
        for (int h = 0; h < 24; ++h) {
            for (int m = 0; m < 60; ++m) {
                char s[20];
                sprintf(s, "%02d:%02d", h, m);
                int ok = 1;
                for (int i = 0; i < 5; ++i) {
                    if (s[i] != time[i] && time[i] != '?') {
                        ok = 0;
                        break;
                    }
                }
                ans += ok;
            }
        }
        return ans;
    }
};
```

```go
func countTime(time string) int {
	ans := 0
	for h := 0; h < 24; h++ {
		for m := 0; m < 60; m++ {
			s := fmt.Sprintf("%02d:%02d", h, m)
			ok := 1
			for i := 0; i < 5; i++ {
				if s[i] != time[i] && time[i] != '?' {
					ok = 0
					break
				}
			}
			ans += ok
		}
	}
	return ans
}
```

```ts
function countTime(time: string): number {
    let ans = 0;
    for (let h = 0; h < 24; ++h) {
        for (let m = 0; m < 60; ++m) {
            const s = `${h}`.padStart(2, '0') + ':' + `${m}`.padStart(2, '0');
            let ok = 1;
            for (let i = 0; i < 5; ++i) {
                if (s[i] !== time[i] && time[i] !== '?') {
                    ok = 0;
                    break;
                }
            }
            ans += ok;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn count_time(time: String) -> i32 {
        let mut ans = 0;

        for i in 0..24 {
            for j in 0..60 {
                let mut ok = true;
                let t = format!("{:02}:{:02}", i, j);

                for (k, ch) in time.chars().enumerate() {
                    if ch != '?' && ch != t.chars().nth(k).unwrap() {
                        ok = false;
                    }
                }

                if ok {
                    ans += 1;
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

### 方法二：枚举优化

我们可以分开枚举小时和分钟，统计有多少个小时和分钟满足条件，然后将二者相乘即可。

时间复杂度 $O(24 + 60)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countTime(self, time: str) -> int:
        def f(s: str, m: int) -> int:
            cnt = 0
            for i in range(m):
                a = s[0] == '?' or (int(s[0]) == i // 10)
                b = s[1] == '?' or (int(s[1]) == i % 10)
                cnt += a and b
            return cnt

        return f(time[:2], 24) * f(time[3:], 60)
```

```java
class Solution {
    public int countTime(String time) {
        return f(time.substring(0, 2), 24) * f(time.substring(3), 60);
    }

    private int f(String s, int m) {
        int cnt = 0;
        for (int i = 0; i < m; ++i) {
            boolean a = s.charAt(0) == '?' || s.charAt(0) - '0' == i / 10;
            boolean b = s.charAt(1) == '?' || s.charAt(1) - '0' == i % 10;
            cnt += a && b ? 1 : 0;
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int countTime(string time) {
        auto f = [](string s, int m) {
            int cnt = 0;
            for (int i = 0; i < m; ++i) {
                bool a = s[0] == '?' || s[0] - '0' == i / 10;
                bool b = s[1] == '?' || s[1] - '0' == i % 10;
                cnt += a && b;
            }
            return cnt;
        };
        return f(time.substr(0, 2), 24) * f(time.substr(3, 2), 60);
    }
};
```

```go
func countTime(time string) int {
	f := func(s string, m int) (cnt int) {
		for i := 0; i < m; i++ {
			a := s[0] == '?' || int(s[0]-'0') == i/10
			b := s[1] == '?' || int(s[1]-'0') == i%10
			if a && b {
				cnt++
			}
		}
		return
	}
	return f(time[:2], 24) * f(time[3:], 60)
}
```

```ts
function countTime(time: string): number {
    const f = (s: string, m: number): number => {
        let cnt = 0;
        for (let i = 0; i < m; ++i) {
            const a = s[0] === '?' || s[0] === Math.floor(i / 10).toString();
            const b = s[1] === '?' || s[1] === (i % 10).toString();
            if (a && b) {
                ++cnt;
            }
        }
        return cnt;
    };
    return f(time.slice(0, 2), 24) * f(time.slice(3), 60);
}
```

```rust
impl Solution {
    pub fn count_time(time: String) -> i32 {
        let f = |s: &str, m: usize| -> i32 {
            let mut cnt = 0;
            let first = s.chars().nth(0).unwrap();
            let second = s.chars().nth(1).unwrap();

            for i in 0..m {
                let a = first == '?' || (first.to_digit(10).unwrap() as usize) == i / 10;

                let b = second == '?' || (second.to_digit(10).unwrap() as usize) == i % 10;

                if a && b {
                    cnt += 1;
                }
            }

            cnt
        };

        f(&time[..2], 24) * f(&time[3..], 60)
    }
}
```

<!-- tabs:end -->

<!-- end -->
