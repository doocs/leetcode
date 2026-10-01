---
comments: true
difficulty: Easy
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [35. Search Insert Position](https://leetcode.com/problems/search-insert-position)

[中文文档](/solution/0000-0099/0035.Search%20Insert%20Position/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên phân biệt đã được sắp xếp và một giá trị đích, hãy trả về chỉ số nếu tìm thấy giá trị đích. Nếu không, hãy trả về chỉ số mà nó sẽ nằm tại đó nếu được chèn theo đúng thứ tự.</p>

<p>Bạn phải viết một thuật toán có độ phức tạp thời gian <code>O(log n)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,3,5,6], target = 5
<strong>Đầu ra:</strong> 2
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,3,5,6], target = 2
<strong>Đầu ra:</strong> 1
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,3,5,6], target = 7
<strong>Đầu ra:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> chứa các giá trị <strong>phân biệt</strong> được sắp xếp theo thứ tự <strong>tăng dần</strong>.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là quét từ trái sang phải cho đến chỉ số đầu tiên $\ge target$. Cách này đúng, và $n \le 10^4$ nên sẽ vượt qua, nhưng mảng tăng nghiêm ngặt, vì vậy việc quét tuyến tính làm lãng phí tính có thứ tự.
>
> Vị trí chèn chính xác là chỉ số đầu tiên không nhỏ hơn $target$ — một lower bound tiêu chuẩn.
>
> Duy trì một khoảng nửa kín $[l,r)$: nếu $nums[mid] \ge target$, đáp án nằm ở nửa bên trái (bao gồm $mid$), nếu không thì nó nằm bên phải $mid$. Khi $l=r$, $l$ là chỉ số chèn.

<!-- thinking:end -->

Vì mảng $nums$ đã được sắp xếp, chúng ta có thể sử dụng phương pháp tìm kiếm nhị phân để tìm vị trí chèn của giá trị $target$.

Độ phức tạp thời gian là $O(\log n)$, còn độ phức tạp không gian là $O(1)$. Trong đó, $n$ là độ dài của mảng $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        l, r = 0, len(nums)
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] >= target:
                r = mid
            else:
                l = mid + 1
        return l
```

#### Java

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func searchInsert(nums []int, target int) int {
	l, r := 0, len(nums)
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] >= target {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
```

#### TypeScript

```ts
function searchInsert(nums: number[], target: number): number {
    let [l, r] = [0, nums.length];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= target) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

#### Rust

```rust
impl Solution {
    pub fn search_insert(nums: Vec<i32>, target: i32) -> i32 {
        let mut l: usize = 0;
        let mut r: usize = nums.len();
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] >= target {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l as i32
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
var searchInsert = function (nums, target) {
    let [l, r] = [0, nums.length];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= target) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer
     */
    function searchInsert($nums, $target) {
        $l = 0;
        $r = count($nums);
        while ($l < $r) {
            $mid = ($l + $r) >> 1;
            if ($nums[$mid] >= $target) {
                $r = $mid;
            } else {
                $l = $mid + 1;
            }
        }
        return $l;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Tìm kiếm nhị phân (Hàm dựng sẵn)

<!-- thinking:start -->

> **Tư duy**
>
> Phương pháp 1 đã là $O(\log n)$; điều duy nhất còn thiếu là chúng ta không cần tự viết tìm kiếm nhị phân. Các ngôn ngữ đã cung cấp một lower bound: Python's `bisect_left`, C++'s `lower_bound`, Java's `Arrays.binarySearch` (trả về $-i-1$ khi không tìm thấy). Ý nghĩa khớp với Phương pháp 1; chúng ta chỉ cần gọi hàm dựng sẵn.

<!-- thinking:end -->

Chúng ta cũng có thể trực tiếp sử dụng hàm dựng sẵn để tìm kiếm nhị phân.

Độ phức tạp thời gian là $O(\log n)$, trong đó $n$ là độ dài của mảng $nums$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        return bisect_left(nums, target)
```

#### Java

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int i = Arrays.binarySearch(nums, target);
        return i < 0 ? -i - 1 : i;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        return lower_bound(nums.begin(), nums.end(), target) - nums.begin();
    }
};
```

#### Go

```go
func searchInsert(nums []int, target int) int {
	return sort.SearchInts(nums, target)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
