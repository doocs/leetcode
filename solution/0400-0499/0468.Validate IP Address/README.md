---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0468.Validate%20IP%20Address/README.md
tags:
    - 字符串
---

<!-- problem:start -->

# [468. 验证 IP 地址](https://leetcode.cn/problems/validate-ip-address)

[English Version](/solution/0400-0499/0468.Validate%20IP%20Address/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串&nbsp;<code>queryIP</code>。如果是有效的 IPv4 地址，返回 <code>"IPv4"</code> ；如果是有效的 IPv6 地址，返回 <code>"IPv6"</code> ；如果不是上述类型的 IP 地址，返回 <code>"Neither"</code> 。</p>

<p><strong>有效的IPv4地址</strong> 是 <code>“x1.x2.x3.x4”</code> 形式的IP地址。 其中&nbsp;<code>0 &lt;= x<sub>i</sub>&nbsp;&lt;= 255</code>&nbsp;且&nbsp;<code>x<sub>i</sub></code>&nbsp;<strong>不能包含</strong> 前导零。例如:&nbsp;<code>“192.168.1.1”</code>&nbsp;、 <code>“192.168.1.0”</code> 为有效IPv4地址， <code>“192.168.01.1”</code> 为无效IPv4地址; <code>“192.168.1.00”</code> 、 <code>“192.168@1.1”</code> 为无效IPv4地址。</p>

<p><strong>一个有效的IPv6地址&nbsp;</strong>是一个格式为<code>“x1:x2:x3:x4:x5:x6:x7:x8”</code> 的IP地址，其中:</p>

<ul>
	<li><code>1 &lt;= x<sub>i</sub>.length &lt;= 4</code></li>
	<li><code>x<sub>i</sub></code>&nbsp;是一个 <strong>十六进制字符串</strong> ，可以包含数字、小写英文字母( <code>'a'</code> 到 <code>'f'</code> )和大写英文字母( <code>'A'</code> 到 <code>'F'</code> )。</li>
	<li>在&nbsp;<code>x<sub>i</sub></code>&nbsp;中允许前导零。</li>
</ul>

<p>例如 <code>"2001:0db8:85a3:0000:0000:8a2e:0370:7334"</code> 和 <code>"2001:db8:85a3:0:0:8A2E:0370:7334"</code> 是有效的 IPv6 地址，而 <code>"2001:0db8:85a3::8A2E:037j:7334"</code> 和 <code>"02001:0db8:85a3:0000:0000:8a2e:0370:7334"</code> 是无效的 IPv6 地址。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>queryIP = "172.16.254.1"
<strong>输出：</strong>"IPv4"
<strong>解释：</strong>有效的 IPv4 地址，返回 "IPv4"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
<strong>输出：</strong>"IPv6"
<strong>解释：</strong>有效的 IPv6 地址，返回 "IPv6"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>queryIP = "256.256.256.256"
<strong>输出：</strong>"Neither"
<strong>解释：</strong>既不是 IPv4 地址，又不是 IPv6 地址
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>queryIP</code> 仅由英文字母，数字，字符 <code>'.'</code> 和 <code>':'</code> 组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以定义两个函数 `isIPv4` 和 `isIPv6` 来判断一个字符串是否是有效的 IPv4 地址和 IPv6 地址。

函数 `isIPv4` 的实现如下：

1. 我们首先判断字符串 `s` 是否以 `.` 结尾，如果是的话，说明 `s` 不是一个有效的 IPv4 地址，直接返回 `false`。
1. 然后我们将字符串 `s` 以 `.` 分割成一个字符串数组 `ss`，如果 `ss` 的长度不等于 `4`，说明 `s` 不是一个有效的 IPv4 地址，直接返回 `false`。
1. 对于数组 `ss` 中的每个字符串 `t`，我们判断：
    - 如果 `t` 的长度大于 `1` 且 `t` 的第一个字符是 `0`，说明 `t` 不是一个有效的 IPv4 地址，直接返回 `false`。
    - 如果 `t` 不是一个数字或者 `t` 不在 `0` 到 `255` 的范围内，说明 `t` 不是一个有效的 IPv4 地址，直接返回 `false`。
1. 如果上述条件都不满足，说明 `s` 是一个有效的 IPv4 地址，返回 `true`。

函数 `isIPv6` 的实现如下：

1. 我们首先判断字符串 `s` 是否以 `:` 结尾，如果是的话，说明 `s` 不是一个有效的 IPv6 地址，直接返回 `false`。
1. 然后我们将字符串 `s` 以 `:` 分割成一个字符串数组 `ss`，如果 `ss` 的长度不等于 `8`，说明 `s` 不是一个有效的 IPv6 地址，直接返回 `false`。
1. 对于数组 `ss` 中的每个字符串 `t`，我们判断：
    - 如果 `t` 的长度小于 `1` 或大于 `4`，说明 `t` 不是一个有效的 IPv6 地址，直接返回 `false`。
    - 如果 `t` 中的字符不全是 `0` 到 `9` 和 `a` 到 `f`（不区分大小写），说明 `t` 不是一个有效的 IPv6 地址，直接返回 `false`。
1. 如果上述条件都不满足，说明 `s` 是一个有效的 IPv6 地址，返回 `true`。

最后，我们调用 `isIPv4` 和 `isIPv6` 函数判断 `queryIP` 是不是一个有效的 IPv4 地址或 IPv6 地址，如果都不是，返回 `Neither`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `queryIP` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validIPAddress(self, queryIP: str) -> str:
        def is_ipv4(s: str) -> bool:
            ss = s.split(".")
            if len(ss) != 4:
                return False
            for t in ss:
                if len(t) > 1 and t[0] == "0":
                    return False
                if not t.isdigit() or not 0 <= int(t) <= 255:
                    return False
            return True

        def is_ipv6(s: str) -> bool:
            ss = s.split(":")
            if len(ss) != 8:
                return False
            for t in ss:
                if not 1 <= len(t) <= 4:
                    return False
                if not all(c in "0123456789abcdefABCDEF" for c in t):
                    return False
            return True

        if is_ipv4(queryIP):
            return "IPv4"
        if is_ipv6(queryIP):
            return "IPv6"
        return "Neither"
```

#### Java

```java
class Solution {
    public String validIPAddress(String queryIP) {
        if (isIPv4(queryIP)) {
            return "IPv4";
        }
        if (isIPv6(queryIP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean isIPv4(String s) {
        if (s.endsWith(".")) {
            return false;
        }
        String[] ss = s.split("\\.");
        if (ss.length != 4) {
            return false;
        }
        for (String t : ss) {
            if (t.length() == 0 || t.length() > 1 && t.charAt(0) == '0') {
                return false;
            }
            int x = convert(t);
            if (x < 0 || x > 255) {
                return false;
            }
        }
        return true;
    }

    private boolean isIPv6(String s) {
        if (s.endsWith(":")) {
            return false;
        }
        String[] ss = s.split(":");
        if (ss.length != 8) {
            return false;
        }
        for (String t : ss) {
            if (t.length() < 1 || t.length() > 4) {
                return false;
            }
            for (char c : t.toCharArray()) {
                if (!Character.isDigit(c)
                    && !"0123456789abcdefABCDEF".contains(String.valueOf(c))) {
                    return false;
                }
            }
        }
        return true;
    }

    private int convert(String s) {
        int x = 0;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return -1;
            }
            x = x * 10 + (c - '0');
            if (x > 255) {
                return x;
            }
        }
        return x;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string validIPAddress(string queryIP) {
        if (isIPv4(queryIP)) {
            return "IPv4";
        }
        if (isIPv6(queryIP)) {
            return "IPv6";
        }
        return "Neither";
    }

private:
    bool isIPv4(const string& s) {
        if (s.empty() || s.back() == '.') {
            return false;
        }
        vector<string> ss = split(s, '.');
        if (ss.size() != 4) {
            return false;
        }
        for (const string& t : ss) {
            if (t.empty() || (t.size() > 1 && t[0] == '0')) {
                return false;
            }
            int x = convert(t);
            if (x < 0 || x > 255) {
                return false;
            }
        }
        return true;
    }

    bool isIPv6(const string& s) {
        if (s.empty() || s.back() == ':') {
            return false;
        }
        vector<string> ss = split(s, ':');
        if (ss.size() != 8) {
            return false;
        }
        for (const string& t : ss) {
            if (t.size() < 1 || t.size() > 4) {
                return false;
            }
            for (char c : t) {
                if (!isxdigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    int convert(const string& s) {
        int x = 0;
        for (char c : s) {
            if (!isdigit(c)) {
                return -1;
            }
            x = x * 10 + (c - '0');
            if (x > 255) {
                return x;
            }
        }
        return x;
    }

    vector<string> split(const string& s, char delimiter) {
        vector<string> tokens;
        string token;
        istringstream iss(s);
        while (getline(iss, token, delimiter)) {
            tokens.push_back(token);
        }
        return tokens;
    }
};
```

#### Go

```go
func validIPAddress(queryIP string) string {
	if isIPv4(queryIP) {
		return "IPv4"
	}
	if isIPv6(queryIP) {
		return "IPv6"
	}
	return "Neither"
}

func isIPv4(s string) bool {
	if strings.HasSuffix(s, ".") {
		return false
	}
	ss := strings.Split(s, ".")
	if len(ss) != 4 {
		return false
	}
	for _, t := range ss {
		if len(t) == 0 || (len(t) > 1 && t[0] == '0') {
			return false
		}
		x := convert(t)
		if x < 0 || x > 255 {
			return false
		}
	}
	return true
}

func isIPv6(s string) bool {
	if strings.HasSuffix(s, ":") {
		return false
	}
	ss := strings.Split(s, ":")
	if len(ss) != 8 {
		return false
	}
	for _, t := range ss {
		if len(t) < 1 || len(t) > 4 {
			return false
		}
		for _, c := range t {
			if !unicode.IsDigit(c) && !strings.ContainsRune("0123456789abcdefABCDEF", c) {
				return false
			}
		}
	}
	return true
}

func convert(s string) int {
	x := 0
	for _, c := range s {
		if !unicode.IsDigit(c) {
			return -1
		}
		x = x*10 + int(c-'0')
		if x > 255 {
			return x
		}
	}
	return x
}
```

#### TypeScript

```ts
function validIPAddress(queryIP: string): string {
    if (isIPv4(queryIP)) {
        return 'IPv4';
    }
    if (isIPv6(queryIP)) {
        return 'IPv6';
    }
    return 'Neither';
}

function isIPv4(s: string): boolean {
    if (s.endsWith('.')) {
        return false;
    }
    const ss = s.split('.');
    if (ss.length !== 4) {
        return false;
    }
    for (const t of ss) {
        if (t.length === 0 || (t.length > 1 && t[0] === '0')) {
            return false;
        }
        const x = convert(t);
        if (x < 0 || x > 255) {
            return false;
        }
    }
    return true;
}

function isIPv6(s: string): boolean {
    if (s.endsWith(':')) {
        return false;
    }
    const ss = s.split(':');
    if (ss.length !== 8) {
        return false;
    }
    for (const t of ss) {
        if (t.length < 1 || t.length > 4) {
            return false;
        }
        for (const c of t) {
            if (!isHexDigit(c)) {
                return false;
            }
        }
    }
    return true;
}

function convert(s: string): number {
    let x = 0;
    for (const c of s) {
        if (!isDigit(c)) {
            return -1;
        }
        x = x * 10 + (c.charCodeAt(0) - '0'.charCodeAt(0));
        if (x > 255) {
            return x;
        }
    }
    return x;
}

function isDigit(c: string): boolean {
    return c >= '0' && c <= '9';
}

function isHexDigit(c: string): boolean {
    return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
}
```

#### Rust

```rust
impl Solution {
    pub fn valid_ip_address(query_ip: String) -> String {
        if Self::is_ipv4(&query_ip) {
            return "IPv4".to_string();
        }
        if Self::is_ipv6(&query_ip) {
            return "IPv6".to_string();
        }
        "Neither".to_string()
    }

    fn is_ipv4(s: &str) -> bool {
        if s.ends_with('.') {
            return false;
        }
        let ss: Vec<&str> = s.split('.').collect();
        if ss.len() != 4 {
            return false;
        }
        for t in ss {
            if t.is_empty() || (t.len() > 1 && t.starts_with('0')) {
                return false;
            }
            match Self::convert(t) {
                Some(x) if x <= 255 => {
                    continue;
                }
                _ => {
                    return false;
                }
            }
        }
        true
    }

    fn is_ipv6(s: &str) -> bool {
        if s.ends_with(':') {
            return false;
        }
        let ss: Vec<&str> = s.split(':').collect();
        if ss.len() != 8 {
            return false;
        }
        for t in ss {
            if t.len() < 1 || t.len() > 4 {
                return false;
            }
            if !t.chars().all(|c| c.is_digit(16)) {
                return false;
            }
        }
        true
    }

    fn convert(s: &str) -> Option<i32> {
        let mut x = 0;
        for c in s.chars() {
            if !c.is_digit(10) {
                return None;
            }
            x = x * 10 + (c.to_digit(10).unwrap() as i32);
            if x > 255 {
                return Some(x);
            }
        }
        Some(x)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
