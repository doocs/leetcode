---
comments: true
difficulty: Medium
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array)

[中文文档](/solution/0100-0199/0153.Find%20Minimum%20in%20Rotated%20Sorted%20Array/README.md)

## Mô tả

<!-- description:start -->

<p>Giả sử một mảng có độ dài <code>n</code> được sắp xếp theo thứ tự tăng dần và được <strong>xoay</strong> từ <code>1</code> đến <code>n</code> lần. Ví dụ, mảng <code>nums = [0,1,2,4,5,6,7]</code> có thể trở thành:</p>

<ul>
	<li><code>[4,5,6,7,0,1,2]</code> nếu nó được xoay <code>4</code> lần.</li>
	<li><code>[0,1,2,4,5,6,7]</code> nếu nó được xoay <code>7</code> lần.</li>
</ul>

<p>Lưu ý rằng việc <strong>xoay</strong> một mảng <code>[a[0], a[1], a[2], ..., a[n-1]]</code> 1 lần sẽ tạo ra mảng <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code>.</p>

<p>Cho mảng đã sắp xếp và xoay <code>nums</code> gồm các phần tử <strong>khác nhau</strong>, hãy trả về <em>phần tử nhỏ nhất của mảng này</em>.</p>

<p>Bạn phải viết một thuật toán chạy trong&nbsp;<code>O(log n) time</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [3,4,5,1,2]
<strong>Đầu ra:</strong> 1
<strong>Giải thích:</strong> Mảng ban đầu là [1,2,3,4,5] và được xoay 3 lần.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [4,5,6,7,0,1,2]
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> Mảng ban đầu là [0,1,2,4,5,6,7] và được xoay 4 lần.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [11,13,15,17]
<strong>Đầu ra:</strong> 11
<strong>Giải thích:</strong> Mảng ban đầu là [11,13,15,17] và được xoay 4 lần.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
	<li>Tất cả các số nguyên trong <code>nums</code> đều <strong>khác nhau</strong>.</li>
	<li><code>nums</code> được sắp xếp và xoay từ <code>1</code> đến <code>n</code> lần.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Mảng đã sắp xếp sau khi xoay có các giá trị khác nhau; duyệt tuyến tính có độ phức tạp $O(n)$ và với $n\le 5000$ thì vẫn phù hợp, nhưng tính chất của bài toán cho phép đạt $\log n$. Phần tử nhỏ nhất chia mảng thành hai đoạn tăng dần. So sánh phần tử giữa với giá trị cuối: nếu lớn hơn thì phần tử nhỏ nhất nằm bên phải, ngược lại nằm bên trái (bao gồm cả vị trí giữa). Thu hẹp phạm vi cho đến khi chỉ còn một chỉ số.

<!-- thinking:end -->

Chúng ta có thể sử dụng tìm kiếm nhị phân để giải bài toán này.

Đầu tiên, chúng ta xác định hai con trỏ $l$ và $r$ lần lượt trỏ đến đầu và cuối mảng. Sau đó, chúng ta lặp cho đến khi $l$ không còn nhỏ hơn $r$.

Trong mỗi lần lặp, chúng ta tính vị trí giữa $mid$ và so sánh $nums[mid]$ với $nums[n-1]$. Nếu $nums[mid]$ lớn hơn $nums[n-1]$, giá trị nhỏ nhất nằm bên phải $mid$, nên chúng ta cập nhật $l$ thành $mid + 1$. Ngược lại, giá trị nhỏ nhất nằm tại $mid$ hoặc bên trái nó, nên chúng ta cập nhật $r$ thành $mid$. Khi vòng lặp kết thúc, con trỏ $l$ sẽ trỏ đến giá trị nhỏ nhất và chúng ta trả về $nums[l]$.

Độ phức tạp thời gian là $O(\log n)$, trong đó $n$ là độ dài của mảng $\textit{nums}$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMin(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] > nums[-1]:
                l = mid + 1
            else:
                r = mid
        return nums[l]
```

#### Java

```java
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[nums.length - 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMin(vector<int>& nums) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums.back()) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
};
```

#### Go

```go
func findMin(nums []int) int {
	l, r := 0, len(nums)-1
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] > nums[len(nums)-1] {
			l = mid + 1
		} else {
			r = mid
		}
	}
	return nums[l]
}
```

#### TypeScript

```ts
function findMin(nums: number[]): number {
    let l = 0,
        r = nums.length - 1;
    while (l < r) {
        let mid = (l + r) >> 1;
        if (nums[mid] > nums[nums.length - 1]) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return nums[l];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_min(nums: Vec<i32>) -> i32 {
        let (mut l, mut r) = (0, nums.len() - 1);
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] > nums[nums.len() - 1] {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        nums[l]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMin = function (nums) {
    let l = 0,
        r = nums.length - 1;
    while (l < r) {
        let mid = (l + r) >> 1;
        if (nums[mid] > nums[nums.length - 1]) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return nums[l];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
