# [2283. 判断一个数的数字计数是否等于数位的值](https://leetcode.cn/problems/check-if-number-has-equal-digit-count-and-digit-value)

[English Version](/solution/2200-2299/2283.Check%20if%20Number%20Has%20Equal%20Digit%20Count%20and%20Digit%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的字符串&nbsp;<code>num</code>&nbsp;，它只包含数字。</p>

<p>如果对于 <strong>每个</strong><em>&nbsp;</em><code>0 &lt;= i &lt; n</code>&nbsp;的下标&nbsp;<code>i</code>&nbsp;，都满足数位<em>&nbsp;</em><code>i</code>&nbsp;在 <code>num</code>&nbsp;中出现了&nbsp;<code>num[i]</code>次，那么请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>num = "1210"
<b>输出：</b>true
<strong>解释：</strong>
num[0] = '1' 。数字 0 在 num 中出现了一次。
num[1] = '2' 。数字 1 在 num 中出现了两次。
num[2] = '1' 。数字 2 在 num 中出现了一次。
num[3] = '0' 。数字 3 在 num 中出现了零次。
"1210" 满足题目要求条件，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>num = "030"
<b>输出：</b>false
<strong>解释：</strong>
num[0] = '0' 。数字 0 应该出现 0 次，但是在 num 中出现了两次。
num[1] = '3' 。数字 1 应该出现 3 次，但是在 num 中出现了零次。
num[2] = '0' 。数字 2 在 num 中出现了 0 次。
下标 0 和 1 都违反了题目要求，所以返回 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == num.length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>num</code>&nbsp;只包含数字。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 枚举**

统计字符串中每个数字出现的次数，然后枚举每个数字，判断其出现的次数是否与其值相等，若都相等则返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串 `num` 的长度，而 $C$ 是数字的个数。本题中 $C=10$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def digitCount(self, num: str) -> bool:
        cnt = Counter(num)
        return all(cnt[str(i)] == int(v) for i, v in enumerate(num))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean digitCount(String num) {
        int[] cnt = new int[10];
        int n = num.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[num.charAt(i) - '0'];
        }
        for (int i = 0; i < n; ++i) {
            if (cnt[i] != num.charAt(i) - '0') {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool digitCount(string num) {
        int cnt[10]{};
        for (char& c : num) {
            ++cnt[c - '0'];
        }
        for (int i = 0; i < num.size(); ++i) {
            if (cnt[i] != num[i] - '0') {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func digitCount(num string) bool {
	cnt := [10]int{}
	for _, c := range num {
		cnt[c-'0']++
	}
	for i, v := range num {
		if cnt[i] != int(v-'0') {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts
function digitCount(num: string): boolean {
    const n = num.length;
    const count = new Array(10).fill(0);
    for (let i = 0; i < n; i++) {
        count[i] = Number(num[i]);
    }
    for (const c of num) {
        count[c]--;
    }
    return count.every(v => v === 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn digit_count(num: String) -> bool {
        let s = num.as_bytes();
        let n = num.len();
        let mut count = [0; 10];
        for i in 0..n {
            count[i] = s[i] - b'0';
        }
        for c in s {
            count[(c - b'0') as usize] -= 1;
        }
        count.iter().all(|v| *v == 0)
    }
}
```

### **C**

```c
bool digitCount(char *num) {
    int count[10] = {0};
    for (int i = 0; num[i]; i++) {
        count[i] = num[i] - '0';
    }
    for (int i = 0; num[i]; i++) {
        count[num[i] - '0']--;
    }
    for (int i = 0; i < 10; i++) {
        if (count[i] != 0) {
            return false;
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
