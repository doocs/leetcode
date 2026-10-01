---
comments: true
difficulty: Medium
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [81. Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii)

[中文文档](/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Có một mảng số nguyên <code>nums</code> được sắp xếp theo thứ tự không giảm (không nhất thiết có các giá trị <strong>phân biệt</strong>).</p>

<p>Trước khi được truyền vào hàm của bạn, <code>nums</code> được <strong>xoay</strong> tại một chỉ số pivot chưa biết <code>k</code> (<code>0 &lt;= k &lt; nums.length</code>) sao cho mảng kết quả là <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code> (<strong>được đánh chỉ số từ 0</strong>). Ví dụ, <code>[0,1,2,4,4,4,5,6,6,7]</code> có thể được xoay tại chỉ số pivot <code>5</code> và trở thành <code>[4,5,6,6,7,0,1,2,4,4]</code>.</p>

<p>Cho mảng <code>nums</code> <strong>sau</strong> phép xoay và một số nguyên <code>target</code>, hãy trả về <code>true</code><em> nếu </em><code>target</code><em> nằm trong </em><code>nums</code><em>, hoặc </em><code>false</code><em> nếu nó không nằm trong </em><code>nums</code><em>.</em></p>

<p>Bạn phải giảm số bước thực hiện tổng thể nhiều nhất có thể.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [2,5,6,0,0,1,2], target = 0
<strong>Đầu ra:</strong> true
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [2,5,6,0,0,1,2], target = 3
<strong>Đầu ra:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> được đảm bảo đã được xoay tại một pivot nào đó.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bài toán này tương tự như <a href="/problems/search-in-rotated-sorted-array/description/" target="_blank">Tìm kiếm trong mảng đã xoay</a>, nhưng <code>nums</code> có thể chứa các giá trị <strong>trùng lặp</strong>. Điều này có ảnh hưởng đến độ phức tạp thời gian không? Như thế nào và tại sao?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là quét tuyến tính: so sánh từng phần tử với $\textit{target}$. Cách này đúng, nhưng đề bài yêu cầu giảm số thao tác, và mảng gồm hai nửa đã được sắp xếp sau phép xoay. $n \le 5000$ khiến việc quét vẫn có thể vượt qua, nhưng cách đó bỏ qua thứ tự của mảng.
>
> Nút thắt là chưa tận dụng việc “ít nhất một nửa được sắp xếp”. Tìm kiếm nhị phân thông thường cần một khoảng được sắp xếp hoàn toàn; so sánh $\textit{nums}[\textit{mid}]$ với $\textit{nums}[r]$ cho biết phía nào có thứ tự, sau đó cho biết liệu $\textit{target}$ có nằm trong nửa đó hay không.
>
> Không giống như Tìm kiếm trong mảng đã xoay, các giá trị trùng lặp được cho phép. Khi $\textit{nums}[\textit{mid}] = \textit{nums}[r]$, chúng ta không thể biết phía nào được sắp xếp, nên chỉ giảm $r$. Nếu toàn bộ khoảng đều bằng nhau, cách này suy biến thành $O(n)$ — chi phí không thể tránh khỏi của các giá trị trùng lặp.

<!-- thinking:end -->

Chúng ta xác định biên trái của tìm kiếm nhị phân là $l = 0$ và biên phải là $r = n - 1$, trong đó $n$ là độ dài của mảng.

Trong mỗi lần tìm kiếm nhị phân, chúng ta lấy điểm giữa hiện tại là $\textit{mid} = (l + r) / 2$.

- Nếu $\textit{nums}[\textit{mid}] > \textit{nums}[r]$, điều đó có nghĩa là $[l, \textit{mid}]$ được sắp xếp. Nếu $\textit{nums}[l] \le \textit{target} \le \textit{nums}[\textit{mid}]$, điều đó có nghĩa là $\textit{target}$ nằm trong $[l, \textit{mid}]$. Nếu không, $\textit{target}$ nằm trong $[\textit{mid} + 1, r]$.
- Nếu $\textit{nums}[\textit{mid}] < \textit{nums}[r]$, điều đó có nghĩa là $[\textit{mid} + 1, r]$ được sắp xếp. Nếu $\textit{nums}[\textit{mid}] < \textit{target} \le \textit{nums}[r]$, điều đó có nghĩa là $\textit{target}$ nằm trong $[\textit{mid} + 1, r]$. Nếu không, $\textit{target}$ nằm trong $[l, \textit{mid}]$.
- Nếu $\textit{nums}[\textit{mid}] = \textit{nums}[r]$, điều đó có nghĩa là các phần tử $\textit{nums}[\textit{mid}]$ và $\textit{nums}[r]$ bằng nhau. Trong trường hợp này, chúng ta không thể xác định $\textit{target}$ nằm trong khoảng nào, vì vậy chỉ có thể giảm $r$ đi $1$.

Sau khi tìm kiếm nhị phân kết thúc, nếu $\textit{nums}[l] = \textit{target}$, điều đó có nghĩa là giá trị đích $\textit{target}$ tồn tại trong mảng. Nếu không, nó không tồn tại.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def search(self, nums: List[int], target: int) -> bool:
        n = len(nums)
        l, r = 0, n - 1
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] > nums[r]:
                if nums[l] <= target <= nums[mid]:
                    r = mid
                else:
                    l = mid + 1
            elif nums[mid] < nums[r]:
                if nums[mid] < target <= nums[r]:
                    l = mid + 1
                else:
                    r = mid
            else:
                r -= 1
        return nums[l] == target
```

#### Java

```java
class Solution {
    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[r]) {
                if (nums[l] <= target && target <= nums[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if (nums[mid] < nums[r]) {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                --r;
            }
        }
        return nums[l] == target;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool search(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[r]) {
                if (nums[l] <= target && target <= nums[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if (nums[mid] < nums[r]) {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                --r;
            }
        }
        return nums[l] == target;
    }
};
```

#### Go

```go
func search(nums []int, target int) bool {
	l, r := 0, len(nums)-1
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] > nums[r] {
			if nums[l] <= target && target <= nums[mid] {
				r = mid
			} else {
				l = mid + 1
			}
		} else if nums[mid] < nums[r] {
			if nums[mid] < target && target <= nums[r] {
				l = mid + 1
			} else {
				r = mid
			}
		} else {
			r--
		}
	}
	return nums[l] == target
}
```

#### TypeScript

```ts
function search(nums: number[], target: number): boolean {
    let [l, r] = [0, nums.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] > nums[r]) {
            if (nums[l] <= target && target <= nums[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        } else if (nums[mid] < nums[r]) {
            if (nums[mid] < target && target <= nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        } else {
            --r;
        }
    }
    return nums[l] === target;
}
```

#### Rust

```rust
impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> bool {
        let (mut l, mut r) = (0, nums.len() - 1);
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] > nums[r] {
                if nums[l] <= target && target <= nums[mid] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if nums[mid] < nums[r] {
                if nums[mid] < target && target <= nums[r] {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                r -= 1;
            }
        }
        nums[l] == target
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {boolean}
 */
var search = function (nums, target) {
    let [l, r] = [0, nums.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] > nums[r]) {
            if (nums[l] <= target && target <= nums[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        } else if (nums[mid] < nums[r]) {
            if (nums[mid] < target && target <= nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        } else {
            --r;
        }
    }
    return nums[l] === target;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
