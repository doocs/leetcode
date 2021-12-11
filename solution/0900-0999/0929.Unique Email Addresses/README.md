# [929. 独特的电子邮件地址](https://leetcode-cn.com/problems/unique-email-addresses)

[English Version](/solution/0900-0999/0929.Unique%20Email%20Addresses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>每封电子邮件都由一个本地名称和一个域名组成，以 @ 符号分隔。</p>

<p>例如，在&nbsp;<code>alice@leetcode.com</code>中，&nbsp;<code>alice</code>&nbsp;是本地名称，而&nbsp;<code>leetcode.com</code>&nbsp;是域名。</p>

<p>除了小写字母，这些电子邮件还可能包含 <code>&#39;.&#39;</code> 或 <code>&#39;+&#39;</code>。</p>

<p>如果在电子邮件地址的<strong>本地名称</strong>部分中的某些字符之间添加句点（<code>&#39;.&#39;</code>），则发往那里的邮件将会转发到本地名称中没有点的同一地址。例如，<code>&quot;alice.z@leetcode.com&rdquo;</code> 和 <code>&ldquo;alicez@leetcode.com&rdquo;</code>&nbsp;会转发到同一电子邮件地址。 （请注意，此规则不适用于域名。）</p>

<p>如果在<strong>本地名称</strong>中添加加号（<code>&#39;+&#39;</code>），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件，例如 <code>m.y+name@email.com</code> 将转发到 <code>my@email.com</code>。 （同样，此规则不适用于域名。）</p>

<p>可以同时使用这两个规则。</p>

<p>给定电子邮件列表 <code>emails</code>，我们会向列表中的每个地址发送一封电子邮件。实际收到邮件的不同地址有多少？</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[&quot;test.email+alex@leetcode.com&quot;,&quot;test.e.mail+bob.cathy@leetcode.com&quot;,&quot;testemail+david@lee.tcode.com&quot;]
<strong>输出：</strong>2
<strong>解释：</strong>实际收到邮件的是 &quot;testemail@leetcode.com&quot; 和 &quot;testemail@lee.tcode.com&quot;。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= emails[i].length&nbsp;&lt;= 100</code></li>
	<li><code>1 &lt;= emails.length &lt;= 100</code></li>
	<li>每封 <code>emails[i]</code> 都包含有且仅有一个 <code>&#39;@&#39;</code> 字符。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用哈希表存放转换后的电子邮件，最后返回哈希表的 size 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numUniqueEmails(self, emails: List[str]) -> int:
        ans = 0
        s = set()
        for email in emails:
            local, domain = email.split('@')
            local = local.replace('.', '')
            if '+' in local:
                local = local[:local.find('+')]
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
            String local = t[0];
            String domain = t[1];
            local = local.replace(".", "");
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
        for (auto& email : emails)
        {
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

### **...**

```

```

<!-- tabs:end -->
