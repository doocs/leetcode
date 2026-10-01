---
comments: true
difficulty: Medium
tags:
    - Bit Manipulation
    - Hash Table
    - String
    - Sliding Window
    - Hash Function
    - Rolling Hash
    - Boyer–Moore
    - Extended KMP
---

<!-- problem:start -->

# [187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences)

[中文文档](/solution/0100-0199/0187.Repeated%20DNA%20Sequences/README.md)

## Mô tả

<!-- description:start -->

<p><strong>Chuỗi DNA</strong> được tạo thành từ một dãy nucleotide, được viết tắt là <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code> và <code>&#39;T&#39;</code>.</p>

<ul>
	<li>Ví dụ, <code>&quot;ACGAATTCCG&quot;</code> là một <strong>chuỗi DNA</strong>.</li>
</ul>

<p>Khi nghiên cứu <strong>DNA</strong>, việc xác định các chuỗi lặp lại trong DNA là hữu ích.</p>

<p>Cho một chuỗi <code>s</code> biểu diễn một <strong>chuỗi DNA</strong>, hãy trả về tất cả các chuỗi có độ dài <strong><code>10</code> ký tự</strong> (các chuỗi con) xuất hiện nhiều hơn một lần trong một phân tử DNA. Bạn có thể trả về đáp án theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
<strong>Đầu ra:</strong> ["AAAAACCCCC","CCCCCAAAAA"]
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> s = "AAAAAAAAAAAAA"
<strong>Đầu ra:</strong> ["AAAAAAAAAA"]
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> là một trong các ký tự <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code> hoặc <code>&#39;T&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm

<!-- thinking:start -->

> **Tư duy**
>
> Các chuỗi con có độ dài $10$ xuất hiện ít nhất hai lần. Vì $n\le 10^5$, không thể so sánh từng cặp của tất cả các cửa sổ. Trượt qua $s[i..i+9]$, đếm trong một hash map và thêm một chuỗi vào đáp án khi nó xuất hiện lần thứ hai.

<!-- thinking:end -->

Chúng ta định nghĩa một bảng băm $cnt$ để lưu số lần xuất hiện của tất cả các chuỗi con có độ dài $10$.

Chúng ta duyệt qua tất cả các chuỗi con có độ dài $10$ trong chuỗi $s$. Với chuỗi con hiện tại $t$, chúng ta cập nhật số lần xuất hiện của nó trong bảng băm. Nếu số lần xuất hiện của $t$ là $2$, chúng ta thêm nó vào đáp án.

Sau khi duyệt xong, chúng ta trả về mảng đáp án.

Độ phức tạp thời gian là $O(n \times 10)$, còn độ phức tạp không gian là $O(n \times 10)$. Trong đó, $n$ là độ dài của chuỗi $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        cnt = Counter()
        ans = []
        for i in range(len(s) - 10 + 1):
            t = s[i : i + 10]
            cnt[t] += 1
            if cnt[t] == 2:
                ans.append(t)
        return ans
```

#### Java

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> cnt = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < s.length() - 10 + 1; ++i) {
            String t = s.substring(i, i + 10);
            if (cnt.merge(t, 1, Integer::sum) == 2) {
                ans.add(t);
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
    vector<string> findRepeatedDnaSequences(string s) {
        unordered_map<string, int> cnt;
        vector<string> ans;
        for (int i = 0, n = s.size() - 10 + 1; i < n; ++i) {
            auto t = s.substr(i, 10);
            if (++cnt[t] == 2) {
                ans.emplace_back(t);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findRepeatedDnaSequences(s string) (ans []string) {
	cnt := map[string]int{}
	for i := 0; i < len(s)-10+1; i++ {
		t := s[i : i+10]
		cnt[t]++
		if cnt[t] == 2 {
			ans = append(ans, t)
		}
	}
	return
}
```

#### TypeScript

```ts
function findRepeatedDnaSequences(s: string): string[] {
    const n = s.length;
    const cnt: Map<string, number> = new Map();
    const ans: string[] = [];
    for (let i = 0; i <= n - 10; ++i) {
        const t = s.slice(i, i + 10);
        cnt.set(t, (cnt.get(t) ?? 0) + 1);
        if (cnt.get(t) === 2) {
            ans.push(t);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn find_repeated_dna_sequences(s: String) -> Vec<String> {
        if s.len() < 10 {
            return vec![];
        }
        let mut cnt = HashMap::new();
        let mut ans = Vec::new();
        for i in 0..s.len() - 9 {
            let t = &s[i..i + 10];
            let count = cnt.entry(t).or_insert(0);
            *count += 1;
            if *count == 2 {
                ans.push(t.to_string());
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {string[]}
 */
var findRepeatedDnaSequences = function (s) {
    const cnt = new Map();
    const ans = [];
    for (let i = 0; i < s.length - 10 + 1; ++i) {
        const t = s.slice(i, i + 10);
        cnt.set(t, (cnt.get(t) || 0) + 1);
        if (cnt.get(t) === 2) {
            ans.push(t);
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public IList<string> FindRepeatedDnaSequences(string s) {
        var cnt = new Dictionary<string, int>();
        var ans = new List<string>();
        for (int i = 0; i < s.Length - 10 + 1; ++i) {
            var t = s.Substring(i, 10);
            if (!cnt.ContainsKey(t)) {
                cnt[t] = 0;
            }
            if (++cnt[t] == 2) {
                ans.Add(t);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Thuật toán so khớp chuỗi Rabin-Karp

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 băm một lát cắt có độ dài $10$ ở mỗi bước. Bảng chữ cái chỉ có bốn chữ cái, vì vậy cửa sổ có thể được biểu diễn bằng một số nguyên; việc đưa phần tử mới vào và loại phần tử cũ ra khỏi cửa sổ có độ phức tạp $O(1)$ ở mỗi bước, và tổng thời gian là $O(n)$.

<!-- thinking:end -->

Phương pháp này về cơ bản kết hợp cửa sổ trượt và hash. Tương tự như 0028. Find the Index of the First Occurrence in a String, bài toán này có thể sử dụng một hàm băm để giảm độ phức tạp thời gian đếm các dãy con xuống $O(1)$.

Độ phức tạp thời gian là $O(n)$, còn độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của chuỗi $s$.

<!-- tabs:start -->

#### Go

```go
func findRepeatedDnaSequences(s string) []string {
	hashCode := map[byte]int{'A': 0, 'C': 1, 'G': 2, 'T': 3}
	ans, cnt, left, right := []string{}, map[int]int{}, 0, 0

	sha, multi := 0, int(math.Pow(4, 9))
	for ; right < len(s); right++ {
		sha = sha*4 + hashCode[s[right]]
		if right-left+1 < 10 {
			continue
		}
		cnt[sha]++
		if cnt[sha] == 2 {
			ans = append(ans, s[left:right+1])
		}
		sha, left = sha-multi*hashCode[s[left]], left+1
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
