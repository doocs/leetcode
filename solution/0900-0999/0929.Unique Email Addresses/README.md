# [929. 独特的电子邮件地址](https://leetcode.cn/problems/unique-email-addresses)

[English Version](/solution/0900-0999/0929.Unique%20Email%20Addresses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

利用哈希表存放转换后的电子邮件，最后返回哈希表的 size 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numUniqueEmails(self, emails: List[str]) -> int:
        s = set()
        for email in emails:
            local, domain = email.split('@')
            local = local.replace('.', '')
            if (i := local.find('+')) != -1:
                local = local[:i]
            s.add(local + '@' + domain)
        return len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> s = new HashSet<>();
        for (String email : emails) {
            String[] t = email.split("@");
            String local = t[0].replace(".", "");
            String domain = t[1];
            int i = local.indexOf('+');
            if (i != -1) {
                local = local.substring(0, i);
            }
            s.add(local + "@" + domain);
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numUniqueEmails(vector<string>& emails) {
        unordered_set<string> s;
        for (auto& email : emails) {
            int i = email.find('@');
            string local = email.substr(0, i);
            string domain = email.substr(i + 1);
            i = local.find('+', 0);
            if (~i) local = local.substr(0, i);
            while (~(i = local.find('.', 0)))
                local.erase(local.begin() + i);
            s.insert(local + "@" + domain);
        }
        return s.size();
    }
};
```

### **Go**

```go
func numUniqueEmails(emails []string) int {
	s := map[string]bool{}
	for _, email := range emails {
		i := strings.IndexByte(email, '@')
		local := strings.SplitN(email[:i], "+", 2)[0]
		local = strings.ReplaceAll(local, ".", "")
		domain := email[i:]
		s[local+domain] = true
	}
	return len(s)
}
```

### **JavaScript**

```js
const numUniqueEmails2 = function (emails) {
    const emailFilter = function (str) {
        let index = str.search(/@/);
        let s = str.substring(0, index);
        let s2 = str.substring(index + 1, str.length);
        let res = '';
        for (let i = 0; i < s.length; i++) {
            if (s[i] === '+') break;
            if (s[i] === '.') continue;
            res = res + s[i];
        }
        return res + s2;
    };

    let arr = [];
    for (let i = 0; i < emails.length; i++) {
        let t = emailFilter(emails[i]);
        if (arr.indexOf(t) === -1) {
            arr.push(t);
        }
    }
    return arr.length;
};

const numUniqueEmails = function (emails) {
    let arr = emails.map(str => {
        let index = str.search(/@/);
        let s = str.substring(0, index);
        let s2 = str.substring(index + 1, str.length);
        let res = '';
        for (let i = 0; i < s.length; i++) {
            if (s[i] === '+') break;
            if (s[i] === '.') continue;
            res = res + s[i];
        }
        return res + s2;
    });
    let set = new Set(arr);
    return set.size;
};
```

### **TypeScript**

```ts
function numUniqueEmails(emails: string[]): number {
    return new Set(
        emails
            .map(email => email.split('@'))
            .map(([start, end]) => start.replace(/\+.*|\./g, '') + '@' + end),
    ).size;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn num_unique_emails(emails: Vec<String>) -> i32 {
        let mut set = HashSet::new();
        for email in emails.iter() {
            let res: Vec<&str> = email.split('@').collect();
            let mut s = String::new();
            for &c in res[0].as_bytes().iter() {
                if c == b'.' {
                    continue;
                }
                if c == b'+' {
                    break;
                }
                s.push(c as char)
            }
            s.push('@');
            s.push_str(res[1]);
            set.insert(s);
        }
        set.len() as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
