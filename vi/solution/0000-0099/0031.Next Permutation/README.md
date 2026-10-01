---
comments: true
difficulty: Medium
tags:
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [31. Next Permutation](https://leetcode.com/problems/next-permutation)

[中文文档](/solution/0000-0099/0031.Next%20Permutation/README.md)

## Mô tả

<!-- description:start -->

<p><strong>Hoán vị</strong> của một mảng số nguyên là cách sắp xếp các phần tử của mảng thành một dãy hoặc thứ tự tuyến tính.</p>

<ul>
	<li>Ví dụ, với <code>arr = [1,2,3]</code>, các hoán vị sau đây là toàn bộ các hoán vị của <code>arr</code>: <code>[1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]</code>.</li>
</ul>

<p><strong>Hoán vị kế tiếp</strong> của một mảng số nguyên là hoán vị lớn hơn tiếp theo theo thứ tự từ điển của mảng đó. Cụ thể hơn, nếu tất cả các hoán vị của mảng được sắp xếp trong một container theo thứ tự từ điển, thì <strong>hoán vị kế tiếp</strong> của mảng đó là hoán vị đứng ngay sau nó trong container đã sắp xếp. Nếu không thể có cách sắp xếp như vậy, mảng phải được sắp xếp lại theo thứ tự nhỏ nhất có thể (tức là sắp xếp theo thứ tự tăng dần).</p>

<ul>
	<li>Ví dụ, hoán vị kế tiếp của <code>arr = [1,2,3]</code> là <code>[1,3,2]</code>.</li>
	<li>Tương tự, hoán vị kế tiếp của <code>arr = [2,3,1]</code> là <code>[3,1,2]</code>.</li>
	<li>Trong khi đó, hoán vị kế tiếp của <code>arr = [3,2,1]</code> là <code>[1,2,3]</code> vì <code>[3,2,1]</code> không có cách sắp xếp nào lớn hơn theo thứ tự từ điển.</li>
</ul>

<p>Cho một mảng số nguyên <code>nums</code>, hãy <em>tìm hoán vị kế tiếp của</em> <code>nums</code>.</p>

<p>Phép thay thế phải được thực hiện <strong><a href="http://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">tại chỗ</a></strong> và chỉ sử dụng bộ nhớ bổ sung hằng số.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,3]
<strong>Đầu ra:</strong> [1,3,2]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [3,2,1]
<strong>Đầu ra:</strong> [1,2,3]
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,1,5]
<strong>Đầu ra:</strong> [1,5,1]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai lần duyệt

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là sinh mọi hoán vị rồi lấy hoán vị đứng sau dãy hiện tại. Cách này đúng, nhưng $n!$ là không thể thực hiện được khi $n \le 100$, và bài toán yêu cầu cập nhật tại chỗ với bộ nhớ bổ sung hằng số.
>
> Chúng ta không cần toàn bộ tập hợp, mà chỉ cần dãy kế tiếp theo thứ tự từ điển: lớn hơn nghiêm ngặt, thay đổi càng về bên phải càng tốt và thay đổi một lượng nhỏ nhất.
>
> Hậu tố dài nhất không tăng đã là cách sắp xếp lớn nhất của phần đuôi đó; phần tử ngay trước nó, $nums[i]$, là pivot mà chúng ta phải tăng lên. Phần tử thay thế lớn hơn nhỏ nhất nằm ở cuối hậu tố đó. Sau khi hoán đổi, đảo ngược hậu tố để nó trở thành dãy tăng. Nếu toàn bộ mảng không tăng, không tồn tại hoán vị lớn hơn và chúng ta đảo ngược toàn bộ mảng.

<!-- thinking:end -->

Trước tiên, chúng ta duyệt mảng từ cuối về đầu và tìm vị trí đầu tiên $i$ sao cho $nums[i] \lt nums[i + 1]$.

