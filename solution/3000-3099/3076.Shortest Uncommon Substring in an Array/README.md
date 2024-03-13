# [3076. 数组中的最短非公共子字符串](https://leetcode.cn/problems/shortest-uncommon-substring-in-an-array)

[English Version](/solution/3000-3099/3076.Shortest%20Uncommon%20Substring%20in%20an%20Array/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>arr</code>&nbsp;，数组中有 <code>n</code>&nbsp;个 <b>非空</b>&nbsp;字符串。</p>

<p>请你求出一个长度为 <code>n</code>&nbsp;的字符串&nbsp;<code>answer</code>&nbsp;，满足：</p>

<ul>
	<li><code>answer[i]</code>&nbsp;是 <code>arr[i]</code>&nbsp;<strong>最短</strong>&nbsp;的子字符串，且它不是 <code>arr</code>&nbsp;中其他任何字符串的子字符串。如果有多个这样的子字符串存在，<code>answer[i]</code>&nbsp;应该是它们中字典序最小的一个。如果不存在这样的子字符串，<code>answer[i]</code>&nbsp;为空字符串。</li>
</ul>

<p>请你返回数组<em>&nbsp;</em><code>answer</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>arr = ["cab","ad","bad","c"]
<b>输出：</b>["ab","","ba",""]
<b>解释：</b>求解过程如下：
- 对于字符串 "cab" ，最短没有在其他字符串中出现过的子字符串是 "ca" 或者 "ab" ，我们选择字典序更小的子字符串，也就是 "ab" 。
- 对于字符串 "ad" ，不存在没有在其他字符串中出现过的子字符串。
- 对于字符串 "bad" ，最短没有在其他字符串中出现过的子字符串是 "ba" 。
- 对于字符串 "c" ，不存在没有在其他字符串中出现过的子字符串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>arr = ["abc","bcd","abcd"]
<b>输出：</b>["","","abcd"]
<b>解释：</b>求解过程如下：
- 对于字符串 "abc" ，不存在没有在其他字符串中出现过的子字符串。
- 对于字符串 "bcd" ，不存在没有在其他字符串中出现过的子字符串。
- 对于字符串 "abcd" ，最短没有在其他字符串中出现过的子字符串是 "abcd" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 20</code></li>
	<li><code>arr[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：枚举

我们注意到数据规模很小，所以可以直接枚举每个字符串的所有子串，然后判断是否是其他字符串的子串。

具体地，我们先枚举每个字符串 `arr[i]`，然后从小到大枚举每个子串的长度 $j$，然后枚举每个子串的起始位置 $l$，可以得到当前子串为 `sub = arr[i][l:l+j]`，然后判断 `sub` 是否是其他字符串的子串，如果是的话，就跳过当前子串，否则更新答案。

时间复杂度 $O(n^2 \times m^4)$，空间复杂度 $O(m)$。其中 $n$ 是字符串数组 `arr` 的长度，而 $m$ 是字符串的最大长度，本题中 $m \le 20$。

<!-- tabs:start -->

```python
class Solution:
    def shortestSubstrings(self, arr: List[str]) -> List[str]:
        ans = [""] * len(arr)
        for i, s in enumerate(arr):
            m = len(s)
            for j in range(1, m + 1):
                for l in range(m - j + 1):
                    sub = s[l : l + j]
                    if not ans[i] or ans[i] > sub:
                        if all(k == i or sub not in t for k, t in enumerate(arr)):
                            ans[i] = sub
                if ans[i]:
                    break
        return ans
```

```java
class Solution {
    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        String[] ans = new String[n];
        Arrays.fill(ans, "");
        for (int i = 0; i < n; ++i) {
            int m = arr[i].length();
            for (int j = 1; j <= m && ans[i].isEmpty(); ++j) {
                for (int l = 0; l <= m - j; ++l) {
                    String sub = arr[i].substring(l, l + j);
                    if (ans[i].isEmpty() || sub.compareTo(ans[i]) < 0) {
                        boolean ok = true;
                        for (int k = 0; k < n && ok; ++k) {
                            if (k != i && arr[k].contains(sub)) {
                                ok = false;
                            }
                        }
                        if (ok) {
                            ans[i] = sub;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> shortestSubstrings(vector<string>& arr) {
        int n = arr.size();
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            int m = arr[i].size();
            for (int j = 1; j <= m && ans[i].empty(); ++j) {
                for (int l = 0; l <= m - j; ++l) {
                    string sub = arr[i].substr(l, j);
                    if (ans[i].empty() || sub < ans[i]) {
                        bool ok = true;
                        for (int k = 0; k < n && ok; ++k) {
                            if (k != i && arr[k].find(sub) != string::npos) {
                                ok = false;
                            }
                        }
                        if (ok) {
                            ans[i] = sub;
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

```go
func shortestSubstrings(arr []string) []string {
	ans := make([]string, len(arr))
	for i, s := range arr {
		m := len(s)
		for j := 1; j <= m && len(ans[i]) == 0; j++ {
			for l := 0; l <= m-j; l++ {
				sub := s[l : l+j]
				if len(ans[i]) == 0 || ans[i] > sub {
					ok := true
					for k, t := range arr {
						if k != i && strings.Contains(t, sub) {
							ok = false
							break
						}
					}
					if ok {
						ans[i] = sub
					}
				}
			}
		}
	}
	return ans
}
```

```ts
function shortestSubstrings(arr: string[]): string[] {
    const n: number = arr.length;
    const ans: string[] = Array(n).fill('');
    for (let i = 0; i < n; ++i) {
        const m: number = arr[i].length;
        for (let j = 1; j <= m && ans[i] === ''; ++j) {
            for (let l = 0; l <= m - j; ++l) {
                const sub: string = arr[i].slice(l, l + j);
                if (ans[i] === '' || sub.localeCompare(ans[i]) < 0) {
                    let ok: boolean = true;
                    for (let k = 0; k < n && ok; ++k) {
                        if (k !== i && arr[k].includes(sub)) {
                            ok = false;
                        }
                    }
                    if (ok) {
                        ans[i] = sub;
                    }
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
