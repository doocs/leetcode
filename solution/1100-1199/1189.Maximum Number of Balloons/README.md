---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1189.Maximum%20Number%20of%20Balloons/README.md
rating: 1181
source: 第 154 场周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [1189. “气球” 的最大数量](https://leetcode.cn/problems/maximum-number-of-balloons)

[English Version](/solution/1100-1199/1189.Maximum%20Number%20of%20Balloons/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>text</code>，你需要使用 <code>text</code> 中的字母来拼凑尽可能多的单词&nbsp;<strong>"balloon"（气球）</strong>。</p>

<p>字符串&nbsp;<code>text</code> 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词&nbsp;<strong>"balloon"</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1189.Maximum%20Number%20of%20Balloons/images/1536_ex1_upd.jpeg" style="height: 35px; width: 154px;" /></strong></p>

<pre>
<strong>输入：</strong>text = "nlaebolko"
<strong>输出：</strong>1
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1189.Maximum%20Number%20of%20Balloons/images/1536_ex2_upd.jpeg" style="height: 35px; width: 233px;" /></strong></p>

<pre>
<strong>输入：</strong>text = "loonbalxballpoon"
<strong>输出：</strong>2
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>text = "leetcode"
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10<sup>4</sup></code></li>
	<li><code>text</code>&nbsp;全部由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与&nbsp;<a href="https://leetcode.cn/problems/rearrange-characters-to-make-target-string/">2287. 重排字符形成目标字符串</a>&nbsp;相同。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们统计字符串 `text` 中每个字母出现的次数，然后将其中字母 `'o'` 和 `'l'` 的出现次数分别除以 2，这是因为单词 `balloon` 中字母 `'o'` 和 `'l'` 都出现了 2 次。

接着，我们遍历单词 `balon` 中的每个字母，统计每个字母在字符串 `text` 中出现的次数的最小值，这个最小值就是单词 `balloon` 在字符串 `text` 中出现的最大次数。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 `text` 的长度；而 $C$ 为字符集大小，本题中 $C = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxNumberOfBalloons(self, text: str) -> int:
        cnt = Counter(text)
        cnt['o'] >>= 1
        cnt['l'] >>= 1
        return min(cnt[c] for c in 'balon')
```

#### Java

```java
class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int[26];
        for (int i = 0; i < text.length(); ++i) {
            ++cnt[text.charAt(i) - 'a'];
        }
        cnt['l' - 'a'] >>= 1;
        cnt['o' - 'a'] >>= 1;
        int ans = 1 << 30;
        for (char c : "balon".toCharArray()) {
            ans = Math.min(ans, cnt[c - 'a']);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxNumberOfBalloons(string text) {
        int cnt[26]{};
        for (char c : text) {
            ++cnt[c - 'a'];
        }
        cnt['o' - 'a'] >>= 1;
        cnt['l' - 'a'] >>= 1;
        int ans = 1 << 30;
        string t = "balon";
        for (char c : t) {
            ans = min(ans, cnt[c - 'a']);
        }
        return ans;
    }
};
```

#### Go

```go
func maxNumberOfBalloons(text string) int {
	cnt := [26]int{}
	for _, c := range text {
		cnt[c-'a']++
	}
	cnt['l'-'a'] >>= 1
	cnt['o'-'a'] >>= 1
	ans := 1 << 30
	for _, c := range "balon" {
		if x := cnt[c-'a']; ans > x {
			ans = x
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxNumberOfBalloons(text: string): number {
    const cnt = new Array(26).fill(0);
    for (const c of text) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    return Math.min(cnt[0], cnt[1], cnt[11] >> 1, cnt[14] >> 1, cnt[13]);
}
```

#### Rust

```rust
impl Solution {
    pub fn max_number_of_balloons(text: String) -> i32 {
        let mut arr = [0; 5];
        for c in text.chars() {
            match c {
                'b' => {
                    arr[0] += 1;
                }
                'a' => {
                    arr[1] += 1;
                }
                'l' => {
                    arr[2] += 1;
                }
                'o' => {
                    arr[3] += 1;
                }
                'n' => {
                    arr[4] += 1;
                }
                _ => {}
            }
        }
        arr[2] /= 2;
        arr[3] /= 2;
        let mut res = i32::MAX;
        for num in arr {
            res = res.min(num);
        }
        res
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $text
     * @return Integer
     */
    function maxNumberOfBalloons($text) {
        $cnt1 = $cnt2 = $cnt3 = $cnt4 = $cnt5 = 0;
        for ($i = 0; $i < strlen($text); $i++) {
            if ($text[$i] == 'b') {
                $cnt1 += 1;
            } elseif ($text[$i] == 'a') {
                $cnt2 += 1;
            } elseif ($text[$i] == 'l') {
                $cnt3 += 1;
            } elseif ($text[$i] == 'o') {
                $cnt4 += 1;
            } elseif ($text[$i] == 'n') {
                $cnt5 += 1;
            }
        }
        $cnt3 = floor($cnt3 / 2);
        $cnt4 = floor($cnt4 / 2);
        return min($cnt1, $cnt2, $cnt3, $cnt4, $cnt5);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
