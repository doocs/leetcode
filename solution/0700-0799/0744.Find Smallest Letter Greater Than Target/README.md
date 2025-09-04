---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0744.Find%20Smallest%20Letter%20Greater%20Than%20Target/README.md
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [744. 寻找比目标字母大的最小字母](https://leetcode.cn/problems/find-smallest-letter-greater-than-target)

[English Version](/solution/0700-0799/0744.Find%20Smallest%20Letter%20Greater%20Than%20Target/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符数组 <code>letters</code>，该数组按<strong>非递减顺序</strong>排序，以及一个字符 <code>target</code>。<code>letters</code>&nbsp;里<strong>至少有两个不同</strong>的字符。</p>

<p>返回&nbsp;<code>letters</code>&nbsp;中大于 <code>target</code> 的最小的字符。如果不存在这样的字符，则返回&nbsp;<code>letters</code> 的第一个字符。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>letters = ['c', 'f', 'j']，target = 'a'
<strong>输出:</strong> 'c'
<strong>解释：</strong>letters 中字典上比 'a' 大的最小字符是 'c'。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> letters = ['c','f','j'], target = 'c'
<strong>输出:</strong> 'f'
<strong>解释：</strong>letters 中字典顺序上大于 'c' 的最小字符是 'f'。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> letters = ['x','x','y','y'], target = 'z'
<strong>输出:</strong> 'x'
<strong>解释：</strong>letters 中没有一个字符在字典上大于 'z'，所以我们返回 letters[0]。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= letters.length &lt;= 10<sup>4</sup></code></li>
	<li><code>letters[i]</code>&nbsp;是一个小写字母</li>
	<li><code>letters</code> 按<strong>非递减顺序</strong>排序</li>
	<li><code>letters</code> 最少包含两个不同的字母</li>
	<li><code>target</code> 是一个小写字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

由于 $\textit{letters}$ 是按照非递减顺序排序的，所以我们可以使用二分查找来找到大于 `target` 的最小字符。

我们定义二分查找的左边界 $l = 0$，右边界 $r = n$。对于每一次二分查找，我们计算中间位置 $mid = (l + r) / 2$，如果 $letters[mid] > \textit{target}$，则说明我们需要在左半部分继续查找，即 $r = mid$；否则我们需要在右半部分继续查找，即 $l = mid + 1$。

最后我们返回 $letters[l \mod n]$ 即可。

时间复杂度 $O(\log n)$，其中 $n$ 是 $\textit{letters}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nextGreatestLetter(self, letters: List[str], target: str) -> str:
        i = bisect_right(letters, ord(target), key=lambda c: ord(c))
        return letters[i % len(letters)]
```

#### Java

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int i = Arrays.binarySearch(letters, (char) (target + 1));
        i = i < 0 ? -i - 1 : i;
        return letters[i % letters.length];
    }
}
```

#### C++

```cpp
class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        int i = upper_bound(letters.begin(), letters.end(), target) - letters.begin();
        return letters[i % letters.size()];
    }
};
```

#### Go

```go
func nextGreatestLetter(letters []byte, target byte) byte {
	i := sort.Search(len(letters), func(i int) bool { return letters[i] > target })
	return letters[i%len(letters)]
}
```

#### TypeScript

```ts
function nextGreatestLetter(letters: string[], target: string): string {
    let [l, r] = [0, letters.length];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (letters[mid] > target) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return letters[l % letters.length];
}
```

#### Rust

```rust
impl Solution {
    pub fn next_greatest_letter(letters: Vec<char>, target: char) -> char {
        let mut l = 0;
        let mut r = letters.len();
        while l < r {
            let mid = l + (r - l) / 2;
            if letters[mid] > target {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        letters[l % letters.len()]
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String[] $letters
     * @param String $target
     * @return String
     */
    function nextGreatestLetter($letters, $target) {
        $l = 0;
        $r = count($letters);
        while ($l < $r) {
            $mid = $l + $r >> 1;
            if ($letters[$mid] > $target) {
                $r = $mid;
            } else {
                $l = $mid + 1;
            }
        }
        return $letters[$l % count($letters)];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
