---
comments: true
difficulty: Medium
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters)

[中文文档](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>s</code>, hãy tìm độ dài của <strong>chuỗi</strong> <span data-keyword="substring-nonempty"><strong>con dài nhất</strong></span> không chứa ký tự trùng lặp.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;abcabcbb&quot;
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> Đáp án là &quot;abc&quot;, với độ dài là 3. Lưu ý rằng <code>&quot;bca&quot;</code> và <code>&quot;cab&quot;</code> cũng là các đáp án đúng.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;bbbbb&quot;
<strong>Đầu ra:</strong> 1
<strong>Giải thích:</strong> Đáp án là &quot;b&quot;, với độ dài là 1.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;pwwkew&quot;
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> Đáp án là &quot;wke&quot;, với độ dài là 3.
Lưu ý rằng đáp án phải là một chuỗi con; &quot;pwke&quot; là một dãy con chứ không phải chuỗi con.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> chỉ gồm các chữ cái tiếng Anh, chữ số, ký hiệu và khoảng trắng.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Cửa sổ trượt

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là thử mọi chuỗi con và kiểm tra tính duy nhất. Cách này đúng, nhưng tốn ít nhất $O(n^2)$. Với $n \le 10^5$, chương trình sẽ bị quá thời gian.
>
> Phần lãng phí nằm ở sự chồng lấn: một khi $[l,r]$ không có ký tự lặp, việc thêm ký tự $c$ vào bên phải chỉ cần kiểm tra xem $c$ có làm hỏng cửa sổ hay không, chứ không cần quét lại toàn bộ.
>
> Một ký tự trùng lặp nghĩa là đáp án hợp lệ không thể giữ thêm $c$ ở phía bên trái, nên $l$ phải dịch sang phải. Một bảng đếm cho biết liệu $\textit{cnt}[c] > 1$ có đủ để quyết định việc thu hẹp hay không; sau khi $c$ chỉ xuất hiện một lần, cửa sổ là chuỗi con dài nhất không lặp kết thúc tại $r$.

<!-- thinking:end -->

Chúng ta có thể sử dụng hai con trỏ $l$ và $r$ để duy trì một cửa sổ trượt luôn thỏa mãn điều kiện không có ký tự lặp trong cửa sổ. Ban đầu, cả $l$ và $r$ đều trỏ tới ký tự đầu tiên của chuỗi. Chúng ta sử dụng một bảng băm hoặc một mảng có độ dài $128$, gọi là $\textit{cnt}$, để ghi lại số lần xuất hiện của mỗi ký tự, trong đó $\textit{cnt}[c]$ biểu thị số lần xuất hiện của ký tự $c$.

Tiếp theo, chúng ta di chuyển con trỏ phải $r$ từng bước một. Mỗi lần di chuyển, chúng ta tăng giá trị của $\textit{cnt}[s[r]]$ lên $1$, sau đó kiểm tra xem giá trị của $\textit{cnt}[s[r]]$ trong cửa sổ hiện tại $[l, r]$ có lớn hơn $1$ hay không. Nếu lớn hơn $1$, nghĩa là cửa sổ hiện tại có ký tự lặp và chúng ta cần di chuyển con trỏ trái $l$ cho đến khi cửa sổ không còn ký tự lặp. Sau đó, chúng ta cập nhật đáp án $\textit{ans} = \max(\textit{ans}, r - l + 1)$.

Cuối cùng, chúng ta trả về đáp án $\textit{ans}$.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của chuỗi. Độ phức tạp không gian là $O(|\Sigma|)$, trong đó $\Sigma$ biểu diễn tập ký tự và kích thước của $\Sigma$ là $128$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        cnt = Counter()
        ans = l = 0
        for r, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 1:
                cnt[s[l]] -= 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] cnt = new int[128];
        int ans = 0, n = s.length();
        for (int l = 0, r = 0; r < n; ++r) {
            char c = s.charAt(r);
            ++cnt[c];
            while (cnt[c] > 1) {
                --cnt[s.charAt(l++)];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int cnt[128]{};
        int ans = 0, n = s.size();
        for (int l = 0, r = 0; r < n; ++r) {
            ++cnt[s[r]];
            while (cnt[s[r]] > 1) {
                --cnt[s[l++]];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func lengthOfLongestSubstring(s string) (ans int) {
	cnt := [128]int{}
	l := 0
	for r, c := range s {
		cnt[c]++
		for cnt[c] > 1 {
			cnt[s[l]]--
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
```

#### TypeScript

```ts
function lengthOfLongestSubstring(s: string): number {
    let ans = 0;
    const cnt = new Map<string, number>();
    const n = s.length;
    for (let l = 0, r = 0; r < n; ++r) {
        cnt.set(s[r], (cnt.get(s[r]) || 0) + 1);
        while (cnt.get(s[r])! > 1) {
            cnt.set(s[l], cnt.get(s[l])! - 1);
            ++l;
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let mut cnt = [0; 128];
        let mut ans = 0;
        let mut l = 0;
        let chars: Vec<char> = s.chars().collect();
        let n = chars.len();
        for (r, &c) in chars.iter().enumerate() {
            cnt[c as usize] += 1;
            while cnt[c as usize] > 1 {
                cnt[chars[l] as usize] -= 1;
                l += 1;
            }
            ans = ans.max((r - l + 1) as i32);
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
    let ans = 0;
    const n = s.length;
    const cnt = new Map();
    for (let l = 0, r = 0; r < n; ++r) {
        cnt.set(s[r], (cnt.get(s[r]) || 0) + 1);
        while (cnt.get(s[r]) > 1) {
            cnt.set(s[l], cnt.get(s[l]) - 1);
            ++l;
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int LengthOfLongestSubstring(string s) {
        int n = s.Length;
        int ans = 0;
        var cnt = new int[128];
        for (int l = 0, r = 0; r < n; ++r) {
            ++cnt[s[r]];
            while (cnt[s[r]] > 1) {
                --cnt[s[l++]];
            }
            ans = Math.Max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    function lengthOfLongestSubstring($s) {
        $n = strlen($s);
        $ans = 0;
        $cnt = array_fill(0, 128, 0);
        $l = 0;
        for ($r = 0; $r < $n; ++$r) {
            $cnt[ord($s[$r])]++;
            while ($cnt[ord($s[$r])] > 1) {
                $cnt[ord($s[$l])]--;
                $l++;
            }
            $ans = max($ans, $r - $l + 1);
        }
        return $ans;
    }
}
```

#### Swift

```swift
class Solution {
    func lengthOfLongestSubstring(_ s: String) -> Int {
        let n = s.count
        var ans = 0
        var cnt = [Int](repeating: 0, count: 128)
        var l = 0
        let sArray = Array(s)
        for r in 0..<n {
            cnt[Int(sArray[r].asciiValue!)] += 1
            while cnt[Int(sArray[r].asciiValue!)] > 1 {
                cnt[Int(sArray[l].asciiValue!)] -= 1
                l += 1
            }
            ans = max(ans, r - l + 1)
        }
        return ans
    }
}
```

#### Kotlin

```kotlin
class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        val n = s.length
        var ans = 0
        val cnt = IntArray(128)
        var l = 0
        for (r in 0 until n) {
            cnt[s[r].toInt()]++
            while (cnt[s[r].toInt()] > 1) {
                cnt[s[l].toInt()]--
                l++
            }
            ans = Math.max(ans, r - l + 1)
        }
        return ans
    }
}
```

#### C

```c
int lengthOfLongestSubstring(char* s) {
    int freq[256] = {0};
    int l = 0, r = 0;
    int ans = 0;
    int len = strlen(s);

    for (r = 0; r < len; r++) {
        char c = s[r];
        freq[(unsigned char) c]++;

        while (freq[(unsigned char) c] > 1) {
            freq[(unsigned char) s[l]]--;
            l++;
        }

        if (ans < r - l + 1) {
            ans = r - l + 1;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
