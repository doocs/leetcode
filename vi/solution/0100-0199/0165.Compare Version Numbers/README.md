---
comments: true
difficulty: Medium
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [165. Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers)

[中文文档](/solution/0100-0199/0165.Compare%20Version%20Numbers/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai <strong>chuỗi phiên bản</strong>, <code>version1</code> và <code>version2</code>, hãy so sánh chúng. Một chuỗi phiên bản bao gồm các <strong>revision</strong> được phân tách bằng dấu chấm <code>&#39;.&#39;</code>. <strong>Giá trị của revision</strong> là kết quả của việc <strong>chuyển đổi sang số nguyên</strong> và bỏ qua các số 0 ở đầu.</p>

<p>Để so sánh các chuỗi phiên bản, hãy so sánh giá trị của các revision theo <strong>thứ tự từ trái sang phải</strong>. Nếu một trong hai chuỗi phiên bản có ít revision hơn, hãy xem các giá trị revision bị thiếu là <code>0</code>.</p>

<p>Trả về kết quả sau:</p>

<ul>
	<li>Nếu <code>version1 &lt; version2</code>, trả về -1.</li>
	<li>Nếu <code>version1 &gt; version2</code>, trả về 1.</li>
	<li>Ngược lại, trả về 0.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">version1 = &quot;1.2&quot;, version2 = &quot;1.10&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">-1</span></p>

<p><strong>Giải thích:</strong></p>

<p>Revision thứ hai của version1 là &quot;2&quot; và revision thứ hai của version2 là &quot;10&quot;: 2 &lt; 10, nên version1 &lt; version2.</p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">version1 = &quot;1.01&quot;, version2 = &quot;1.001&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">0</span></p>

<p><strong>Giải thích:</strong></p>

<p>Bỏ qua các số 0 ở đầu, cả &quot;01&quot; và &quot;001&quot; đều biểu diễn cùng một số nguyên &quot;1&quot;.</p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">version1 = &quot;1.0&quot;, version2 = &quot;1.0.0.0&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">0</span></p>

<p><strong>Giải thích:</strong></p>

<p>version1 có ít revision hơn, nghĩa là mọi revision bị thiếu đều được xem là &quot;0&quot;.</p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= version1.length, version2.length &lt;= 500</code></li>
	<li><code>version1</code> và <code>version2</code>&nbsp;chỉ chứa các chữ số và <code>&#39;.&#39;</code>.</li>
	<li><code>version1</code> và <code>version2</code>&nbsp;<strong>là các số phiên bản hợp lệ</strong>.</li>
	<li>Tất cả revision trong&nbsp;<code>version1</code> và <code>version2</code>&nbsp;đều có thể được lưu trong một&nbsp;<strong>số nguyên 32 bit</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> So sánh các số revision; các phần bị thiếu được xem là $0$ và các số 0 ở đầu không được tính. Việc tách thành các mảng số nguyên sẽ sử dụng thêm bộ nhớ. Độ dài tối đa là $500$. Hai con trỏ cùng quét, hoàn tất một phần tại mỗi dấu chấm và xem phía bị thiếu là $0$ cho đến khi chúng khác nhau hoặc cả hai cùng kết thúc.

<!-- thinking:end -->

Duyệt đồng thời hai chuỗi bằng hai con trỏ $i$ và $j$, lần lượt trỏ đến vị trí hiện tại trong mỗi chuỗi, bắt đầu với $i = j = 0$.

Mỗi lần, trích xuất các số revision tương ứng từ hai chuỗi, ký hiệu là $a$ và $b$. So sánh $a$ và $b$: nếu $a \lt b$, trả về $-1$; nếu $a \gt b$, trả về $1$; nếu $a = b$, tiếp tục so sánh cặp số revision tiếp theo.

Độ phức tạp thời gian là $O(\max(m, n))$, và độ phức tạp không gian là $O(1)$, trong đó $m$ và $n$ lần lượt là độ dài của hai chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def compareVersion(self, version1: str, version2: str) -> int:
        m, n = len(version1), len(version2)
        i = j = 0
        while i < m or j < n:
            a = b = 0
            while i < m and version1[i] != '.':
                a = a * 10 + int(version1[i])
                i += 1
            while j < n and version2[j] != '.':
                b = b * 10 + int(version2[j])
                j += 1
            if a != b:
                return -1 if a < b else 1
            i, j = i + 1, j + 1
        return 0
```

#### Java

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        int m = version1.length(), n = version2.length();
        for (int i = 0, j = 0; i < m || j < n; ++i, ++j) {
            int a = 0, b = 0;
            while (i < m && version1.charAt(i) != '.') {
                a = a * 10 + (version1.charAt(i++) - '0');
            }
            while (j < n && version2.charAt(j) != '.') {
                b = b * 10 + (version2.charAt(j++) - '0');
            }
            if (a != b) {
                return a < b ? -1 : 1;
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int compareVersion(string version1, string version2) {
        int m = version1.size(), n = version2.size();
        for (int i = 0, j = 0; i < m || j < n; ++i, ++j) {
            int a = 0, b = 0;
            while (i < m && version1[i] != '.') {
                a = a * 10 + (version1[i++] - '0');
            }
            while (j < n && version2[j] != '.') {
                b = b * 10 + (version2[j++] - '0');
            }
            if (a != b) {
                return a < b ? -1 : 1;
            }
        }
        return 0;
    }
};
```

#### Go

```go
func compareVersion(version1 string, version2 string) int {
	m, n := len(version1), len(version2)
	for i, j := 0, 0; i < m || j < n; i, j = i+1, j+1 {
		var a, b int
		for i < m && version1[i] != '.' {
			a = a*10 + int(version1[i]-'0')
			i++
		}
		for j < n && version2[j] != '.' {
			b = b*10 + int(version2[j]-'0')
			j++
		}
		if a < b {
			return -1
		}
		if a > b {
			return 1
		}
	}
	return 0
}
```

#### TypeScript

```ts
function compareVersion(version1: string, version2: string): number {
    const [m, n] = [version1.length, version2.length];
    let [i, j] = [0, 0];
    while (i < m || j < n) {
        let [a, b] = [0, 0];
        while (i < m && version1[i] !== '.') {
            a = a * 10 + +version1[i];
            i++;
        }
        while (j < n && version2[j] !== '.') {
            b = b * 10 + +version2[j];
            j++;
        }
        if (a !== b) {
            return a < b ? -1 : 1;
        }
        i++;
        j++;
    }
    return 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn compare_version(version1: String, version2: String) -> i32 {
        let (bytes1, bytes2) = (version1.as_bytes(), version2.as_bytes());
        let (m, n) = (bytes1.len(), bytes2.len());
        let (mut i, mut j) = (0, 0);

        while i < m || j < n {
            let mut a = 0;
            let mut b = 0;

            while i < m && bytes1[i] != b'.' {
                a = a * 10 + (bytes1[i] - b'0') as i32;
                i += 1;
            }
            while j < n && bytes2[j] != b'.' {
                b = b * 10 + (bytes2[j] - b'0') as i32;
                j += 1;
            }

            if a != b {
                return if a < b { -1 } else { 1 };
            }

            i += 1;
            j += 1;
        }

        0
    }
}
```

#### C#

```cs
public class Solution {
    public int CompareVersion(string version1, string version2) {
        int m = version1.Length, n = version2.Length;
        for (int i = 0, j = 0; i < m || j < n; ++i, ++j) {
            int a = 0, b = 0;
            while (i < m && version1[i] != '.') {
                a = a * 10 + (version1[i++] - '0');
            }
            while (j < n && version2[j] != '.') {
                b = b * 10 + (version2[j++] - '0');
            }
            if (a != b) {
                return a < b ? -1 : 1;
            }
        }
        return 0;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
