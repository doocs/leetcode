---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3692.Majority%20Frequency%20Characters/README.md
rating: 1384
source: 第 166 场双周赛 Q1
---

<!-- problem:start -->

# [3692. 众数频率字符](https://leetcode.cn/problems/majority-frequency-characters)

[English Version](/solution/3600-3699/3692.Majority%20Frequency%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母组成的字符串 <code>s</code>。</p>

<p>对于一个值 <code>k</code>，<strong>频率组</strong> 是在 <code>s</code> 中恰好出现 <code>k</code> 次的字符集合。</p>

<p><strong>众数频率组</strong> 是包含 <strong>不同&nbsp;</strong>字符数量最多的频率组。</p>

<p>返回一个字符串，包含众数频率组中的所有字符，字符的顺序 <strong>不限&nbsp;</strong>。如果两个或多个频率组的大小并列最大，则选择其频率 <code>k</code> <strong>较大&nbsp;</strong>的那个组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "aaabbbccdddde"</span></p>

<p><strong>输出:</strong> <span class="example-io">"ab"</span></p>

<p><strong>解释:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">频率 (k)</th>
			<th style="border: 1px solid black;">组中不同字符</th>
			<th style="border: 1px solid black;">组大小</th>
			<th style="border: 1px solid black;">是否众数?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">{d}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">{a, b}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><strong>是</strong></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">{c}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{e}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">否</td>
		</tr>
	</tbody>
</table>

<p>字符 <code>'a'</code> 和 <code>'b'</code> 的频率相同，都为 3，它们在众数频率组中。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "abcd"</span></p>

<p><strong>输出:</strong> <span class="example-io">"abcd"</span></p>

<p><strong>解释:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">频率 (k)</th>
			<th style="border: 1px solid black;">组中不同字符</th>
			<th style="border: 1px solid black;">组大小</th>
			<th style="border: 1px solid black;">是否众数?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{a, b, c, d}</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><strong>是</strong></td>
		</tr>
	</tbody>
</table>

<p>所有字符的频率都相同，都为 1，它们都在众数频率组中。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "pfpfgi"</span></p>

<p><strong>输出:</strong> <span class="example-io">"fp"</span></p>

<p><strong>解释:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">频率 (k)</th>
			<th style="border: 1px solid black;">组中不同字符</th>
			<th style="border: 1px solid black;">组大小</th>
			<th style="border: 1px solid black;">是否众数?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">{p, f}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><strong>是</strong></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{g, i}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">否 (组大小并列，选择频率更大的 k = 2)</td>
		</tr>
	</tbody>
</table>

<p>字符 <code>'p'</code> 和 <code>'f'</code> 的频率相同，都为 2，它们在众数频率组中。频率为 1 的组大小并列，但我们选择频率更高的组 2。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们先用一个数组或哈希表 $\textit{cnt}$ 统计字符串中每个字符的出现频率。然后，我们再用一个哈希表 $\textit{f}$，将出现频率 $k$ 相同的字符放在同一个列表中，即 $\textit{f}[k]$ 存储所有出现频率为 $k$ 的字符。

接下来，我们遍历哈希表 $\textit{f}$，找到组大小最大的频率组。如果有多个频率组的大小并列最大，则选择其频率 $k$ 较大的那个组。最后，我们将该频率组中的所有字符连接成一个字符串并返回。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $\textit{s}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def majorityFrequencyGroup(self, s: str) -> str:
        cnt = Counter(s)
        f = defaultdict(list)
        for c, v in cnt.items():
            f[v].append(c)
        mx = mv = 0
        ans = []
        for v, cs in f.items():
            if mx < len(cs) or (mx == len(cs) and mv < v):
                mx = len(cs)
                mv = v
                ans = cs
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String majorityFrequencyGroup(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        Map<Integer, StringBuilder> f = new HashMap<>();
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                f.computeIfAbsent(cnt[i], k -> new StringBuilder()).append((char) ('a' + i));
            }
        }
        int mx = 0;
        int mv = 0;
        String ans = "";
        for (var e : f.entrySet()) {
            int v = e.getKey();
            var cs = e.getValue();
            if (mx < cs.length() || (mx == cs.length() && mv < v)) {
                mx = cs.length();
                mv = v;
                ans = cs.toString();
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string majorityFrequencyGroup(string s) {
        vector<int> cnt(26, 0);
        for (char c : s) {
            ++cnt[c - 'a'];
        }

        unordered_map<int, string> f;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                f[cnt[i]].push_back('a' + i);
            }
        }

        int mx = 0, mv = 0;
        string ans;
        for (auto& e : f) {
            int v = e.first;
            string& cs = e.second;
            if (mx < (int) cs.size() || (mx == (int) cs.size() && mv < v)) {
                mx = cs.size();
                mv = v;
                ans = cs;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func majorityFrequencyGroup(s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}

	f := make(map[int][]byte)
	for i, v := range cnt {
		if v > 0 {
			f[v] = append(f[v], byte('a'+i))
		}
	}

	mx, mv := 0, 0
	var ans []byte
	for v, cs := range f {
		if len(cs) > mx || (len(cs) == mx && v > mv) {
			mx = len(cs)
			mv = v
			ans = cs
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function majorityFrequencyGroup(s: string): string {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    const f = new Map<number, string[]>();
    for (const [c, v] of Object.entries(cnt)) {
        if (!f.has(v)) {
            f.set(v, []);
        }
        f.get(v)!.push(c);
    }
    let [mx, mv] = [0, 0];
    let ans = '';
    f.forEach((cs, v) => {
        if (mx < cs.length || (mx == cs.length && mv < v)) {
            mx = cs.length;
            mv = v;
            ans = cs.join('');
        }
    });
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
