# [833. 字符串中的查找与替换](https://leetcode.cn/problems/find-and-replace-in-string)

[English Version](/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/README_EN.md)

<!-- tags:数组,字符串,排序 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>你会得到一个字符串 <code>s</code>&nbsp;(索引从 0 开始)，你必须对它执行 <code>k</code> 个替换操作。替换操作以三个长度均为 <code>k</code> 的并行数组给出：<code>indices</code>,&nbsp;<code>sources</code>,&nbsp;&nbsp;<code>targets</code>。</p>

<p>要完成第 <code>i</code> 个替换操作:</p>

<ol>
	<li>检查 <strong>子字符串</strong> &nbsp;<code>sources[i]</code>&nbsp;是否出现在 <strong>原字符串</strong> <code>s</code> 的索引&nbsp;<code>indices[i]</code>&nbsp;处。</li>
	<li>如果没有出现，&nbsp;<strong>什么也不做</strong>&nbsp;。</li>
	<li>如果出现，则用&nbsp;<code>targets[i]</code>&nbsp;<strong>替换</strong>&nbsp;该子字符串。</li>
</ol>

<p>例如，如果 <code>s = "abcd"</code>&nbsp;，&nbsp;<code>indices[i] = 0</code> ,&nbsp;<code>sources[i] = "ab"</code>， <code>targets[i] = "eee"</code> ，那么替换的结果将是 <code>"<u>eee</u>cd"</code> 。</p>

<p>所有替换操作必须 <strong>同时</strong> 发生，这意味着替换操作不应该影响彼此的索引。测试用例保证元素间<strong>不会重叠 </strong>。</p>

<ul>
	<li>例如，一个 <code>s = "abc"</code> ，&nbsp; <code>indices = [0,1]</code> ， <code>sources = ["ab"，"bc"]</code>&nbsp;的测试用例将不会生成，因为 <code>"ab"</code> 和 <code>"bc"</code> 替换重叠。</li>
</ul>

<p><em>在对 <code>s</code>&nbsp;执行所有替换操作后返回 <strong>结果字符串</strong> 。</em></p>

<p><strong>子字符串</strong> 是字符串中连续的字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/images/833-ex1.png" /></p>

<pre>
<strong>输入：</strong>s = "abcd", indices = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
<strong>输出：</strong>"eeebffff"
<strong>解释：
</strong>"a" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
"cd" 从 s 中的索引 2 开始，所以它被替换为 "ffff"。
</pre>

<p><strong>示例 2：</strong><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/images/833-ex2-1.png" /></p>

<pre>
<strong>输入：</strong>s = "abcd", indices = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
<strong>输出：</strong>"eeecd"
<strong>解释：
</strong>"ab" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
"ec" 没有从<strong>原始的</strong> S 中的索引 2 开始，所以它没有被替换。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>k == indices.length == sources.length == targets.length</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li><code>0 &lt;= indices[i] &lt; s.length</code></li>
	<li><code>1 &lt;= sources[i].length, targets[i].length &lt;= 50</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
	<li><code>sources[i]</code> 和 <code>targets[i]</code> 仅由小写英文字母组成</li>
</ul>

## 解法

### 方法一：模拟

我们遍历每个替换操作，对于当前第 $k$ 个替换操作 $(i, src)$，如果 $s[i..i+|src|-1]$ 与 $src$ 相等，此时我们记录下标 $i$ 处需要替换的是 $targets$ 的第 $k$ 个字符串，否则不需要替换。

接下来，我们只需要遍历原字符串 $s$，根据记录的信息进行替换即可。

时间复杂度 $O(L)$，空间复杂度 $O(n)$。其中 $L$ 是所有字符串的长度之和，而 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def findReplaceString(
        self, s: str, indices: List[int], sources: List[str], targets: List[str]
    ) -> str:
        n = len(s)
        d = [-1] * n
        for k, (i, src) in enumerate(zip(indices, sources)):
            if s.startswith(src, i):
                d[i] = k
        ans = []
        i = 0
        while i < n:
            if ~d[i]:
                ans.append(targets[d[i]])
                i += len(sources[d[i]])
            else:
                ans.append(s[i])
                i += 1
        return "".join(ans)
```

```java
class Solution {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        var d = new int[n];
        Arrays.fill(d, -1);
        for (int k = 0; k < indices.length; ++k) {
            int i = indices[k];
            if (s.startsWith(sources[k], i)) {
                d[i] = k;
            }
        }
        var ans = new StringBuilder();
        for (int i = 0; i < n;) {
            if (d[i] >= 0) {
                ans.append(targets[d[i]]);
                i += sources[d[i]].length();
            } else {
                ans.append(s.charAt(i++));
            }
        }
        return ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string findReplaceString(string s, vector<int>& indices, vector<string>& sources, vector<string>& targets) {
        int n = s.size();
        vector<int> d(n, -1);
        for (int k = 0; k < indices.size(); ++k) {
            int i = indices[k];
            if (s.compare(i, sources[k].size(), sources[k]) == 0) {
                d[i] = k;
            }
        }
        string ans;
        for (int i = 0; i < n;) {
            if (~d[i]) {
                ans += targets[d[i]];
                i += sources[d[i]].size();
            } else {
                ans += s[i++];
            }
        }
        return ans;
    }
};
```

```go
func findReplaceString(s string, indices []int, sources []string, targets []string) string {
	n := len(s)
	d := make([]int, n)
	for k, i := range indices {
		if strings.HasPrefix(s[i:], sources[k]) {
			d[i] = k + 1
		}
	}
	ans := &strings.Builder{}
	for i := 0; i < n; {
		if d[i] > 0 {
			ans.WriteString(targets[d[i]-1])
			i += len(sources[d[i]-1])
		} else {
			ans.WriteByte(s[i])
			i++
		}
	}
	return ans.String()
}
```

```ts
function findReplaceString(
    s: string,
    indices: number[],
    sources: string[],
    targets: string[],
): string {
    const n = s.length;
    const d: number[] = Array(n).fill(-1);
    for (let k = 0; k < indices.length; ++k) {
        const [i, src] = [indices[k], sources[k]];
        if (s.startsWith(src, i)) {
            d[i] = k;
        }
    }
    const ans: string[] = [];
    for (let i = 0; i < n; ) {
        if (d[i] >= 0) {
            ans.push(targets[d[i]]);
            i += sources[d[i]].length;
        } else {
            ans.push(s[i++]);
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- end -->
