---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3085.Minimum%20Deletions%20to%20Make%20String%20K-Special/README.md
rating: 1764
tags:
    - 贪心
    - 哈希表
    - 字符串
    - 计数
    - 排序
---

# [3085. 成为 K 特殊字符串需要删除的最少字符数](https://leetcode.cn/problems/minimum-deletions-to-make-string-k-special)

[English Version](/solution/3000-3099/3085.Minimum%20Deletions%20to%20Make%20String%20K-Special/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>word</code> 和一个整数 <code>k</code>。</p>

<p>如果&nbsp;<code>|freq(word[i]) - freq(word[j])| &lt;= k</code> 对于字符串中所有下标 <code>i</code> 和 <code>j</code>&nbsp; 都成立，则认为 <code>word</code> 是 <strong>k 特殊字符串</strong>。</p>

<p>此处，<code>freq(x)</code> 表示字符 <code>x</code> 在 <code>word</code> 中的<span data-keyword="frequency-letter">出现频率</span>，而 <code>|y|</code> 表示 <code>y</code> 的绝对值。</p>

<p>返回使 <code>word</code> 成为 <strong>k 特殊字符串</strong> 需要删除的字符的最小数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">word = "aabcaba", k = 0</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">3</span></p>

<p><strong>解释：</strong>可以删除 <code>2</code> 个 <code>"a"</code> 和 <code>1</code> 个 <code>"c"</code> 使 <code>word</code> 成为 <code>0</code> 特殊字符串。<code>word</code> 变为 <code>"baba"</code>，此时 <code>freq('a') == freq('b') == 2</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">word = "dabdcbdcdcd", k = 2</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">2</span></p>

<p><strong>解释：</strong>可以删除 <code>1</code> 个 <code>"a"</code> 和 <code>1</code> 个 <code>"d"</code> 使 <code>word</code> 成为 <code>2</code> 特殊字符串。<code>word</code> 变为 <code>"bdcbdcdcd"</code>，此时 <code>freq('b') == 2</code>，<code>freq('c') == 3</code>，<code>freq('d') == 4</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">word = "aaabaaa", k = 2</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">1</span></p>

<p><strong>解释：</strong>可以删除<strong> </strong>1 个 <code>"b"</code> 使 <code>word</code> 成为 <code>2</code>特殊字符串。因此，<code>word</code> 变为 <code>"aaaaaa"</code>，此时每个字母的频率都是 <code>6</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code> 仅由小写英文字母组成。</li>
</ul>

## 解法

### 方法一：计数 + 枚举

我们可以先统计字符串中每个字符的出现次数，将所有次数放入数组 $nums$ 中，由于题目中字符串只包含小写字母，所以数组 $nums$ 的长度不会超过 $26$。

接下来，我们可以枚举在 $[0,..n]$ 的范围内枚举 $K$ 特殊字符串中的字符最小频率 $v$，然后用一个函数 $f(v)$ 来计算将所有字符的频率调整为 $v$ 的最小删除次数，最后取所有 $f(v)$ 的最小值即为答案。

函数 $f(v)$ 的计算方法如下：

遍历数组 $nums$ 中的每个元素 $x$，如果 $x \lt v$，则说明我们需要将出现次数为 $x$ 的字符全部删除，删除次数为 $x$；如果 $x \gt v + k$，则说明我们需要将出现次数为 $x$ 的字符全部调整为 $v + k$，删除次数为 $x - v - k$。累加所有删除次数即为 $f(v)$ 的值。

时间复杂度 $O(n \times |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 为字符串长度；而 $|\Sigma|$ 为字符集大小，本题中 $|\Sigma| = 26$。

<!-- tabs:start -->

```python
class Solution:
    def minimumDeletions(self, word: str, k: int) -> int:
        def f(v: int) -> int:
            ans = 0
            for x in nums:
                if x < v:
                    ans += x
                elif x > v + k:
                    ans += x - v - k
            return ans

        nums = Counter(word).values()
        return min(f(v) for v in range(len(word) + 1))
```

```java
class Solution {
    private List<Integer> nums = new ArrayList<>();

    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            ++freq[word.charAt(i) - 'a'];
        }
        for (int v : freq) {
            if (v > 0) {
                nums.add(v);
            }
        }
        int ans = n;
        for (int i = 0; i <= n; ++i) {
            ans = Math.min(ans, f(i, k));
        }
        return ans;
    }

    private int f(int v, int k) {
        int ans = 0;
        for (int x : nums) {
            if (x < v) {
                ans += x;
            } else if (x > v + k) {
                ans += x - v - k;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumDeletions(string word, int k) {
        int freq[26]{};
        for (char& c : word) {
            ++freq[c - 'a'];
        }
        vector<int> nums;
        for (int v : freq) {
            if (v) {
                nums.push_back(v);
            }
        }
        int n = word.size();
        int ans = n;
        auto f = [&](int v) {
            int ans = 0;
            for (int x : nums) {
                if (x < v) {
                    ans += x;
                } else if (x > v + k) {
                    ans += x - v - k;
                }
            }
            return ans;
        };
        for (int i = 0; i <= n; ++i) {
            ans = min(ans, f(i));
        }
        return ans;
    }
};
```

```go
func minimumDeletions(word string, k int) int {
	freq := [26]int{}
	for _, c := range word {
		freq[c-'a']++
	}
	nums := []int{}
	for _, v := range freq {
		if v > 0 {
			nums = append(nums, v)
		}
	}
	f := func(v int) int {
		ans := 0
		for _, x := range nums {
			if x < v {
				ans += x
			} else if x > v+k {
				ans += x - v - k
			}
		}
		return ans
	}
	ans := len(word)
	for i := 0; i <= len(word); i++ {
		ans = min(ans, f(i))
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
