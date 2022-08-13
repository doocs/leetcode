# [面试题 46. 把数字翻译成字符串](https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 &ldquo;a&rdquo; ，1 翻译成 &ldquo;b&rdquo;，&hellip;&hellip;，11 翻译成 &ldquo;l&rdquo;，&hellip;&hellip;，25 翻译成 &ldquo;z&rdquo;。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 12258
<strong>输出:</strong> <code>5
</code><strong>解释:</strong> 12258有5种不同的翻译，分别是&quot;bccfi&quot;, &quot;bwfi&quot;, &quot;bczi&quot;, &quot;mcfi&quot;和&quot;mzi&quot;</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt; 2<sup>31</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

递归求解。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def translateNum(self, num: int) -> int:
        def cal(s):
            if len(s) < 2:
                return 1
            t = int(s[:2])
            return cal(s[1:]) if t < 10 or t > 25 else cal(s[1:]) + cal(s[2:])

        return cal(str(num))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int translateNum(int num) {
        return cal(String.valueOf(num));
    }

    private int cal(String s) {
        int n = s.length();
        if (n < 2) {
            return 1;
        }
        int t = Integer.parseInt(s.substring(0, 2));
        return t < 10 || t > 25 ? cal(s.substring(1)) : cal(s.substring(1)) + cal(s.substring(2));
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} num
 * @return {number}
 */
var translateNum = function (num) {
    let res = 0;
    num = num.toString();
    function dfs(i) {
        if (i >= num.length) {
            res++;
            return;
        }
        dfs(i + 1);
        let tmp = +(num[i] + num[i + 1]);
        if (num[i] !== '0' && tmp >= 0 && tmp < 26) {
            dfs(i + 2);
        }
    }
    dfs(0);
    return res;
};
```

### **C++**

动态规划解法，定义 `dp[i]` 表示前 `i` 个数字有多少种不同的翻译方法。

注释部分是常规的一维 dp ，因为 `dp[i]` 只依赖 `dp[i - 1]` 和 `dp[i - 2]` ，所以可以进一步压缩空间。

```cpp
class Solution {
public:
    int translateNum(int num) {
        // string s = to_string(num);
        // int n = s.size();
        // vector<int> dp(n + 1);
        // dp[0] = dp[1] = 1;
        // for (int i = 2; i <= n; ++i) {
        //     dp[i] = dp[i - 1];
        //     if (s[i - 2] == '1' || s[i - 2] == '2' && s[i - 1] < '6') {
        //         dp[i] += dp[i - 2];
        //     }
        // }
        // return dp[n];
        string s = to_string(num);
        int n = s.size();
        int dp_0 = 1, dp_1 = 1, dp_2 = 1;
        for (int i = 2; i <= n; ++i) {
            dp_2 = dp_1;
            if (s[i - 2] == '1' || s[i - 2] == '2' && s[i - 1] < '6') {
                dp_2 += dp_0;
            }
            dp_0 = dp_1;
            dp_1 = dp_2;
        }
        return dp_2;
    }
};
```

### **TypeScript**

```ts
function translateNum(num: number): number {
    let a = 1;
    let b = 1;
    const str = num + '';
    for (let i = 1; i < str.length; i++) {
        const val = Number(str[i - 1] + str[i]);
        if (val >= 10 && val < 26) {
            [a, b] = [b, a + b];
        } else {
            a = b;
        }
    }
    return b;
}
```

```ts
function translateNum(num: number): number {
    const s = num + '';
    const n = s.length;
    let res = 1;
    const dfs = (i: number) => {
        if (i >= n) {
            return;
        }
        const val = Number(s[i - 1] + s[i]);
        if (val >= 10 && val <= 25) {
            res++;
            dfs(i + 2);
        }
        dfs(i + 1);
    };
    dfs(1);
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn translate_num(num: i32) -> i32 {
        let mut a = 1;
        let mut b = 1;
        let str = num.to_string();
        for i in 0..str.len() - 1 {
            let c = a + b;
            a = b;
            let num = str[i..i + 2].parse::<i32>().unwrap();
            if num >= 10 && num < 26 {
                b = c;
            }
        }
        b
    }
}
```

```rust
impl Solution {
    fn dfs(s: &String, i: usize, res: &mut i32) {
        if i >= s.len() {
            return;
        }
        let val = s[i - 1..=i].parse::<i32>().unwrap();
        if val >= 10 && val <= 25 {
            *res += 1;
            Self::dfs(s, i + 2, res);
        }
        Self::dfs(s, i + 1, res);
    }

    pub fn translate_num(num: i32) -> i32 {
        let s = num.to_string();
        let mut res = 1;
        Self::dfs(&s, 1, &mut res);
        res
    }
}
```

### **C#**

```cs
public class Solution {
    public int TranslateNum(int num) {
        return TranslateString(num.ToString());
    }

    private int TranslateString(string s) {
        if (s.Length < 2) {
            return 1;
        }
        int t = int.Parse(s[..2]);
        return t < 10 || t > 25 ? TranslateString(s[1..]) : TranslateString(s[1..]) + TranslateString(s[2..]);
    }
}

```

### **...**

```

```

<!-- tabs:end -->
