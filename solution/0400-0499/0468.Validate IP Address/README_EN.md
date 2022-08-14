# [468. Validate IP Address](https://leetcode.com/problems/validate-ip-address)

[中文文档](/solution/0400-0499/0468.Validate%20IP%20Address/README.md)

## Description

<p>Given a string <code>queryIP</code>, return <code>&quot;IPv4&quot;</code> if IP is a valid IPv4 address, <code>&quot;IPv6&quot;</code> if IP is a valid IPv6 address or <code>&quot;Neither&quot;</code> if IP is not a correct IP of any type.</p>

<p><strong>A valid IPv4</strong> address is an IP in the form <code>&quot;x<sub>1</sub>.x<sub>2</sub>.x<sub>3</sub>.x<sub>4</sub>&quot;</code> where <code>0 &lt;= x<sub>i</sub> &lt;= 255</code> and <code>x<sub>i</sub></code> <strong>cannot contain</strong> leading zeros. For example, <code>&quot;192.168.1.1&quot;</code> and <code>&quot;192.168.1.0&quot;</code> are valid IPv4 addresses while <code>&quot;192.168.01.1&quot;</code>, <code>&quot;192.168.1.00&quot;</code>, and <code>&quot;192.168@1.1&quot;</code> are invalid IPv4 addresses.</p>

<p><strong>A valid IPv6</strong> address is an IP in the form <code>&quot;x<sub>1</sub>:x<sub>2</sub>:x<sub>3</sub>:x<sub>4</sub>:x<sub>5</sub>:x<sub>6</sub>:x<sub>7</sub>:x<sub>8</sub>&quot;</code> where:</p>

<ul>
	<li><code>1 &lt;= x<sub>i</sub>.length &lt;= 4</code></li>
	<li><code>x<sub>i</sub></code> is a <strong>hexadecimal string</strong> which may contain digits, lowercase English letter (<code>&#39;a&#39;</code> to <code>&#39;f&#39;</code>) and upper-case English letters (<code>&#39;A&#39;</code> to <code>&#39;F&#39;</code>).</li>
	<li>Leading zeros are allowed in <code>x<sub>i</sub></code>.</li>
</ul>

<p>For example, &quot;<code>2001:0db8:85a3:0000:0000:8a2e:0370:7334&quot;</code> and &quot;<code>2001:db8:85a3:0:0:8A2E:0370:7334&quot;</code> are valid IPv6 addresses, while &quot;<code>2001:0db8:85a3::8A2E:037j:7334&quot;</code> and &quot;<code>02001:0db8:85a3:0000:0000:8a2e:0370:7334&quot;</code> are invalid IPv6 addresses.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> queryIP = &quot;172.16.254.1&quot;
<strong>Output:</strong> &quot;IPv4&quot;
<strong>Explanation:</strong> This is a valid IPv4 address, return &quot;IPv4&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> queryIP = &quot;2001:0db8:85a3:0:0:8A2E:0370:7334&quot;
<strong>Output:</strong> &quot;IPv6&quot;
<strong>Explanation:</strong> This is a valid IPv6 address, return &quot;IPv6&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> queryIP = &quot;256.256.256.256&quot;
<strong>Output:</strong> &quot;Neither&quot;
<strong>Explanation:</strong> This is neither a IPv4 address nor a IPv6 address.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>queryIP</code> consists only of English letters, digits and the characters <code>&#39;.&#39;</code> and <code>&#39;:&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

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
