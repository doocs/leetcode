# [811. 子域名访问计数](https://leetcode.cn/problems/subdomain-visit-count)

[English Version](/solution/0800-0899/0811.Subdomain%20Visit%20Count/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>网站域名 <code>"discuss.leetcode.com"</code> 由多个子域名组成。顶级域名为 <code>"com"</code> ，二级域名为 <code>"leetcode.com"</code> ，最低一级为 <code>"discuss.leetcode.com"</code> 。当访问域名 <code>"discuss.leetcode.com"</code> 时，同时也会隐式访问其父域名 <code>"leetcode.com" </code>以及 <code>"com"</code> 。</p>

<p><strong>计数配对域名</strong> 是遵循 <code>"rep d1.d2.d3"</code> 或 <code>"rep d1.d2"</code> 格式的一个域名表示，其中 <code>rep</code> 表示访问域名的次数，<code>d1.d2.d3</code> 为域名本身。</p>

<ul>
	<li>例如，<code>"9001 discuss.leetcode.com"</code> 就是一个 <strong>计数配对域名</strong> ，表示 <code>discuss.leetcode.com</code> 被访问了 <code>9001</code> 次。</li>
</ul>

<p>给你一个<strong> 计数配对域名 </strong>组成的数组 <code>cpdomains</code> ，解析得到输入中每个子域名对应的&nbsp;<strong>计数配对域名</strong> ，并以数组形式返回。可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>cpdomains = ["9001 discuss.leetcode.com"]
<strong>输出：</strong>["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
<strong>解释：</strong>例子中仅包含一个网站域名："discuss.leetcode.com"。
按照前文描述，子域名 "leetcode.com" 和 "com" 都会被访问，所以它们都被访问了 9001 次。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
<strong>输出：</strong>["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
<strong>解释：</strong>按照前文描述，会访问 "google.mail.com" 900 次，"yahoo.com" 50 次，"intel.mail.com" 1 次，"wiki.org" 5 次。
而对于父域名，会访问 "mail.com" 900 + 1 = 901 次，"com" 900 + 50 + 1 = 951 次，和 "org" 5 次。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= cpdomain.length &lt;= 100</code></li>
	<li><code>1 &lt;= cpdomain[i].length &lt;= 100</code></li>
	<li><code>cpdomain[i]</code> 会遵循 <code>"rep<sub>i</sub> d1<sub>i</sub>.d2<sub>i</sub>.d3<sub>i</sub>"</code> 或 <code>"rep<sub>i</sub> d1<sub>i</sub>.d2<sub>i</sub>"</code> 格式</li>
	<li><code>rep<sub>i</sub></code> 是范围 <code>[1, 10<sup>4</sup>]</code> 内的一个整数</li>
	<li><code>d1<sub>i</sub></code>、<code>d2<sub>i</sub></code> 和 <code>d3<sub>i</sub></code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subdomainVisits(self, cpdomains: List[str]) -> List[str]:
        domains = Counter()
        for item in cpdomains:
            count, domain = item.split()
            count = int(count)
            subs = domain.split('.')
            for i in range(len(subs)):
                key = '.'.join(subs[i:])
                domains[key] += count
        return [f'{cnt} {domain}' for domain, cnt in domains.items()]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> domains = new HashMap<>();
        for (String domain : cpdomains) {
            String[] t = domain.split(" ");
            int count = Integer.parseInt(t[0]);
            String[] subs = t[1].split("\\.");
            String cur = "";
            for (int i = subs.length - 1; i >= 0; --i) {
                cur = subs[i] + (i == subs.length - 1 ? "" : ".") + cur;
                domains.put(cur, domains.getOrDefault(cur, 0) + count);
            }
        }
        List<String> res = new ArrayList<>();
        domains.forEach((domain, count) -> {
            res.add(count + " " + domain);
        });
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
