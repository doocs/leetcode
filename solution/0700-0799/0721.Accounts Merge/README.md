---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0721.Accounts%20Merge/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 数组
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [721. 账户合并](https://leetcode.cn/problems/accounts-merge)

[English Version](/solution/0700-0799/0721.Accounts%20Merge/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个列表 <code>accounts</code>，每个元素 <code>accounts[i]</code>&nbsp;是一个字符串列表，其中第一个元素 <code>accounts[i][0]</code>&nbsp;是&nbsp;<em>名称 (name)</em>，其余元素是 <em><strong>emails</strong> </em>表示该账户的邮箱地址。</p>

<p>现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。</p>

<p>合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是 <strong>按字符 ASCII 顺序排列</strong> 的邮箱地址。账户本身可以以 <strong>任意顺序</strong> 返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
<b>输出：</b>[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
<b>解释：</b>
第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。 
第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
<strong>输出：</strong>[["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= accounts.length &lt;= 1000</code></li>
	<li><code>2 &lt;= accounts[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= accounts[i][j].length &lt;= 30</code></li>
	<li><code>accounts[i][0]</code> 由英文字母组成</li>
	<li><code>accounts[i][j] (for j &gt; 0)</code> 是有效的邮箱地址</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(accounts)
        p = list(range(n))
        email_id = {}
        for i, account in enumerate(accounts):
            name = account[0]
            for email in account[1:]:
                if email in email_id:
                    p[find(i)] = find(email_id[email])
                else:
                    email_id[email] = i
        mp = defaultdict(set)
        for i, account in enumerate(accounts):
            for email in account[1:]:
                mp[find(i)].add(email)

        ans = []
        for i, emails in mp.items():
            t = [accounts[i][0]]
            t.extend(sorted(emails))
            ans.append(t)
        return ans
```

```java
class Solution {
    private int[] p;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        Map<String, Integer> emailId = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            List<String> account = accounts.get(i);
            String name = account.get(0);
            for (int j = 1; j < account.size(); ++j) {
                String email = account.get(j);
                if (emailId.containsKey(email)) {
                    p[find(i)] = find(emailId.get(email));
                } else {
                    emailId.put(email, i);
                }
            }
        }
        Map<Integer, Set<String>> mp = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); ++j) {
                String email = account.get(j);
                mp.computeIfAbsent(find(i), k -> new HashSet<>()).add(email);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : mp.entrySet()) {
            List<String> t = new LinkedList<>();
            t.addAll(entry.getValue());
            Collections.sort(t);
            t.add(0, accounts.get(entry.getKey()).get(0));
            res.add(t);
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    vector<int> p;

    vector<vector<string>> accountsMerge(vector<vector<string>>& accounts) {
        int n = accounts.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<string, int> emailId;
        for (int i = 0; i < n; ++i) {
            auto account = accounts[i];
            auto name = account[0];
            for (int j = 1; j < account.size(); ++j) {
                string email = account[j];
                if (emailId.count(email))
                    p[find(i)] = find(emailId[email]);
                else
                    emailId[email] = i;
            }
        }
        unordered_map<int, unordered_set<string>> mp;
        for (int i = 0; i < n; ++i) {
            auto account = accounts[i];
            for (int j = 1; j < account.size(); ++j) {
                string email = account[j];
                mp[find(i)].insert(email);
            }
        }
        vector<vector<string>> ans;
        for (auto& [i, emails] : mp) {
            vector<string> t;
            t.push_back(accounts[i][0]);
            for (string email : emails) t.push_back(email);
            sort(t.begin() + 1, t.end());
            ans.push_back(t);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
