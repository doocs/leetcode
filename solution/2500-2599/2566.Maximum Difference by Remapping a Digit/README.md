# [2566. 替换一个数字后的最大差值](https://leetcode.cn/problems/maximum-difference-by-remapping-a-digit)

[English Version](/solution/2500-2599/2566.Maximum%20Difference%20by%20Remapping%20a%20Digit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>num</code>&nbsp;。你知道 Danny Mittal 会偷偷将 <code>0</code>&nbsp;到 <code>9</code>&nbsp;中的一个数字 <strong>替换</strong> 成另一个数字。</p>

<p>请你返回将 <code>num</code>&nbsp;中&nbsp;<strong>恰好一个</strong>&nbsp;数字进行替换后，得到的最大值和最小值的差为多少。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>当 Danny 将一个数字 <code>d1</code> 替换成另一个数字 <code>d2</code> 时，Danny 需要将&nbsp;<code>nums</code>&nbsp;中所有 <code>d1</code>&nbsp;都替换成&nbsp;<code>d2</code>&nbsp;。</li>
	<li>Danny 可以将一个数字替换成它自己，也就是说&nbsp;<code>num</code>&nbsp;可以不变。</li>
	<li>Danny 可以将数字分别替换成两个不同的数字分别得到最大值和最小值。</li>
	<li>替换后得到的数字可以包含前导 0 。</li>
	<li>Danny Mittal 获得周赛 326 前 10 名，让我们恭喜他。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>num = 11891
<b>输出：</b>99009
<b>解释：</b>
为了得到最大值，我们将数字 1 替换成数字 9 ，得到 99899 。
为了得到最小值，我们将数字 1 替换成数字 0 ，得到 890 。
两个数字的差值为 99009 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>num = 90
<b>输出：</b>99
<strong>解释：</strong>
可以得到的最大值是 99（将 0 替换成 9），最小值是 0（将 9 替换成 0）。
所以我们得到 99 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们先将数字转为字符串 $s$。

要得到最小值，我们只需要将找到字符串 $s$ 的第一个数字 $s[0]$，然后把字符串中所有的 $s[0]$ 替换成 $0$ 即可。

要得到最大值，我们需要找到字符串 $s$ 中第一个不是 $9$ 的数字 $s[i]$，然后把字符串中所有的 $s[i]$ 替换成 $9$ 即可。

最后返回最大值和最小值的差即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数字 $num$ 的大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minMaxDifference(self, num: int) -> int:
        s = str(num)
        mi = int(s.replace(s[0], '0'))
        for c in s:
            if c != '9':
                return int(s.replace(c, '9')) - mi
        return num - mi
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minMaxDifference(int num) {
        String s = String.valueOf(num);
        int mi = Integer.parseInt(s.replace(s.charAt(0), '0'));
        for (char c : s.toCharArray()) {
            if (c != '9') {
                return Integer.parseInt(s.replace(c, '9')) - mi;
            }
        }
        return num - mi;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMaxDifference(int num) {
        string s = to_string(num);
        string t = s;
        char first = s[0];
        for (char& c : s) {
            if (c == first) {
                c = '0';
            }
        }
        int mi = stoi(s);
        for (int i = 0; i < t.size(); ++i) {
            if (t[i] != '9') {
                char second = t[i];
                for (int j = i; j < t.size(); ++j) {
                    if (t[j] == second) {
                        t[j] = '9';
                    }
                }
                return stoi(t) - mi;
            }
        }
        return num - mi;
    }
};
```

### **Go**

```go
func minMaxDifference(num int) int {
	s := []byte(strconv.Itoa(num))
	first := s[0]
	for i := range s {
		if s[i] == first {
			s[i] = '0'
		}
	}
	mi, _ := strconv.Atoi(string(s))
	t := []byte(strconv.Itoa(num))
	for i := range t {
		if t[i] != '9' {
			second := t[i]
			for j := i; j < len(t); j++ {
				if t[j] == second {
					t[j] = '9'
				}
			}
			mx, _ := strconv.Atoi(string(t))
			return mx - mi
		}
	}
	return num - mi
}
```

### **TypeScript**

```ts
function minMaxDifference(num: number): number {
    const s = num + '';
    const min = Number(s.replace(new RegExp(s[0], 'g'), '0'));
    for (const c of s) {
        if (c !== '9') {
            return Number(s.replace(new RegExp(c, 'g'), '9')) - min;
        }
    }
    return num - min;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_max_difference(num: i32) -> i32 {
        let s = num.to_string();
        let min = s
            .replace(char::from(s.as_bytes()[0]), "0")
            .parse::<i32>()
            .unwrap();
        for &c in s.as_bytes() {
            if c != b'9' {
                return s.replace(c, "9").parse().unwrap() - min;
            }
        }
        num - min
    }
}
```

### **C**

```c
int getLen(int num) {
    int res = 0;
    while (num) {
        num /= 10;
        res++;
    }
    return res;
}

int minMaxDifference(int num) {
    int n = getLen(num);
    int *nums = malloc(sizeof(int) * n);
    int t = num;
    for (int i = n - 1; i >= 0; i--) {
        nums[i] = t % 10;
        t /= 10;
    }
    int min = 0;
    for (int i = 0; i < n; i++) {
        min *= 10;
        if (nums[i] != nums[0]) {
            min += nums[i];
        }
    }
    int max = 0;
    int target = 10;
    for (int i = 0; i < n; i++) {
        max *= 10;
        if (target == 10 && nums[i] != 9) {
            target = nums[i];
        }
        max += nums[i] == target ? 9 : nums[i];
    }
    free(nums);
    return max - min;
}
```

### **...**

```

```

<!-- tabs:end -->
