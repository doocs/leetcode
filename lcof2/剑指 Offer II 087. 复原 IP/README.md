---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20087.%20%E5%A4%8D%E5%8E%9F%20IP/README.md
---

<!-- problem:start -->

# [剑指 Offer II 087. 复原 IP](https://leetcode.cn/problems/0on3uN)

## 题目描述

<!-- description:start -->

<p>给定一个只包含数字的字符串 <code>s</code> ，用以表示一个 IP 地址，返回所有可能从&nbsp;<code>s</code> 获得的 <strong>有效 IP 地址 </strong>。你可以按任何顺序返回答案。</p>

<p><strong>有效 IP 地址</strong> 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 <code>0</code>），整数之间用 <code>&#39;.&#39;</code> 分隔。</p>

<p>例如：&quot;0.1.2.201&quot; 和 &quot;192.168.1.1&quot; 是 <strong>有效</strong> IP 地址，但是 &quot;0.011.255.245&quot;、&quot;192.168.1.312&quot; 和 &quot;192.168@1.1&quot; 是 <strong>无效</strong> IP 地址。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;25525511135&quot;
<strong>输出：</strong>[&quot;255.255.11.135&quot;,&quot;255.255.111.35&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;0000&quot;
<strong>输出：</strong>[&quot;0.0.0.0&quot;]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;1111&quot;
<strong>输出：</strong>[&quot;1.1.1.1&quot;]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;010010&quot;
<strong>输出：</strong>[&quot;0.10.0.10&quot;,&quot;0.100.1.0&quot;]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;10203040&quot;
<strong>输出：</strong>[&quot;10.20.30.40&quot;,&quot;102.0.30.40&quot;,&quot;10.203.0.40&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 3000</code></li>
	<li><code>s</code> 仅由数字组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 93&nbsp;题相同：<a href="https://leetcode.cn/problems/restore-ip-addresses/">https://leetcode.cn/problems/restore-ip-addresses/</a>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们定义一个函数 $dfs(i)$，表示从字符串 $s$ 的第 $i$ 位开始，搜索能够组成的 IP 地址列表。

函数 $dfs(i)$ 的执行步骤如下：

如果 $i$ 大于等于字符串 $s$ 的长度，说明已经完成了四段 IP 地址的拼接，判断是否满足四段 IP 地址的要求，如果满足则将当前 $IP$ 加入答案。

如果 $i$ 小于字符串 $s$ 的长度，此时还需要拼接 $IP$ 地址的一段，此时需要确定这一段 $IP$ 地址的值。如果该值大于 $255$，或者当前位置 $i$ 为 $0$ 且 $i$ 之后的若干位的数值大于 $0$，则说明不满足要求，直接返回。否则，将其加入 $IP$ 地址列表，并继续搜索下一段 $IP$ 地址。

时间复杂度 $O(n \times 3^4)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        def check(i: int, j: int) -> int:
            if s[i] == "0" and i != j:
                return False
            return 0 <= int(s[i : j + 1]) <= 255

        def dfs(i: int):
            if i >= n and len(t) == 4:
                ans.append(".".join(t))
                return
            if i >= n or len(t) >= 4:
                return
            for j in range(i, min(i + 3, n)):
                if check(i, j):
                    t.append(s[i : j + 1])
                    dfs(j + 1)
                    t.pop()

        n = len(s)
        ans = []
        t = []
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private String s;
    private List<String> ans = new ArrayList<>();
    private List<String> t = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        n = s.length();
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= n && t.size() == 4) {
            ans.add(String.join(".", t));
            return;
        }
        if (i >= n || t.size() >= 4) {
            return;
        }
        int x = 0;
        for (int j = i; j < Math.min(i + 3, n); ++j) {
            x = x * 10 + s.charAt(j) - '0';
            if (x > 255 || (s.charAt(i) == '0' && i != j)) {
                break;
            }
            t.add(s.substring(i, j + 1));
            dfs(j + 1);
            t.remove(t.size() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        int n = s.size();
        vector<string> ans;
        vector<string> t;
        function<void(int)> dfs = [&](int i) {
            if (i >= n && t.size() == 4) {
                ans.push_back(t[0] + "." + t[1] + "." + t[2] + "." + t[3]);
                return;
            }
            if (i >= n || t.size() >= 4) {
                return;
            }
            int x = 0;
            for (int j = i; j < min(n, i + 3); ++j) {
                x = x * 10 + s[j] - '0';
                if (x > 255 || (j > i && s[i] == '0')) {
                    break;
                }
                t.push_back(s.substr(i, j - i + 1));
                dfs(j + 1);
                t.pop_back();
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func restoreIpAddresses(s string) (ans []string) {
	n := len(s)
	t := []string{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n && len(t) == 4 {
			ans = append(ans, strings.Join(t, "."))
			return
		}
		if i >= n || len(t) == 4 {
			return
		}
		x := 0
		for j := i; j < i+3 && j < n; j++ {
			x = x*10 + int(s[j]-'0')
			if x > 255 || (j > i && s[i] == '0') {
				break
			}
			t = append(t, s[i:j+1])
			dfs(j + 1)
			t = t[:len(t)-1]
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function restoreIpAddresses(s: string): string[] {
    const n = s.length;
    const ans: string[] = [];
    const t: string[] = [];
    const dfs = (i: number): void => {
        if (i >= n && t.length === 4) {
            ans.push(t.join('.'));
            return;
        }
        if (i >= n || t.length === 4) {
            return;
        }
        let x = 0;
        for (let j = i; j < i + 3 && j < n; ++j) {
            x = x * 10 + s[j].charCodeAt(0) - '0'.charCodeAt(0);
            if (x > 255 || (j > i && s[i] === '0')) {
                break;
            }
            t.push(x.toString());
            dfs(j + 1);
            t.pop();
        }
    };
    dfs(0);
    return ans;
}
```

#### C#

```cs
public class Solution {
    private IList<string> ans = new List<string>();
    private IList<string> t = new List<string>();
    private int n;
    private string s;

    public IList<string> RestoreIpAddresses(string s) {
        n = s.Length;
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= n && t.Count == 4) {
            ans.Add(string.Join(".", t));
            return;
        }
        if (i >= n || t.Count == 4) {
            return;
        }
        int x = 0;
        for (int j = i; j < i + 3 && j < n; ++j) {
            x = x * 10 + (s[j] - '0');
            if (x > 255 || (j > i && s[i] == '0')) {
                break;
            }
            t.Add(x.ToString());
            dfs(j + 1);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

#### Swift

```swift
class Solution {
    private var n: Int = 0
    private var s: String = ""
    private var ans: [String] = []
    private var t: [String] = []

    func restoreIpAddresses(_ s: String) -> [String] {
        n = s.count
        self.s = s
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        if i >= n && t.count == 4 {
            ans.append(t.joined(separator: "."))
            return
        }
        if i >= n || t.count >= 4 {
            return
        }
        var x = 0
        let chars = Array(s)
        for j in i..<min(i + 3, n) {
            x = x * 10 + Int(chars[j].wholeNumberValue!)
            if x > 255 || (chars[i] == "0" && i != j) {
                break
            }
            t.append(String(chars[i...j]))
            dfs(j + 1)
            t.removeLast()
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
