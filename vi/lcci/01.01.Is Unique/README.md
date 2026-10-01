---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [01.01. Is Unique](https://leetcode.cn/problems/is-unique-lcci)

[中文文档](/lcci/01.01.Is%20Unique/README.md)

## Mô tả

<!-- description:start -->

<p>Hãy cài đặt một thuật toán để xác định xem mọi ký tự trong một chuỗi có đều là duy nhất hay không. Nếu bạn không thể dùng cấu trúc dữ liệu bổ sung thì sao?</p>

<p><strong>Ví dụ 1:</strong></p>

<pre>

<strong>Đầu vào: </strong> = &quot;leetcode&quot;

<strong>Đầu ra: </strong>false

</pre>

<p><strong>Ví dụ 2:</strong></p>

<pre>

<strong>Đầu vào: </strong>s = &quot;abc&quot;

<strong>Đầu ra: </strong>true

</pre>

<p><strong>Lưu ý:</strong></p>

<ul>
	<li><code>0 &lt;= len(s) &lt;= 100 </code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Thao tác bit

<!-- thinking:start -->

> **Tư duy**
>
> Một hash set chứa các ký tự đã gặp cho phép xác định tính duy nhất chỉ trong một lượt quét, với thời gian $O(n)$ và không gian tỉ lệ với kích thước bảng chữ cái. Ràng buộc $n \le 100$ cho phép cách đó, nhưng câu hỏi mở rộng yêu cầu không dùng cấu trúc dữ liệu bổ sung.
>
> Nếu chuỗi chỉ chứa chữ cái thường thì có tối đa $26$ ký hiệu, nên mỗi bit của một số nguyên có thể ghi lại việc một chữ cái đã xuất hiện hay chưa. Với ký tự $c$, kiểm tra bit tương ứng: nếu bit đó đã là $1$ thì có ký tự trùng; nếu không thì bật bit đó lên.
>
> Phép toán bit biến việc kiểm tra phần tử có thuộc tập hay không và thao tác thêm phần tử thành công việc tốn thời gian hằng số và không gian hằng số; đó là lý do dùng mask thay vì bảng băm hay mảng boolean.

<!-- thinking:end -->

Dựa vào các ví dụ, chúng ta có thể giả sử rằng chuỗi chỉ chứa chữ cái thường (điều này đã được xác nhận khi kiểm chứng thực tế).

Vì vậy, chúng ta có thể dùng từng bit của một số nguyên $32$ bit `mask` để biểu diễn việc mỗi ký tự trong chuỗi đã xuất hiện hay chưa.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của chuỗi. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isUnique(self, astr: str) -> bool:
        mask = 0
        for i in map(lambda c: ord(c) - ord("a"), astr):
            if (mask >> i) & 1:
                return False
            mask |= 1 << i
        return True
```

#### Java

```java
class Solution {
    public boolean isUnique(String astr) {
        int mask = 0;
        for (char c : astr.toCharArray()) {
            int i = c - 'a';
            if (((mask >> i) & 1) == 1) {
                return false;
            }
            mask |= 1 << i;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isUnique(string astr) {
        int mask = 0;
        for (char c : astr) {
            int i = c - 'a';
            if (mask >> i & 1) {
                return false;
            }
            mask |= 1 << i;
        }
        return true;
    }
};
```

#### Go

```go
func isUnique(astr string) bool {
	mask := 0
	for _, c := range astr {
		i := c - 'a'
		if mask>>i&1 == 1 {
			return false
		}
		mask |= 1 << i
	}
	return true
}
```

#### TypeScript

```ts
function isUnique(astr: string): boolean {
    let mask = 0;
    for (let j = 0; j < astr.length; ++j) {
        const i = astr.charCodeAt(j) - 'a'.charCodeAt(0);
        if ((mask >> i) & 1) {
            return false;
        }
        mask |= 1 << i;
    }
    return true;
}
```

#### JavaScript

```js
/**
 * @param {string} astr
 * @return {boolean}
 */
var isUnique = function (astr) {
    let mask = 0;
    for (const c of astr) {
        const i = c.charCodeAt() - 'a'.charCodeAt();
        if ((mask >> i) & 1) {
            return false;
        }
        mask |= 1 << i;
    }
    return true;
};
```

#### Swift

```swift
class Solution {
    func isUnique(_ astr: String) -> Bool {
        var mask = 0
        for c in astr {
            let i = Int(c.asciiValue! - Character("a").asciiValue!)
            if (mask >> i) & 1 != 0 {
                return false
            }
            mask |= 1 << i
        }
        return true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
