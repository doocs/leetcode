# [3114. 替换字符可以得到的最晚时间](https://leetcode.cn/problems/latest-time-you-can-obtain-after-replacing-characters)

[English Version](/solution/3100-3199/3114.Latest%20Time%20You%20Can%20Obtain%20After%20Replacing%20Characters/README_EN.md)

<!-- tags:字符串,枚举 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>，表示一个 12 小时制的时间格式，其中一些数字（可能没有）被 <code>"?"</code> 替换。</p>

<p>12 小时制时间格式为 <code>"HH:MM"</code> ，其中 <code>HH</code> 的取值范围为 <code>00</code> 至 <code>11</code>，<code>MM</code> 的取值范围为 <code>00</code> 至 <code>59</code>。最早的时间为 <code>00:00</code>，最晚的时间为 <code>11:59</code>。</p>

<p>你需要将 <code>s</code> 中的<strong> 所有</strong> <code>"?"</code> 字符替换为数字，使得结果字符串代表的时间是一个<strong> 有效 </strong>的 12 小时制时间，并且是可能的 <strong>最晚 </strong>时间。</p>

<p>返回结果字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "1?:?4"</span></p>

<p><strong>输出：</strong> <span class="example-io">"11:54"</span></p>

<p><strong>解释：</strong> 通过替换 <code>"?"</code> 字符，可以得到的最晚12小时制时间是 <code>"11:54"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "0?:5?"</span></p>

<p><strong>输出：</strong> <span class="example-io">"09:59"</span></p>

<p><strong>解释：</strong> 通过替换 <code>"?"</code> 字符，可以得到的最晚12小时制时间是 <code>"09:59"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s.length == 5</code></li>
	<li><code>s[2]</code> 是字符 <code>":"</code></li>
	<li>除 <code>s[2]</code> 外，其他字符都是数字或 <code>"?"</code></li>
	<li>输入保证在替换 <code>"?"</code> 字符后至少存在一个介于 <code>"00:00"</code> 和 <code>"11:59"</code> 之间的时间。</li>
</ul>

## 解法

### 方法一：枚举

我们可以从大到小枚举所有的时间，其中小时 $h$ 从 $11$ 到 $0$，分钟 $m$ 从 $59$ 到 $0$。对于每一个时间 $t$，我们检查是否满足 $t$ 的每一位字符都与 $s$ 的对应位置字符相等（如果 $s$ 的对应位置字符不是 "?" 的话）。如果满足，那么我们就找到了答案，返回 $t$。

时间复杂度 $O(h \times m)$，其中 $h = 12$, $m = 60$。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findLatestTime(self, s: str) -> str:
        for h in range(11, -1, -1):
            for m in range(59, -1, -1):
                t = f"{h:02d}:{m:02d}"
                if all(a == b for a, b in zip(s, t) if a != "?"):
                    return t
