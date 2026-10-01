---
comments: true
difficulty: Easy
tags:
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array)

[中文文档](/solution/0000-0099/0026.Remove%20Duplicates%20from%20Sorted%20Array/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code> đã được sắp xếp theo <strong>thứ tự không giảm</strong>, hãy xóa các phần tử trùng lặp <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a> sao cho mỗi phần tử duy nhất chỉ xuất hiện <strong>một lần</strong>. <strong>Thứ tự tương đối</strong> của các phần tử phải được giữ <strong>nguyên</strong>.</p>

<p>Hãy coi số lượng <em>phần tử duy nhất</em> trong&nbsp;<code>nums</code> là <code>k<strong>​​​​​​​</strong></code>​​​​​​​. <meta charset="UTF-8" />Sau khi xóa các phần tử trùng lặp, hãy trả về số lượng phần tử duy nhất&nbsp;<code>k</code>.</p>

<p><meta charset="UTF-8" />Các <code>k</code> phần tử đầu tiên của <code>nums</code> phải chứa các số duy nhất theo <strong>thứ tự đã sắp xếp</strong>. Các phần tử còn lại sau chỉ số <code>k - 1</code> có thể được bỏ qua.</p>

<p><strong>Bộ chấm tùy chỉnh:</strong></p>

<p>Bộ chấm sẽ kiểm tra lời giải của bạn bằng đoạn mã sau:</p>

<pre>
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i &lt; k; i++) {
    assert nums[i] == expectedNums[i];
}
</pre>

<p>Nếu tất cả các assertion đều đúng, lời giải của bạn sẽ được <strong>chấp nhận</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,1,2]
<strong>Đầu ra:</strong> 2, nums = [1,2,_]
<strong>Giải thích:</strong> Hàm của bạn cần trả về k = 2, trong đó hai phần tử đầu tiên của nums lần lượt là 1 và 2.
Các phần tử sau k có giá trị gì không quan trọng (do đó chúng được biểu diễn bằng dấu gạch dưới).
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,0,1,1,1,2,2,3,3,4]
<strong>Đầu ra:</strong> 5, nums = [0,1,2,3,4,_,_,_,_,_]
<strong>Giải thích:</strong> Hàm của bạn cần trả về k = 5, trong đó năm phần tử đầu tiên của nums lần lượt là 0, 1, 2, 3 và 4.
Các phần tử sau k có giá trị gì không quan trọng (do đó chúng được biểu diễn bằng dấu gạch dưới).
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> được sắp xếp theo <strong>thứ tự không giảm</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt một lượt

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là dùng một hash set để lưu các giá trị đã gặp, sau đó ghi chúng trở lại. $n \le 3\times 10^4$ vẫn đáp ứng được, nhưng bài toán yêu cầu viết lại in-place, nên bảng bổ sung là lãng phí.
>
> Nút thắt là truy vấn "đã gặp giá trị này chưa". Mảng có thứ tự không giảm, nên các giá trị bằng nhau nằm cạnh nhau và không cần tra cứu toàn cục.
>
> Hãy so sánh $x$ với giá trị cuối cùng đã được ghi. Nếu chúng khác nhau (hoặc chưa có giá trị nào được ghi), hãy ghi nó vào chỉ số $k$; nếu không thì bỏ qua.
>
> $k$ vừa là con trỏ ghi vừa là độ dài các phần tử duy nhất. Chỉ cần một lượt duyệt và $O(1)$ không gian bổ sung.

<!-- thinking:end -->

Chúng ta dùng một biến $k$ để ghi lại độ dài hiện tại của mảng đã được xử lý. Ban đầu, $k=0$ biểu diễn một mảng rỗng.

Tiếp theo, chúng ta duyệt mảng từ trái sang phải. Với mỗi phần tử $x$ gặp được, nếu $k=0$ hoặc $x \neq nums[k-1]$, chúng ta đặt $x$ vào vị trí của $nums[k]$, rồi tăng $k$ thêm $1$. Ngược lại, $x$ giống với $nums[k-1]$, nên chúng ta bỏ qua phần tử này. Tiếp tục duyệt cho đến khi duyệt hết toàn bộ mảng.

Như vậy, khi kết thúc duyệt, $k$ phần tử đầu tiên trong $nums$ là đáp án cần tìm, và $k$ là độ dài của đáp án.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(1)$. Ở đây, $n$ là độ dài của mảng.

Bổ sung:

Bài toán gốc yêu cầu cùng một số xuất hiện nhiều nhất một lần. Chúng ta có thể mở rộng để giữ lại nhiều nhất $k$ số giống nhau.

- Vì cùng một số có thể được giữ lại nhiều nhất $k$ lần, chúng ta có thể trực tiếp giữ lại $k$ phần tử đầu tiên của mảng ban đầu;
- Với các số tiếp theo, điều kiện để có thể giữ lại là so sánh số hiện tại $x$ với phần tử thứ $k$ tính từ cuối trong các phần tử đã được giữ lại trước đó. Nếu chúng khác nhau, giữ lại chúng, nếu không thì bỏ qua.

Các bài toán tương tự:

- [80. Remove Duplicates from Sorted Array II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0080.Remove%20Duplicates%20from%20Sorted%20Array%20II/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        k = 0
        for x in nums:
            if k == 0 or x != nums[k - 1]:
                nums[k] = x
                k += 1
        return k
```

#### Java

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0;
        for (int x : nums) {
            if (k == 0 || x != nums[k - 1]) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int k = 0;
        for (int x : nums) {
            if (k == 0 || x != nums[k - 1]) {
                nums[k++] = x;
            }
        }
        return k;
    }
};
```

#### Go

```go
func removeDuplicates(nums []int) int {
	k := 0
	for _, x := range nums {
		if k == 0 || x != nums[k-1] {
			nums[k] = x
			k++
		}
	}
	return k
}
```

#### TypeScript

```ts
function removeDuplicates(nums: number[]): number {
    let k: number = 0;
    for (const x of nums) {
        if (k === 0 || x !== nums[k - 1]) {
            nums[k++] = x;
        }
    }
    return k;
}
```

#### Rust

```rust
impl Solution {
    pub fn remove_duplicates(nums: &mut Vec<i32>) -> i32 {
        let mut k = 0;
        for i in 0..nums.len() {
            if k == 0 || nums[i] != nums[k - 1] {
                nums[k] = nums[i];
                k += 1;
            }
        }
        k as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function (nums) {
    let k = 0;
    for (const x of nums) {
        if (k === 0 || x !== nums[k - 1]) {
            nums[k++] = x;
        }
    }
    return k;
};
```

#### C#

```cs
public class Solution {
    public int RemoveDuplicates(int[] nums) {
        int k = 0;
        foreach (int x in nums) {
            if (k == 0 || x != nums[k - 1]) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function removeDuplicates(&$nums) {
        $k = 0;
        foreach ($nums as $x) {
            if ($k == 0 || $x != $nums[$k - 1]) {
                $nums[$k++] = $x;
            }
        }
        return $k;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
