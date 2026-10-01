---
comments: true
difficulty: Easy
tags:
    - Trie
    - Array
    - String
---

<!-- problem:start -->

# [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix)

[中文文档](/solution/0000-0099/0014.Longest%20Common%20Prefix/README.md)

## Mô tả

<!-- description:start -->

<p>Viết một hàm để tìm chuỗi tiền tố chung dài nhất trong một mảng các chuỗi.</p>

<p>Nếu không có tiền tố chung, trả về chuỗi rỗng <code>&quot;&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> strs = [&quot;flower&quot;,&quot;flow&quot;,&quot;flight&quot;]
<strong>Đầu ra:</strong> &quot;fl&quot;
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> strs = [&quot;dog&quot;,&quot;racecar&quot;,&quot;car&quot;]
<strong>Đầu ra:</strong> &quot;&quot;
<strong>Giải thích:</strong> Không có tiền tố chung giữa các chuỗi đầu vào.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 200</code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 200</code></li>
	<li><code>strs[i]</code> chỉ gồm các chữ cái tiếng Anh viết thường nếu không rỗng.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: So sánh ký tự

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là lấy chuỗi đầu tiên làm tiền tố rồi thu ngắn nó theo từng chuỗi phía sau. $n,m\le 200$, vì vậy ngay cả vòng lặp ba lớp cũng sẽ đáp ứng được. Trie cũng dùng được, nhưng nặng hơn mức cần thiết cho bài toán này.
>
> Tiền tố chỉ có thể ngắn đi: một khi một cột không khớp, không thể tồn tại tiền tố dài hơn. Căn chỉnh các chuỗi theo chiều dọc; vị trí $i$ chỉ có thể mở rộng tiền tố khi mọi chuỗi đều khớp tại đó.
>
> Vì vậy, chúng ta so sánh từng cột với $strs[0]$ và trả về ngay khi một chuỗi quá ngắn hoặc một ký tự khác nhau. Không cần trie.

<!-- thinking:end -->

Chúng ta sử dụng chuỗi đầu tiên $strs[0]$ làm chuẩn và so sánh xem ký tự thứ $i$ của các chuỗi tiếp theo có giống ký tự thứ $i$ của $strs[0]$ hay không. Nếu giống nhau, chúng ta tiếp tục so sánh ký tự tiếp theo. Nếu không, chúng ta trả về $i$ ký tự đầu tiên của $strs[0]$.

Nếu quá trình duyệt kết thúc, điều đó có nghĩa là $i$ ký tự đầu tiên của tất cả các chuỗi đều giống nhau, và chúng ta trả về $strs[0]$.

Độ phức tạp thời gian là $O(n \times m)$, trong đó $n$ và $m$ lần lượt là độ dài của mảng chuỗi và độ dài nhỏ nhất của các chuỗi. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        for i in range(len(strs[0])):
            for s in strs[1:]:
                if len(s) <= i or s[i] != strs[0][i]:
                    return s[:i]
        return strs[0]
```

#### Java

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        for (int i = 0; i < strs[0].length(); ++i) {
            for (int j = 1; j < n; ++j) {
                if (strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        int n = strs.size();
        for (int i = 0; i < strs[0].size(); ++i) {
            for (int j = 1; j < n; ++j) {
                if (strs[j].size() <= i || strs[j][i] != strs[0][i]) {
                    return strs[0].substr(0, i);
                }
            }
        }
        return strs[0];
    }
};
```

#### Go

```go
func longestCommonPrefix(strs []string) string {
	n := len(strs)
	for i := range strs[0] {
		for j := 1; j < n; j++ {
			if len(strs[j]) <= i || strs[j][i] != strs[0][i] {
				return strs[0][:i]
			}
		}
	}
	return strs[0]
}
```

#### TypeScript

```ts
function longestCommonPrefix(strs: string[]): string {
    const len = strs.reduce((r, s) => Math.min(r, s.length), Infinity);
    for (let i = len; i > 0; i--) {
        const target = strs[0].slice(0, i);
        if (strs.every(s => s.slice(0, i) === target)) {
            return target;
        }
    }
    return '';
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_common_prefix(strs: Vec<String>) -> String {
        let mut len = strs.iter().map(|s| s.len()).min().unwrap();
        for i in (1..=len).rev() {
            let mut is_equal = true;
            let target = strs[0][0..i].to_string();
            if strs.iter().all(|s| target == s[0..i]) {
                return target;
            }
        }
        String::new()
    }
}
```

#### JavaScript

```js
/**
 * @param {string[]} strs
 * @return {string}
 */
var longestCommonPrefix = function (strs) {
    for (let j = 0; j < strs[0].length; j++) {
        for (let i = 0; i < strs.length; i++) {
            if (strs[0][j] !== strs[i][j]) {
                return strs[0].substring(0, j);
            }
        }
    }
    return strs[0];
};
```

#### C#

```cs
public class Solution {
    public string LongestCommonPrefix(string[] strs) {
        int n = strs.Length;
        for (int i = 0; i < strs[0].Length; ++i) {
            for (int j = 1; j < n; ++j) {
                if (i >= strs[j].Length || strs[j][i] != strs[0][i]) {
                    return strs[0].Substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String[] $strs
     * @return String
     */
    function longestCommonPrefix($strs) {
        $rs = '';
        for ($i = 0; $i < strlen($strs[0]); $i++) {
            for ($j = 1; $j < count($strs); $j++) {
                if ($strs[0][$i] != $strs[$j][$i]) {
                    return $rs;
                }
            }
            $rs = $rs . $strs[0][$i];
        }
        return $rs;
    }
}
```

#### Ruby

```rb
# @param {String[]} strs
# @return {String}
def longest_common_prefix(strs)
  return '' if strs.nil? || strs.length.zero?

  return strs[0] if strs.length == 1

  idx = 0
  while idx < strs[0].length
    cur_char = strs[0][idx]

    str_idx = 1
    while str_idx < strs.length
      return idx > 0 ? strs[0][0..idx-1] : '' if strs[str_idx].length <= idx

      return '' if strs[str_idx][idx] != cur_char && idx.zero?
      return strs[0][0..idx - 1] if strs[str_idx][idx] != cur_char
      str_idx += 1
    end

    idx += 1
  end

  idx > 0 ? strs[0][0..idx] : ''
end
```

#### C

```c
char* longestCommonPrefix(char** strs, int strsSize) {
    for (int i = 0; strs[0][i]; i++) {
        for (int j = 1; j < strsSize; j++) {
            if (strs[j][i] != strs[0][i]) {
                strs[0][i] = '\0';
                return strs[0];
            }
        }
    }
    return strs[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