```

```java
class Solution {
    public String findLatestTime(String s) {
        for (int h = 11;; h--) {
            for (int m = 59; m >= 0; m--) {
                String t = String.format("%02d:%02d", h, m);
                boolean ok = true;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) != '?' && s.charAt(i) != t.charAt(i)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    return t;
                }
            }
        }
    }
}
```

```cpp
class Solution {
public:
    string findLatestTime(string s) {
        for (int h = 11;; h--) {
            for (int m = 59; m >= 0; m--) {
                char t[6];
                sprintf(t, "%02d:%02d", h, m);
                bool ok = true;
                for (int i = 0; i < s.length(); i++) {
                    if (s[i] != '?' && s[i] != t[i]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    return t;
                }
            }
        }
    }
};
```

```go
func findLatestTime(s string) string {
	for h := 11; ; h-- {
		for m := 59; m >= 0; m-- {
			t := fmt.Sprintf("%02d:%02d", h, m)
			ok := true
			for i := 0; i < len(s); i++ {
				if s[i] != '?' && s[i] != t[i] {
					ok = false
					break
				}
			}
			if ok {
				return t
			}
		}
	}
}
```

```ts
function findLatestTime(s: string): string {
    for (let h = 11; ; h--) {
        for (let m = 59; m >= 0; m--) {
            const t: string = `${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}`;
            let ok: boolean = true;
            for (let i = 0; i < s.length; i++) {
                if (s[i] !== '?' && s[i] !== t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return t;
            }
        }
    }
}
```

<!-- tabs:end -->

### 方法二：逐个判断

我们可以逐个判断 $s$ 的每一位字符，如果是 "?" 的话，我们就根据前后的字符来确定这一位字符的值。具体地，我们有以下规则：

-   如果 $s[0]$ 是 "?"，那么 $s[0]$ 的值应该是 "1" 或 "0"，具体取决于 $s[1]$ 的值。如果 $s[1]$ 是 "?" 或者 $s[1]$ 小于 "2"，那么 $s[0]$ 的值应该是 "1"，否则 $s[0]$ 的值应该是 "0"。
-   如果 $s[1]$ 是 "?"，那么 $s[1]$ 的值应该是 "1" 或 "9"，具体取决于 $s[0]$ 的值。如果 $s[0]$ 是 "1"，那么 $s[1]$ 的值应该是 "1"，否则 $s[1]$ 的值应该是 "9"。
-   如果 $s[3]$ 是 "?"，那么 $s[3]$ 的值应该是 "5"。
-   如果 $s[4]$ 是 "?"，那么 $s[4]$ 的值应该是 "9"。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findLatestTime(self, s: str) -> str:
        s = list(s)
        if s[0] == "?":
            s[0] = "1" if s[1] == "?" or s[1] < "2" else "0"
        if s[1] == "?":
            s[1] = "1" if s[0] == "1" else "9"
        if s[3] == "?":
            s[3] = "5"
        if s[4] == "?":
            s[4] = "9"
        return "".join(s)
```

```java
class Solution {
    public String findLatestTime(String s) {
        char[] cs = s.toCharArray();
        if (cs[0] == '?') {
            cs[0] = cs[1] == '?' || cs[1] < '2' ? '1' : '0';
        }
        if (cs[1] == '?') {
            cs[1] = cs[0] == '1' ? '1' : '9';
        }
        if (cs[3] == '?') {
            cs[3] = '5';
        }
        if (cs[4] == '?') {
            cs[4] = '9';
        }
        return new String(cs);
    }
}
```

```cpp
class Solution {
public:
    string findLatestTime(string s) {
        if (s[0] == '?') {
            s[0] = s[1] == '?' || s[1] < '2' ? '1' : '0';
        }
        if (s[1] == '?') {
            s[1] = s[0] == '1' ? '1' : '9';
        }
        if (s[3] == '?') {
            s[3] = '5';
        }
        if (s[4] == '?') {
            s[4] = '9';
        }
        return s;
    }
};
```

```go
func findLatestTime(s string) string {
	cs := []byte(s)
	if cs[0] == '?' {
		if cs[1] == '?' || cs[1] < '2' {
			cs[0] = '1'
		} else {
			cs[0] = '0'
		}
	}
	if cs[1] == '?' {
		if cs[0] == '1' {
			cs[1] = '1'
		} else {
			cs[1] = '9'
		}
	}
	if cs[3] == '?' {
		cs[3] = '5'
	}
	if cs[4] == '?' {
		cs[4] = '9'
	}
	return string(cs)
}
```

```ts
function findLatestTime(s: string): string {
    const cs = s.split('');
    if (cs[0] === '?') {
        cs[0] = cs[1] === '?' || cs[1] < '2' ? '1' : '0';
    }
    if (cs[1] === '?') {
        cs[1] = cs[0] === '1' ? '1' : '9';
    }
    if (cs[3] === '?') {
        cs[3] = '5';
    }
    if (cs[4] === '?') {
        cs[4] = '9';
    }
    return cs.join('');
}
```

<!-- tabs:end -->

<!-- end -->
