---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0744.Find%20Smallest%20Letter%20Greater%20Than%20Target/README_EN.md
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [744. Find Smallest Letter Greater Than Target](https://leetcode.com/problems/find-smallest-letter-greater-than-target)

[中文文档](/solution/0700-0799/0744.Find%20Smallest%20Letter%20Greater%20Than%20Target/README.md)

## Description

<p>You are given an array of characters <code>letters</code> that is sorted in <strong>non-decreasing order</strong>, and a character <code>target</code>. There are <strong>at least two different</strong> characters in <code>letters</code>.</p>

<p>Return <em>the smallest character in </em><code>letters</code><em> that is lexicographically greater than </em><code>target</code>. If such a character does not exist, return the first character in <code>letters</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> letters = [&quot;c&quot;,&quot;f&quot;,&quot;j&quot;], target = &quot;a&quot;
<strong>Output:</strong> &quot;c&quot;
<strong>Explanation:</strong> The smallest character that is lexicographically greater than &#39;a&#39; in letters is &#39;c&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> letters = [&quot;c&quot;,&quot;f&quot;,&quot;j&quot;], target = &quot;c&quot;
<strong>Output:</strong> &quot;f&quot;
<strong>Explanation:</strong> The smallest character that is lexicographically greater than &#39;c&#39; in letters is &#39;f&#39;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> letters = [&quot;x&quot;,&quot;x&quot;,&quot;y&quot;,&quot;y&quot;], target = &quot;z&quot;
<strong>Output:</strong> &quot;x&quot;
<strong>Explanation:</strong> There are no characters in letters that is lexicographically greater than &#39;z&#39; so we return letters[0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= letters.length &lt;= 10<sup>4</sup></code></li>
	<li><code>letters[i]</code> is a lowercase English letter.</li>
	<li><code>letters</code> is sorted in <strong>non-decreasing</strong> order.</li>
	<li><code>letters</code> contains at least two different characters.</li>
	<li><code>target</code> is a lowercase English letter.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

Since `letters` is sorted in non-decreasing order, we can use binary search to find the smallest character that is larger than `target`.

We define the left boundary of the binary search as $l = 0$, and the right boundary as $r = n$. For each binary search, we calculate the middle position $mid = (l + r) / 2$. If $letters[mid] > \text{target}$, it means we need to continue searching in the left half, so we set $r = mid$. Otherwise, we need to continue searching in the right half, so we set $l = mid + 1$.

Finally, we return $letters[l \mod n]$.

The time complexity is $O(\log n)$, where $n$ is the length of `letters`. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def nextGreatestLetter(self, letters: List[str], target: str) -> str:
        i = bisect_right(letters, ord(target), key=lambda c: ord(c))
        return letters[i % len(letters)]
```

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int i = Arrays.binarySearch(letters, (char) (target + 1));
        i = i < 0 ? -i - 1 : i;
        return letters[i % letters.length];
    }
}
```

```cpp
class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        int i = upper_bound(letters.begin(), letters.end(), target) - letters.begin();
        return letters[i % letters.size()];
    }
};
```

```go
func nextGreatestLetter(letters []byte, target byte) byte {
	i := sort.Search(len(letters), func(i int) bool { return letters[i] > target })
	return letters[i%len(letters)]
}
```

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
