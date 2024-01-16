# [93. Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses)

[中文文档](/solution/0000-0099/0093.Restore%20IP%20Addresses/README.md)

## Description

<p>A <strong>valid IP address</strong> consists of exactly four integers separated by single dots. Each integer is between <code>0</code> and <code>255</code> (<strong>inclusive</strong>) and cannot have leading zeros.</p>

<ul>
	<li>For example, <code>&quot;0.1.2.201&quot;</code> and <code>&quot;192.168.1.1&quot;</code> are <strong>valid</strong> IP addresses, but <code>&quot;0.011.255.245&quot;</code>, <code>&quot;192.168.1.312&quot;</code> and <code>&quot;192.168@1.1&quot;</code> are <strong>invalid</strong> IP addresses.</li>
</ul>

<p>Given a string <code>s</code> containing only digits, return <em>all possible valid IP addresses that can be formed by inserting dots into </em><code>s</code>. You are <strong>not</strong> allowed to reorder or remove any digits in <code>s</code>. You may return the valid IP addresses in <strong>any</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;25525511135&quot;
<strong>Output:</strong> [&quot;255.255.11.135&quot;,&quot;255.255.111.35&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0000&quot;
<strong>Output:</strong> [&quot;0.0.0.0&quot;]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;101023&quot;
<strong>Output:</strong> [&quot;1.0.10.23&quot;,&quot;1.0.102.3&quot;,&quot;10.1.0.23&quot;,&quot;10.10.2.3&quot;,&quot;101.0.2.3&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> consists of digits only.</li>
</ul>

## Solutions

### Solution 1: DFS

We define a function $dfs(i)$, which represents the list of IP addresses that can be formed starting from the $i$th position of string $s$.

The execution steps of function $dfs(i)$ are as follows:

If $i$ is greater than or equal to the length of string $s$, it means that we have completed the splicing of four segments of the IP address. At this point, we need to check whether it meets the requirements of the four segments of the IP address. If it does, add the current $IP$ to the answer.

If $i$ is less than the length of string $s$, it means that we still need to splice a segment of the IP address. At this point, we need to determine the value of this segment of the IP address. If the value is greater than $255$, or the current position $i$ is $0$ and the value of several positions after $i$ is greater than $0$, it means that it does not meet the requirements, so we return directly. Otherwise, add it to the IP address list, and continue to search for the next segment of the IP address.

The time complexity is $O(n \times 3^4)$, and the space complexity is $O(n)$. Here, $n$ is the length of string $s$.

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
