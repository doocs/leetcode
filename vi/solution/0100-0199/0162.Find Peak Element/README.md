---
comments: true
difficulty: Medium
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [162. Find Peak Element](https://leetcode.com/problems/find-peak-element)

[中文文档](/solution/0100-0199/0162.Find%20Peak%20Element/README.md)

## Mô tả

<!-- description:start -->

<p>Phần tử đỉnh là một phần tử lớn hơn nghiêm ngặt so với các phần tử lân cận của nó.</p>

<p>Cho một mảng số nguyên <code>nums</code> được đánh chỉ số từ <strong>0</strong>, hãy tìm một phần tử đỉnh và trả về chỉ số của nó. Nếu mảng chứa nhiều đỉnh, hãy trả về chỉ số của <strong>bất kỳ đỉnh nào</strong>.</p>

<p>Bạn có thể giả sử rằng <code>nums[-1] = nums[n] = -&infin;</code>. Nói cách khác, một phần tử luôn được xem là lớn hơn nghiêm ngặt so với một phần tử lân cận nằm ngoài mảng.</p>

<p>Bạn phải viết một thuật toán chạy trong thời gian <code>O(log n)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,3,1]
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> 3 là một phần tử đỉnh và hàm của bạn nên trả về chỉ số 2.</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,1,3,5,6,4]
<strong>Đầu ra:</strong> 5
<strong>Giải thích:</strong> Hàm của bạn có thể trả về chỉ số 1, tại đó phần tử đỉnh là 2, hoặc chỉ số 5, tại đó phần tử đỉnh là 6.</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>nums[i] != nums[i + 1]</code> với mọi <code>i</code> hợp lệ.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Vì các phần tử lân cận khác nhau và các phần tử lính canh là $-\infty$, nên một đỉnh luôn tồn tại. Duyệt tuyến tính hoạt động với $n\le 1000$; bài toán yêu cầu $O(\log n)$. So sánh mid với phần tử bên phải: nếu bên phải nhỏ hơn thì có một đỉnh trong nửa trái (bao gồm mid), nếu không thì đỉnh nằm ở nửa phải. Thu hẹp khoảng tìm kiếm cho đến khi hai đầu mút gặp nhau.

<!-- thinking:end -->

Chúng ta xác định biên trái của phép tìm kiếm nhị phân là $left=0$ và biên phải là $right=n-1$, trong đó $n$ là độ dài của mảng. Ở mỗi bước tìm kiếm nhị phân, chúng ta tìm phần tử giữa $mid$ của khoảng hiện tại và so sánh giá trị của $mid$ với phần tử bên phải của nó là $mid+1$:

- Nếu giá trị của $mid$ lớn hơn giá trị của $mid+1$, tồn tại một phần tử đỉnh ở phía bên trái, và chúng ta cập nhật biên phải $right$ thành $mid$.
- Ngược lại, tồn tại một phần tử đỉnh ở phía bên phải, và chúng ta cập nhật biên trái $left$ thành $mid+1$.
- Cuối cùng, khi biên trái $left$ bằng biên phải $right$, chúng ta đã tìm thấy phần tử đỉnh của mảng.

Độ phức tạp thời gian là $O(\log n)$, trong đó $n$ là độ dài của mảng $nums$. Mỗi bước của tìm kiếm nhị phân có thể giảm khoảng tìm kiếm đi một nửa, vì vậy độ phức tạp thời gian là $O(\log n)$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            if nums[mid] > nums[mid + 1]:
                right = mid
            else:
                left = mid + 1
        return left
```

#### Java

```java
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findPeakElement(vector<int>& nums) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

#### Go

```go
func findPeakElement(nums []int) int {
	left, right := 0, len(nums)-1
	for left < right {
		mid := (left + right) >> 1
		if nums[mid] > nums[mid+1] {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

#### TypeScript

```ts
function findPeakElement(nums: number[]): number {
    let [left, right] = [0, nums.length - 1];
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] > nums[mid + 1]) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
