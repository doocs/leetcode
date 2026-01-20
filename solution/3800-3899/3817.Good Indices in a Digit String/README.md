---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3817.Good%20Indices%20in%20a%20Digit%20String/README.md
---

<!-- problem:start -->

# [3817. Good Indices in a Digit String 🔒](https://leetcode.cn/problems/good-indices-in-a-digit-string)

[English Version](/solution/3800-3899/3817.Good%20Indices%20in%20a%20Digit%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of digits.</p>

<p>An index <code>i</code> is called <strong>good</strong> if there exists a <span data-keyword="substring-nonempty">substring</span> of <code>s</code> that ends at index <code>i</code> and is equal to the decimal representation of <code>i</code>.</p>

<p>Return an integer array of all good indices in <strong>increasing order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0234567890112&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,11,12]</span></p>

<p><strong>Explanation:​​​​​​​</strong></p>

<ul>
	<li>
	<p>At index 0, the decimal representation of the index is <code>&quot;0&quot;</code>. The substring <code>s[0]</code> is <code>&quot;0&quot;</code>, which matches, so index <code>0</code> is good.</p>
	</li>
	<li>
	<p>At index 11, the decimal representation is <code>&quot;11&quot;</code>. The substring <code>s[10..11]</code> is <code>&quot;11&quot;</code>, which matches, so index <code>11</code> is good.</p>
	</li>
	<li>
	<p>At index 12, the decimal representation is <code>&quot;12&quot;</code>. The substring <code>s[11..12]</code> is <code>&quot;12&quot;</code>, which matches, so index <code>12</code> is good.</p>
	</li>
</ul>

<p>No other index has a substring ending at it that equals its decimal representation. Therefore, the answer is <code>[0, 11, 12]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;01234&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1,2,3,4]</span></p>

<p><strong>Explanation:</strong></p>

<p>For every index <code>i</code> from 0 to 4, the decimal representation of <code>i</code> is a single digit, and the substring <code>s[i]</code> matches that digit.</p>

<p>Therefore, a valid substring ending at each index exists, making all indices good.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;12345&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>No index has a substring ending at it that matches its decimal representation.</p>

<p>Therefore, there are no good indices and the result is an empty array.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> only consists of digits from <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code>.</li>
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
