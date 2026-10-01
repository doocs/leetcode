---
comments: true
difficulty: Medium
tags:
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [80. Remove Duplicates from Sorted Array II](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii)

[中文文档](/solution/0000-0099/0080.Remove%20Duplicates%20from%20Sorted%20Array%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code> đã được sắp xếp theo <strong>thứ tự không giảm</strong>, hãy xóa một số phần tử trùng lặp <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a> sao cho mỗi phần tử duy nhất xuất hiện <strong>nhiều nhất hai lần</strong>. <strong>Thứ tự tương đối</strong> của các phần tử phải được giữ <strong>nguyên</strong>.</p>

<p>Vì trong một số ngôn ngữ không thể thay đổi độ dài của mảng, thay vào đó bạn phải đặt kết quả vào <strong>phần đầu</strong> của mảng <code>nums</code>. Cụ thể hơn, nếu sau khi xóa các phần tử trùng lặp còn <code>k</code> phần tử, thì <code>k</code> phần tử đầu tiên của <code>nums</code>&nbsp;phải chứa kết quả cuối cùng. Các phần tử nằm sau&nbsp;<code>k</code>&nbsp;phần tử đầu tiên không quan trọng.</p>

<p>Trả về <code>k</code><em> sau khi đặt kết quả cuối cùng vào </em><code>k</code><em> vị trí đầu tiên của </em><code>nums</code>.</p>

<p><strong>Không</strong> được cấp phát thêm không gian cho một mảng khác. Bạn phải <strong>sửa đổi mảng đầu vào <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a></strong> với O(1) bộ nhớ bổ sung.</p>

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
<strong>Đầu vào:</strong> nums = [1,1,1,2,2,3]
<strong>Đầu ra:</strong> 5, nums = [1,1,2,2,3,_]
<strong>Giải thích:</strong> Hàm của bạn cần trả về k = 5, trong đó năm phần tử đầu tiên của nums lần lượt là 1, 1, 2, 2 và 3.
Các phần tử sau k có giá trị gì không quan trọng (do đó chúng được biểu diễn bằng dấu gạch dưới).
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,0,1,1,1,1,2,3,3]
<strong>Đầu ra:</strong> 7, nums = [0,0,1,1,2,3,3,_,_]
<strong>Giải thích:</strong> Hàm của bạn cần trả về k = 7, trong đó bảy phần tử đầu tiên của nums lần lượt là 0, 0, 1, 1, 2, 3 và 3.
Các phần tử sau k có giá trị gì không quan trọng (do đó chúng được biểu diễn bằng dấu gạch dưới).
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> được sắp xếp theo <strong>thứ tự không giảm</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt một lượt

<!-- thinking:start -->

> **Tư duy**
>
> Sao chép vào một mảng mới, giữ nhiều nhất hai lần mỗi giá trị: cách này đúng, nhưng bài toán yêu cầu thực hiện in-place với $O(1)$ không gian bổ sung. $n \le 3\times 10^4$, nên chỉ cần duyệt một lượt.
>
> Trong mảng đã sắp xếp, các dãy giá trị giống nhau nằm liên tiếp; bài 26 giữ lại một bản sao, còn bài này giữ lại hai. Gọi $k$ là độ dài prefix được giữ lại: có thể ghi $x$ khi và chỉ khi đã giữ lại ít hơn hai phần tử, hoặc x khác phần tử áp chót trong các phần tử đã giữ lại; nếu không, đó sẽ là bản sao thứ ba. Ghi vào $nums[k]$, tăng $k$, rồi trả về $k$.

<!-- thinking:end -->

Chúng ta dùng một biến $k$ để ghi lại độ dài hiện tại của mảng đã được xử lý. Ban đầu, $k=0$ biểu diễn một mảng rỗng.

Tiếp theo, chúng ta duyệt mảng từ trái sang phải. Với mỗi phần tử $x$ gặp được, nếu $k < 2$ hoặc $x \neq nums[k-2]$, chúng ta đặt $x$ vào vị trí của $nums[k]$, rồi tăng $k$ thêm $1$. Ngược lại, $x$ giống với $nums[k-2]$, nên chúng ta bỏ qua phần tử này. Tiếp tục duyệt cho đến khi duyệt hết toàn bộ mảng.

Như vậy, khi kết thúc duyệt, $k$ phần tử đầu tiên trong $nums$ là đáp án cần tìm, và $k$ là độ dài của đáp án.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(1)$. Ở đây, $n$ là độ dài của mảng.

Bổ sung:

Bài toán gốc yêu cầu cùng một số xuất hiện nhiều nhất $2$ lần. Chúng ta có thể mở rộng để giữ lại nhiều nhất $k$ số giống nhau.

- Vì cùng một số có thể được giữ lại nhiều nhất $k$ lần, chúng ta có thể trực tiếp giữ lại $k$ phần tử đầu tiên của mảng ban đầu;
- Với các số tiếp theo, điều kiện để có thể giữ lại là so sánh số hiện tại $x$ với phần tử thứ $k$ tính từ cuối trong các phần tử đã được giữ lại trước đó. Nếu chúng khác nhau, giữ lại nó, nếu không thì bỏ qua.

Các bài toán tương tự:

- [26. Remove Duplicates from Sorted Array](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0026.Remove%20Duplicates%20from%20Sorted%20Array/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        k = 0
        for x in nums:
            if k < 2 or x != nums[k - 2]:
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
            if (k < 2 || x != nums[k - 2]) {
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
            if (k < 2 || x != nums[k - 2]) {
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
		if k < 2 || x != nums[k-2] {
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
    let k = 0;
    for (const x of nums) {
        if (k < 2 || x !== nums[k - 2]) {
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
            if k < 2 || nums[i] != nums[k - 2] {
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
        if (k < 2 || x !== nums[k - 2]) {
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
            if (k < 2 || x != nums[k - 2]) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
