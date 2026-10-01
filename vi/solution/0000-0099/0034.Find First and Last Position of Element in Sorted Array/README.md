---
comments: true
difficulty: Medium
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array)

[中文文档](/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code> được sắp xếp theo thứ tự không giảm, hãy tìm vị trí bắt đầu và kết thúc của giá trị <code>target</code> đã cho.</p>

<p>Nếu không tìm thấy <code>target</code> trong mảng, hãy trả về <code>[-1, -1]</code>.</p>

<p>Bạn phải viết một thuật toán có độ phức tạp thời gian là <code>O(log n)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [5,7,7,8,8,10], target = 8
<strong>Đầu ra:</strong> [3,4]
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [5,7,7,8,8,10], target = 6
<strong>Đầu ra:</strong> [-1,-1]
</pre><p><strong class="example">Ví dụ 3:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [], target = 0
<strong>Đầu ra:</strong> [-1,-1]
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> là một mảng không giảm.</li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= target&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là quét tuyến tính và ghi lại lần xuất hiện đầu tiên và cuối cùng. Cách này đúng, nhưng $n \le 10^5$ và bài toán yêu cầu $O(\log n)$. Mở rộng ra hai phía từ một vị trí khớp vẫn có độ phức tạp $O(n)$ trong trường hợp xấu nhất.
>
> Mảng được sắp xếp theo thứ tự không giảm, nên hai biên là hai bài toán tìm kiếm nhị phân riêng biệt; không cần tìm cả hai trong cùng một lượt.
>
> Biên trái là chỉ số đầu tiên $\ge target$; biên phải là chỉ số đầu tiên $\ge target+1$, trừ đi một. Chỉ cần hai lần truy vấn lower bound; nếu chúng cho cùng một chỉ số, $target$ không tồn tại.

<!-- thinking:end -->

Chúng ta có thể thực hiện hai lần tìm kiếm nhị phân để tìm biên trái và biên phải.

Độ phức tạp thời gian là $O(\log n)$, trong đó $n$ là độ dài của mảng $\textit{nums}$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        l = bisect_left(nums, target)
        r = bisect_left(nums, target + 1)
        return [-1, -1] if l == r else [l, r - 1]
```

#### Java

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = search(nums, target);
        int r = search(nums, target + 1);
        return l == r ? new int[] {-1, -1} : new int[] {l, r - 1};
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= x) {
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
    vector<int> searchRange(vector<int>& nums, int target) {
        int l = lower_bound(nums.begin(), nums.end(), target) - nums.begin();
        int r = lower_bound(nums.begin(), nums.end(), target + 1) - nums.begin();
        if (l == r) {
            return {-1, -1};
        }
        return {l, r - 1};
    }
};
```

#### Go

```go
func searchRange(nums []int, target int) []int {
	l := sort.SearchInts(nums, target)
	r := sort.SearchInts(nums, target+1)
	if l == r {
		return []int{-1, -1}
	}
	return []int{l, r - 1}
}
```

#### TypeScript

```ts
function searchRange(nums: number[], target: number): number[] {
    const search = (x: number): number => {
        let [left, right] = [0, nums.length];
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const l = search(target);
    const r = search(target + 1);
    return l === r ? [-1, -1] : [l, r - 1];
}
```

#### Rust

```rust
impl Solution {
    pub fn search_range(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let n = nums.len();
        let search = |x| {
            let mut left = 0;
            let mut right = n;
            while left < right {
                let mid = left + (right - left) / 2;
                if nums[mid] < x {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            left
        };
        let l = search(target);
        let r = search(target + 1);
        if l == r {
            return vec![-1, -1];
        }
        vec![l as i32, (r - 1) as i32]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var searchRange = function (nums, target) {
    function search(x) {
        let left = 0,
            right = nums.length;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    const l = search(target);
    const r = search(target + 1);
    return l == r ? [-1, -1] : [l, r - 1];
};
```

#### C#

```cs
public class Solution {
    public int[] SearchRange(int[] nums, int target) {
        int l = Search(nums, target);
        int r = Search(nums, target + 1);
        return l == r ? new int[] {-1, -1} : new int[] {l, r - 1};
    }

    private int Search(int[] nums, int x) {
        int left = 0, right = nums.Length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function searchRange($nums, $target) {
        $search = function ($x) use ($nums) {
            $left = 0;
            $right = count($nums);
            while ($left < $right) {
                $mid = intdiv($left + $right, 2);
                if ($nums[$mid] >= $x) {
                    $right = $mid;
                } else {
                    $left = $mid + 1;
                }
            }
            return $left;
        };

        $l = $search($target);
        $r = $search($target + 1);
        return $l === $r ? [-1, -1] : [$l, $r - 1];
    }
}
```

#### Kotlin

```kotlin
class Solution {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        val left = this.search(nums, target)
        val right = this.search(nums, target + 1)
        return if (left == right) intArrayOf(-1, -1) else intArrayOf(left, right - 1)
    }

    private fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size
        while (left < right) {
            val middle = (left + right) / 2
            if (nums[middle] < target) {
                left = middle + 1
            } else {
                right = middle
            }
        }
        return left
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
