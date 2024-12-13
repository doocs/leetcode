---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0929.Unique%20Email%20Addresses/README_EN.md
tags:
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [929. Unique Email Addresses](https://leetcode.com/problems/unique-email-addresses)

[中文文档](/solution/0900-0999/0929.Unique%20Email%20Addresses/README.md)

## Description

<!-- description:start -->

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
	<li>Domain names must contain at least one character before <code>&quot;.com&quot;</code> suffix.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $s$ to store all unique email addresses. Then, we traverse the array $\textit{emails}$. For each email address, we split it into the local part and the domain part. We process the local part by removing all dots and ignoring characters after a plus sign. Finally, we concatenate the processed local part with the domain part and add it to the hash table $s$.

In the end, we return the size of the hash table $s$.

The time complexity is $O(L)$, and the space complexity is $O(L)$, where $L$ is the total length of all email addresses.

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
