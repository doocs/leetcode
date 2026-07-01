---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0443.String%20Compression/README.md
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [443. 压缩字符串](https://leetcode.cn/problems/string-compression)

[English Version](/solution/0400-0499/0443.String%20Compression/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符数组 <code>chars</code> ，请使用下述算法压缩：</p>

<p>从一个空字符串 <code>s</code> 开始。对于 <code>chars</code> 中的每组 <strong>连续重复字符</strong> ：</p>

<ul>
	<li>如果这一组长度为 <code>1</code> ，则将字符追加到 <code>s</code> 中。</li>
	<li>否则，需要向 <code>s</code> 追加字符，后跟这一组的长度。</li>
</ul>

<p>压缩后得到的字符串 <code>s</code> <strong>不应该直接返回</strong> ，需要转储到字符数组 <code>chars</code> 中。需要注意的是，如果组长度为 <code>10</code> 或 <code>10</code> 以上，则在 <code>chars</code> 数组中会被拆分为多个字符。</p>

<p>请在 <strong>修改完输入数组后</strong> ，返回该数组的新长度。</p>

<p>你必须设计并实现一个只使用常量额外空间的算法来解决此问题。</p>

<p><strong>注意：</strong>数组中超出返回长度的字符无关紧要，应予忽略。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>chars = ["a","a","b","b","c","c","c"]
<strong>输出：</strong>6
<strong>解释：</strong>分组是 "aa"、"bb" 和 "ccc"，压缩为 "a2b2c3"。
在原地修改输入数组之后，chars 的前 6 个字符应为 ["a","2","b","2","c","3"]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>chars = ["a"]
<strong>输出：</strong>1
<strong>解释：</strong>唯一的组是 "a"，因为它是一个单独的字符，所以保持不变。
在原地修改输入数组后，chars 的第一个字符应为 ["a"]。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
<strong>输出：</strong>4
<strong>解释：</strong>分组是 "a" 和 "bbbbbbbbbbbb"，压缩为"ab12"。
在对输入数组进行原地修改后，chars 的前 4 个字符应为 ["a","b","1","2"]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= chars.length &lt;= 2000</code></li>
	<li><code>chars[i]</code> 可以是小写英文字母、大写英文字母、数字或符号</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def compress(self, chars: List[str]) -> int:
        i, k, n = 0, 0, len(chars)
        while i < n:
            j = i + 1
            while j < n and chars[j] == chars[i]:
                j += 1
            chars[k] = chars[i]
            k += 1
            if j - i > 1:
                cnt = str(j - i)
                for c in cnt:
                    chars[k] = c
                    k += 1
            i = j
        return k
```

#### Java

```java
class Solution {
    public int compress(char[] chars) {
        int k = 0, n = chars.length;
        for (int i = 0, j = i + 1; i < n;) {
            while (j < n && chars[j] == chars[i]) {
                ++j;
            }
            chars[k++] = chars[i];
            if (j - i > 1) {
                String cnt = String.valueOf(j - i);
                for (char c : cnt.toCharArray()) {
                    chars[k++] = c;
                }
            }
            i = j;
        }
        return k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int compress(vector<char>& chars) {
        int k = 0, n = chars.size();
        for (int i = 0, j = i + 1; i < n;) {
            while (j < n && chars[j] == chars[i])
                ++j;
            chars[k++] = chars[i];
            if (j - i > 1) {
                for (char c : to_string(j - i)) {
                    chars[k++] = c;
                }
            }
            i = j;
        }
        return k;
    }
};
```

#### Go

```go
func compress(chars []byte) int {
	i, k, n := 0, 0, len(chars)
	for i < n {
		j := i + 1
		for j < n && chars[j] == chars[i] {
			j++
		}
		chars[k] = chars[i]
		k++
		if j-i > 1 {
			cnt := strconv.Itoa(j - i)
			for _, c := range cnt {
				chars[k] = byte(c)
				k++
			}
		}
		i = j
	}
	return k
}
```

#### Rust

```rust
impl Solution {
    pub fn compress(chars: &mut Vec<char>) -> i32 {
        let (mut i, mut k, n) = (0, 0, chars.len());
        while i < n {
            let mut j = i + 1;
            while j < n && chars[j] == chars[i] {
                j += 1;
            }
            chars[k] = chars[i];
            k += 1;

            if j - i > 1 {
                let cnt = (j - i).to_string();
                for c in cnt.chars() {
                    chars[k] = c;
                    k += 1;
                }
            }
            i = j;
        }
        k as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
