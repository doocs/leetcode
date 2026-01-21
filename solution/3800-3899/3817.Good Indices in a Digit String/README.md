---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3817.Good%20Indices%20in%20a%20Digit%20String/README.md
---

<!-- problem:start -->

# [3817. 数字字符串中的好索引 🔒](https://leetcode.cn/problems/good-indices-in-a-digit-string)

[English Version](/solution/3800-3899/3817.Good%20Indices%20in%20a%20Digit%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个由数字组成的字符串&nbsp;<code>s</code>。</p>

<p>如果存在一个 <span data-keyword="substring-nonempty">子串</span>，它以索引 <code>i</code> 结尾并且等于 <code>i</code> 的十进制表示，则称索引 <code>i</code> 为好索引。</p>

<p>返回一个包含所有好索引的整数数组，并按 <strong>升序排列</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "0234567890112"</span></p>

<p><span class="example-io"><b>输出：</b>[0,11,12]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>
	<p>在索引 0 处，索引的十进制表示为 <code>"0"</code>。子串 <code>s[0]</code> 是 <code>"0"</code>，匹配成功，所以索引 <code>0</code> 是好的。</p>
	</li>
	<li>
	<p>在索引 11 处，索引的十进制表示是&nbsp;<code>"11"</code>。子串&nbsp;<code>s[10..11]</code>&nbsp;是&nbsp;<code>"11"</code>，匹配成功，所以索引&nbsp;<code>11</code>&nbsp;是好的。</p>
	</li>
	<li>
	<p>在索引 12&nbsp;处，索引的十进制表示是 <code>"12"</code>。子串&nbsp;<code>s[11..12]</code>&nbsp;是&nbsp;<code>"12"</code>，匹配成功，所以索引&nbsp;<code>12</code>&nbsp;是好的。</p>
	</li>
</ul>

<p>没有其他索引在其结束处有一个子串等于其十进制表示。因此，答案是&nbsp;<code>[0, 11, 12]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "01234"</span></p>

<p><span class="example-io"><b>输出：</b>[0,1,2,3,4]</span></p>

<p><strong>解释：</strong></p>

<p>对于 0 到 4 的每个索引&nbsp;<code>i</code>，<code>i</code>&nbsp;的十进制表示都是单个数字，并且子串&nbsp;<code>s[i]</code>&nbsp;匹配这个数字。</p>

<p>因此，每个索引都存在一个有效的子字符串，使得所有索引都是好的。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "12345"</span></p>

<p><span class="example-io"><b>输出：</b>[]</span></p>

<p><strong>解释：</strong></p>

<p>没有索引的子字符串以它结尾并与它的十进制表示匹配。</p>

<p>因此，没有好索引，结果是一个空数组。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含&nbsp;<code>'0'</code> 到&nbsp;<code>'9'</code>&nbsp;的数字。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们注意到，字符串 $s$ 的长度最大为 $10^5$，而索引 $i$ 的十进制表示的长度最多为 $6$（因为 $10^5$ 的十进制表示为 $100000$，长度为 $6$）。因此，我们只需要检查每个索引 $i$ 的十进制表示对应的子串是否与之相等。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def goodIndices(self, s: str) -> List[int]:
        ans = []
        for i in range(len(s)):
            t = str(i)
            k = len(t)
            if s[i + 1 - k : i + 1] == t:
                ans.append(i)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> goodIndices(String s) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            String t = String.valueOf(i);
            int k = t.length();
            if (s.substring(i + 1 - k, i + 1).equals(t)) {
                ans.add(i);
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
    vector<int> goodIndices(string s) {
        vector<int> ans;
        for (int i = 0; i < s.size(); i++) {
            string t = to_string(i);
            int k = t.size();
            if (s.substr(i + 1 - k, k) == t) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func goodIndices(s string) (ans []int) {
	for i := range s {
		t := strconv.Itoa(i)
		k := len(t)
		if s[i+1-k:i+1] == t {
			ans = append(ans, i)
		}
	}
	return
}
```

#### TypeScript

```ts
function goodIndices(s: string): number[] {
    const ans: number[] = [];
    for (let i = 0; i < s.length; i++) {
        const t = String(i);
        const k = t.length;
        if (s.slice(i + 1 - k, i + 1) === t) {
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
