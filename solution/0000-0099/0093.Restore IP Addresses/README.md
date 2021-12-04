# [93. 复原 IP 地址](https://leetcode-cn.com/problems/restore-ip-addresses)

[English Version](/solution/0000-0099/0093.Restore%20IP%20Addresses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 <code>s</code> 获得的 <strong>有效 IP 地址 </strong>。你可以按任何顺序返回答案。</p>

<p><strong>有效 IP 地址</strong> 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 <code>0</code>），整数之间用 <code>'.'</code> 分隔。</p>

<p>例如："0.1.2.201" 和 "192.168.1.1" 是 <strong>有效</strong> IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 <strong>无效</strong> IP 地址。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "25525511135"
<strong>输出：</strong>["255.255.11.135","255.255.111.35"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "0000"
<strong>输出：</strong>["0.0.0.0"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "1111"
<strong>输出：</strong>["1.1.1.1"]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "010010"
<strong>输出：</strong>["0.10.0.10","0.100.1.0"]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>s = "101023"
<strong>输出：</strong>["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= s.length <= 3000</code></li>
	<li><code>s</code> 仅由数字组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        ans = []

        def check(s):
            if not (0 <= int(s) <= 255):
                return False
            if s[0] == '0' and len(s) > 1:
                return False
            return True

        def dfs(s, t):
            if len(t) == 4:
                if not s:
                    ans.append('.'.join(t))
                return
            for i in range(1, min(4, len(s) + 1)):
                if check(s[:i]):
                    t.append(s[:i])
                    dfs(s[i:], t)
                    t.pop()

        dfs(s, [])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<String> ans;

    public List<String> restoreIpAddresses(String s) {
        ans = new ArrayList<>();
        dfs(s, new ArrayList<>());
        return ans;
    }

    private void dfs(String s, List<String> t) {
        if (t.size() == 4) {
            if ("".equals(s)) {
                ans.add(String.join(".", t));
            }
            return;
        }
        for (int i = 1; i < Math.min(4, s.length() + 1); ++i) {
            String c = s.substring(0, i);
            if (check(c)) {
                t.add(c);
                dfs(s.substring(i), t);
                t.remove(t.size() - 1);
            }
        }
    }

    private boolean check(String s) {
        if ("".equals(s)) {
            return false;
        }
        int num = Integer.parseInt(s);
        if (num > 255) {
            return false;
        }
        if (s.charAt(0) == '0' && s.length() > 1) {
            return false;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> ans;
        vector<string> t;
        dfs(s, t, ans);
        return ans;
    }

    void dfs(string s, vector<string>& t, vector<string>& ans) {
        if (t.size() == 4)
        {
            if (s == "")
            {
                string p = "";
                for (auto e : t) p += e + ".";
                p.pop_back();
                ans.push_back(p);
            }
            return;
        }
        for (int i = 1; i < min(4, (int) s.size() + 1); ++i)
        {
            string c = s.substr(0, i);
            if (check(c))
            {
                t.push_back(c);
                dfs(s.substr(i, s.size() - i), t, ans);
                t.pop_back();
            }
        }
    }

    bool check(string s) {
        if (s == "") return false;
        int num = stoi(s);
        if (num > 255) return false;
        if (s[0] == '0' && s.size() > 1) return false;
        return true;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
