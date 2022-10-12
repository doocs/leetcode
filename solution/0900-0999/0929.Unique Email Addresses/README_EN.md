# [929. Unique Email Addresses](https://leetcode.com/problems/unique-email-addresses)

[中文文档](/solution/0900-0999/0929.Unique%20Email%20Addresses/README.md)

## Description

<p>Every <strong>valid email</strong> consists of a <strong>local name</strong> and a <strong>domain name</strong>, separated by the <code>&#39;@&#39;</code> sign. Besides lowercase letters, the email may contain one or more <code>&#39;.&#39;</code> or <code>&#39;+&#39;</code>.</p>

<ul>
	<li>For example, in <code>&quot;alice@leetcode.com&quot;</code>, <code>&quot;alice&quot;</code> is the <strong>local name</strong>, and <code>&quot;leetcode.com&quot;</code> is the <strong>domain name</strong>.</li>
</ul>

<p>If you add periods <code>&#39;.&#39;</code> between some characters in the <strong>local name</strong> part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule <strong>does not apply</strong> to <strong>domain names</strong>.</p>

<ul>
	<li>For example, <code>&quot;alice.z@leetcode.com&quot;</code> and <code>&quot;alicez@leetcode.com&quot;</code> forward to the same email address.</li>
</ul>

<p>If you add a plus <code>&#39;+&#39;</code> in the <strong>local name</strong>, everything after the first plus sign <strong>will be ignored</strong>. This allows certain emails to be filtered. Note that this rule <strong>does not apply</strong> to <strong>domain names</strong>.</p>

<ul>
	<li>For example, <code>&quot;m.y+name@email.com&quot;</code> will be forwarded to <code>&quot;my@email.com&quot;</code>.</li>
</ul>

<p>It is possible to use both of these rules at the same time.</p>

<p>Given an array of strings <code>emails</code> where we send one email to each <code>emails[i]</code>, return <em>the number of different addresses that actually receive mails</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> emails = [&quot;test.email+alex@leetcode.com&quot;,&quot;test.e.mail+bob.cathy@leetcode.com&quot;,&quot;testemail+david@lee.tcode.com&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> &quot;testemail@leetcode.com&quot; and &quot;testemail@lee.tcode.com&quot; actually receive mails.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> emails = [&quot;a@leetcode.com&quot;,&quot;b@leetcode.com&quot;,&quot;c@leetcode.com&quot;]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= emails.length &lt;= 100</code></li>
	<li><code>1 &lt;= emails[i].length &lt;= 100</code></li>
	<li><code>emails[i]</code> consist of lowercase English letters, <code>&#39;+&#39;</code>, <code>&#39;.&#39;</code> and <code>&#39;@&#39;</code>.</li>
	<li>Each <code>emails[i]</code> contains exactly one <code>&#39;@&#39;</code> character.</li>
	<li>All local and domain names are non-empty.</li>
	<li>Local names do not start with a <code>&#39;+&#39;</code> character.</li>
	<li>Domain names end with the <code>&quot;.com&quot;</code> suffix.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
