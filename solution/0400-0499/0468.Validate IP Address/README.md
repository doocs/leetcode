# [468. 验证 IP 地址](https://leetcode.cn/problems/validate-ip-address)

[English Version](/solution/0400-0499/0468.Validate%20IP%20Address/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts
function validIPAddress(queryIP: string): string {
    const isIPv4 = () => {
        const ss = queryIP.split('.');
        if (ss.length !== 4) {
            return false;
        }
        for (const s of ss) {
            const num = Number(s);
            if (num < 0 || num > 255 || num + '' !== s) {
                return false;
            }
        }
        return true;
    };
    const isIPv6 = () => {
        const ss = queryIP.split(':');
        if (ss.length !== 8) {
            return false;
        }
        for (const s of ss) {
            if (s.length === 0 || s.length > 4) {
                return false;
            }
            for (const c of s) {
                if (
                    (c >= '0' && c <= '9') ||
                    (c >= 'a' && c <= 'f') ||
                    (c >= 'A' && c <= 'F')
                ) {
                    continue;
                }
                return false;
            }
        }
        return true;
    };
    if (isIPv4()) {
        return 'IPv4';
    }
    if (isIPv6()) {
        return 'IPv6';
    }
    return 'Neither';
}
```

### **Rust**

```rust
impl Solution {
    fn is_IPv4(s: &String) -> bool {
        let ss = s.split('.').collect::<Vec<&str>>();
        if ss.len() != 4 {
            return false;
        }
        for s in ss {
            match s.parse::<i32>() {
                Err(_) => return false,
                Ok(num) => {
                    if num < 0 || num > 255 || num.to_string() != s.to_string() {
                        return false;
                    }
                }
            }
        }
        true
    }


    fn is_IPv6(s: &String) -> bool {
        let ss = s.split(':').collect::<Vec<&str>>();
        if ss.len() != 8 {
            return false;
        }
        for s in ss {
            if s.len() == 0 || s.len() > 4 {
                return false;
            }
            for &c in s.as_bytes() {
                if c >= b'0' && c <= b'9' || c >= b'a' && c <= b'f' || c >= b'A' && c <= b'F' {
                    continue;
                }
                return false;
            }
        }
        true
    }


    pub fn valid_ip_address(query_ip: String) -> String {
        if Self::is_IPv4(&query_ip) {
            return String::from("IPv4");
        }
        if Self::is_IPv6(&query_ip) {
            return String::from("IPv6");
        }
        String::from("Neither")
    }
}
```

### **...**

```

```

<!-- tabs:end -->