Sau đó, chúng ta lại duyệt mảng từ cuối về đầu và tìm vị trí đầu tiên $j$ sao cho $nums[j] \gt nums[i]$. Hoán đổi $nums[i]$ và $nums[j]$, rồi đảo ngược các phần tử từ $nums[i + 1]$ đến $nums[n - 1]$ để thu được hoán vị kế tiếp.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(1)$. Trong đó $n$ là độ dài của mảng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        n = len(nums)
        i = next((i for i in range(n - 2, -1, -1) if nums[i] < nums[i + 1]), -1)
        if ~i:
            j = next((j for j in range(n - 1, i, -1) if nums[j] > nums[i]))
            nums[i], nums[j] = nums[j], nums[i]
        nums[i + 1 :] = nums[i + 1 :][::-1]
```

#### Java

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        for (; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i >= 0) {
            for (int j = n - 1; j > i; --j) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }

        for (int j = i + 1, k = n - 1; j < k; ++j, --k) {
            swap(nums, j, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}
```

#### C++

```cpp
class Solution {
public:
    void nextPermutation(vector<int>& nums) {
        int n = nums.size();
        int i = n - 2;
        while (~i && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (~i) {
            for (int j = n - 1; j > i; --j) {
                if (nums[j] > nums[i]) {
                    swap(nums[i], nums[j]);
                    break;
                }
            }
        }
        reverse(nums.begin() + i + 1, nums.end());
    }
};
```

#### Go

```go
func nextPermutation(nums []int) {
	n := len(nums)
	i := n - 2
	for ; i >= 0 && nums[i] >= nums[i+1]; i-- {
	}
	if i >= 0 {
		for j := n - 1; j > i; j-- {
			if nums[j] > nums[i] {
				nums[i], nums[j] = nums[j], nums[i]
				break
			}
		}
	}
	for j, k := i+1, n-1; j < k; j, k = j+1, k-1 {
		nums[j], nums[k] = nums[k], nums[j]
	}
}
```

#### TypeScript

```ts
function nextPermutation(nums: number[]): void {
    const n = nums.length;
    let i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        --i;
    }
    if (i >= 0) {
        for (let j = n - 1; j > i; --j) {
            if (nums[j] > nums[i]) {
                [nums[i], nums[j]] = [nums[j], nums[i]];
                break;
            }
        }
    }
    for (let j = n - 1; j > i; --j, ++i) {
        [nums[i + 1], nums[j]] = [nums[j], nums[i + 1]];
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var nextPermutation = function (nums) {
    const n = nums.length;
    let i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        --i;
    }
    if (i >= 0) {
        let j = n - 1;
        while (j > i && nums[j] <= nums[i]) {
            --j;
        }
        [nums[i], nums[j]] = [nums[j], nums[i]];
    }
    for (i = i + 1, j = n - 1; i < j; ++i, --j) {
        [nums[i], nums[j]] = [nums[j], nums[i]];
    }
};
```

#### C#

```cs
public class Solution {
    public void NextPermutation(int[] nums) {
        int n = nums.Length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (i >= 0) {
            for (int j = n - 1; j > i; --j) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }
        for (int j = i + 1, k = n - 1; j < k; ++j, --k) {
            swap(nums, j, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param integer[] $nums
     * @return void
     */

    function nextPermutation(&$nums) {
        $n = count($nums);
        $i = $n - 2;
        while ($i >= 0 && $nums[$i] >= $nums[$i + 1]) {
            $i--;
        }
        if ($i >= 0) {
            $j = $n - 1;
            while ($j >= $i && $nums[$j] <= $nums[$i]) {
                $j--;
            }
            $temp = $nums[$i];
            $nums[$i] = $nums[$j];
            $nums[$j] = $temp;
        }
        $this->reverse($nums, $i + 1, $n - 1);
    }

    function reverse(&$nums, $start, $end) {
        while ($start < $end) {
            $temp = $nums[$start];
            $nums[$start] = $nums[$end];
            $nums[$end] = $temp;
            $start++;
            $end--;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
