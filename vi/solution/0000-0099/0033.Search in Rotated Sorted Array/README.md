---
comments: true
difficulty: Medium
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array)

[中文文档](/solution/0000-0099/0033.Search%20in%20Rotated%20Sorted%20Array/README.md)

## Mô tả

<!-- description:start -->

<p>Có một mảng số nguyên <code>nums</code> được sắp xếp theo thứ tự tăng dần (với các giá trị <strong>phân biệt</strong>).</p>

<p>Trước khi được truyền vào hàm của bạn, <code>nums</code> có thể được <strong>xoay trái</strong> tại một chỉ số <code>k</code> chưa biết (<code>1 &lt;= k &lt; nums.length</code>) sao cho mảng kết quả là <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code> (<strong>được đánh chỉ số từ 0</strong>). Ví dụ, <code>[0,1,2,4,5,6,7]</code> có thể được xoay trái <code>3</code> chỉ số và trở thành <code>[4,5,6,7,0,1,2]</code>.</p>

<p>Cho mảng <code>nums</code> <strong>sau</strong> phép xoay (nếu có) và một số nguyên <code>target</code>, hãy trả về <em>chỉ số của </em><code>target</code><em> nếu nó có trong </em><code>nums</code><em>, hoặc </em><code>-1</code><em> nếu nó không có trong </em><code>nums</code>.</p>

<p>Bạn phải viết một thuật toán có độ phức tạp thời gian <code>O(log n)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [4,5,6,7,0,1,2], target = 0
<strong>Đầu ra:</strong> 4
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [4,5,6,7,0,1,2], target = 3
<strong>Đầu ra:</strong> -1
</pre><p><strong class="example">Ví dụ 3:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [1], target = 0
<strong>Đầu ra:</strong> -1
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>Tất cả các giá trị của <code>nums</code> đều <strong>duy nhất</strong>.</li>
	<li><code>nums</code> là một mảng tăng dần có thể đã được xoay.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là quét từ trái sang phải. Cách này đúng, nhưng $n \le 5000$ và đề bài yêu cầu $O(\log n)$.
>
> Sau phép xoay, mảng không còn được sắp xếp hoàn toàn, vì vậy tìm kiếm nhị phân thông thường không thể cho biết nửa nào chứa $target$.
>
> Điểm mấu chốt là mỗi lần chia đều để lại ít nhất một nửa đơn điệu. So sánh $nums[0]$ với $nums[mid]$ cho biết nửa trái có được sắp xếp hay không; sau đó chúng ta kiểm tra xem $target$ có nằm trong khoảng đã sắp xếp đó không và loại bỏ nửa còn lại.
>
> Vì vậy chúng ta vẫn có thể tìm kiếm nhị phân: nửa đã sắp xếp quyết định giữ hay loại bỏ, còn nửa chưa sắp xếp sẽ được chia tiếp ở vòng sau.

<!-- thinking:end -->

Chúng ta sử dụng tìm kiếm nhị phân để chia mảng thành hai phần, $[left,.. mid]$ và $[mid + 1,.. right]$. Tại thời điểm này, chúng ta có thể thấy rằng một trong hai phần chắc chắn được sắp xếp.

Do đó, dựa trên phần đã sắp xếp, chúng ta có thể xác định liệu $target$ có nằm trong phần này hay không:

- Nếu các phần tử trong khoảng $[0,.. mid]$ tạo thành một mảng đã sắp xếp:
    - Nếu $nums[0] \leq target \leq nums[mid]$, thì khoảng tìm kiếm của chúng ta có thể được thu hẹp thành $[left,.. mid]$;
    - Nếu không, tìm kiếm trong $[mid + 1,.. right]$;
- Nếu các phần tử trong khoảng $[mid + 1, n - 1]$ tạo thành một mảng đã sắp xếp:
    - Nếu $nums[mid] \lt target \leq nums[n - 1]$, thì khoảng tìm kiếm của chúng ta có thể được thu hẹp thành $[mid + 1,.. right]$;
    - Nếu không, tìm kiếm trong $[left,.. mid]$.

Điều kiện kết thúc của tìm kiếm nhị phân là $left \geq right$. Nếu cuối cùng chúng ta thấy rằng $nums[left]$ không bằng $target$, điều đó có nghĩa là không có phần tử nào có giá trị $target$ trong mảng, và chúng ta trả về $-1$. Ngược lại, chúng ta trả về chỉ số $left$.

Độ phức tạp thời gian là $O(\log n)$, trong đó $n$ là độ dài của mảng $nums$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        n = len(nums)
        left, right = 0, n - 1
        while left < right:
            mid = (left + right) >> 1
            if nums[0] <= nums[mid]:
                if nums[0] <= target <= nums[mid]:
                    right = mid
                else:
                    left = mid + 1
            else:
                if nums[mid] < target <= nums[n - 1]:
                    left = mid + 1
                else:
                    right = mid
        return left if nums[left] == target else -1
```

#### Java

```java
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int n = nums.size();
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target <= nums[mid])
                    right = mid;
                else
                    left = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[n - 1])
                    left = mid + 1;
                else
                    right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }
};
```

#### Go

```go
func search(nums []int, target int) int {
	n := len(nums)
	left, right := 0, n-1
	for left < right {
		mid := (left + right) >> 1
		if nums[0] <= nums[mid] {
			if nums[0] <= target && target <= nums[mid] {
				right = mid
			} else {
				left = mid + 1
			}
		} else {
			if nums[mid] < target && target <= nums[n-1] {
				left = mid + 1
			} else {
				right = mid
			}
		}
	}
	if nums[left] == target {
		return left
	}
	return -1
}
```

#### TypeScript

```ts
function search(nums: number[], target: number): number {
    const n = nums.length;
    let left = 0,
        right = n - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[0] <= nums[mid]) {
            if (nums[0] <= target && target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        } else {
            if (nums[mid] < target && target <= nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
    }
    return nums[left] == target ? left : -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> i32 {
        let mut l = 0;
        let mut r = nums.len() - 1;
        while l <= r {
            let mid = (l + r) >> 1;
            if nums[mid] == target {
                return mid as i32;
            }

            if nums[l] <= nums[mid] {
                if target < nums[mid] && target >= nums[l] {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if target > nums[mid] && target <= nums[r] {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        -1
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
    const n = nums.length;
    let left = 0,
        right = n - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[0] <= nums[mid]) {
            if (nums[0] <= target && target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        } else {
            if (nums[mid] < target && target <= nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
    }
    return nums[left] == target ? left : -1;
};
```

#### C#

```cs
public class Solution {
    public int Search(int[] nums, int target) {
        int n = nums.Length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param integer[] $nums
     * @param integer $target
     * @return integer
     */

    function search($nums, $target) {
        $foundKey = -1;
        foreach ($nums as $key => $value) {
            if ($value === $target) {
                $foundKey = $key;
            }
        }
        return $foundKey;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
