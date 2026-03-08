---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3860.Unique%20Email%20Groups/README.md
---

<!-- problem:start -->

# [3860. 不同邮件组 🔒](https://leetcode.cn/problems/unique-email-groups)

[English Version](/solution/3800-3899/3860.Unique%20Email%20Groups/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串数组&nbsp;<code>emails</code>，其中每个字符串是一个有效的邮件地址。</p>

<p>如果两个邮件地址的 <strong>规范化</strong> 本地名称和 <strong>规范化</strong> 域名名称 <strong>都相同</strong>，则属于同一组。</p>

<p>规范化规则如下：</p>

<ul>
	<li>本地名称是 <code>'@'</code> 符号 <strong>之前</strong> 的部分。

    <ul>
    	<li>忽略所有点&nbsp;<code>'.'</code>。</li>
    	<li>忽略第一个 <code>'+'</code> 之后的所有内容，如果存在的话。</li>
    	<li>转换为小写。</li>
    </ul>
    </li>
    <li>域名是 <code>'@'</code> 符号 <strong>后面</strong> 的部分。
    <ul>
    	<li>转换为小写。</li>
    </ul>
    </li>

</ul>

<p>返回一个整数，表示规范化后的 <strong>不同</strong> 电子邮件组的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>emails = ["test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>示例：</strong></p>
</div>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">邮件地址</th>
			<th style="border: 1px solid black;">本地名称</th>
			<th style="border: 1px solid black;">规范化本地名称</th>
			<th style="border: 1px solid black;">域名</th>
			<th style="border: 1px solid black;">规范化域名</th>
			<th style="border: 1px solid black;">最终邮件地址</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">test.email+alex@leetcode.com</td>
			<td style="border: 1px solid black;">test.email+alex</td>
			<td style="border: 1px solid black;">testemail</td>
			<td style="border: 1px solid black;">leetcode.com</td>
			<td style="border: 1px solid black;">leetcode.com</td>
			<td style="border: 1px solid black;">testemail@leetcode.com</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">test.e.mail+bob.cathy@leetcode.com</td>
			<td style="border: 1px solid black;">test.e.mail+bob.cathy</td>
			<td style="border: 1px solid black;">testemail</td>
			<td style="border: 1px solid black;">leetcode.com</td>
			<td style="border: 1px solid black;">leetcode.com</td>
			<td style="border: 1px solid black;">testemail@leetcode.com</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">testemail+david@lee.tcode.com</td>
			<td style="border: 1px solid black;">testemail+david</td>
			<td style="border: 1px solid black;">testemail</td>
			<td style="border: 1px solid black;">lee.tcode.com</td>
			<td style="border: 1px solid black;">lee.tcode.com</td>
			<td style="border: 1px solid black;">testemail@lee.tcode.com</td>
		</tr>
	</tbody>
</table>

<p>不同的邮件地址是 [<code>"testemail@leetcode.com"</code>, <code>"testemail@lee.tcode.com"</code>]。因此，答案是 2。</p>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>emails = ["A@B.com", "a@b.com", "ab+xy@b.com", "a.b@b.com"]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>示例：</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">邮件地址</th>
			<th style="border: 1px solid black;">本地名称</th>
			<th style="border: 1px solid black;">规范化本地名称</th>
			<th style="border: 1px solid black;">域名</th>
			<th style="border: 1px solid black;">规范化域名</th>
			<th style="border: 1px solid black;">最终邮件地址</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">A@B.com</td>
			<td style="border: 1px solid black;">A</td>
			<td style="border: 1px solid black;">a</td>
			<td style="border: 1px solid black;">B.com</td>
			<td style="border: 1px solid black;">b.com</td>
			<td style="border: 1px solid black;">a@b.com</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">a@b.com</td>
			<td style="border: 1px solid black;">a</td>
			<td style="border: 1px solid black;">a</td>
			<td style="border: 1px solid black;">b.com</td>
			<td style="border: 1px solid black;">b.com</td>
			<td style="border: 1px solid black;">a@b.com</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">ab+xy@b.com</td>
			<td style="border: 1px solid black;">ab+xy</td>
			<td style="border: 1px solid black;">ab</td>
			<td style="border: 1px solid black;">b.com</td>
			<td style="border: 1px solid black;">b.com</td>
			<td style="border: 1px solid black;">ab@b.com</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">a.b@b.com</td>
			<td style="border: 1px solid black;">a.b</td>
			<td style="border: 1px solid black;">ab</td>
			<td style="border: 1px solid black;">b.com</td>
			<td style="border: 1px solid black;">b.com</td>
			<td style="border: 1px solid black;">ab@b.com</td>
		</tr>
	</tbody>
</table>

<p>不同的邮件地址是&nbsp;[<code>"a@b.com"</code>, <code>"ab@b.com"</code>]。因此，答案是 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>emails = ["a.b+c.d+e@DoMain.com", "ab+xyz@domain.com", "ab@domain.com"]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">邮件地址</th>
			<th style="border: 1px solid black;">本地名称</th>
			<th style="border: 1px solid black;">规范化本地名称</th>
			<th style="border: 1px solid black;">域名</th>
			<th style="border: 1px solid black;">规范化域名</th>
			<th style="border: 1px solid black;">最终邮件地址</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">a.b+c.d+e@DoMain.com</td>
			<td style="border: 1px solid black;">a.b+c.d+e</td>
			<td style="border: 1px solid black;">ab</td>
			<td style="border: 1px solid black;">DoMain.com</td>
			<td style="border: 1px solid black;">domain.com</td>
			<td style="border: 1px solid black;">ab@domain.com</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">ab+xyz@domain.com</td>
			<td style="border: 1px solid black;">ab+xyz</td>
			<td style="border: 1px solid black;">ab</td>
			<td style="border: 1px solid black;">domain.com</td>
			<td style="border: 1px solid black;">domain.com</td>
			<td style="border: 1px solid black;">ab@domain.com</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">ab@domain.com</td>
			<td style="border: 1px solid black;">ab</td>
			<td style="border: 1px solid black;">ab</td>
			<td style="border: 1px solid black;">domain.com</td>
			<td style="border: 1px solid black;">domain.com</td>
			<td style="border: 1px solid black;">ab@domain.com</td>
		</tr>
	</tbody>
</table>

<p>所有邮件地址规范化为&nbsp;<code>"ab@domain.com"</code>。因此，答案是 1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= emails.length &lt;= 1000</code></li>
	<li><code>1 &lt;= emails[i].length &lt;= 100</code></li>
	<li><code>emails[i]</code>&nbsp;包含大小写英文字母，数字，以及字符&nbsp;<code>'.'</code>，<code>'+'</code>&nbsp;和&nbsp;<code>'@'</code>。</li>
	<li>每个&nbsp;<code>emails[i]</code>&nbsp;包含 <strong>恰好</strong>&nbsp;一个&nbsp;<code>'@'</code> 字符。</li>
	<li>所有本地名称和域名都不为空；本地名不能以&nbsp;<code>'+'</code> 开头。</li>
	<li>域名以 <code>".com"</code> 后缀结尾，并且在 <code>".com"</code> 之前至少包含一个字符。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以使用一个哈希表 $\textit{st}$ 来存储每个邮箱地址的规范化结果。对于每个邮箱地址，我们按照题目要求进行规范化处理：

- 将邮箱地址分为本地名和域名两部分。
- 对于本地名，去掉所有的点 `.`，并且如果存在加号 `+`，则去掉加号及其后面的部分。最后将本地名转换为小写。
- 对于域名，将其转换为小写。
- 将规范化后的本地名和域名拼接起来，得到规范化后的邮箱地址，并将其加入哈希表 $\textit{st}$ 中。

最后，哈希表 $\textit{st}$ 中的元素个数即为唯一邮箱组的数量。

时间复杂度 $O(n \cdot m)$，其中 $n$ 和 $m$ 分别是邮箱地址的数量和每个邮箱地址的平均长度。空间复杂度 $O(n \cdot m)$，最坏情况下所有邮箱地址都不同。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniqueEmailGroups(self, emails: list[str]) -> int:
        st = set()
        for email in emails:
            local, domain = email.split("@")
            local = local.split("+")[0].replace(".", "").lower()
            domain = domain.lower()
            normalized = local + domain
            st.add(normalized)
        return len(st)
```

#### Java

```java
class Solution {
    public int uniqueEmailGroups(String[] emails) {
        Set<String> st = new HashSet<>();

        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];

            int plusIndex = local.indexOf('+');
            if (plusIndex != -1) {
                local = local.substring(0, plusIndex);
            }

            local = local.replace(".", "").toLowerCase();
            domain = domain.toLowerCase();

            String normalized = local + domain;
            st.add(normalized);
        }

        return st.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniqueEmailGroups(vector<string>& emails) {
        unordered_set<string> st;

        for (auto& email : emails) {
            int atPos = email.find('@');
            string local = email.substr(0, atPos);
            string domain = email.substr(atPos + 1);

            int plusPos = local.find('+');
            if (plusPos != string::npos) {
                local = local.substr(0, plusPos);
            }

            string cleaned;
            for (char c : local) {
                if (c != '.') {
                    cleaned += tolower(c);
                }
            }

            for (char& c : domain) {
                c = tolower(c);
            }

            st.insert(cleaned + domain);
        }

        return st.size();
    }
};
```

#### Go

```go
func uniqueEmailGroups(emails []string) int {
	st := make(map[string]struct{})

	for _, email := range emails {
		parts := strings.Split(email, "@")
		local := parts[0]
		domain := parts[1]

		if idx := strings.Index(local, "+"); idx != -1 {
			local = local[:idx]
		}

		local = strings.ReplaceAll(local, ".", "")
		local = strings.ToLower(local)
		domain = strings.ToLower(domain)

		normalized := local + domain
		st[normalized] = struct{}{}
	}

	return len(st)
}
```

#### TypeScript

```ts
function uniqueEmailGroups(emails: string[]): number {
    const st = new Set<string>();

    for (const email of emails) {
        let [local, domain] = email.split('@');
        local = local.split('+')[0].replace(/\./g, '').toLowerCase();
        domain = domain.toLowerCase();

        const normalized = local + domain;
        st.add(normalized);
    }

    return st.size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
