---
comments: true
difficulty: Easy
tags:
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array)

[中文文档](/solution/0000-0099/0088.Merge%20Sorted%20Array/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai mảng số nguyên <code>nums1</code> và <code>nums2</code> đã được sắp xếp theo <strong>thứ tự không giảm</strong>, cùng hai số nguyên <code>m</code> và <code>n</code>, lần lượt biểu thị số phần tử của <code>nums1</code> và <code>nums2</code>.</p>

<p><strong>Trộn</strong> <code>nums1</code> và <code>nums2</code> thành một mảng duy nhất được sắp xếp theo <strong>thứ tự không giảm</strong>.</p>

<p>Mảng cuối cùng sau khi sắp xếp không được trả về bởi hàm, mà phải được <em>lưu bên trong mảng </em><code>nums1</code>. Để đáp ứng điều này, <code>nums1</code> có độ dài là <code>m + n</code>, trong đó <code>m</code> phần tử đầu tiên là các phần tử cần được trộn, còn <code>n</code> phần tử cuối được đặt là <code>0</code> và cần được bỏ qua. <code>nums2</code> có độ dài là <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
<strong>Đầu ra:</strong> [1,2,2,3,5,6]
<strong>Giải thích:</strong> Các mảng được trộn là [1,2,3] và [2,5,6].
Kết quả của phép trộn là [<u>1</u>,<u>2</u>,2,<u>3</u>,5,6], trong đó các phần tử được gạch chân đến từ nums1.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums1 = [1], m = 1, nums2 = [], n = 0
<strong>Đầu ra:</strong> [1]
<strong>Giải thích:</strong> Các mảng được trộn là [1] và [].
Kết quả của phép trộn là [1].
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums1 = [0], m = 0, nums2 = [1], n = 1
<strong>Đầu ra:</strong> [1]
<strong>Giải thích:</strong> Các mảng được trộn là [] và [1].
Kết quả của phép trộn là [1].
Lưu ý rằng vì m = 0 nên nums1 không có phần tử nào. Số 0 chỉ nhằm bảo đảm rằng kết quả trộn có thể chứa vừa trong nums1.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>nums1.length == m + n</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 &lt;= m, n &lt;= 200</code></li>
	<li><code>1 &lt;= m + n &lt;= 200</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng: </strong>Bạn có thể nghĩ ra một thuật toán chạy trong thời gian <code>O(m + n)</code> không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là cấp phát một mảng khác, trộn từ đầu rồi sao chép trở lại vào $\textit{nums1}$. Cách này đúng, và $m + n \le 200$ là rất nhỏ, nhưng nó sử dụng thêm $O(m + n)$ không gian và không thực hiện in-place.
>
> Điểm nghẽn nằm ở việc ghi từ bên trái: thao tác đó sẽ ghi đè các giá trị trong $\textit{nums1}$ mà chúng ta chưa xử lý. Các vị trí trống nằm ở phía cuối.
>
> Vì vậy, chúng ta trộn từ phía sau. Phần cuối của $\textit{nums1}$ đang trống; đặt giá trị lớn hơn hiện tại vào đó sẽ không ghi đè lên các phần tử chưa đọc. Các con trỏ $i$, $j$ đi từ cuối hai mảng, còn $k$ ghi vào cuối mảng đã trộn, cho đến khi $\textit{nums2}$ hết phần tử.

<!-- thinking:end -->

Chúng ta dùng hai con trỏ $i$ và $j$ lần lượt trỏ đến cuối hai mảng, cùng một con trỏ $k$ trỏ đến cuối mảng đã trộn.

Mỗi lần, chúng ta so sánh hai phần tử ở cuối hai mảng và chuyển phần tử lớn hơn vào cuối mảng đã trộn. Sau đó, chúng ta di chuyển con trỏ tương ứng về trước một bước và lặp lại quá trình này cho đến khi hai con trỏ chạm đến đầu các mảng.

Độ phức tạp thời gian là $O(m + n)$, trong đó $m$ và $n$ là độ dài của hai mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        k = m + n - 1
        i, j = m - 1, n - 1
        while j >= 0:
            if i >= 0 and nums1[i] > nums2[j]:
                nums1[k] = nums1[i]
                i -= 1
            else:
                nums1[k] = nums2[j]
                j -= 1
            k -= 1
```

#### Java

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m - 1, j = n - 1, k = m + n - 1; j >= 0; --k) {
            nums1[k] = i >= 0 && nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
        for (int i = m - 1, j = n - 1, k = m + n - 1; ~j; --k) {
            nums1[k] = i >= 0 && nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
    }
};
```

#### Go

```go
func merge(nums1 []int, m int, nums2 []int, n int) {
	for i, j, k := m-1, n-1, m+n-1; j >= 0; k-- {
		if i >= 0 && nums1[i] > nums2[j] {
			nums1[k] = nums1[i]
			i--
		} else {
			nums1[k] = nums2[j]
			j--
		}
	}
}
```

#### TypeScript

```ts
/**
 Do not return anything, modify nums1 in-place instead.
 */
function merge(nums1: number[], m: number, nums2: number[], n: number): void {
    for (let i = m - 1, j = n - 1, k = m + n - 1; j >= 0; --k) {
        nums1[k] = i >= 0 && nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn merge(nums1: &mut Vec<i32>, m: i32, nums2: &mut Vec<i32>, n: i32) {
        let mut k = (m + n - 1) as usize;
        let mut i = (m - 1) as isize;
        let mut j = (n - 1) as isize;

        while j >= 0 {
            if i >= 0 && nums1[i as usize] > nums2[j as usize] {
                nums1[k] = nums1[i as usize];
                i -= 1;
            } else {
                nums1[k] = nums2[j as usize];
                j -= 1;
            }
            k -= 1;
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var merge = function (nums1, m, nums2, n) {
    for (let i = m - 1, j = n - 1, k = m + n - 1; j >= 0; --k) {
        nums1[k] = i >= 0 && nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
    }
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer $m
     * @param Integer[] $nums2
     * @param Integer $n
     * @return NULL
     */
    function merge(&$nums1, $m, $nums2, $n) {
        while (count($nums1) > $m) {
            array_pop($nums1);
        }
        for ($i = 0; $i < $n; $i++) {
            array_push($nums1, $nums2[$i]);
        }
        asort($nums1);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
