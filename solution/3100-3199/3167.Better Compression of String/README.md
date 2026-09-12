---
comments: true
difficulty: 中等
tags:
    - 哈希表
    - 字符串
    - 计数
    - 排序
---

<!-- problem:start -->

# [3167. 字符串的更好压缩 🔒](https://leetcode.cn/problems/better-compression-of-string)

[English Version](/solution/3100-3199/3167.Better%20Compression%20of%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串&nbsp;<code>compressed</code>&nbsp;表示一个字符串的压缩版本。格式是一个字符后面加上其出现频率。例如&nbsp;<code>"a3b1a1c2"</code>&nbsp;是字符串&nbsp;<code>"aaabacc"</code>&nbsp;的一个压缩版本。</p>

<p>我们在以下条件下寻求 <strong>更好的压缩</strong>：</p>

<ol>
	<li>每个字符在压缩版本中只应出现 <strong>一次</strong>。</li>
	<li>字符应按 <strong>字母顺序</strong> 排列。</li>
</ol>

<p>返回&nbsp;<code>compressed</code>&nbsp;的更好压缩版本。</p>

<p><strong>注意：</strong>在更好的压缩版本中，字母的顺序可能会改变，这是可以接受的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">compressed = "a3c9b2c1"</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">"a3b2c10"</span></p>

<p><strong>解释：</strong></p>

<p>字符 "a" 和 "b" 在输入中只出现了一次，但 "c" 出现了两次，第一次长度为 9，另一次是长度为 1。</p>

<p>因此，在结果字符串中，它应当长度为 10。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">compressed = "c2b3a1"</span></p>

<p><span class="example-io"><b>输出：</b>"a1b3c2"</span></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">compressed = "a2b4c1"</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">"a2b4c1"</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= compressed.length &lt;= 6 * 10<sup>4</sup></code></li>
	<li><code>compressed</code> 仅由小写英文字母和数字组成。</li>
	<li><code>compressed</code> 是有效的压缩，即，每个字符后面都有其出现频率。</li>
	<li>出现频率在&nbsp;<code>[1, 10<sup>4</sup>]</code>&nbsp;之间并且没有前导 0。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 双指针

<!-- thinking:start -->

> **思考**
>
> 压缩串由「字符+十进制次数」拼接，需按字母序合并同字符次数。按段切分后若用有序列表插入，实现繁琐。
>
> 字符只有 $26$ 种，先累加次数再排序输出即可。双指针读完一段数字。
>
> 下标 $i$ 指向字符，$j$ 吞掉后续数字并加入 $cnt$，最后按键排序拼接 $k+v$。

<!-- thinking:end -->

我们可以使用哈希表来统计每个字符的频率，然后使用双指针来遍历 `compressed` 字符串，将每个字符的频率累加到哈希表中，最后按照字母顺序将字符和频率拼接成字符串。

时间复杂度 $O(n + |\Sigma| \log |\Sigma|)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 是字符串 `compressed` 的长度，而 $|\Sigma|$ 是字符集的大小，这里字符集是小写字母，所以 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def betterCompression(self, compressed: str) -> str:
        cnt = Counter()
        i, n = 0, len(compressed)
        while i < n:
            j = i + 1
            x = 0
            while j < n and compressed[j].isdigit():
                x = x * 10 + int(compressed[j])
                j += 1
            cnt[compressed[i]] += x
            i = j
        return "".join(sorted(f"{k}{v}" for k, v in cnt.items()))
```

#### Java

```java
class Solution {
    public String betterCompression(String compressed) {
        Map<Character, Integer> cnt = new TreeMap<>();
        int i = 0;
        int n = compressed.length();
        while (i < n) {
            char c = compressed.charAt(i);
            int j = i + 1;
            int x = 0;
            while (j < n && Character.isDigit(compressed.charAt(j))) {
                x = x * 10 + (compressed.charAt(j) - '0');
                j++;
            }
            cnt.merge(c, x, Integer::sum);
            i = j;
        }
        StringBuilder ans = new StringBuilder();
        for (var e : cnt.entrySet()) {
            ans.append(e.getKey()).append(e.getValue());
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string betterCompression(string compressed) {
        map<char, int> cnt;
        int i = 0;
        int n = compressed.length();
        while (i < n) {
            char c = compressed[i];
            int j = i + 1;
            int x = 0;
            while (j < n && isdigit(compressed[j])) {
                x = x * 10 + (compressed[j] - '0');
                j++;
            }
            cnt[c] += x;
            i = j;
        }
        stringstream ans;
        for (const auto& entry : cnt) {
            ans << entry.first << entry.second;
        }
        return ans.str();
    }
};
```

#### Go

```go
func betterCompression(compressed string) string {
	cnt := map[byte]int{}
	n := len(compressed)
	for i := 0; i < n; {
		c := compressed[i]
		j := i + 1
		x := 0
		for j < n && compressed[j] >= '0' && compressed[j] <= '9' {
			x = x*10 + int(compressed[j]-'0')
			j++
		}
		cnt[c] += x
		i = j
	}
	ans := strings.Builder{}
	for c := byte('a'); c <= byte('z'); c++ {
		if cnt[c] > 0 {
			ans.WriteByte(c)
			ans.WriteString(strconv.Itoa(cnt[c]))
		}
	}
	return ans.String()
}
```

#### TypeScript

```ts
function betterCompression(compressed: string): string {
    const cnt = new Map<string, number>();
    const n = compressed.length;
    let i = 0;

    while (i < n) {
        const c = compressed[i];
        let j = i + 1;
        let x = 0;
        while (j < n && /\d/.test(compressed[j])) {
            x = x * 10 + +compressed[j];
            j++;
        }
        cnt.set(c, (cnt.get(c) || 0) + x);
        i = j;
    }
    const keys = Array.from(cnt.keys()).sort();
    const ans: string[] = [];
    for (const k of keys) {
        ans.push(`${k}${cnt.get(k)}`);
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
