---
comments: true
difficulty: Easy
tags:
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [27. Remove Element](https://leetcode.com/problems/remove-element)

[中文文档](/solution/0000-0099/0027.Remove%20Element/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code> và một số nguyên <code>val</code>, hãy xóa tất cả các lần xuất hiện của <code>val</code> trong <code>nums</code> <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>tại chỗ</strong></a>. Thứ tự của các phần tử có thể thay đổi. Sau đó trả về <em>số lượng phần tử trong </em><code>nums</code><em> không bằng </em><code>val</code>.</p>

<p>Gọi số lượng phần tử trong <code>nums</code> không bằng <code>val</code> là <code>k</code>, để được chấp nhận, bạn cần thực hiện các việc sau:</p>

<ul>
	<li>Thay đổi mảng <code>nums</code> sao cho <code>k</code> phần tử đầu tiên của <code>nums</code> chứa các phần tử không bằng <code>val</code>. Các phần tử còn lại của <code>nums</code> cũng như kích thước của <code>nums</code> không quan trọng.</li>
	<li>Trả về <code>k</code>.</li>
</ul>

<p><strong>Trình chấm tùy chỉnh:</strong></p>

<p>Trình chấm sẽ kiểm tra lời giải của bạn bằng đoạn mã sau:</p>

<pre>
int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i &lt; actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
</pre>

<p>Nếu tất cả các assertion đều đúng, lời giải của bạn sẽ được <strong>chấp nhận</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [3,2,2,3], val = 3
<strong>Đầu ra:</strong> 2, nums = [2,2,_,_]
<strong>Giải thích:</strong> Hàm của bạn nên trả về k = 2, với hai phần tử đầu tiên của nums là 2.
Không quan trọng bạn để lại gì sau k được trả về (do đó chúng là các dấu gạch dưới).
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,1,2,2,3,0,4,2], val = 2
<strong>Đầu ra:</strong> 5, nums = [0,1,4,0,3,_,_,_]
<strong>Giải thích:</strong> Hàm của bạn nên trả về k = 5, trong đó năm phần tử đầu tiên của nums chứa 0, 0, 1, 3 và 4.
Lưu ý rằng năm phần tử có thể được trả về theo bất kỳ thứ tự nào.
Không quan trọng bạn để lại gì sau k được trả về (do đó chúng là các dấu gạch dưới).
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>0 &lt;= val &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Một lượt duyệt

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là tạo một mảng mới gồm mọi phần tử không bằng $val$. Với $n \le 100$, cách này sẽ vượt qua được, nhưng bài toán yêu cầu viết lại ngay trên mảng, nên việc cấp phát thêm là không cần thiết.
>
> Nút thắt là việc "xóa" bằng một thao tác như $erase$: các phần tử phía sau bị dịch chuyển lặp đi lặp lại, trường hợp xấu nhất là $O(n^2)$.
>
> Trình chấm chỉ quan tâm rằng $k$ vị trí đầu tiên chứa mọi giá trị không bằng $val$; thứ tự có thể thay đổi. Vì vậy, hãy ghi đè phần đầu mảng bằng các giá trị được giữ lại.
>
> $k$ đếm số phần tử chúng ta đã ghi: khi $x \neq val$, lưu nó vào $nums[k]$ rồi tăng $k$. Chỉ cần một lượt duyệt, với độ phức tạp không gian phụ $O(1)$.

<!-- thinking:end -->

Chúng ta dùng biến $k$ để ghi lại số phần tử không bằng $val$.

Duyệt qua mảng $nums$, nếu phần tử hiện tại $x$ không bằng $val$, gán $x$ cho $nums[k]$, rồi tăng $k$ thêm $1$.

Cuối cùng, trả về $k$.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(1)$, trong đó $n$ là độ dài của mảng $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        k = 0
        for x in nums:
            if x != val:
                nums[k] = x
                k += 1
        return k
```

#### Java

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int x : nums) {
            if (x != val) {
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
    int removeElement(vector<int>& nums, int val) {
        int k = 0;
        for (int x : nums) {
            if (x != val) {
                nums[k++] = x;
            }
        }
        return k;
    }
};
```

#### Go

```go
func removeElement(nums []int, val int) int {
	k := 0
	for _, x := range nums {
		if x != val {
			nums[k] = x
			k++
		}
	}
	return k
}
```

#### TypeScript

```ts
function removeElement(nums: number[], val: number): number {
    let k: number = 0;
    for (const x of nums) {
        if (x !== val) {
            nums[k++] = x;
        }
    }
    return k;
}
```

#### Rust

```rust
impl Solution {
    pub fn remove_element(nums: &mut Vec<i32>, val: i32) -> i32 {
        let mut k = 0;
        for i in 0..nums.len() {
            if nums[i] != val {
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
 * @param {number} val
 * @return {number}
 */
var removeElement = function (nums, val) {
    let k = 0;
    for (const x of nums) {
        if (x !== val) {
            nums[k++] = x;
        }
    }
    return k;
};
```

#### C#

```cs
public class Solution {
    public int RemoveElement(int[] nums, int val) {
        int k = 0;
        foreach (int x in nums) {
            if (x != val) {
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
     * @param Integer $val
     * @return Integer
     */
    function removeElement(&$nums, $val) {
        for ($i = count($nums) - 1; $i >= 0; $i--) {
            if ($nums[$i] == $val) {
                array_splice($nums, $i, 1);
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
