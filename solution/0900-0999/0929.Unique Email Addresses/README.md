---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0929.Unique%20Email%20Addresses/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [929. 独特的电子邮件地址](https://leetcode.cn/problems/unique-email-addresses)

[English Version](/solution/0900-0999/0929.Unique%20Email%20Addresses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>每个 <strong>有效电子邮件地址</strong> 都由一个 <strong>本地名</strong> 和一个 <strong>域名</strong> 组成，以 <code>'@'</code> 符号分隔。除小写字母之外，电子邮件地址还可以含有一个或多个&nbsp;<code>'.'</code> 或 <code>'+'</code> 。</p>

<ul>
	<li>例如，在&nbsp;<code>alice@leetcode.com</code>中，&nbsp;<code>alice</code>&nbsp;是 <strong>本地名</strong> ，而&nbsp;<code>leetcode.com</code>&nbsp;是 <strong>域名</strong> 。</li>
</ul>

<p>如果在电子邮件地址的<strong> 本地名 </strong>部分中的某些字符之间添加句点（<code>'.'</code>），则发往那里的邮件将会转发到本地名中没有点的同一地址。请注意，此规则 <strong>不适用于域名</strong> 。</p>

<ul>
	<li>例如，<code>"alice.z@leetcode.com”</code> 和 <code>“alicez@leetcode.com”</code>&nbsp;会转发到同一电子邮件地址。</li>
</ul>

<p>如果在<strong> 本地名 </strong>中添加加号（<code>'+'</code>），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件。同样，此规则 <strong>不适用于域名</strong> 。</p>

<ul>
	<li>例如 <code>m.y+name@email.com</code> 将转发到 <code>my@email.com</code>。</li>
</ul>

<p>可以同时使用这两个规则。</p>

<p>给你一个字符串数组 <code>emails</code>，我们会向每个 <code>emails[i]</code> 发送一封电子邮件。返回实际收到邮件的不同地址数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
<strong>输出：</strong>2
<strong>解释：</strong>实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
<strong>输出：</strong>3
</pre>

<p><br />
<strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= emails.length &lt;= 100</code></li>
	<li><code>1 &lt;= emails[i].length&nbsp;&lt;= 100</code></li>
	<li><code>emails[i]</code> 由小写英文字母、<code>'+'</code>、<code>'.'</code> 和 <code>'@'</code> 组成</li>
	<li>每个 <code>emails[i]</code> 都包含有且仅有一个 <code>'@'</code> 字符</li>
	<li>所有本地名和域名都不为空</li>
	<li>本地名不会以 <code>'+'</code> 字符作为开头</li>
	<li>域名以&nbsp;<code>".com"</code> 后缀结尾。</li>
	<li>域名在&nbsp;<code>".com"</code> 后缀前至少包含一个字符</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $s$ 来存储所有的电子邮件地址，然后遍历数组 $\textit{emails}$，对于每个电子邮件地址，我们将其分为本地名和域名两部分，然后对本地名进行处理，去掉所有的点号和加号后面的字符，最后将处理后的本地名和域名拼接起来，加入哈希表 $s$ 中。

最后返回哈希表 $s$ 的大小即可。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 为所有电子邮件地址的长度之和。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numUniqueEmails(self, emails: List[str]) -> int:
        s = set()
        for email in emails:
            local, domain = email.split("@")
            t = []
            for c in local:
                if c == ".":
                    continue
                if c == "+":
                    break
                t.append(c)
            s.add("".join(t) + "@" + domain)
        return len(s)
```

#### Java

```java
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> s = new HashSet<>();
        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];
            StringBuilder t = new StringBuilder();
            for (char c : local.toCharArray()) {
                if (c == '.') {
                    continue;
                }
                if (c == '+') {
                    break;
                }
                t.append(c);
            }
            s.add(t.toString() + "@" + domain);
        }
        return s.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numUniqueEmails(vector<string>& emails) {
        unordered_set<string> s;
        for (const string& email : emails) {
            size_t atPos = email.find('@');
            string local = email.substr(0, atPos);
            string domain = email.substr(atPos + 1);
            string t;
            for (char c : local) {
                if (c == '.') {
                    continue;
                }
                if (c == '+') {
                    break;
                }
                t.push_back(c);
            }
            s.insert(t + "@" + domain);
        }
        return s.size();
    }
};
```

#### Go

```go
func numUniqueEmails(emails []string) int {
	s := make(map[string]struct{})
	for _, email := range emails {
		parts := strings.Split(email, "@")
		local := parts[0]
		domain := parts[1]
		var t strings.Builder
		for _, c := range local {
			if c == '.' {
				continue
			}
			if c == '+' {
				break
			}
			t.WriteByte(byte(c))
		}
		s[t.String()+"@"+domain] = struct{}{}
	}
	return len(s)
}
```

#### TypeScript

```ts
function numUniqueEmails(emails: string[]): number {
    const s = new Set<string>();
    for (const email of emails) {
        const [local, domain] = email.split('@');
        let t = '';
        for (const c of local) {
            if (c === '.') {
                continue;
            }
            if (c === '+') {
                break;
            }
            t += c;
        }
        s.add(t + '@' + domain);
    }
    return s.size;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn num_unique_emails(emails: Vec<String>) -> i32 {
        let mut s = HashSet::new();

        for email in emails {
            let parts: Vec<&str> = email.split('@').collect();
            let local = parts[0];
            let domain = parts[1];
            let mut t = String::new();
            for c in local.chars() {
                if c == '.' {
                    continue;
                }
                if c == '+' {
                    break;
                }
                t.push(c);
            }
            s.insert(format!("{}@{}", t, domain));
        }

        s.len() as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {string[]} emails
 * @return {number}
 */
var numUniqueEmails = function (emails) {
    const s = new Set();
    for (const email of emails) {
        const [local, domain] = email.split('@');
        let t = '';
        for (const c of local) {
            if (c === '.') {
                continue;
            }
            if (c === '+') {
                break;
            }
            t += c;
        }
        s.add(t + '@' + domain);
    }
    return s.size;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
