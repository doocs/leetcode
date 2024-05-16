---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3014.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20I/README.md
rating: 1324
source: 第 381 场周赛 Q1
tags:
    - 贪心
    - 数学
    - 字符串
---

# [3014. 输入单词需要的最少按键次数 I](https://leetcode.cn/problems/minimum-number-of-pushes-to-type-word-i)

[English Version](/solution/3000-3099/3014.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>word</code>，由 <strong>不同 </strong>小写英文字母组成。</p>

<p>电话键盘上的按键与 <strong>不同 </strong>小写英文字母集合相映射，可以通过按压按键来组成单词。例如，按键 <code>2</code> 对应 <code>["a","b","c"]</code>，我们需要按一次键来输入 <code>"a"</code>，按两次键来输入 <code>"b"</code>，按三次键来输入 <code>"c"</code><em>。</em></p>

<p>现在允许你将编号为 <code>2</code> 到 <code>9</code> 的按键重新映射到 <strong>不同 </strong>字母集合。每个按键可以映射到<strong> 任意数量 </strong>的字母，但每个字母 <strong>必须</strong> <strong>恰好</strong> 映射到 <strong>一个 </strong>按键上。你需要找到输入字符串 <code>word</code> 所需的<strong> 最少 </strong>按键次数。</p>

<p>返回重新映射按键后输入 <code>word</code> 所需的 <strong>最少 </strong>按键次数。</p>

<p>下面给出了一种电话键盘上字母到按键的映射作为示例。注意 <code>1</code>，<code>*</code>，<code>#</code> 和 <code>0</code> <strong>不</strong> 对应任何字母。</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3014.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20I/images/keypaddesc.png" style="width: 329px; height: 313px;" />
<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3014.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20I/images/keypadv1e1.png" style="width: 329px; height: 313px;" />
<pre>
<strong>输入：</strong>word = "abcde"
<strong>输出：</strong>5
<strong>解释：</strong>图片中给出的重新映射方案的输入成本最小。
"a" -&gt; 在按键 2 上按一次
"b" -&gt; 在按键 3 上按一次
"c" -&gt; 在按键 4 上按一次
"d" -&gt; 在按键 5 上按一次
"e" -&gt; 在按键 6 上按一次
总成本为 1 + 1 + 1 + 1 + 1 = 5 。
可以证明不存在其他成本更低的映射方案。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3014.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20I/images/keypadv1e2.png" style="width: 329px; height: 313px;" />
<pre>
<strong>输入：</strong>word = "xycdefghij"
<strong>输出：</strong>12
<strong>解释：</strong>图片中给出的重新映射方案的输入成本最小。
"x" -&gt; 在按键 2 上按一次
"y" -&gt; 在按键 2 上按两次
"c" -&gt; 在按键 3 上按一次
"d" -&gt; 在按键 3 上按两次
"e" -&gt; 在按键 4 上按一次
"f" -&gt; 在按键 5 上按一次
"g" -&gt; 在按键 6 上按一次
"h" -&gt; 在按键 7 上按一次
"i" -&gt; 在按键 8 上按一次
"j" -&gt; 在按键 9 上按一次
总成本为 1 + 2 + 1 + 2 + 1 + 1 + 1 + 1 + 1 + 1 = 12 。
可以证明不存在其他成本更低的映射方案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 26</code></li>
	<li><code>word</code> 仅由小写英文字母组成。</li>
	<li><code>word</code> 中的所有字母互不相同。</li>
</ul>

## 解法

### 方法一：贪心

我们注意到，字符串 $word$ 中的所有字母都是不同的，因此，我们贪心地将字母均匀地分配到 $8$ 个按键上，即可使得按键次数最少。

时间复杂度 $O(n / 8)$，其中 $n$ 是字符串 $word$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumPushes(self, word: str) -> int:
        n = len(word)
        ans, k = 0, 1
        for _ in range(n // 8):
            ans += k * 8
            k += 1
        ans += k * (n % 8)
        return ans
```

```java
class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int ans = 0, k = 1;
        for (int i = 0; i < n / 8; ++i) {
            ans += k * 8;
            ++k;
        }
        ans += k * (n % 8);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumPushes(string word) {
        int n = word.size();
        int ans = 0, k = 1;
        for (int i = 0; i < n / 8; ++i) {
            ans += k * 8;
            ++k;
        }
        ans += k * (n % 8);
        return ans;
    }
};
```

```go
func minimumPushes(word string) (ans int) {
	n := len(word)
	k := 1
	for i := 0; i < n/8; i++ {
		ans += k * 8
		k++
	}
	ans += k * (n % 8)
	return
}
```

```ts
function minimumPushes(word: string): number {
    const n = word.length;
    let ans = 0;
    let k = 1;
    for (let i = 0; i < ((n / 8) | 0); ++i) {
        ans += k * 8;
        ++k;
    }
    ans += k * (n % 8);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
