# [811. 子域名访问计数](https://leetcode-cn.com/problems/subdomain-visit-count)

[English Version](/solution/0800-0899/0811.Subdomain%20Visit%20Count/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个网站域名，如&quot;discuss.leetcode.com&quot;，包含了多个子域名。作为顶级域名，常用的有&quot;com&quot;，下一级则有&quot;leetcode.com&quot;，最低的一级为&quot;discuss.leetcode.com&quot;。当我们访问域名&quot;discuss.leetcode.com&quot;时，也同时访问了其父域名&quot;leetcode.com&quot;以及顶级域名&nbsp;&quot;com&quot;。</p>

<p>给定一个带访问次数和域名的组合，要求分别计算每个域名被访问的次数。其格式为访问次数+空格+地址，例如：&quot;9001 discuss.leetcode.com&quot;。</p>

<p>接下来会给出一组访问次数和域名组合的列表<code>cpdomains</code>&nbsp;。要求解析出所有域名的访问次数，输出格式和输入格式相同，不限定先后顺序。</p>

<pre>
<strong>示例 1:</strong>
<strong>输入:</strong> 
[&quot;9001 discuss.leetcode.com&quot;]
<strong>输出:</strong> 
[&quot;9001 discuss.leetcode.com&quot;, &quot;9001 leetcode.com&quot;, &quot;9001 com&quot;]
<strong>说明:</strong> 
例子中仅包含一个网站域名：&quot;discuss.leetcode.com&quot;。按照前文假设，子域名&quot;leetcode.com&quot;和&quot;com&quot;都会被访问，所以它们都被访问了9001次。
</pre>

<pre>
<strong>示例 2
输入:</strong> 
[&quot;900 google.mail.com&quot;, &quot;50 yahoo.com&quot;, &quot;1 intel.mail.com&quot;, &quot;5 wiki.org&quot;]
<strong>输出:</strong> 
[&quot;901 mail.com&quot;,&quot;50 yahoo.com&quot;,&quot;900 google.mail.com&quot;,&quot;5 wiki.org&quot;,&quot;5 org&quot;,&quot;1 intel.mail.com&quot;,&quot;951 com&quot;]
<strong>说明:</strong> 
按照假设，会访问&quot;google.mail.com&quot; 900次，&quot;yahoo.com&quot; 50次，&quot;intel.mail.com&quot; 1次，&quot;wiki.org&quot; 5次。
而对于父域名，会访问&quot;mail.com&quot; 900+1 = 901次，&quot;com&quot; 900 + 50 + 1 = 951次，和 &quot;org&quot; 5 次。
</pre>

<p><strong>注意事项：</strong></p>

<ul>
	<li>&nbsp;<code>cpdomains</code>&nbsp;的长度小于&nbsp;<code>100</code>。</li>
	<li>每个域名的长度小于<code>100</code>。</li>
	<li>每个域名地址包含一个或两个&quot;.&quot;符号。</li>
	<li>输入中任意一个域名的访问次数都小于<code>10000</code>。</li>
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
