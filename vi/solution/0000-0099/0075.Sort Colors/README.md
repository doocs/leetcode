---
comments: true
difficulty: Medium
tags:
    - Array
    - Two Pointers
    - Bubble Sort
    - Sorting
    - Quick Sort
---

<!-- problem:start -->

# [75. Sort Colors](https://leetcode.com/problems/sort-colors)

[中文文档](/solution/0000-0099/0075.Sort%20Colors/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một mảng <code>nums</code> gồm <code>n</code> đối tượng có màu đỏ, trắng hoặc xanh dương, hãy sắp xếp chúng <strong><a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">tại chỗ</a> </strong> sao cho các đối tượng cùng màu nằm cạnh nhau, với các màu theo thứ tự đỏ, trắng và xanh dương.</p>

<p>Chúng ta sẽ sử dụng các số nguyên 0, 1 và 2 lần lượt để biểu diễn màu đỏ, trắng và xanh dương.</p>

<p>Bạn phải giải bài toán này mà không sử dụng hàm sort của thư viện.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">nums = [2,0,2,1,1,0]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[0,0,1,1,2,2]</span></p>

<p><strong>Giải thích:</strong></p>

<p>Mảng có hai số 0, hai số 1 và hai số 2. Sắp xếp chúng tại chỗ sẽ đặt tất cả số 0 lên trước, tiếp đến là tất cả số 1, rồi đến tất cả số 2.</p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">nums = [2,0,1]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[0,1,2]</span></p>

<p><strong>Giải thích:</strong></p>

<p>Mảng có mỗi giá trị 0, 1 và 2 một lần, được sắp xếp tại chỗ theo thứ tự 0, 1, 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>nums[i]</code> là 0, 1 hoặc 2.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong>&nbsp;Bạn có thể nghĩ ra một thuật toán một lượt duyệt chỉ sử dụng không gian phụ hằng số không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Ba con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Đếm $0,1,2$ rồi ghi đè: hai lượt duyệt. Sắp xếp cũng phù hợp với $n \le 300$. Bài toán cấm hàm sort của thư viện, và câu hỏi mở rộng yêu cầu một lần quét cùng $O(1)$ không gian.
>
> Chỉ có ba giá trị nên chỉ cần phân hoạch thành toàn bộ $0$s, toàn bộ $1$s và toàn bộ $2$s. Gọi $i$ và $j$ lần lượt là biên của các số $0$ và $2$ đã được đặt, còn $k$ quét phần giữa chưa biết. Hoán đổi về phía $2$ sẽ đưa vào một giá trị chưa được xét, nên giữ nguyên $k$; hoán đổi về phía $0$ đưa vào một giá trị thuộc vùng đã quét, nên $k$ cũng tăng. Một lượt duyệt sẽ hoàn tất ba đoạn.

<!-- thinking:end -->

Chúng ta định nghĩa ba con trỏ $i$, $j$ và $k$. Con trỏ $i$ được dùng để trỏ đến biên phải của các phần tử có giá trị $0$ trong mảng, còn con trỏ $j$ được dùng để trỏ đến biên trái của các phần tử có giá trị $2$ trong mảng. Ban đầu, $i=-1$, $j=n$. Con trỏ $k$ được dùng để trỏ đến phần tử hiện tại đang được duyệt, ban đầu $k=0$.

Khi $k < j$, chúng ta thực hiện các thao tác sau:

- Nếu $nums[k] = 0$, hoán đổi nó với $nums[i+1]$, sau đó tăng cả $i$ và $k$ lên $1$;
- Nếu $nums[k] = 2$, hoán đổi nó với $nums[j-1]$, sau đó giảm $j$ đi $1$;
- Nếu $nums[k] = 1$, tăng $k$ lên $1$.

Sau khi duyệt, các phần tử trong mảng được chia thành ba phần: $[0,i]$, $[i+1,j-1]$ và $[j,n-1]$.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Chỉ cần duyệt mảng một lần. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        i, j, k = -1, len(nums), 0
        while k < j:
            if nums[k] == 0:
                i += 1
                nums[i], nums[k] = nums[k], nums[i]
                k += 1
            elif nums[k] == 2:
                j -= 1
                nums[j], nums[k] = nums[k], nums[j]
            else:
                k += 1
```

#### Java

```java
class Solution {
    public void sortColors(int[] nums) {
        int i = -1, j = nums.length, k = 0;
        while (k < j) {
            if (nums[k] == 0) {
                swap(nums, ++i, k++);
            } else if (nums[k] == 2) {
                swap(nums, --j, k);
            } else {
                ++k;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

#### C++

```cpp
class Solution {
public:
    void sortColors(vector<int>& nums) {
        int i = -1, j = nums.size(), k = 0;
        while (k < j) {
            if (nums[k] == 0) {
                swap(nums[++i], nums[k++]);
            } else if (nums[k] == 2) {
                swap(nums[--j], nums[k]);
            } else {
                ++k;
            }
        }
    }
};
```

#### Go

```go
func sortColors(nums []int) {
	i, j, k := -1, len(nums), 0
	for k < j {
		if nums[k] == 0 {
			i++
			nums[i], nums[k] = nums[k], nums[i]
			k++
		} else if nums[k] == 2 {
			j--
			nums[j], nums[k] = nums[k], nums[j]
		} else {
			k++
		}
	}
}
```

#### TypeScript

```ts
/**
 Do not return anything, modify nums in-place instead.
 */
function sortColors(nums: number[]): void {
    let i = -1;
    let j = nums.length;
    let k = 0;
    while (k < j) {
        if (nums[k] === 0) {
            ++i;
            [nums[i], nums[k]] = [nums[k], nums[i]];
            ++k;
        } else if (nums[k] === 2) {
            --j;
            [nums[j], nums[k]] = [nums[k], nums[j]];
        } else {
            ++k;
        }
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn sort_colors(nums: &mut Vec<i32>) {
        let mut i = -1;
        let mut j = nums.len();
        let mut k = 0;
        while k < j {
            if nums[k] == 0 {
                i += 1;
                nums.swap(i as usize, k as usize);
                k += 1;
            } else if nums[k] == 2 {
                j -= 1;
                nums.swap(j, k);
            } else {
                k += 1;
            }
        }
    }
}
```

#### C#

```cs
public class Solution {
    public void SortColors(int[] nums) {
        int i = -1, j = nums.Length, k = 0;
        while (k < j) {
            if (nums[k] == 0) {
                swap(nums, ++i, k++);
            } else if (nums[k] == 2) {
                swap(nums, --j, k);
            } else {
                ++k;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
