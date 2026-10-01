---
comments: true
difficulty: Hard
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring)

[中文文档](/solution/0000-0099/0076.Minimum%20Window%20Substring/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai chuỗi <code>s</code> và <code>t</code> có độ dài lần lượt là <code>m</code> và <code>n</code>, hãy trả về <em><strong>cửa sổ tối thiểu</strong></em> <span data-keyword="substring-nonempty"><strong><em>chuỗi con</em></strong></span><em> của </em><code>s</code><em> sao cho mọi ký tự trong </em><code>t</code><em> (<strong>bao gồm cả các ký tự trùng lặp</strong>) đều được chứa trong cửa sổ đó</em>. Nếu không có chuỗi con như vậy, hãy trả về <em>chuỗi rỗng </em><code>&quot;&quot;</code>.</p>

<p>Các test case sẽ được tạo sao cho đáp án là <strong>duy nhất</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;ADOBECODEBANC&quot;, t = &quot;ABC&quot;
<strong>Đầu ra:</strong> &quot;BANC&quot;
<strong>Giải thích:</strong> Chuỗi con cửa sổ tối thiểu &quot;BANC&quot; chứa &#39;A&#39;, &#39;B&#39; và &#39;C&#39; từ chuỗi t.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;a&quot;, t = &quot;a&quot;
<strong>Đầu ra:</strong> &quot;a&quot;
<strong>Giải thích:</strong> Toàn bộ chuỗi s là cửa sổ tối thiểu.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;a&quot;, t = &quot;aa&quot;
<strong>Đầu ra:</strong> &quot;&quot;
<strong>Giải thích:</strong> Cả hai ký tự &#39;a&#39; từ t đều phải được chứa trong cửa sổ.
Vì cửa sổ lớn nhất của s chỉ có một &#39;a&#39;, hãy trả về chuỗi rỗng.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>m == s.length</code></li>
	<li><code>n == t.length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> và <code>t</code> chỉ gồm các chữ cái tiếng Anh viết hoa và viết thường.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể tìm một thuật toán chạy trong thời gian <code>O(m + n)</code> không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đếm + Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Liệt kê mọi chuỗi con của $s$ rồi kiểm tra độ bao phủ của $t$: có $O(m^2)$ cửa sổ, với $m \le 10^5$ sẽ TLE. Phần mở rộng yêu cầu $O(m+n)$.
>
> Khi một cửa sổ đã bao phủ đủ, có thể loại bỏ các ký tự thừa ở bên trái; nếu chưa bao phủ, chỉ có thể mở rộng đầu bên phải. $\textit{need}$ lưu hạn ngạch của $t$, $\textit{window}$ lưu cửa sổ, và $\textit{cnt}$ đếm số lần xuất hiện cần thiết đã được đáp ứng, nên việc kiểm tra độ bao phủ là $O(1)$. Duyệt $s$ bằng con trỏ phải; khi cửa sổ hợp lệ, thu hẹp con trỏ trái và ghi nhận đoạn ngắn nhất.

<!-- thinking:end -->

Chúng ta sử dụng một bảng băm hoặc mảng $\textit{need}$ để đếm số lần xuất hiện của mỗi ký tự trong chuỗi $t$, và một bảng băm hoặc mảng $\textit{window}$ khác để đếm số lần xuất hiện của mỗi ký tự trong cửa sổ trượt. Ngoài ra, chúng ta định nghĩa hai con trỏ $l$ và $r$ lần lượt trỏ đến biên trái và biên phải của cửa sổ, một biến $\textit{cnt}$ biểu thị có bao nhiêu ký tự từ $t$ đã được chứa trong cửa sổ, cùng các biến $k$ và $\textit{mi}$ biểu thị vị trí bắt đầu và độ dài của chuỗi con cửa sổ tối thiểu.

Chúng ta duyệt chuỗi $s$ từ trái sang phải. Với ký tự hiện tại $s[r]$:

- Chúng ta thêm ký tự đó vào cửa sổ, tức là $\textit{window}[s[r]] = \textit{window}[s[r]] + 1$. Nếu $\textit{need}[s[r]] \geq \textit{window}[s[r]]$, điều đó có nghĩa $s[r]$ là một "ký tự cần thiết", và chúng ta tăng $\textit{cnt}$ lên một.
- Nếu $\textit{cnt}$ bằng độ dài của $t$, điều đó có nghĩa cửa sổ đã chứa tất cả các ký tự trong $t$, và chúng ta có thể thử cập nhật vị trí bắt đầu và độ dài của chuỗi con cửa sổ tối thiểu. Nếu $r - l + 1 < \textit{mi}$, điều đó có nghĩa chuỗi con hiện tại ngắn hơn, nên chúng ta cập nhật $\textit{mi} = r - l + 1$ và $k = l$.
- Sau đó, chúng ta thử di chuyển biên trái $l$. Nếu $\textit{need}[s[l]] \geq \textit{window}[s[l]]$, điều đó có nghĩa $s[l]$ là một "ký tự cần thiết", và việc di chuyển biên trái sẽ loại $s[l]$ khỏi cửa sổ. Do đó, chúng ta cần giảm $\textit{cnt}$ đi một, cập nhật $\textit{window}[s[l]] = \textit{window}[s[l]] - 1$, rồi di chuyển $l$ sang phải một vị trí.
- Nếu $\textit{cnt}$ không bằng độ dài của $t$, điều đó có nghĩa cửa sổ chưa chứa tất cả các ký tự trong $t$, nên chúng ta không cần di chuyển biên trái. Thay vào đó, chúng ta di chuyển $r$ sang phải một vị trí và tiếp tục duyệt.

Sau khi duyệt xong, nếu không tìm thấy chuỗi con cửa sổ tối thiểu, hãy trả về chuỗi rỗng. Nếu không, hãy trả về $s[k:k+\textit{mi}]$.

Độ phức tạp thời gian là $O(m + n)$, và độ phức tạp không gian là $O(|\Sigma|)$. Trong đó, $m$ và $n$ lần lượt là độ dài của các chuỗi $s$ và $t$; còn $|\Sigma|$ là kích thước của tập ký tự, bằng $128$ trong bài toán này.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        need = Counter(t)
        window = Counter()
        cnt = l = 0
        k, mi = -1, inf
        for r, c in enumerate(s):
            window[c] += 1
            if need[c] >= window[c]:
                cnt += 1
            while cnt == len(t):
                if r - l + 1 < mi:
                    mi = r - l + 1
                    k = l
                if need[s[l]] >= window[s[l]]:
                    cnt -= 1
                window[s[l]] -= 1
                l += 1
        return "" if k < 0 else s[k : k + mi]

```

#### Java

```java
class Solution {
    public String minWindow(String s, String t) {
        int[] need = new int[128];
        int[] window = new int[128];
        for (char c : t.toCharArray()) {
            ++need[c];
        }
        int m = s.length(), n = t.length();
        int k = -1, mi = m + 1, cnt = 0;
        for (int l = 0, r = 0; r < m; ++r) {
            char c = s.charAt(r);
            if (++window[c] <= need[c]) {
                ++cnt;
            }
            while (cnt == n) {
                if (r - l + 1 < mi) {
                    mi = r - l + 1;
                    k = l;
                }
                c = s.charAt(l);
                if (window[c] <= need[c]) {
                    --cnt;
                }
                --window[c];
                ++l;
            }
        }
        return k < 0 ? "" : s.substring(k, k + mi);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string minWindow(string s, string t) {
        vector<int> need(128, 0);
        vector<int> window(128, 0);
        for (char c : t) {
            ++need[c];
        }

        int m = s.length(), n = t.length();
        int k = -1, mi = m + 1, cnt = 0;

        for (int l = 0, r = 0; r < m; ++r) {
            char c = s[r];
            if (++window[c] <= need[c]) {
                ++cnt;
            }

            while (cnt == n) {
                if (r - l + 1 < mi) {
                    mi = r - l + 1;
                    k = l;
                }

                c = s[l];
                if (window[c] <= need[c]) {
                    --cnt;
                }
                --window[c];
                ++l;
            }
        }

        return k < 0 ? "" : s.substr(k, mi);
    }
};
```

#### Go

```go
func minWindow(s string, t string) string {
	need := make([]int, 128)
	window := make([]int, 128)
	for i := 0; i < len(t); i++ {
		need[t[i]]++
	}

	m, n := len(s), len(t)
	k, mi, cnt := -1, m+1, 0

	for l, r := 0, 0; r < m; r++ {
		c := s[r]
		if window[c]++; window[c] <= need[c] {
			cnt++
		}
		for cnt == n {
			if r-l+1 < mi {
				mi = r - l + 1
				k = l
			}

			c = s[l]
			if window[c] <= need[c] {
				cnt--
			}
			window[c]--
			l++
		}
	}
	if k < 0 {
		return ""
	}
	return s[k : k+mi]
}
```

#### TypeScript

```ts
function minWindow(s: string, t: string): string {
    const need: number[] = Array(128).fill(0);
    const window: number[] = Array(128).fill(0);
    for (let i = 0; i < t.length; i++) {
        need[t.charCodeAt(i)]++;
    }
    const [m, n] = [s.length, t.length];
    let [k, mi, cnt] = [-1, m + 1, 0];
    for (let l = 0, r = 0; r < m; r++) {
        let c = s.charCodeAt(r);
        if (++window[c] <= need[c]) {
            cnt++;
        }
        while (cnt === n) {
            if (r - l + 1 < mi) {
                mi = r - l + 1;
                k = l;
            }

            c = s.charCodeAt(l);
            if (window[c] <= need[c]) {
                cnt--;
            }
            window[c]--;
            l++;
        }
    }
    return k < 0 ? '' : s.substring(k, k + mi);
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_window(s: String, t: String) -> String {
        let mut need: HashMap<char, usize> = HashMap::new();
        let mut window: HashMap<char, usize> = HashMap::new();
        for c in t.chars() {
            *need.entry(c).or_insert(0) += 1;
        }
        let m = s.len();
        let n = t.len();
        let mut k = -1;
        let mut mi = m + 1;
        let mut cnt = 0;

        let s_bytes = s.as_bytes();
        let mut l = 0;
        for r in 0..m {
            let c = s_bytes[r] as char;
            *window.entry(c).or_insert(0) += 1;
            if window[&c] <= *need.get(&c).unwrap_or(&0) {
                cnt += 1;
            }
            while cnt == n {
                if r - l + 1 < mi {
                    mi = r - l + 1;
                    k = l as i32;
                }

                let c = s_bytes[l] as char;
                if window[&c] <= *need.get(&c).unwrap_or(&0) {
                    cnt -= 1;
                }
                *window.entry(c).or_insert(0) -= 1;
                l += 1;
            }
        }
        if k < 0 {
            return String::new();
        }
        s[k as usize..(k as usize + mi)].to_string()
    }
}
```

#### C#

```cs
public class Solution {
    public string MinWindow(string s, string t) {
        int[] need = new int[128];
        int[] window = new int[128];

        foreach (var c in t) {
            need[c]++;
        }

        int m = s.Length, n = t.Length;
        int k = -1, mi = m + 1, cnt = 0;

        int l = 0;
        for (int r = 0; r < m; r++) {
            char c = s[r];
            window[c]++;

            if (window[c] <= need[c]) {
                cnt++;
            }

            while (cnt == n) {
                if (r - l + 1 < mi) {
                    mi = r - l + 1;
                    k = l;
                }

                c = s[l];
                if (window[c] <= need[c]) {
                    cnt--;
                }
                window[c]--;
                l++;
            }
        }

        return k < 0 ? "" : s.Substring(k, mi);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
